package pse.modulo_lab_4_2.basic;

import java.io.IOException;

import pse.modulo_lab_4_2.common.BasicController;
import pse.modulo_lab_4_2.devices.Button;
import pse.modulo_lab_4_2.devices.Light;

public class ButtonLedController extends BasicController {
	
	private Light led;
	private Button button;
	
	public ButtonLedController(Button button, Light led){
		this.led = led;
		this.button = button;
	}
	
	public void run(){
		try {
			while (true){	
			  if (button.isPressed()){
				  led.switchOn();
			  } else {
				  led.switchOff(); 
			  }
			  waitFor(50);
			}		
		} catch (InterruptedException ex){
			ex.printStackTrace();
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}
}
