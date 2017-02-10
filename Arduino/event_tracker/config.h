/*
 *  This header file stores symbols that concerns 
 *  the configuration of the system.
 *
 */
#ifndef __CONFIG__
#define __CONFIG__

#define PIR_PIN 6

#define SERVO_PIN 9

#define RX_PIN_B 3
#define TX_PIN_B 4

#define RX_PIN_S 1
#define TX_PIN_S 0

#define THERMOSTAT_PIN A0

#define CLOCK 100

#define P 5000

//Comunicazione Bluetooth
#define BLUETOOTH_PRESENCE_MSG "presence detected"
#define BLUETOOTH_ALARM_RESPONSE "true"
//Comunicazione Seriale
#define SERIAL_ALARM_MSG "alarm detected"
#define SERIAL_PRESENCE_MSG "presence detected"
#define SERIAL_CONNECTION_MSG "connection"

#include "Environment.h"

#endif

