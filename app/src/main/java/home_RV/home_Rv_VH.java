package home_RV;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.protectednotes.R;

public class home_Rv_VH extends RecyclerView.ViewHolder {
    TextView outer;
    TextView Date;
    public home_Rv_VH(@NonNull View itemView) {
        super(itemView);
        outer=itemView.findViewById(R.id.OutNoteContent);
        Date=itemView.findViewById(R.id.DateRv);
    }
}
