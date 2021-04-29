package com.data.databook.di

import android.app.Application
import com.data.core.storage.RoomAppDatabase

class App: Application() {

    companion object {
        lateinit var roomAppDatabase: RoomAppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        roomAppDatabase = RoomAppDatabase.buildDataSource(context = applicationContext)
    }
}