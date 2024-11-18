package com.github.kisthy.gs2.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kisthy.gs2.model.EcoTip
import com.github.kisthy.gs2.database.repository.EcoTipEntity
import com.github.kisthy.gs2.database.repository.EcoTipRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EcoTipsScreenViewModel(private val repository: EcoTipRepository) : ViewModel() {

    private val _tips = MutableStateFlow<List<EcoTip>>(emptyList())
    val tips: StateFlow<List<EcoTip>> = _tips

    init {
        loadTips()
    }

    private fun loadTips() {
        viewModelScope.launch {
            val tips = repository.getAllTips().map {
                EcoTip(it.id, it.title, it.description)
            }
            _tips.value = tips
        }
    }

    fun addTip(title: String, description: String) {
        viewModelScope.launch {
            repository.insertTip(EcoTipEntity(title = title, description = description))
            loadTips()
        }
    }
}