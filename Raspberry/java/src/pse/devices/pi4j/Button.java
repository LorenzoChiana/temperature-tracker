package pse.devices.pi4j;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import pse.common.Event;
import pse.devices.ButtonPressed;
import pse.devices.ButtonReleased;
import pse.devices.ObservableButton;

public class Button extends ObservableButton {

	private GpioPinDigitalInput pin;
	
	public Button(int pinNum){
		super();
		try {
		    GpioController gpio = GpioFactory.getInstance();
		    pin = gpio.provisionDigitalInputPin(Config.pinMap[pinNum],PinPullResistance.PULL_DOWN);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pin.addListener(new ButtonListener(this));
	}
	
	@Override
	public synchronized boolean isPressed() {
		return pin.isHigh();
	}
	
	
	class ButtonListener implements GpioPinListenerDigital {		
		Button button;
		
		public ButtonListener(Button button){
			this.button = button;
		}		

		public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event)  {
        	Event ev = null;
            System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
            if (event.getState().isHigh()){
            	ev = new ButtonPressed(button);
            } else {
            	ev = new ButtonReleased(button);
            }
            notifyEvent(ev);
        }
	}
}
