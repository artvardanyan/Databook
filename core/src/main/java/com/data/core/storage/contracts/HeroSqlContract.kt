package com.data.core.storage.contracts

class HeroSqlContract {

    companion object {
        const val fetch = "SELECT * FROM ${RoomContract.tableHero}"
    }
}