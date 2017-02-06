#include "Arduino.h"
#include "Environment.h"

void Environment::init(int RXpin, int TXpin){
	this->setDistance(0.0);
	this->setTouched(false);
	this->initChannel(RXpin, TXpin);
	this->lastMsg = "";
	this->avalible = false;
}

void Environment::initChannel(int RXpin, int TXpin){
	this->channel = new MsgServiceBluetooth(RXpin, TXpin);
	this->channel->init();
}

void Environment::setDistance(float value){
	this->distance = value;
}
float Environment::getDistance(){
	return this->distance;
}

void Environment::setTouched(bool value){
	this->touch = value;
}
bool Environment::getTouched(){
	return this->touch;
}

MsgServiceBluetooth* Environment::getChannel(){
	return this->channel;
}

void Environment::updateLastMsg(String msg){
	this->lastMsg = msg;
}

String Environment::getLastMsg(){
	return this->lastMsg;
}

bool Environment::isMsgAvalible(){
	return this->avalible;
}

void Environment::setMsgAvalible(bool val){
	this->avalible = val;
}


