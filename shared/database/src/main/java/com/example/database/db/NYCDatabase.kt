package com.example.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.database.db.satdata.SatData
import com.example.database.db.satdata.SatDataDao
import com.example.database.db.schooldata.SchoolData
import com.example.database.db.schooldata.SchoolDataDao

@Database(
    entities = [SchoolData::class, SatData::class], version = 1, exportSchema = false
)
abstract class NYCDatabase : RoomDatabase() {

    abstract val schoolDataDao: SchoolDataDao
    abstract val satDataDao: SatDataDao

    companion object {

        @Volatile
        private var instance: NYCDatabase? = null

        fun getInstance(context: Context): NYCDatabase {
            synchronized(this) {
                var mInstance = instance

                if (mInstance == null) {
                    mInstance = Room.databaseBuilder(
                        context.applicationContext, NYCDatabase::class.java, "nyc_database"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                    instance = mInstance
                }
                return mInstance
            }
        }
    }
}
