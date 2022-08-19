package todoDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import Todo_RV.ToDoData;


@Database(entities = ToDoData.class,version = 1)
abstract public class todoDB extends RoomDatabase {
    private static todoDB instance;
    public abstract todoDao todoDao();
    public static synchronized todoDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), todoDB.class, "list")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
