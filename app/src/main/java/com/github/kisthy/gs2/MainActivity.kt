package com.github.kisthy.gs2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.kisthy.gs2.database.repository.EcoTipRepository
import com.github.kisthy.gs2.screens.EcoTipScreen
import com.github.kisthy.gs2.screens.EcoTipsScreenViewModel
import com.github.kisthy.gs2.ui.theme.Matheus_RM96272Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = EcoTipRepository(applicationContext) // Certifique-se de inicializar corretamente o repositório.
        val viewModel = EcoTipsScreenViewModel(repository)
        setContent {
            Matheus_RM96272Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    EcoTipScreen(viewModel = viewModel)
                }
            }
        }
    }
}
