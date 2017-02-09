package pse.modulo_lab_4_2.fsmsync;

import pse.modulo_lab_4_2.devices.Light;

public class TestBlinking {
	public static void main(String[] args) {
		Light led = new pse.modulo_lab_4_2.devices.emu.Led(4);
		// Light led = new pse.modulo_lab_4_2.devices.dio_impl.Led(4);
		// Light led = new modulo_lab_4_2.devices.p4j_impl.Led(4);
		new Blinker(led).start();
	}

}
