package be.cbconnectit.portfolio.app.ui.main.introduction

import androidx.lifecycle.viewModelScope
import be.cbconnectit.portfolio.app.domain.enums.Social
import be.cbconnectit.portfolio.app.domain.model.Experience
import be.cbconnectit.portfolio.app.domain.model.Link
import be.cbconnectit.portfolio.app.domain.model.Service
import be.cbconnectit.portfolio.app.domain.model.Testimonial
import be.cbconnectit.portfolio.app.domain.model.Work
import be.cbconnectit.portfolio.app.domain.repository.ExperienceRepository
import be.cbconnectit.portfolio.app.domain.repository.ServiceRepository
import be.cbconnectit.portfolio.app.domain.repository.TestimonialRepository
import be.cbconnectit.portfolio.app.domain.repository.WorkRepository
import be.cbconnectit.portfolio.app.ui.base.BaseComposeViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDate
import java.time.Month

class IntroductionViewModel(
    private val serviceRepo: ServiceRepository,
    private val experienceRepo: ExperienceRepository,
    private val workRepository: WorkRepository,
    private val testimonialRepository: TestimonialRepository
) : BaseComposeViewModel() {

    private val _state = MutableStateFlow(
        IntroductionState(
            socialLinks = Social.entries.map { Link(type = it.type, url = it.link) },
            experienceInYears = getUpdateExperienceInYears()
        )
    )
    val state = _state.asStateFlow()

    private val _eventFlow = Channel<IntroductionUiEvent>()
    val eventFlow = _eventFlow.receiveAsFlow()

    init {
        fetchAllData()

        serviceRepo.findAllServices().onEach { services ->
            _state.update { it.copy(services = services) }
        }.launchIn(viewModelScope)

        experienceRepo.findAllExperiences().onEach { experiences ->
            _state.update { it.copy(experiences = experiences) }
        }.launchIn(viewModelScope)

        workRepository.findAllWorks().onEach { works ->
            _state.update {
                it.copy(projects = works, selectedProject = it.selectedProject ?: works.firstOrNull())
            }
        }.launchIn(viewModelScope)

        testimonialRepository.findAllTestimonials().onEach { testimonials ->
            _state.update { it.copy(testimonials = testimonials) }
        }.launchIn(viewModelScope)
    }

    private fun fetchAllData() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }

        val servicesAsync = async { serviceRepo.fetchAllServices() }
        val experiencesAsync = async { experienceRepo.fetchAllExperiences() }
        val worksAsync = async { workRepository.fetchAllWorks() }
        val testimonialsAsync = async { testimonialRepository.fetchAllTestimonials() }

        val services = servicesAsync.await()
        val experiences = experiencesAsync.await()
        val works = worksAsync.await()
        val testimonials = testimonialsAsync.await()

        val calls = listOf(services, experiences, works, testimonials)
        if (calls.any { it.isFailure }) {
            calls.first { it.isFailure }.exceptionOrNull()?.let {
                it.printStackTrace()
                showSnackbar(it.message)
            }
        }

        _state.update { it.copy(isLoading = false) }
    }

    private fun getUpdateExperienceInYears(): Int {
        val startDate = LocalDate.of(2017, Month.NOVEMBER, 1)
        val currentDate = LocalDate.now()
        val yearsBetween = Duration.between(startDate.atStartOfDay(), currentDate.atStartOfDay()).toDays() / 365

        return yearsBetween.toInt()
    }

    fun onEvent(event: IntroductionEvent) = viewModelScope.launch {
        when (event) {
            is IntroductionEvent.OpenSocialLink -> _eventFlow.send(IntroductionUiEvent.OpenSocialLink(event.link))
            is IntroductionEvent.OpenMailClient -> _eventFlow.send(IntroductionUiEvent.OpenMailClient)
            is IntroductionEvent.OpenServiceList -> _eventFlow.send(IntroductionUiEvent.OpenServiceList)
            is IntroductionEvent.OpenServiceDetail -> _eventFlow.send(IntroductionUiEvent.OpenServiceDetail(event.serviceId))
            is IntroductionEvent.OpenPortfolioList -> _eventFlow.send(IntroductionUiEvent.OpenPortfolio)
            is IntroductionEvent.OpenTestimonialsList -> showSnackbar("In Development!")
            is IntroductionEvent.OpenExperiencesList -> _eventFlow.send(IntroductionUiEvent.OpenExperienceList)
            is IntroductionEvent.UpdateSelectedWork -> _state.update {
                it.copy(selectedProject = event.work)
            }
        }
    }
}

sealed class IntroductionEvent {
    data class OpenSocialLink(val link: Link) : IntroductionEvent()
    data object OpenMailClient : IntroductionEvent()
    data object OpenServiceList : IntroductionEvent()
    data class OpenServiceDetail(val serviceId: String) : IntroductionEvent()
    data object OpenPortfolioList : IntroductionEvent()
    data class UpdateSelectedWork(val work: Work) : IntroductionEvent()
    data object OpenTestimonialsList : IntroductionEvent()
    data object OpenExperiencesList : IntroductionEvent()
}

data class IntroductionState(
    val isLoading: Boolean = false,
    val socialLinks: List<Link> = emptyList(),
    val experienceInYears: Int = 0,
    val services: List<Service> = emptyList(),
    val projects: List<Work> = emptyList(),
    val testimonials: List<Testimonial> = emptyList(),
    val experiences: List<Experience> = emptyList(),
    val selectedProject: Work? = null
)

sealed class IntroductionUiEvent {
    data class OpenSocialLink(val link: Link) : IntroductionUiEvent()
    data object OpenMailClient : IntroductionUiEvent()
    data object OpenExperienceList : IntroductionUiEvent()
    data object OpenPortfolio : IntroductionUiEvent()
    data object OpenServiceList : IntroductionUiEvent()
    data class OpenServiceDetail(val serviceId: String) : IntroductionUiEvent()
}