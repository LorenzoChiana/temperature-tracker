package pse.modulo_lab_4_2.devices;

import pse.modulo_lab_4_2.common.*;

public class ButtonReleased implements Event {
	private Button source;
	
	public ButtonReleased(Button source){
		this.source = source;
	}
	
	public Button getSourceButton(){
		return source;
	}
}
