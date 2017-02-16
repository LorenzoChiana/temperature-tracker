#include <Arduino.h>
#include "config.h"

#include "Task.h"
#include "Scheduler.h"

#include "PIRSensor.h"
#include "ServoTimer2.h"
#include "MsgServiceBluetooth.h"
#include "MsgServiceSerial.h"
#include "TemperatureSensor.h"
#include "DetectPresenceTask.h"
#include "TemperatureTask.h"
#include "ControllerTask.h"

/*
 * Matteo Minardi
 * Lorenzo Chiana
 * Consegna quattro
 */

Scheduler sched(5);
Environment* env;

void setup() {
  
  Serial.begin(9600);
  /* Inizializzazione componenti dei task */

  //Pir
  PIRSensor* pir = new PIRSensor(PIR_PIN);
  //Servo
  ServoTimer2* servo = new ServoTimer2();
  servo->attach(SERVO_PIN);
  //Sensore di temperatura
  TemperatureSensor* thermostat = new TemperatureSensor(THERMOSTAT_PIN);
  //Bluetooth Channel
  MsgServiceBluetooth* bluetoothChannel = new MsgServiceBluetooth(RX_PIN_B, TX_PIN_B);
  bluetoothChannel->init();
  //Serial Channel automaticamente nei pin seriali
  MsgServiceSerial* serialChannel = new MsgServiceSerial();
  serialChannel->init();


  //Oggetto della classe che contiente il canale di comunicazioni e le variabili globali
  Environment* env = new Environment();
  env->init(serialChannel, bluetoothChannel);

  sched.init(CLOCK);
  //Task che rileva la presenza di un allarme e lo segnala al servo e al dispositivo Bluetooth
  Task* detectPresenceTask = new DetectPresenceTask(env, pir, servo);
  detectPresenceTask->init(CLOCK);
  sched.addTask(detectPresenceTask);
  //Task che rileva la temperatura e ogni periodo P la manda al dispositivo Seriale
  Task* temperatureTask = new TemperatureTask(env, thermostat);
  temperatureTask->init(5*CLOCK);
  sched.addTask(temperatureTask);
  //Task che svolge la funzione di watchdog task e mandi segnali di controllo al dispositivo Seriale
  Task* controllerTask = new ControllerTask(env);
  controllerTask->init(3*CLOCK);
  sched.addTask(controllerTask);

}

void loop() {
  sched.schedule();
}
