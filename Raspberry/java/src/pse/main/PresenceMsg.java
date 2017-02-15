package pse.main;

import pse.common.Event;

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