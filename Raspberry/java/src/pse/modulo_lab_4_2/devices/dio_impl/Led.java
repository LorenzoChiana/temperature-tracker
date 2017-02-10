package pse.modulo_lab_4_2.devices.dio_impl;

import jdk.dio.DeviceConfig;
import jdk.dio.DeviceManager;
import jdk.dio.gpio.GPIOPin;
import jdk.dio.gpio.GPIOPinConfig;
import pse.modulo_lab_4_2.devices.*;

import java.io.IOException;


public class Led implements Light {
	private int pinNum;
	private GPIOPin pin;
	
	public Led(int pinNum){

        GPIOPinConfig pinConfig = new GPIOPinConfig(DeviceConfig.DEFAULT,
                pinNum,
                GPIOPinConfig.DIR_OUTPUT_ONLY,
                GPIOPinConfig.MODE_OUTPUT_PUSH_PULL,
                GPIOPinConfig.TRIGGER_NONE,
                false);
       

		this.pinNum = pinNum;
		try {
			 pin = (GPIOPin)DeviceManager.open(GPIOPin.class, pinConfig);
			 // pin = DeviceManager.open(pinNum);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void switchOn() throws IOException {
		pin.setValue(true);		
		// System.out.println("LIGHT ON - pin "+pin);
	}

	@Override
	public synchronized void switchOff() throws IOException {
		pin.setValue(false);
		// System.out.println("LIGHT OFF - pin "+pin);		
	}
	
}
