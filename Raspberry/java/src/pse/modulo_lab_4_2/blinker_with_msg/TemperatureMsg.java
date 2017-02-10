package pse.modulo_lab_4_2.blinker_with_msg;

import pse.modulo_lab_4_2.common.Event;

public class TemperatureMsg implements Event{
	private float value;

	public TemperatureMsg(String value){
		this.value = Float.parseFloat(value);
	}

	public TemperatureMsg(float value){
		this.value = value;
	}
	
	public float getValue(){
		return this.value;
	}
	
	
}
