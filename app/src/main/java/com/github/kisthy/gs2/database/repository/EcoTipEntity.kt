package com.github.kisthy.gs2.database.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EcoTipEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String
)