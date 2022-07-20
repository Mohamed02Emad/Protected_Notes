package Todo_RV;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ToDoData {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String Text;
    private boolean CheckBoxState;

    public ToDoData(String Text, boolean CheckBoxState) {
        this.Text = Text;
       this. CheckBoxState = CheckBoxState;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
