@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.marsexo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.marsexo.R
import com.example.marsexo.ui.screens.HomeScreen
import com.example.marsexo.ui.screens.MarsViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.marsexo.ui.theme.backgroundColor


@Composable
fun MarsApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { MarsTopAppBar(scrollBehavior = scrollBehavior) }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val marsViewModel: MarsViewModel =
                viewModel(factory = MarsViewModel.Factory)
            HomeScreen(
                marsUiState = marsViewModel.marsUiState,
                retryAction = marsViewModel::getMarsPhotos,
                contentPadding = it
            )
        }
    }
}


@Composable
fun MarsTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) { // out
    Surface(
        color = backgroundColor, //  custom background color to the Surface
        modifier = Modifier.fillMaxWidth()
    ) {
        CenterAlignedTopAppBar(
            scrollBehavior = scrollBehavior,
            title = {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(Color.DarkGray)
                ) {
                    Text(
                        text = stringResource(R.string.username), // design
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontFamily = FontFamily.Monospace,
                    )
                }
            },
            modifier = modifier
        )
    }
}
