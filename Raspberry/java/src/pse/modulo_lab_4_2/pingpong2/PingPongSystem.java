package pse.modulo_lab_4_2.pingpong2;

import pse.modulo_lab_4_2.common.MsgEvent;
import pse.modulo_lab_4_2.devices.*;

public class PingPongSystem {

	public static void main(String[] args) {
		Light ledA = new pse.modulo_lab_4_2.devices.emu.Led(4,"green");
		Light ledB = new pse.modulo_lab_4_2.devices.emu.Led(4,"red");

		Pinger pinger = new Pinger();
		Ponger ponger = new Ponger();
		
		pinger.init(ledA, ponger);
		ponger.init(ledB, pinger);
		
		pinger.start();
		ponger.start();
		pinger.notifyEvent(new MsgEvent(new BootMsg()));
				
	}

}
