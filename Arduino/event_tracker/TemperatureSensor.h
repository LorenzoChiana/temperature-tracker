#ifndef __TEMPERATURESENSOR__
#define __TEMPERATURESENSOR__

class TemperatureSensor {
	
public: 
	TemperatureSensor(int pin);
	float getTemperature();

private:
	int pin;
	int getValue();

};

#endif
