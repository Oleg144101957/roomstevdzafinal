package com.example.roomstevdzafinal.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomstevdzafinal.models.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteRoomDb : RoomDatabase(){

    abstract fun noteDao() : NoteDao

    companion object {

        @Volatile
        private var INSTANCE : NoteRoomDb? = null

        fun getNoteDatabase(context: Context) : NoteRoomDb {
            val tempDb = INSTANCE
            if (tempDb != null) return tempDb

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteRoomDb::class.java,
                    "notes_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}