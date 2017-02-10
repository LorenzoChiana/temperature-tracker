#ifndef __CONTROLLERTASK__
#define __CONTROLLERTASK__

#include "Task.h"
#include "config.h"

class ControllerTask: public Task {

public:

	ControllerTask(Environment* env);
	void init(int period);  
	void tick();

private:

	Environment* env;
	long currentTime, initialTime;
	enum { IDLE, SENDING } state;

};

#endif

