package com.data.domain

import com.data.domain.models.Hero
import kotlinx.coroutines.Deferred

interface CarryRepository {
    suspend fun fetchCarries(): Deferred<List<Hero>>
    suspend fun fetchLocalCarries(): Deferred<List<Hero>>
}