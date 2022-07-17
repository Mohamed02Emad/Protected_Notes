package home_RV;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.protectednotes.R;

import java.util.ArrayList;

public class home_Rv_Adapter extends RecyclerView.Adapter<home_Rv_VH> {
    ArrayList<home_Rv_Data> arrayList;
    public home_Rv_Adapter(ArrayList<home_Rv_Data> arrayList){
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public home_Rv_VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new home_Rv_VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull home_Rv_VH holder, int position) {
    home_Rv_Data d1=arrayList.get(position);
    holder.outer.setText(d1.getOuterContent());
    holder.Date.setText(d1.getOuterDate());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
