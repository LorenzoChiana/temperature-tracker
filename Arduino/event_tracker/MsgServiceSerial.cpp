#include "Arduino.h"
#include "MsgServiceSerial.h"

static MsgSerial* currentMsg;
static bool msgAvailable;
static String content;

void MsgServiceSerial::init(){
  content.reserve(256);
  Serial.begin(9600);
  while (!Serial){}
}

void MsgServiceSerial::sendMsg(MsgSerial msg){
  Serial.println(msg.getContent());  
}

bool MsgServiceSerial::isMsgAvailable(){
  return msgAvailable;
}

MsgSerial* MsgServiceSerial::receiveMsg(){
  if (msgAvailable){
    MsgSerial* msg = currentMsg;
    msgAvailable = false;
    currentMsg = NULL;
    content = "";
    return msg;  
  } else {
    return NULL; 
  }
}

void serialEvent() {
  /* reading the content */
  while (Serial.available()) {
    char ch = (char) Serial.read();
    if (ch == '\n'){
      currentMsg = new MsgSerial(content);
      msgAvailable = true;      
    } else {
      content += ch;      
    }
  }
}




