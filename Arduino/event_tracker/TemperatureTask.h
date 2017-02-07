#ifndef __TEMPERATURETASK__
#define __TEMPERATURETASK__

#include "Task.h"
#include "TemperatureSensor.h"
#include "config.h"

class TemperatureTask: public Task {

public:

  TemperatureTask(Environment* env, TemperatureSensor* thermostat);
  void init(int period);  
  void tick();

private:
  TemperatureSensor* thermostat;
  Environment* env;
  float temperatureValue;
  long currentTime, initialTime;
  enum { SETUP, ACTION, WAITING } state;

};

#endif

