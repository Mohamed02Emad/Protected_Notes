package home_RV;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
 public class home_Rv_Data {
    //data members
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String outerContent="";
    private String title;
    private String Content;



     //constructor
     public home_Rv_Data( String Content,String title){
         this.Content=Content;
         this.title=title;
         makeOuter();
     }


     private void makeOuter() {
         long number=Content.toString().length();
         if(number<98){
             outerContent=Content;
         }
         else{
                 outerContent=outerContent + Content.substring(0,94) +" ....";
             }

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

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}
}
