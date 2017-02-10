package pse.modulo_lab_4_2.blinker_with_msg;

import java.io.IOException;

import pse.modulo_lab_4_2.common.BasicEventLoopController;
import pse.modulo_lab_4_2.common.Event;
import pse.modulo_lab_4_2.devices.ButtonPressed;
import pse.modulo_lab_4_2.devices.Light;
import pse.modulo_lab_4_2.devices.ObservableButton;
import pse.modulo_lab_4_2.devices.ObservableTimer;
import pse.modulo_lab_4_2.devices.Tick;

public class EventTracker extends BasicEventLoopController {

	private Light greenLed1;
	private Light greenLed2;
	private Light alarmLed;

	private boolean ackRecived = false;

	private ObservableTimer timer;
	private ObservableButton button;

	private enum State {CONNECTED, NOT_CONNECTED};
	private State currentState;

	public EventTracker(Light led1, Light led2, Light led3, ObservableButton button){

		this.greenLed1 = led1;
		this.greenLed2 = led2;
		this.alarmLed  = led3;
		
		this.button = button;
		button.addObserver(this);

		this.timer = new ObservableTimer();		
		timer.addObserver(this);
		timer.start(50000);
		
		currentState = State.NOT_CONNECTED;
	}

	protected void processEvent(Event ev){
		switch (currentState){
		case CONNECTED:
			try {
				if (ev instanceof TemperatureMsg){
					TemperatureMsg tempMsg = (TemperatureMsg) ev;
					float currentTemp = tempMsg.getValue();
					System.err.println(tempMsg.getValue());					
					blinkLed(greenLed2);				
				} else if (ev instanceof ConnectionMsg){
					ackRecived = true;					
				} else if (ev instanceof PresenceMsg){
					PresenceMsg presenceMsg = (PresenceMsg) ev;
					if (presenceMsg.isAlarm()){
						alarmLed.switchOn();
					} 					
					blinkLed(greenLed2);
					System.err.println("Presenza : " + presenceMsg.isAlarm());
				} else if (ev instanceof Tick){
					System.err.println("Tick di controllo");
					if (!ackRecived){
						currentState = State.NOT_CONNECTED;
						greenLed1.switchOff();
						greenLed2.switchOff();
						alarmLed.switchOff();
					} 
					ackRecived = false;
				} else if (ev instanceof ButtonPressed){
					alarmLed.switchOff();
					break;
				}
			} catch (IOException ex){
				ex.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		case NOT_CONNECTED:
			try {
				if (ev instanceof ConnectionMsg){
					currentState = State.CONNECTED;
					greenLed1.switchOn();
					ackRecived = true;
				}
			} catch (IOException ex){
				ex.printStackTrace();
			}
			break;
		}
	}
	
	private void blinkLed(Light led) throws InterruptedException, IOException{
		led.switchOn();
		waitFor(50);
		led.switchOff();	
	}
	
	private void waitFor(long ms) throws InterruptedException{
		Thread.sleep(ms);
	}
}
