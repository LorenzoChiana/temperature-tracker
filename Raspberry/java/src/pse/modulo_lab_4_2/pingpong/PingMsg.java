package pse.modulo_lab_4_2.pingpong;

import pse.modulo_lab_4_2.common.*;

public class PingMsg implements Msg {
	
	private int count;
	
	public PingMsg(int count){
		this.count = count;
	}
	
	public int getCount(){
		return count;
	}
	
}
