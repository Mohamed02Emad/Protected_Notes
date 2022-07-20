package Todo_RV;

public class ToDoData {

    private String Text;
    private boolean CheckBoxState;

    public ToDoData(String Text, boolean CheckBoxState) {
        this.Text = Text;
       this. CheckBoxState = CheckBoxState;
    }




    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public boolean isCheckBoxState() {
        return CheckBoxState;
    }

    public void setCheckBoxState(boolean checkBoxState) {
        CheckBoxState = checkBoxState;
    }
}
