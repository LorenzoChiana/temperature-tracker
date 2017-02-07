#ifndef __DETECTPRESENCETASK__
#define __DETECTPRESENCETASK__

#include "Task.h"
#include "PIRSensor.h"
#include "config.h"

class DetectPresenceTask: public Task {

public:

	DetectPresenceTask(Environment* env, PIRSensor* pir);
	void init(int period);  
	void tick();

private:
	void sendAlarm();
	void sendPresence();
	PIRSensor* pir;
	Environment* env;
	long currentTime, initialTime;
	enum { NO_ONE, WAITING } state;

};

#endif

