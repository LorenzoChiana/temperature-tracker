package pse.modulo_lab_4_2.basic;

import pse.modulo_lab_4_2.devices.Light;

public class TestBlinking {
	public static void main(String[] args) {
		// Light led = new pse.modulo_lab_4_2.devices.emu.Led(4);
		// Light led = new pse.modulo_lab_4_2.devices.dio_impl.Led(4);
		Light led = new pse.modulo_lab_4_2.devices.p4j_impl.Led(7);
		new Blinker(led,500).start();
		// Light led2 = new pse.modulo_lab_4_2.devices.emu.Led(2,"led2");
		// new Blinker(led2,250).start();
	}

}
