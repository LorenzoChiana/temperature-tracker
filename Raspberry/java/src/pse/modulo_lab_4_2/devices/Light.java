package pse.modulo_lab_4_2.devices;

import java.io.IOException;

public interface Light {
	void switchOn() throws IOException;
	void switchOff() throws IOException;
}
