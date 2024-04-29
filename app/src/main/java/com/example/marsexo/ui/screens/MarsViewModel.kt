
package com.example.marsexo.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsexo.data.MarsPhotosRepository
import com.example.marsexo.data.MarsPic
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MarsUiState {
    data class Success(val photos: List<MarsPic>) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

class MarsViewModel(private val marsPhotosRepository: MarsPhotosRepository) : ViewModel() {
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set
    init {
        getMarsPhotos()
    }

   // Mars photos information from the Mars API Retrofit
    fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState = MarsUiState.Loading
            marsUiState = try {
                MarsUiState.Success(marsPhotosRepository.getMarsPhotos())
            } catch (e: IOException) {
                MarsUiState.Error
            } catch (e: HttpException) {
                MarsUiState.Error
            }
        }
    }

   // MarsPhotosRepository
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsExoViewer)
                val marsPhotosRepository = application.container.marsPhotosRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }
}
