package com.example.enjoeielo7.di.modules

import com.example.enjoeielo7.network.LoginService
import com.example.enjoeielo7.network.UserService
import com.example.enjoeielo7.repository.ILoginRepository
import com.example.enjoeielo7.repository.IUserRepository
import com.example.enjoeielo7.repository.LoginRepositoryImpl
import com.example.enjoeielo7.repository.UserRepositoryImpl
import com.example.enjoeielo7.ui.screen.login.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideLoginRepositoryInstance(network: LoginService): ILoginRepository =
        LoginRepositoryImpl(networking = network)

    @Provides
    fun provideUserRepositoryInstance(network: UserService): IUserRepository =
        UserRepositoryImpl(networking = network)

    @Provides
    fun provideLoginServiceInstance(retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)

    @Provides
    fun provideUserServiceInstance(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {

        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .readTimeout(TIMEOUT, TimeUnit.MINUTES)
            .connectTimeout(TIMEOUT, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://github.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    companion object {
        private const val TIMEOUT = 1L
    }
}