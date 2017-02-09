package pse.modulo_lab_4_2.pingpong;

import pse.modulo_lab_4_2.common.*;

public class PongMsg implements Msg {
	
	private int count;
	
	public PongMsg(int count){
		this.count = count;
	}
	
	public int getCount(){
		return count;
	}
	
}
