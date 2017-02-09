package pse.modulo_lab_4_2.mds;

import pse.modulo_lab_4_2.devices.*;

public class MotionDetectorSystem {

	public static void main(String[] args) {
		
		AlarmCounter alarmCounter = new AlarmCounter();		
		Light led = new pse.modulo_lab_4_2.devices.emu.Led(4);
		Button resetButton = new pse.modulo_lab_4_2.devices.emu.Button(7);
		MotionDetectorSensor pir = new pse.modulo_lab_4_2.devices.emu.PirSensor(2);
		Serial serialDev = new pse.modulo_lab_4_2.devices.emu.SerialImpl(8, 9);
		
		MotionDetector mda = new MotionDetector(led, pir, resetButton, alarmCounter);
		InputMsgReceiver rec = new InputMsgReceiver(alarmCounter, serialDev);
		mda.start();
		rec.start();
	}

}
