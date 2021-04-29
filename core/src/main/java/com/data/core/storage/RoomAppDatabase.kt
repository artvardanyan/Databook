package com.data.core.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.data.core.storage.contracts.RoomContract
import com.data.core.storage.dao.AntipickDao
import com.data.core.storage.dao.HeroDao
import com.data.core.storage.models.AntipickEntity
import com.data.core.storage.models.HeroEntity

@Database(entities = [AntipickEntity::class, HeroEntity::class], version = 1)
public abstract class RoomAppDatabase: RoomDatabase() {

    abstract fun heroDao(): HeroDao
    abstract fun antipickDao(): AntipickDao

    companion object {

        fun buildDataSource(context: Context): RoomAppDatabase = Room.databaseBuilder(
            context, RoomAppDatabase::class.java, RoomContract.databaseApp)
            .build()
    }
}