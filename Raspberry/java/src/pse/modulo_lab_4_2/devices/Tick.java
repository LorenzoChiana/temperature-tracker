package pse.modulo_lab_4_2.devices;

import pse.modulo_lab_4_2.common.*;

public class Tick implements Event {
	
	private long time;
	
	public Tick(long time ){
		this.time = time;
	}
	
	public long getTime(){
		return time;
	}
}
