package pse.main;

import pse.common.BasicController;
import pse.serial.SerialCommChannel;

//Classe che riceve i messaggi dalla seriale su un Thread e li fa processare all'Event Tracker	
public class SerialReciver extends BasicController {
	private enum MsgValues {		
        CONNECTION("connection"), PRESENCE("presence detected"), ALARM("alarm detected");		
		private String value;		
		public String getValue(){
			return this.value;
		}		
		MsgValues(String value){
			this.value = value;
		}
    }

	private EventTracker tracker;
	private SerialCommChannel serialDevice;
	
	public SerialReciver(EventTracker tracker, SerialCommChannel serialDevice){
		this.tracker = tracker;
		this.serialDevice = serialDevice;
	}
	
	@Override
	public void run() {
		while (true){
			try {
				String msg = serialDevice.receiveMsg();
				
				if (msg.equals(MsgValues.CONNECTION.getValue())){					
					tracker.notifyEvent(new ConnectionMsg());					
				} else if (msg.equals(MsgValues.ALARM.getValue())){					
					tracker.notifyEvent(new PresenceMsg(true));					
				} else if (msg.equals(MsgValues.PRESENCE.getValue())){					
					tracker.notifyEvent(new PresenceMsg(false));					
				} else {					
					tracker.notifyEvent(new TemperatureMsg(msg));					
				}				
			} catch (Exception ex){
				System.out.println("Message not recognized.");
			}
		}
	}

}

