package Todo_RV;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.protectednotes.MainActivity;
import com.example.android.protectednotes.R;

public class ToDoVH extends RecyclerView.ViewHolder {
    TextView textView;
    CheckBox checkBox;
    MediaPlayer mediaPlayer;
    public ToDoVH(@NonNull View itemView,ListInterface listInterface) {
        super(itemView);
        textView = itemView.findViewById(R.id.to_do_Text);
        checkBox = itemView.findViewById(R.id.checkbox_Card);

        mediaPlayer = MediaPlayer.create(itemView.getContext(), R.raw.click);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.SoundON) mediaPlayer.start();
                if ( listInterface != null) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        listInterface.OnItemClick(pos);
                    }
                }

            }
        });
    }
}
