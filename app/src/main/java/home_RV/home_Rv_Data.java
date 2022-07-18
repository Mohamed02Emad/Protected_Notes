package home_RV;

 public class home_Rv_Data {
    //data members
    private String outerContent;
    private String title;
    private String Content;



     //constructor
     public home_Rv_Data(String outerContent,String outerDate){
         this.outerContent=outerContent;
         this.title=outerDate;
     }

     public void setContent(String Content) {
         this.Content = Content;
     }

     public String getOuterContent() {
         return outerContent;
     }

     public void setOuterContent(String outerContent) {
         this.outerContent = outerContent;
     }

     public String getTitle() {
         return title;
     }

     public void setTitle(String title) {
         this.title = title;
     }

     public String getContent() {
         return Content;
     }
 }
