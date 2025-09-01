package dev.himanshu.search.ui

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import dev.himanshu.search.domain.useCases.SearchUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update


class SearchViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(viewModelScope, SearchUiState())

    @NativeCoroutinesState
    val uiState: StateFlow<SearchUiState> = _uiState

    private val _query = MutableStateFlow(viewModelScope, "")

    @NativeCoroutinesState
    val query: StateFlow<String> = _query


    fun updateQuery(q: String) {
        _query.update { q }
    }

    init {
        viewModelScope.launch {
            _query.filter { it.isNotEmpty() }
                .debounce(500)
                .distinctUntilChanged()
                .collectLatest { query ->
                    _uiState.update { SearchUiState(isLoading = true) }
                    searchUseCase.execute(query)
                        .onSuccess { data ->
                            _uiState.update { SearchUiState(data = data) }
                        }.onFailure { error ->
                            _uiState.update { SearchUiState(error = error.message.toString()) }
                        }
                }
        }
    }


}