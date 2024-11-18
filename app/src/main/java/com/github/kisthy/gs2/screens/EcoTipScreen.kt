package com.github.kisthy.gs2.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.kisthy.gs2.model.EcoTip
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField

@Composable
fun EcoTipScreen(viewModel: EcoTipsScreenViewModel) {
    val tips by viewModel.tips.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var newTipTitle by remember { mutableStateOf("") }
    var newTipDescription by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("EcoDicas - Matheus RM96272 e João RM93076") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Pesquisar") },
                modifier = Modifier.fillMaxWidth()
            )
            EcoTipList(
                tips = tips.filter {
                    it.title.contains(searchQuery, ignoreCase = true) ||
                            it.description.contains(searchQuery, ignoreCase = true)
                }
            )
        }

        // Exibe o diálogo se `showDialog` for verdadeiro
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Nova EcoDica") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = newTipTitle,
                            onValueChange = { newTipTitle = it },
                            label = { Text("Título") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = newTipDescription,
                            onValueChange = { newTipDescription = it },
                            label = { Text("Descrição") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        // Adiciona a nova dica e fecha o diálogo
                        if (newTipTitle.isNotBlank() && newTipDescription.isNotBlank()) {
                            viewModel.addTip(newTipTitle, newTipDescription)
                            newTipTitle = ""
                            newTipDescription = ""
                            showDialog = false
                        }
                    }) {
                        Text("Salvar")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}



@Composable
fun EcoTipList(tips: List<EcoTip>) {
    LazyColumn {
        items(tips) { tip ->
            EcoTipItem(tip)
        }
    }
}

@Composable
fun EcoTipItem(tip: EcoTip) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = tip.title, style = MaterialTheme.typography.bodySmall)
            Text(text = tip.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
