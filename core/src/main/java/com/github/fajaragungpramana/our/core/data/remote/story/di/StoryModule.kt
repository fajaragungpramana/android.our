package com.github.fajaragungpramana.our.core.data.remote.story.di

import com.github.fajaragungpramana.our.core.data.remote.story.IStoryDataSource
import com.github.fajaragungpramana.our.core.data.remote.story.IStoryRepository
import com.github.fajaragungpramana.our.core.data.remote.story.StoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object StoryModule {

    @Provides
    fun provideStoryDataSource(retrofit: Retrofit): IStoryDataSource =
        retrofit.create(IStoryDataSource::class.java)

    @Provides
    fun provideStoryRepository(storyDataSource: IStoryDataSource): IStoryRepository =
        StoryRepository(storyDataSource)

}