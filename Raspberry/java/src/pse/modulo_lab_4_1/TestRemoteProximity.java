package pse.modulo_lab_4_1;

/**
 * Testing simple message passing.
 * 
 * To be used with an Arduino connected via Serial Port
 * running modulo-2.4/serial_led.ino
 * 
 * @author aricci
 *
 */
public class TestRemoteProximity {

	public static void main(String[] args) throws Exception {
		SerialCommChannel channel = new SerialCommChannel(args[0],9600);		
		/* attesa necessaria per fare in modo che Arduino completi il reboot */
		System.out.println("Waiting Arduino for rebooting...");		
		Thread.sleep(4000);
		System.out.println("Ready.");		

		while (true){
			channel.sendMsg("request_distance");
			String msg = channel.receiveMsg();
			System.out.println("reply: "+msg);
			Thread.sleep(1000);
		}
	}

}
