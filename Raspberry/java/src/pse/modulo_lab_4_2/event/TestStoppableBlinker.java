package pse.modulo_lab_4_2.event;

import pse.modulo_lab_4_2.devices.Light;
import pse.modulo_lab_4_2.devices.ObservableButton;

public class TestStoppableBlinker {
	public static void main(String[] args) {
		Light led = new pse.modulo_lab_4_2.devices.emu.Led(4);
		ObservableButton button = new pse.modulo_lab_4_2.devices.emu.ObservableButton(17);
		// Light led = new pse.modulo_lab_4_2.devices.dio_impl.Led(4);
		// Light led = new pse.modulo_lab_4_2.devices.p4j_impl.Led(4);
		//ObservableButton button = new pse.modulo_lab_4_2.devices.p4j_impl.Button(17);
		new StoppableBlinker(button,led).start();
	}

}
