package pse.event;

import java.io.IOException;

import pse.common.BasicEventLoopController;
import pse.common.Event;
import pse.devices.ButtonPressed;
import pse.devices.ButtonReleased;
import pse.devices.Light;
import pse.devices.ObservableButton;

public class ButtonLedController extends BasicEventLoopController {
	
	private Light led;
	private ObservableButton button;

	public ButtonLedController(ObservableButton button, Light led){
		this.led = led;
		this.button = button;
		button.addObserver(this);
	}
	
	protected void processEvent(Event ev){
		try {
			if (ev instanceof ButtonPressed){
				led.switchOn();
			} else if (ev instanceof ButtonReleased){
				led.switchOff();
			}
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}
}
