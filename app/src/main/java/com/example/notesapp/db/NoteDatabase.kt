package com.example.notesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/* Database class should be abstract and annotated with @Database and pass all entities as an array
  so use [] brackets and specify the version number, in future if you want to change the DB schema
  and change the version number and create migration class to change database structure */
@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase():RoomDatabase() { // Must Inherit from RoomDatabase
    abstract fun getNoteDao() : NoteDao // Need this function to get the Dao for the Entity
    // Build RoomDB
    companion object {
         //  means that this field is immediately made visible to other threads
        @Volatile private var instance : NoteDatabase? = null
        private val LOCK = Any() // The root of the Kotlin class hierarchy. Every Kotlin class has [Any] as a superclass.
        /*  Help of ?: elvis operator check if the instance is not null return the instance,
            if it is null then synchronized block will  work, inside this also check null or not and call the function buildDatabase*/
            operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
           // Create a instance also return the instance
            instance ?: buildDatabase(context).also {
                    instance = it
            }
        }
        // The above code similar like below
     /*   fun getDatabase(context: Context) : NoteDatabase? {
            synchronized(LOCK) {
                if (instance == null)
                    instance = buildDatabase(context)
            }
            return instance
        }*/
        /*About invoke operator function : An interesting feature of the Kotlin language is the ability to define an "invoke operator".
        When you specify an invoke operator on a class, it can be called on any instances of the class
        without a method name! This trick seems especially useful for classes that really have one method to be used.
        If it is  static, can directly call with the class name*/

        // Function to build database
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "notedatabase"
        ).build()
    }
}