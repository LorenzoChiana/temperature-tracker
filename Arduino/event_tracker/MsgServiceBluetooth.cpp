#include "Arduino.h"
#include "MsgServiceBluetooth.h"


MsgServiceBluetooth::MsgServiceBluetooth(int rxPin, int txPin){
  channel = new SoftwareSerial(rxPin, txPin);
}

void MsgServiceBluetooth::init(){
  content.reserve(256);
  channel->begin(9600);
}

bool MsgServiceBluetooth::sendMsg(MsgBluetooth msg){
  byte output[256];
  String content = msg.getContent();
  int len = content.length();
  if (len >= 0 && len <= 255){
    output[0] = (byte) len;
    content.getBytes((output+1),len+1);
    channel->write((const char*)output,len+1);
    return true;
  } else {
    return false;
  }
}

bool MsgServiceBluetooth::isMsgAvailable(){
  return channel->available();
}

MsgBluetooth* MsgServiceBluetooth::receiveMsg(){
  if (channel->available()){    
    content="";
    int size = channel->read();
    // Serial.println("> "+String(size));
    int nDataRec = 0;
    while (nDataRec < size) {
      if (channel->available()){
        content += (char)channel->read();      
        nDataRec++;
      }
    }
    return new MsgBluetooth(content);
  } else {
    return NULL;  
  }
}




