package pse.modulo_lab_4_2.blinker_with_msg;
public enum MsgType {
         
        TEMPERATURE("time","value","temperature.json"),
        ALARM("time", "alarm", "log.json");
         
        private String firstField;
        private String secondField;
        private String fileName;
 
        public String getFirstField() {
            return this.firstField;
        }
        
        public String getSecondField() {
            return this.secondField;
        }
         
        public String getFileName() {
            return this.fileName;
        }
         
        private MsgType(String firstField, String secondField, String path) {
 
            this.firstField = firstField;
            this.secondField = secondField;
            this.fileName = path;
        }
}