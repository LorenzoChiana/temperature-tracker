package pse.modulo_lab_4_2.fsmsync;

import pse.modulo_lab_4_2.devices.*;

public class TestButtonLed {
	public static void main(String[] args) {
		Light led = new pse.modulo_lab_4_2.devices.emu.Led(4);
		Button button = new pse.modulo_lab_4_2.devices.emu.Button(17);
		// Light led = new pse.modulo_lab_4.4.devices.dio_impl.Led(4);
		// Light led = new pse.modulo_lab_4_4.devices.p4j_impl.Led(4);
		// Button button = new pse.modulo_lab_4_4.devices.p4j_impl.Button(17);
		new ButtonLedController(button,led).start();
	}

}
