package com.data.core.remote.services

import com.data.core.remote.model.HeroApi
import com.data.core.remote.model.HeroStat
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface HeroesService {

    @GET("./heroes")
    fun getHeroes(): Deferred<List<HeroApi>>

    @GET("./heroStats")
    fun getHeroesStats(): Deferred<List<HeroStat>>
}