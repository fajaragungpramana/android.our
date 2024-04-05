package com.github.fajaragungpramana.our.core.data.remote.auth.di

import com.github.fajaragungpramana.our.core.data.local.CacheManager
import com.github.fajaragungpramana.our.core.data.remote.auth.AuthRepository
import com.github.fajaragungpramana.our.core.data.remote.auth.IAuthDataSource
import com.github.fajaragungpramana.our.core.data.remote.auth.IAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    fun provideAuthDataSource(retrofit: Retrofit): IAuthDataSource =
        retrofit.create(IAuthDataSource::class.java)

    @Provides
    fun provideAuthRepository(
        authDataSource: IAuthDataSource,
        cacheManager: CacheManager
    ): IAuthRepository = AuthRepository(authDataSource, cacheManager)

}