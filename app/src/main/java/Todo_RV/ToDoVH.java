package Todo_RV;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.protectednotes.R;

public class ToDoVH extends RecyclerView.ViewHolder {
    TextView textView;
    CheckBox checkBox;
    public ToDoVH(@NonNull View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.to_do_Text);
        checkBox=itemView.findViewById(R.id.checkbox_Card);
    }
}
