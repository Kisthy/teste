package com.github.kisthy.gs2.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.github.kisthy.gs2.database.repository.EcoTipEntity

@Dao
interface EcoTipDao {
    @Query("SELECT * FROM EcoTipEntity")
    suspend fun getAllTips(): List<EcoTipEntity>

    @Insert
    suspend fun insertTip(tip: EcoTipEntity)
}
