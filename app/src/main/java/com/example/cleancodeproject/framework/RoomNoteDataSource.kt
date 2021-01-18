package com.example.cleancodeproject.framework

import android.content.Context
import com.example.cleancodeproject.framework.db.DataBaseService
import com.example.cleancodeproject.framework.db.NoteEntity
import com.example.core.data.Note
import com.example.core.repository.NoteDataSource

class RoomNoteDataSource(context: Context): NoteDataSource {
    val noteDao = DataBaseService.getInstance(context).noteDao()
    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long) = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll() = noteDao.getAllNotesEntities().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
}