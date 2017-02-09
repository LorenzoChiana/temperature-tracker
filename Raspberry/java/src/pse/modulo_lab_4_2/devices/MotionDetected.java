package pse.modulo_lab_4_2.devices;

import pse.modulo_lab_4_2.common.*;

public class MotionDetected implements Event {
	private ObservableMotionDetectorSensor source;
	
	public MotionDetected(ObservableMotionDetectorSensor source){
		this.source = source;
	}
	
	public ObservableMotionDetectorSensor getSource(){
		return source;
	}
}
