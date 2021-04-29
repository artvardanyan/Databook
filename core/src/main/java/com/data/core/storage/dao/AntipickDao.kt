package com.data.core.storage.dao

import androidx.room.Dao
import androidx.room.Query
import com.data.core.storage.contracts.RoomContract
import com.data.core.storage.models.AntipickEntity

@Dao
interface AntipickDao {

    @Query("SELECT * FROM ${ RoomContract.tableAntipick} WHERE id = :id")
    fun fetchAntipicks(id: Int): List<AntipickEntity>

}