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
  state = NO_ONE;


}

void DetectPresenceTask::tick(){
  switch (state){
    case NO_ONE:
    if (pir->detected()){
        //Manda un messaggio nel canale Bluetooth al telefono
      MsgBluetooth presenceMsg = MsgBluetooth(String("Ei, ciè qulcn"));
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
      if (responseMsg == "Allarme"){
        sendAlarm();
        actuateServo(toggle);
      } else {
        sendPresence();
      }
    } else if (currentTime - initialTime > 10000){
      sendAlarm();
      actuateServo(toggle);
    } else {
      currentTime = millis();
    }
    break;
  }
}

void DetectPresenceTask::sendAlarm(){
  env->getBluetooth()->sendMsg(MsgBluetooth(String("Allarme")));
}

void DetectPresenceTask::sendPresence(){
  env->getBluetooth()->sendMsg(MsgBluetooth(String("Presenza")));
}

void DetectPresenceTask::actuateServo(bool toggle){
  servo->write(toggle ? 2200 : 500);
  toggle = !toggle;
}
