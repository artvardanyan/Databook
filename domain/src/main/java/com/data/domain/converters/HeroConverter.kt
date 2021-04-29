package com.data.domain.converters

import com.data.core.remote.model.HeroApi
import com.data.core.remote.model.HeroStat
import com.data.core.storage.models.HeroEntity
import com.data.domain.models.Hero

interface HeroesConverter {
    fun convertFromApiToDomain(heroApi: HeroApi, heroStat: HeroStat): Hero
    fun convertFromApiToDB(heroApi: HeroApi, heroStat: HeroStat): HeroEntity
    fun convertFromDbToDomain(heroEntity: HeroEntity): Hero
}