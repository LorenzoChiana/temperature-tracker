#ifndef __ENVIRONMENT__
#define __ENVIRONMENT__

#include "config.h"
#include "MsgServiceBluetooth.h"

class Environment {

	float distance;
	bool touch;

	MsgServiceBluetooth* channel;
	void initChannel(int RXpin, int TXpin);	

	String lastMsg;
	bool avalible;
public:
	void init(int RXpin, int TXpin);

	void setDistance(float);
	float getDistance();

	void setTouched(bool);
	bool getTouched();

	
	MsgServiceBluetooth* getChannel();

	void updateLastMsg(String msg);
	String getLastMsg();
	bool isMsgAvalible();
	void setMsgAvalible(bool val);
};

#endif
