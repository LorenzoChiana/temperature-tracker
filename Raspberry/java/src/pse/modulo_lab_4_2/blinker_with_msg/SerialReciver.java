package pse.modulo_lab_4_2.blinker_with_msg;

import pse.modulo_lab_2_3.SerialCommChannel;
import pse.modulo_lab_4_2.common.BasicController;

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
				ex.printStackTrace();
			}
		}
	}

}

