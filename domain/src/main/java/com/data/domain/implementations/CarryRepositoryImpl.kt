package com.data.domain.implementations

import com.data.core.remote.HeroesProvider
import com.data.core.remote.helpers.RetrofitFactory
import com.data.core.storage.RoomAppDatabase
import com.data.domain.CarryRepository
import com.data.domain.converters.HeroesConverter
import com.data.domain.models.Hero
import com.data.domain.models.HeroType
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.lang.Exception

class CarryRepositoryImpl(val roomAppDatabase: RoomAppDatabase, val heroesProvider: HeroesProvider,
                          val heroesConverter: HeroesConverter) : CarryRepository {

    override suspend fun fetchLocalCarries(): Deferred<List<Hero>> {
        return GlobalScope.async {
            roomAppDatabase.heroDao().fetchHeroes().map {
                heroesConverter.convertFromDbToDomain(heroEntity = it)
            }
        }
    }

    override suspend fun fetchCarries(): Deferred<List<Hero>> {
        try {
            val heroes = heroesProvider.fetchHeroes().await()
            val stats = heroesProvider.fetchHeroStats().await()

            return GlobalScope.async {
                stats.map {
                    it.icon = "${RetrofitFactory.baseImg}${it.icon}"
                    it.img = "${RetrofitFactory.baseImg}${it.img}"
                    it
                }

                heroes.map { heroApi ->
                    val heroStat = stats.first { stat -> stat.id == heroApi.id }

                    roomAppDatabase.heroDao().insertHero(heroesConverter
                        .convertFromApiToDB(heroApi = heroApi, heroStat = heroStat))
                    heroesConverter.convertFromApiToDomain(
                        heroApi = heroApi,
                        heroStat = heroStat)
                }
                    .filter { it.heroType == HeroType.Carry }
            }
        } catch (e: Exception) {
            return GlobalScope.async { error(e) }
        }
    }
}