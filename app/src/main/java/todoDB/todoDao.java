package todoDB;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import Todo_RV.ToDoData;


@Dao
public interface todoDao {
    @Query("Select * FROM notes")
    List<ToDoData> getAll();

    @Delete
    void Delete(ToDoData toDoData);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insert(ToDoData toDoData);
}
