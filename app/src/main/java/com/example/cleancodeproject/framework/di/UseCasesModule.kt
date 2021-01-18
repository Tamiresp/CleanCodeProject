package com.example.cleancodeproject.framework.di

import com.example.cleancodeproject.framework.UseCases
import com.example.core.repository.NoteRepository
import com.example.core.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun getUseCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository),
        GetWordCount()
    )
}