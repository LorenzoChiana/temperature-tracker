package pse.modulo_lab_4_2.blinker_with_msg;

import pse.modulo_lab_2_3.SerialCommChannel;
import pse.modulo_lab_4_2.devices.Light;
import pse.modulo_lab_4_2.devices.ObservableButton;

public class MainEventTracker {
	public static void main(String[] args) throws Exception {
		
		//Light greenLed1 = new pse.modulo_lab_4_2.devices.emu.Led(4);
		//Light greenLed2 = new pse.modulo_lab_4_2.devices.emu.Led(5);
		//Light alarmLed = new pse.modulo_lab_4_2.devices.emu.Led(8,"red");
		Light greenLed1 = new pse.modulo_lab_4_2.devices.p4j_impl.Led(4);
		Light greenLed2 = new pse.modulo_lab_4_2.devices.p4j_impl.Led(5);
		Light alarmLed = new pse.modulo_lab_4_2.devices.p4j_impl.Led(8);
		//Serial serialConnection = new pse.modulo_lab_4_2.devices.emu.SerialImpl(6, 7);		
		SerialCommChannel serialConnection = new SerialCommChannel(args[0],9600);
		
		//ObservableButton button = new pse.modulo_lab_4_2.devices.emu.ObservableButton(17);
		ObservableButton button = new pse.modulo_lab_4_2.devices.p4j_impl.Button(17);
		
		EventTracker tracker = new EventTracker(greenLed1, greenLed2, alarmLed, button);
		SerialReciver rec = new SerialReciver(tracker, serialConnection);
		
		tracker.start();
		rec.start();
	}

}
