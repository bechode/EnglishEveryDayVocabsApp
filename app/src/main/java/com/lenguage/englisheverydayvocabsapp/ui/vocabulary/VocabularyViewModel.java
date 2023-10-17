package com.lenguage.englisheverydayvocabsapp.ui.vocabulary;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.lenguage.englisheverydayvocabsapp.data.entity.Vocabulary;
import com.lenguage.englisheverydayvocabsapp.data.model.Resultado;
import com.lenguage.englisheverydayvocabsapp.repository.VocabularyRepository;

import java.util.List;

public class VocabularyViewModel extends AndroidViewModel {

    private VocabularyRepository repository;
    public VocabularyViewModel(@NonNull Application application) {
        super(application);
        repository = VocabularyRepository.getInstance(application);
    }

    public void init() {
        repository.init();
    }

    public MutableLiveData<List<Vocabulary>> getListaVocabulary() {
        return repository.getListaVocabulary();
    }

    public void setListaVocabulary(List<Vocabulary> lista) {
        repository.setListaVocabulary(lista);
    }

    //
    public MutableLiveData<Resultado<List<Vocabulary>>> getElResultado() {
        return repository.getElResultado();
    }

    public void cargarListaVocabulary() {
        repository.cargarListaVocabulary();
    }

    public void insertarListaVocabulary(Vocabulary data) {
        repository.insertarListaVocabulary(data);
    }

    public void eliminarVocabulary(Vocabulary data) {
        repository.eliminarVocabulary(data);
    }

}
