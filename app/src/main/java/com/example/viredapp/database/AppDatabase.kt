package com.example.viredapp.database

import android.arch.persistence.room.*
import android.content.Context
import android.view.ViewDebug
import com.example.viredapp.db.*
import com.example.viredapp.utilities.DateConverter
@TypeConverters(DateConverter::class)
@Database(entities = [feed::class,
                Friends::class,
                Profile::class,
                Request::class,
                Likes::class],version = 1,
        exportSchema = false)

public abstract class AppDatabase: RoomDatabase() {
    public abstract fun feedDao():FeedDao
    public abstract fun requestDao():RequestDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }



        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext, AppDatabase::class.java,"AppDB.db")
                        .build()
        }
    }

//    fun getDatabase(context: Context): AppDatabase? {
//        if(INSTANCE == null){
//            synchronized(AppDatabase::class){
//                if(INSTANCE==null){
//                    INSTANCE = Room.databaseBuilder(context.applicationContext,
//                            AppDatabase::class.java,"FeedDatabase")
//                            .build()
//                }
//            }
//        }
//        return INSTANCE
//    }
