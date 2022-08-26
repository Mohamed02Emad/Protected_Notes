package home_RV;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.kono_protected2.android.protectednotes.R;

import java.util.ArrayList;

public class home_Rv_Adapter extends RecyclerView.Adapter<home_Rv_VH> {
    ArrayList<home_Rv_Data> arrayList;
    private final home_Rv_interface home_rv_interface;
    public home_Rv_Adapter(ArrayList<home_Rv_Data> arrayList,home_Rv_interface home_rv_interface){
        this.arrayList=arrayList;
        this.home_rv_interface=home_rv_interface;
    }

    @NonNull
    @Override
    public home_Rv_VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new home_Rv_VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_card,parent,false), home_rv_interface);
    }

    @Override
    public void onBindViewHolder(@NonNull home_Rv_VH holder, int position) {
    home_Rv_Data d1=arrayList.get(position);
    holder.outer.setText(d1.getOuterContent());
    holder.Title.setText(d1.getTitle());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public home_Rv_Data ReturnData(int pos){

        return arrayList.get(pos);
    }
}
