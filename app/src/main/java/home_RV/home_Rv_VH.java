package home_RV;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.protectednotes.R;

public class home_Rv_VH extends RecyclerView.ViewHolder {
    TextView outer;
    TextView Title;
    public home_Rv_VH(@NonNull View itemView,home_Rv_interface home_rv_interface) {
        super(itemView);
        outer=itemView.findViewById(R.id.OutNoteContent);
        Title=itemView.findViewById(R.id.DateRv);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if (home_rv_interface != null){
                  int pos=getAdapterPosition();
                  if(pos!=RecyclerView.NO_POSITION){
                      home_rv_interface.OnItemClick(pos);
                  }
              }

            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (home_rv_interface != null){
                    int pos=getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION){
                        home_rv_interface.OnItemLongClick(pos);
                    }
                }
                return false;
            }

    });
}

}
