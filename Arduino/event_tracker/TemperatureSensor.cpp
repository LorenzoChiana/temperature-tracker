#include "TemperatureSensor.h"
#include "Arduino.h"

TemperatureSensor::TemperatureSensor(int pin){
	this->pin = pin;
} 

int TemperatureSensor::getValue(){
	return analogRead(pin);
}

float TemperatureSensor::getTemperature(){
	int value = this->getValue();
	//Ottengo il voltaggio in Volt sul pin
	float voltage = value * 5.0 / 1024;
	//Calcolo la temperatura sapendo l'offset
	float temperatureC = (voltage - 0.5) * 100 ;

	return temperatureC;
}


