package com.data.core.remote.model

import kotlinx.serialization.Serializable


@Serializable
data class HeroStat(val id: Int, var img: String, var icon: String)