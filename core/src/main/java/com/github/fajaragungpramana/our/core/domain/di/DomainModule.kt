package com.github.fajaragungpramana.our.core.domain.di

import com.github.fajaragungpramana.our.core.data.remote.auth.IAuthRepository
import com.github.fajaragungpramana.our.core.data.remote.story.IStoryRepository
import com.github.fajaragungpramana.our.core.domain.auth.AuthInteractor
import com.github.fajaragungpramana.our.core.domain.auth.AuthUseCase
import com.github.fajaragungpramana.our.core.domain.story.StoryInteractor
import com.github.fajaragungpramana.our.core.domain.story.StoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideAuthUseCase(authRepository: IAuthRepository): AuthUseCase =
        AuthInteractor(authRepository)

    @Provides
    fun provideStoryUseCase(storyRepository: IStoryRepository): StoryUseCase =
        StoryInteractor(storyRepository)

}