package pse.modulo_lab_4_2.devices;

import pse.modulo_lab_4_2.common.*;

public class ButtonPressed implements Event {
	private Button source;
	
	public ButtonPressed(Button source){
		this.source = source;
	}
	
	public Button getSourceButton(){
		return source;
	}
}
