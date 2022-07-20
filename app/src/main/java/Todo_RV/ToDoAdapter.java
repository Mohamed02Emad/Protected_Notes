package Todo_RV;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.protectednotes.R;

import java.util.ArrayList;
import java.util.List;

import home_RV.home_Rv_Data;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoVH>  {
    ArrayList<ToDoData> arrayList;
    private final ListInterface listInterface;
    public ToDoAdapter (ArrayList<ToDoData> arrayList,ListInterface listInterface){
        this.listInterface=listInterface;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public ToDoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ToDoVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_card,parent,false),listInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoVH holder, int position) {
        ToDoData d1=arrayList.get(position);
        holder.checkBox.setChecked(d1.isCheckBoxState());
        holder.textView.setText(d1.getText());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public ToDoData ReturnData(int pos){

        return arrayList.get(pos);
    }


}






