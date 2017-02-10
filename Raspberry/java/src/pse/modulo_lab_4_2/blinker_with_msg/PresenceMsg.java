package pse.modulo_lab_4_2.blinker_with_msg;

import pse.modulo_lab_4_2.common.Event;

public class PresenceMsg implements Event{
	
	private boolean isAlarm;

	public PresenceMsg(String value){
		//Metterci i messaggi
	}

	public PresenceMsg(boolean value){
		this.isAlarm = value;
	}
	
	public boolean isAlarm(){
		return this.isAlarm;
	}

}