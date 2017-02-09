package pse.modulo_lab_4_2.mds_event;

import pse.modulo_lab_4_2.devices.*;

public class MotionDetectorSystem {

	public static void main(String[] args) {
		AlarmCounter alarmCounter = new AlarmCounter();
		Light led = new pse.modulo_lab_4_2.devices.emu.Led(4);
		ObservableButton resetButton = new pse.modulo_lab_4_2.devices.emu.ObservableButton(7);
		ObservableMotionDetectorSensor pir = new pse.modulo_lab_4_2.devices.emu.ObservablePirSensor(2);
		Serial serialDev = new pse.modulo_lab_4_2.devices.emu.SerialImpl(8, 9);
		
		MotionDetector mda = new MotionDetector(led, pir, resetButton, alarmCounter);
		InputMsgReceiver rec = new InputMsgReceiver(alarmCounter, serialDev);
		mda.start();
		rec.start();
	}

}
