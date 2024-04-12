package com.lenguage.englisheverydayvocabsapp.datasource;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.lenguage.englisheverydayvocabsapp.data.dao.VocabularyDao;
import com.lenguage.englisheverydayvocabsapp.data.db.DataBaseDefinition;
import com.lenguage.englisheverydayvocabsapp.data.entity.Vocabulary;
import com.lenguage.englisheverydayvocabsapp.data.model.Resultado;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VocabularyDatasource {

    private VocabularyDao vocabularyDao;

    public VocabularyDatasource(Application application) {
        DataBaseDefinition db = DataBaseDefinition.getDatabase(application);
        vocabularyDao = db.getVocabularyDao();
    }


    public void cargarListaVocabulario(@NonNull MutableLiveData<List<Vocabulary>> vocabularyList) {
        ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(1);
        databaseWriteExecutor.execute(() -> {
            List<Vocabulary> lista = vocabularyDao.getAll();
            if(lista != null) {
                vocabularyList.postValue(lista);
            }
        });
    }

    public void insertarVocabulary(@NonNull MutableLiveData<Resultado<List<Vocabulary>>> elResultado, @NonNull Vocabulary data) {
        ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(1);
        databaseWriteExecutor.execute(() -> {

            Resultado<List<Vocabulary>> resuVal = null;

            long maxid = vocabularyDao.getMaxId();
            data.setId(maxid + 1);
            long res = vocabularyDao.insert(data);

            List<Vocabulary> lista = new ArrayList<>();
            lista.add(data);
            if(res <= 0) {
                resuVal = new Resultado<List<Vocabulary>>(false, "No se registró el vocabulario", Resultado.TIPO_INSERCION, null);
            } else {
                resuVal = new Resultado<List<Vocabulary>>(true, "El vocabulario registró correctamente", Resultado.TIPO_INSERCION, lista);
            }

            elResultado.postValue(resuVal);

        });
    }

    public void eliminarVocabulary(Vocabulary data, @NonNull MutableLiveData<Resultado<List<Vocabulary>>> elResultado, @NonNull MutableLiveData<List<Vocabulary>> lista) {
        ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(1);
        databaseWriteExecutor.execute(() -> {
            Resultado<List<Vocabulary>> resuVal = null;
            List<Vocabulary> listaRes = lista.getValue();
            try {
                if (data == null || data.getId() == 0) {
                    try {
                        listaRes.remove(data);
                    } catch (Exception ex) {

                    }
                    resuVal = new Resultado<List<Vocabulary>>(false, "El vocabulario no se registró previamente", Resultado.TIPO_ELIMINACION, listaRes);
                } else {
                    long res = vocabularyDao.delete(data);
                    if (res > 0) {
                        listaRes.remove(data);
                        resuVal = new Resultado<List<Vocabulary>>(true, "El vocabulario se eliminó correctamente", Resultado.TIPO_ELIMINACION, listaRes);
                    } else {
                        resuVal = new Resultado<List<Vocabulary>>(false, "No se pudo eliminar el vocabulario", Resultado.TIPO_ELIMINACION, listaRes);
                    }
                }
                elResultado.postValue(resuVal);
            } catch (Exception exception) {}
        });
    }

}