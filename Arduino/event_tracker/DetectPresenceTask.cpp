#include "Arduino.h"
#include "DetectPresenceTask.h"

DetectPresenceTask::DetectPresenceTask(Environment* env, PIRSensor* pir, ServoTimer2* servo){
  this->pir = pir;
  this->env = env;
  this->servo = servo;
}

void DetectPresenceTask::init(int period){
  Task::init(period);
  this->toggle = true;
  prevState = currState = false;
  state = NO_ONE;
}

void DetectPresenceTask::tick(){
  switch (state){
    case NO_ONE:
    prevState = currState;
    currState = pir->detected();
    if (currState && prevState == false){
        //Manda un messaggio nel canale Bluetooth al telefono
      MsgBluetooth presenceMsg = MsgBluetooth(String(BLUETOOTH_PRESENCE_MSG));
      env->getBluetooth()->sendMsg(presenceMsg);
        //Avvia un timer e va in attesa della risposta
      currentTime = initialTime = millis(); 
      state = WAITING;  
    }
    break;
    case WAITING:
      //Contorlla che ci sia un messaggio di risposta
    bool response = env->getBluetooth()->isMsgAvailable();
    if (response){
      String responseMsg = env->getBluetooth()->receiveMsg()->getContent();
      if (responseMsg == BLUETOOTH_ALARM_RESPONSE){
        sendAlarm();
        actuateServo(&toggle);
        state = NO_ONE;
      } else {
        sendPresence();
        state = NO_ONE;
      }
      //Se non vi è risposta entro 10 secondi manda l'allarme comunque
    } else if (currentTime - initialTime > 10000){
      sendAlarm();
      actuateServo(&toggle);
      state = NO_ONE;
    } else {
      currentTime = millis();
    }
    break;
  }
}

void DetectPresenceTask::sendAlarm(){
  env->getSerial()->sendMsg(MsgSerial(SERIAL_ALARM_MSG));
}

void DetectPresenceTask::sendPresence(){
  env->getSerial()->sendMsg(MsgSerial(SERIAL_PRESENCE_MSG));
}

void DetectPresenceTask::actuateServo(bool* toggle){
  servo->write(*toggle ? 2200 : 500);
  *toggle = !(*toggle);
}
