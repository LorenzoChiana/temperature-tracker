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



/*
 * Matteo Minardi
 * Lorenzo Chiana
 * Consegna quattro
 */

#define debug

Scheduler sched(5);
Environment* env;

void setup() {
  
  Serial.begin(9600);
  /* Inizializzazione componenti dei task */

  //PIR
  PIRSensor* pir = new PIRSensor(PIR_PIN);
  //SERVO
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

  Task* detectPresenceTask = new DetectPresenceTask(env, pir, servo);
  detectPresenceTask->init(3*CLOCK);
  sched.addTask(detectPresenceTask);
  
  Task* temperatureTask = new TemperatureTask(env, thermostat);
  temperatureTask->init(3*CLOCK);
  sched.addTask(temperatureTask);

}

void loop() {
  sched.schedule();
}
