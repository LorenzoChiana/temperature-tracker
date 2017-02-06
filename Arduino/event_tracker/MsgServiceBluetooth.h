#ifndef __MSGSERVICEBLUETOOTH__
#define __MSGSERVICEBLUETOOTH__

#include "Arduino.h"
#include "SoftwareSerial.h"

class MsgBluetooth {
  String content;

public:
  MsgBluetooth(const String& content){
    this->content = content;
  }
  
  String getContent(){
    return content;
  }
};

class MsgServiceBluetooth {
    
public: 
  MsgServiceBluetooth(int rxPin, int txPin);  
  void init();  
  bool isMsgAvailable();
  MsgBluetooth* receiveMsg();
  bool sendMsg(MsgBluetooth msg);

private:
  String content;
  SoftwareSerial* channel;
  
};

#endif

