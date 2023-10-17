package com.lenguage.englisheverydayvocabsapp.data.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.lenguage.englisheverydayvocabsapp.data.dao.VocabularyDao;
import com.lenguage.englisheverydayvocabsapp.data.entity.Vocabulary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Vocabulary.class}, version = 1, exportSchema = false)
public abstract class DataBaseDefinition extends RoomDatabase {

    private static volatile DataBaseDefinition INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public abstract VocabularyDao getVocabularyDao();

    public static DataBaseDefinition getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DataBaseDefinition.class) {
                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DataBaseDefinition.class, "english_ev_vocabs_db")
                            .fallbackToDestructiveMigration()
                            //.fallbackToDestructiveMigrationFrom(13)
                            //.addMigrations(MIGRATION_12_13)
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block


            //actualizarClientesDeVentaYCobroYDatosIniciales();
            //if(db.getVersion() <= 1) {
                //actualizarRegistros();
            //}
            //if(db.getVersion() >= 8) {
            //    cargarParametrosFtp();
            //}

        }

    };


    @Override
    public void clearAllTables() {

    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(@NonNull DatabaseConfiguration databaseConfiguration) {
        return null;
    }
}
