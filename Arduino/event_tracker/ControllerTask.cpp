#include "Arduino.h"
#include "ControllerTask.h"

ControllerTask::ControllerTask(Environment* env){
  this->env = env;
}

void ControllerTask::init(int period){
  Task::init(period);
  state = SENDING;
}

void ControllerTask::tick(){
  switch (state){
    case IDLE:
      currentTime = millis();
      if (currentTime - initialTime > 1000){
        state = SENDING;
      }
    break;
    case SENDING:
      MsgSerial connectionMsg = MsgSerial(SERIAL_CONNECTION_MSG);
      env->getSerial()->sendMsg(connectionMsg);
      currentTime = initialTime = millis();
      state = IDLE;
    break;
  }
}
