package com.data.core.remote

import com.data.core.remote.model.HeroApi
import com.data.core.remote.model.HeroStat
import kotlinx.coroutines.Deferred

interface HeroesProvider {
    suspend fun fetchHeroes(): Deferred<List<HeroApi>>
    suspend fun fetchHeroStats(): Deferred<List<HeroStat>>
}