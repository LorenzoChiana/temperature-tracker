package pse.modulo_lab_4_2.devices;

public interface Serial {
	
	/* async interface */
	boolean isMsgAvailable();
	String 	readMsg();
	void	sendMsg(String msg);
	
	/* sync interface */
	String 	waitForMsg() throws InterruptedException;
}
