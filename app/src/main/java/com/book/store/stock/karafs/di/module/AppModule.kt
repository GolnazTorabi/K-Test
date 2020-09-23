package com.book.store.stock.karafs.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.book.store.stock.karafs.data.DB.AppDatabase
import com.book.store.stock.karafs.data.DB.UserDao
import com.book.store.stock.karafs.data.net.ApiInterface
import com.book.store.stock.karafs.data.repository.UserRepository
import com.book.store.stock.karafs.data.repository.UserRepositoryImpl
import com.book.store.stock.karafs.di.component.ViewModelSubComponent
import com.book.store.stock.karafs.di.factory.ViewModelFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module(subcomponents = [ViewModelSubComponent::class])
internal class AppModule {
    @Inject
    @Singleton
    @Provides
    fun provideApiClient(): ApiInterface {
        val builder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder.interceptors().add(logging)
        builder.connectTimeout(60, TimeUnit.SECONDS)
        builder.readTimeout(60, TimeUnit.SECONDS)
        builder.writeTimeout(60, TimeUnit.SECONDS)
        return Retrofit.Builder()
            .baseUrl("http://karafsapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())
            .build()
            .create(ApiInterface::class.java)
    }


    @Provides
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }


    @Provides
    fun provideViewModelFactory(viewModelSubComponent: ViewModelSubComponent.Builder): ViewModelProvider.Factory {
        return ViewModelFactory(viewModelSubComponent.build())
    }


    @Singleton
    @Provides
    fun provideRoomDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            "Karafs-Database"
        )
            .allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideUserRepository(repo: UserRepositoryImpl): UserRepository = repo


    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }


}