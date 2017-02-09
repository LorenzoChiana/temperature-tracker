package pse.modulo_lab_4_1;

import javax.json.*;


/**
 * Testing simple message passing based on JSON
 * 
 * To be used with an Arduino connected via Serial Port
 * running modulo-2.4/serial_json_XXX.ino
 * 
 * @author aricci
 *
 */
public class TestMsgJSON {

	public static void main(String[] args) throws Exception {
		SerialCommChannel channel = new SerialCommChannel(args[0],9600);
		
		/* attesa necessaria per fare in modo che Arduino completi il reboot */
		System.out.println("Waiting Arduino for rebooting...");		
		Thread.sleep(4000);
		System.out.println("Ready.");		
		
		while (true){
			try {
				String msg = channel.receiveMsg();
				System.out.println(msg);		
				JsonReader reader = Json.createReader(new java.io.StringReader(msg));
				JsonObject obj = reader.readObject();
				String type = obj.getString("msg");
				int value = obj.getInt("value");
				System.out.println("Type: "+type+" value: "+value);		
				Thread.sleep(500);
			} catch (Exception ex){}
	
		}
	}

}
