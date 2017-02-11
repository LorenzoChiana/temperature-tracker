package pse.main;

import pse.devices.Light;
import pse.devices.ObservableButton;
import pse.serial.SerialCommChannel;

public class MainEventTracker {
	
	private static String fileSeparator = System.getProperty("file.separator");
	public static String JSON_FOLDER = fileSeparator + "var" + fileSeparator + "www" 
										+ fileSeparator + "html" + fileSeparator;
	
	public static void main(String[] args) throws Exception {
		
		
		System.out.println("Json path : " + JSON_FOLDER);
		
		//Light greenLed1 = new pse.modulo_lab_4_2.devices.emu.Led(4);
		//Light greenLed2 = new pse.modulo_lab_4_2.devices.emu.Led(5);
		//Light alarmLed = new pse.modulo_lab_4_2.devices.emu.Led(8,"red");
		Light greenLed1 = new pse.devices.pi4j.Led(4);
		Light greenLed2 = new pse.devices.pi4j.Led(5);
		Light alarmLed = new pse.devices.pi4j.Led(8);
		//Serial serialConnection = new pse.modulo_lab_4_2.devices.emu.SerialImpl(6, 7);		
		SerialCommChannel serialConnection = new SerialCommChannel(args[0],9600);
		
		//ObservableButton button = new pse.modulo_lab_4_2.devices.emu.ObservableButton(17);
		ObservableButton button = new pse.devices.pi4j.Button(17);
		
		EventTracker tracker = new EventTracker(greenLed1, greenLed2, alarmLed, button);
		SerialReciver rec = new SerialReciver(tracker, serialConnection);
		
		tracker.start();
		rec.start();
	}

}
