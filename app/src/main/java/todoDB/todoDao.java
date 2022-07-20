package todoDB;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Todo_RV.ToDoData;


@Dao
public interface todoDao {
    @Query("Select * FROM ToDoData")
    List<ToDoData> getAll();

    @Delete
    void Delete(ToDoData toDoData);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insert(ToDoData toDoData);

    @Update
    void Update(ToDoData toDoData);
}
