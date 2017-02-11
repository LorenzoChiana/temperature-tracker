package pse.common;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONController {

	private String fileAbsolutePath;	
	private MsgType msgType;

	static DateFormat dateFormat;
	private JSONParser globalParser = new JSONParser();
	private JSONArray currentArray;

	public JSONController(String fileAbsolutePath, MsgType msgType) {
		this.msgType = msgType;
		this.fileAbsolutePath = fileAbsolutePath + this.msgType.getFileName();		

		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try( FileReader file = new FileReader(this.fileAbsolutePath) ) {
			currentArray = (JSONArray) globalParser.parse( file );
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void append(String fistField, float secondField){
		if (msgType == MsgType.TEMPERATURE){
			JSONObject newObject = new JSONObject();
			newObject.put(this.msgType.getFirstField(), fistField);
			newObject.put(this.msgType.getSecondField(), secondField);

			currentArray.add(newObject);
		}				
	}

	public void append(String fistField, boolean secondField){
		if (msgType == MsgType.ALARM){
			JSONObject newObject = new JSONObject();
			newObject.put(this.msgType.getFirstField(), fistField);
			newObject.put(this.msgType.getSecondField(), secondField);

			currentArray.add(newObject);
		}		
	}

	public void append(float secondField){
		this.append(this.getCurrentDate(), secondField);
	}

	public void append(boolean secondField){
		this.append(this.getCurrentDate(), secondField);
	}

	public void write(){
		try ( FileWriter file = new FileWriter(this.fileAbsolutePath) ) {
			file.write(this.currentArray.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getCurrentDate(){
		return dateFormat.format(new Date()).toString();
	}

}
