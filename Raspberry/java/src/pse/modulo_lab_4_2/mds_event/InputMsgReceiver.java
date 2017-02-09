package pse.modulo_lab_4_2.mds_event;

import pse.modulo_lab_4_2.common.*;
import pse.modulo_lab_4_2.devices.*;

public class InputMsgReceiver extends BasicController {

	private AlarmCounter alarmCounter;
	private Serial serialDevice;
	
	public InputMsgReceiver(AlarmCounter alarmCounter, Serial serialDevice){
		this.alarmCounter = alarmCounter;
		this.serialDevice = serialDevice;
	}
	
	@Override
	public void run() {
		while (true){
			try {
				String msg = serialDevice.waitForMsg();
				if (msg.equals("r")){
					String outMsg = String.valueOf(alarmCounter.getCurrentNumAlarms());
					serialDevice.sendMsg(outMsg);
				}
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
	}

}
