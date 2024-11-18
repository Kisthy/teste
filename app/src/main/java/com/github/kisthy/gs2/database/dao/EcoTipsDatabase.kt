package com.github.kisthy.gs2.database.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.kisthy.gs2.database.repository.EcoTipEntity

@Database(entities = [EcoTipEntity::class], version = 2)
abstract class EcoTipsDatabase  : RoomDatabase() {
    abstract fun ecoTipDao(): EcoTipDao
}
