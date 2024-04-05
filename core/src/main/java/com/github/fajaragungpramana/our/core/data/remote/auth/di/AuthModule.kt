package com.github.fajaragungpramana.our.core.data.remote.auth.di

import com.github.fajaragungpramana.our.core.data.local.CacheManager
import com.github.fajaragungpramana.our.core.data.remote.auth.AuthRepository
import com.github.fajaragungpramana.our.core.data.remote.auth.AuthService
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
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    fun provideAuthRepository(
        authService: AuthService,
        cacheManager: CacheManager
    ): IAuthRepository = AuthRepository(authService, cacheManager)

}