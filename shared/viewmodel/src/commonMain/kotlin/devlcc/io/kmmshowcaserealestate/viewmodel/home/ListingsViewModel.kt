package devlcc.io.kmmshowcaserealestate.viewmodel.home

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmm.viewmodel.stateIn
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesIgnore
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import devlcc.io.kmmshowcaserealestate.core.data.repository.PropertiesRepository
import devlcc.io.kmmshowcaserealestate.core.model.property.Property
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class ListingsViewModel(
    private val propertiesRepository: PropertiesRepository,
): KMMViewModel() {

    private val _uiState = MutableStateFlow<ListingsUiState>(ListingsUiState.initial())
    @NativeCoroutinesState
    val uiState: StateFlow<ListingsUiState> =
        _uiState.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            ListingsUiState.initial()
        )

    private val _uiEffect = MutableStateFlow<ListingsUiEffect?>(null)
    @NativeCoroutinesState
    val uiEffect: StateFlow<ListingsUiEffect?> =
        _uiEffect.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            null
        )

    init {
        viewModelScope.coroutineScope.launch {
            doFetchProperties()
        }
    }

    fun refresh() {
        viewModelScope.coroutineScope.launch {
            doFetchProperties()
        }
    }

    fun clearEffect(which: ListingsUiEffect) {
        if (_uiEffect.value == which) {
            _uiEffect.update { null }
        }
    }

    @NativeCoroutinesIgnore
    private suspend fun doFetchProperties() {
        viewModelScope.coroutineScope.launch {
            val propertiesForSale = async {
                propertiesRepository.getPropertiesByStatus(
                    status = Property.Status.ForSale,
                    offset = 0,
                    limit = 100,
                    sort = Property.Sort.Default
                )
            }
            val propertiesForRent = async {
                propertiesRepository.getPropertiesByStatus(
                    status = Property.Status.ForRent,
                    offset = 0,
                    limit = 100,
                    sort = Property.Sort.Default
                )
            }
            val propertiesSold = async {
                propertiesRepository.getPropertiesByStatus(
                    status = Property.Status.NotForSale,
                    offset = 0,
                    limit = 100,
                    sort = Property.Sort.Default
                )
            }

            val allProperties = awaitAll(propertiesForSale, propertiesForRent, propertiesSold)
                .reduce { acc, props -> acc + props }

            val vs = _uiState.value
            _uiState.value = vs.copy(
                isLoading = false,
                propertiesForSale = allProperties,
                propertiesForRent = allProperties,
                propertiesSold = allProperties,
            )

        }
    }

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