package com.data.core.remote.implementations

import com.data.core.remote.HeroesProvider
import com.data.core.remote.helpers.RetrofitFactory
import com.data.core.remote.model.HeroApi
import com.data.core.remote.model.HeroStat
import kotlinx.coroutines.Deferred

class HeroesProviderImpl : HeroesProvider {
    private val TAG = HeroesProviderImpl::class.java.simpleName

    override suspend fun fetchHeroes(): Deferred<List<HeroApi>> {
        return RetrofitFactory.getHeroesService().getHeroes()
    }

    override suspend fun fetchHeroStats(): Deferred<List<HeroStat>> {
        return RetrofitFactory.getHeroesService().getHeroesStats()
    }
}