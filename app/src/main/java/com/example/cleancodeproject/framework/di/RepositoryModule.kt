package com.example.cleancodeproject.framework.di

import android.app.Application
import com.example.cleancodeproject.framework.RoomNoteDataSource
import com.example.core.repository.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))
}