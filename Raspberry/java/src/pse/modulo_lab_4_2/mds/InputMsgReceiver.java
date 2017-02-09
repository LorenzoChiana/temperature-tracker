package pse.modulo_lab_4_2.mds;

import pse.modulo_lab_4_2.common.*;
import pse.modulo_lab_4_2.devices.*;

public class InputMsgReceiver extends BasicFSMController {

	private AlarmCounter alarmCounter;
	private Serial serialDevice;
	
	public InputMsgReceiver(AlarmCounter alarmCounter, Serial serialDevice){
		super(200);
		this.alarmCounter = alarmCounter;
		this.serialDevice = serialDevice;
	}
	
	@Override
	protected void tick() {
		try { 	 
			if (serialDevice.isMsgAvailable()){
				String msg = serialDevice.readMsg();
				if (msg.equals("r")){
					String outMsg = String.valueOf(alarmCounter.getCurrentNumAlarms());
					serialDevice.sendMsg(outMsg);
				}
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}

}
