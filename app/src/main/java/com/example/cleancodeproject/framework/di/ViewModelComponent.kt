package com.example.cleancodeproject.framework.di

import com.example.cleancodeproject.framework.ListViewModel
import com.example.cleancodeproject.framework.NoteViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)
}