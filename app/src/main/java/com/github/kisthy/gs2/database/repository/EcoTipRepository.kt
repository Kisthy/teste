package com.github.kisthy.gs2.database.repository

import androidx.room.Room
import com.github.kisthy.gs2.database.dao.EcoTipsDatabase
import android.content.Context

class EcoTipRepository(private val context: Context) {
    private val database: EcoTipsDatabase = Room.databaseBuilder(
        context.applicationContext,
        EcoTipsDatabase::class.java,
        "eco_tips_database"
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration().build()

    private val dao = database.ecoTipDao()

    suspend fun getAllTips() = dao.getAllTips()
    suspend fun insertTip(tip: EcoTipEntity) = dao.insertTip(tip)
}