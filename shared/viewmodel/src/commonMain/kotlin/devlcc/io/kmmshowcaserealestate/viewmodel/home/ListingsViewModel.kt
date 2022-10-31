package devlcc.io.kmmshowcaserealestate.viewmodel.home

import devlcc.io.kmmshowcaserealestate.core.data.repository.PropertiesRepository
import devlcc.io.kmmshowcaserealestate.core.model.property.Property
import devlcc.io.kmmshowcaserealestate.viewmodel.ViewModel
import kotlinx.coroutines.flow.*

class ListingsViewModel(
    private val propertiesRepository: PropertiesRepository,
): ViewModel() {

    private val _uiState = MutableStateFlow<ListingsUiState>(ListingsUiState.initial())
    val uiState: StateFlow<ListingsUiState> =
        _uiState.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _uiState.value
        )

     private val _uiEffect = MutableSharedFlow<ListingsUiEffect>(extraBufferCapacity = 32)
    val uiEffect: SharedFlow<ListingsUiEffect> = _uiEffect.asSharedFlow()

}

sealed class ListingsIntent {
    object Refresh: ListingsIntent()
}

data class ListingsUiState(
    val isLoading: Boolean,
    val propertiesForSale: List<Property> = listOf(),
    val propertiesForRent: List<Property> = listOf(),
    val propertiesSold: List<Property> = listOf(),
) {
    companion object {
        fun initial(): ListingsUiState =
            ListingsUiState(isLoading = true)
    }
}

sealed class ListingsUiEffect {
    // TODO::
}