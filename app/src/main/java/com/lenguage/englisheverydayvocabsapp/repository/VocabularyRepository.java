package com.lenguage.englisheverydayvocabsapp.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.lenguage.englisheverydayvocabsapp.data.entity.Vocabulary;
import com.lenguage.englisheverydayvocabsapp.data.model.Resultado;
import com.lenguage.englisheverydayvocabsapp.datasource.VocabularyDatasource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VocabularyRepository {

    private static VocabularyRepository instance;
    private VocabularyDatasource vocabularyLocalDatasource;

    private MutableLiveData<List<Vocabulary>> listaVocabulary = new MutableLiveData<>();
    private MutableLiveData<Resultado<List<Vocabulary>>> elResultado = new MutableLiveData<>();

    public static VocabularyRepository getInstance(Application application) {
        if (instance == null) {
            instance = new VocabularyRepository(application);
        }
        return instance;
    }

    private VocabularyRepository(Application application) {
        vocabularyLocalDatasource = new VocabularyDatasource(application);

        listaVocabulary.setValue(new ArrayList<>());
    }

    public void init() {
        elResultado.setValue(null);
    }

    public MutableLiveData<List<Vocabulary>> getListaVocabulary() {
        return listaVocabulary;
    }

    public void setListaVocabulary(List<Vocabulary> lista) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            for(Vocabulary data : lista){
                //vocabularyLocalDatasource.verificarDorsal(data);
            }
            this.listaVocabulary.postValue(lista);
        });
    }

//
    public MutableLiveData<Resultado<List<Vocabulary>>> getElResultado() {
        return elResultado;
    }

    public void cargarListaVocabulary() {
        vocabularyLocalDatasource.cargarListaVocabulario(listaVocabulary);
    }

    public void insertarListaVocabulary(Vocabulary data) {
        vocabularyLocalDatasource.insertarVocabulary(elResultado, data);
    }

    public void eliminarVocabulary(Vocabulary data) {
        vocabularyLocalDatasource.eliminarVocabulary(data, elResultado, listaVocabulary);
    }

}