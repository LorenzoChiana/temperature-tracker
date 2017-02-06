#ifndef __MSGSERVICESERIAL__
#define __MSGSERVICESERIAL__

#include "Arduino.h"

class MsgSerial {
  String content;

public:
  MsgSerial(const String& content){
    this->content = content;
  }
  
  String getContent(){
    return content;
  }
};

class MsgServiceSerial {
    
public:   
  void init();  
  bool isMsgAvailable();
  MsgSerial* receiveMsg();
  void sendMsg(MsgSerial msg);

};

#endif

