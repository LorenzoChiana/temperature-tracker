#ifndef __ENVIRONMENT__
#define __ENVIRONMENT__

#include "config.h"
#include "MsgServiceBluetooth.h"
#include "MsgServiceSerial.h"

//Classe che controlla semplicemente i canali comuni
class Environment {

	MsgServiceSerial* serialChannel;
	MsgServiceBluetooth* bluetoothChannel;

public:
	
	void init(MsgServiceSerial* serialChannel, MsgServiceBluetooth* bluetoothChannel);
	
	MsgServiceBluetooth* getBluetooth();
	MsgServiceSerial* getSerial();

};

#endif
