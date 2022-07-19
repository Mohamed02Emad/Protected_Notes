package home_DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import home_RV.home_Rv_Data;

@Database(entities = home_Rv_Data.class,version = 1)
abstract public class NotesDataBase extends RoomDatabase {
    private static NotesDataBase instance;

    public abstract NoteDao noteDao();

    public static synchronized NotesDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), NotesDataBase.class, "NotesDataBase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
