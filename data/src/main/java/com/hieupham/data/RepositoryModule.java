package com.hieupham.data;

import android.arch.persistence.room.Room;
import android.content.Context;
import com.hieupham.data.source.Mapper;
import com.hieupham.data.source.TransactionRepositoryImpl;
import com.hieupham.data.source.local.TransactionLocalDataSource;
import com.hieupham.data.source.local.api.DatabaseApi;
import com.hieupham.data.source.local.api.DatabaseApiImpl;
import com.hieupham.data.source.local.api.DatabaseManager;
import com.hieupham.data.source.remote.TransactionRemoteDataSource;
import com.hieupham.domain.repository.TransactionRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    TransactionRepository provideTransactionRepo(TransactionRemoteDataSource remoteDataSource,
            TransactionLocalDataSource localDataSource, Mapper mapper) {
        return new TransactionRepositoryImpl(remoteDataSource, localDataSource, mapper);
    }

    @Singleton
    @Provides
    DatabaseApi provideDatabaseApi(DatabaseManager databaseManager) {
        return new DatabaseApiImpl(databaseManager);
    }

    @Singleton
    @Provides
    DatabaseManager provideDatabaseManager(Context context) {
        return Room.databaseBuilder(context, DatabaseManager.class, DatabaseManager.DATABASE_NAME)
                .build();
    }
}
