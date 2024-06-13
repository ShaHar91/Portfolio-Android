package be.cbconnectit.portfolio.app.ui.main.introduction.services.serviceDetail

import androidx.lifecycle.viewModelScope
import be.cbconnectit.portfolio.app.domain.model.Service
import be.cbconnectit.portfolio.app.domain.repository.ServiceRepository
import be.cbconnectit.portfolio.app.ui.base.BaseComposeViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ServiceDetailViewModel(
    private val serviceRepository: ServiceRepository,
    serviceId: String
) : BaseComposeViewModel() {

    private val _state = MutableStateFlow(ServiceDetailState())
    val state = _state.asStateFlow()

    private val _eventFlow = Channel<ServiceDetailUiEvent>()
    val eventFlow = _eventFlow.receiveAsFlow()

    init {
        fetchServiceDetailData()

        serviceRepository.findParentServiceName(serviceId).onEach { service ->
            _state.update { it.copy(parentService = service) }
        }.launchIn(viewModelScope)

        serviceRepository.findAllServices(serviceId).onEach { services ->
            _state.update { it.copy(services = services) }
        }.launchIn(viewModelScope)
    }

    private fun fetchServiceDetailData() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }

        val call = serviceRepository.fetchAllServices()
        if (call.isFailure) {
            call.exceptionOrNull()?.let {
                it.printStackTrace()
                showSnackbar(it.message)
            }
        }

        _state.update { it.copy(isLoading = false) }
    }

    fun onEvent(event: ServiceDetailEvent) = viewModelScope.launch {
        when (event) {
            is ServiceDetailEvent.OpenProjectByTag -> _eventFlow.send(ServiceDetailUiEvent.OpenProjectByTag(event.tagId))
        }
    }
}

sealed class ServiceDetailEvent {
    data class OpenProjectByTag(val tagId: String) : ServiceDetailEvent()
}

data class ServiceDetailState(
    val isLoading: Boolean = false,
    val services: List<Service> = emptyList(),
    val parentService: Service? = null
)

sealed class ServiceDetailUiEvent {
    data class OpenProjectByTag(val tagId: String) : ServiceDetailUiEvent()
}