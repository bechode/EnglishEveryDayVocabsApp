package com.lenguage.englisheverydayvocabsapp.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "vocabularies", indices = {@Index(value = {"id"})})
public class Vocabulary {
    @PrimaryKey
    private Long id;
    @ColumnInfo(name = "word")
    private String word;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "spanish_translation")
    private String spanishTranslation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpanishTranslation() {
        return spanishTranslation;
    }

    public void setSpanishTranslation(String spanishTranslation) {
        this.spanishTranslation = spanishTranslation;
    }
}
