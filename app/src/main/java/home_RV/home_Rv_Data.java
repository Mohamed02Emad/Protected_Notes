package home_RV;

 public class home_Rv_Data {
    //data members
    private String outerContent;
    private String outerDate;

    //constructor
    public home_Rv_Data(String outerContent,String outerDate){
        this.outerContent=outerContent;
        this.outerDate=outerDate;
    }

    //setters
    public void setOuterContent(String outerContent) {
        this.outerContent = outerContent;
    }

    public void setOuterDate(String outerDate) {
        this.outerDate = outerDate;
    }

     //getters
     public String getOuterContent() {
         return outerContent;
     }

     public String getOuterDate() {
         return outerDate;
     }
 }
