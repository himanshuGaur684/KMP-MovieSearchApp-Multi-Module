package dev.himanshu.details.ui

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import dev.himanshu.details.domain.useCases.GetMovieDetailsUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DetailsViewModel(
    private val detailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow(viewModelScope, DetailsUiState())

    @NativeCoroutinesState
    val uiState: StateFlow<DetailsUiState> = _uiState


    fun getMovies(id: String) = viewModelScope.launch {
        _uiState.update { DetailsUiState(isLoading = true) }
        detailsUseCase.execute(id).onSuccess { data ->
            _uiState.update { DetailsUiState(movieDetails = data) }
        }.onFailure { error ->
            _uiState.update { DetailsUiState(error = error.message.toString()) }
        }
    }
}
