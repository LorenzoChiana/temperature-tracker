#ifndef __DETECTPRESENCETASK__
#define __DETECTPRESENCETASK__

#include "Task.h"
#include "PIRSensor.h"
#include "ServoTimer2.h"
#include "config.h"

class DetectPresenceTask: public Task {

public:

	DetectPresenceTask(Environment* env, PIRSensor* pir, ServoTimer2* servo);
	void init(int period);  
	void tick();

private:
	void sendAlarm();
	void sendPresence();
	void actuateServo(bool* toggle);
	bool toggle;
	PIRSensor* pir;
	Environment* env;
	ServoTimer2* servo;
	long currentTime, initialTime;
	bool prevState, currState;
	enum { NO_ONE, WAITING } state;

};

#endif

