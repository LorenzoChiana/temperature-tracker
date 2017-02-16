#include "Arduino.h"
#include "TemperatureTask.h"


TemperatureTask::TemperatureTask(Environment* env, TemperatureSensor* thermostat){
  this->thermostat = thermostat;
  this->env = env;
}

void TemperatureTask::init(int period){
  Task::init(period);
  temperatureValue = 0;
  state = SETUP;    
}

void TemperatureTask::tick(){
  //Ogni P secondi prende la temperatura e la manda sul canale Seriale
  switch (state){
    case SETUP:
      currentTime = initialTime = millis();
      state = WAITING;
    break;    
    case WAITING:
      currentTime = millis();
      if (currentTime - initialTime > P) {
        temperatureValue = thermostat->getTemperature();
        state = ACTION;
      }
    break;
    case ACTION:
      MsgSerial tempMsg = MsgSerial(String(temperatureValue));
      env->getSerial()->sendMsg(tempMsg);
      state = SETUP;
    break;
  }
}
