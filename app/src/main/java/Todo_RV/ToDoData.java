package Todo_RV;

public class ToDoData {

    private String Text;
    private boolean CheckBoxState;

    public ToDoData(String text, boolean checkBoxState) {
        Text = text;
        CheckBoxState = checkBoxState;
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
