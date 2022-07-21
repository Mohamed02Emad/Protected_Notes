package home_DB;

import android.database.Observable;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import home_RV.home_Rv_Data;
import io.reactivex.Completable;

@Dao
public interface NoteDao {
    @Query("Select * FROM notes")
    List<home_Rv_Data> getAll();

    @Delete
    void DeleteNote(home_Rv_Data home_rv_data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insert(home_Rv_Data home_rv_data);

    @Update
    void Update(home_Rv_Data home_rv_data);



}
