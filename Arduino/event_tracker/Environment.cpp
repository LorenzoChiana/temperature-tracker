#include "Arduino.h"
#include "Environment.h"

void Environment::init(MsgServiceSerial* serialChannel, MsgServiceBluetooth* bluetoothChannel){
	this->bluetoothChannel = bluetoothChannel;
	this->serialChannel = serialChannel;
}

MsgServiceSerial* Environment::getSerial(){
	return this->serialChannel;
}

MsgServiceBluetooth* Environment::getBluetooth(){
	return this->bluetoothChannel;
}


