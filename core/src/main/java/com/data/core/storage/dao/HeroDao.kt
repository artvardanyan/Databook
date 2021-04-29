package com.data.core.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.data.core.storage.contracts.HeroSqlContract
import com.data.core.storage.models.HeroEntity

@Dao
interface HeroDao {

    @Query(HeroSqlContract.fetch)
    fun fetchHeroes(): List<HeroEntity>

    @Insert(entity = HeroEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insertHero(heroEntity: HeroEntity)
}