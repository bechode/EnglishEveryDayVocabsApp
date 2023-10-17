package com.lenguage.englisheverydayvocabsapp.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.lenguage.englisheverydayvocabsapp.data.entity.Vocabulary;

import java.util.List;

@Dao
public interface VocabularyDao {

    @Query("SELECT * from vocabularies")
    public List<Vocabulary> getAll();

    @Query("SELECT count(*) from vocabularies d where d.word = :word")
    public long existeByValorLectura(String word);


    @Query("SELECT d.* from vocabularies d where d.word = :word LIMIT 1")
    public Vocabulary getByWord(String word);
    @Query("SELECT ifnull(max(id), 0) from vocabularies")
    public int getMaxId();

    @Insert
    public long insert(Vocabulary obj);

    @Update
    public int update(Vocabulary obj);

    @Delete
    public int delete(Vocabulary obj);

    @Query("DELETE FROM vocabularies")
    public int deleteAll();

}
