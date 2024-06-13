package be.cbconnectit.portfolio.app.ui.main.introduction.services.serviceDetail

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import be.cbconnectit.portfolio.app.domain.model.Service
import be.cbconnectit.portfolio.app.domain.model.previewData
import be.cbconnectit.portfolio.app.ui.components.DefaultAppBar
import be.cbconnectit.portfolio.app.ui.main.destinations.PortfolioScreenDestination
import be.cbconnectit.portfolio.app.ui.main.introduction.services.components.ServiceHeader
import be.cbconnectit.portfolio.app.ui.main.introduction.services.components.ServiceItem
import be.cbconnectit.portfolio.app.ui.theme.PortfolioTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate
import dev.jeziellago.compose.markdowntext.MarkdownText
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Destination
@Composable
fun ServiceDetailScreen(
    navController: NavController,
    serviceId: String,
    viewModel: ServiceDetailViewModel = koinViewModel {
        parametersOf(serviceId)
    }
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is ServiceDetailUiEvent.OpenProjectByTag -> navController.navigate(PortfolioScreenDestination(arrayOf(event.tagId)))
            }
        }
    }
    ServiceDetailScreenContent(
        state = state,
        navController = navController,
        onEvent = viewModel::onEvent
    ) { viewModel.CreateSnackBarHost() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceDetailScreenContent(
    state: ServiceDetailState,
    navController: NavController,
    onEvent: (ServiceDetailEvent) -> Unit,
    createSnackBarHost: @Composable () -> Unit = {},
) {
    val scrollState = rememberScrollState()

    Box(contentAlignment = Alignment.BottomCenter) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { DefaultAppBar(navController = navController, appBarTitle = "") },
            snackbarHost = { createSnackBarHost() }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(scrollState)
            ) {
                // TODO: see if this header can be placed inside a Collapsing Toolbar
                ServiceHeader(
                    title = state.parentService?.title ?: "",
                    text = state.parentService?.bannerDescription ?: "",
                    imageUrl = state.parentService?.imageUrl
                )

                state.services.forEachIndexed { index, service ->
                    ServiceItem(service = service, shouldColorBackground = index % 2 == 1) {
                        service.tag?.let {
                            onEvent(ServiceDetailEvent.OpenProjectByTag(it.id))
                        }
                    }
                }

                val extraInfo = state.parentService?.extraInfo
                if (!extraInfo.isNullOrEmpty()) {

                    Spacer(modifier = Modifier.height(48.dp))

                    MarkdownText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        markdown = extraInfo,
                        style = MaterialTheme.typography.bodyLarge,
                        linkColor = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        AnimatedVisibility(visible = state.isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ServiceDetailScreenContentPreview() {
    PortfolioTheme {
        ServiceDetailScreenContent(
            navController = rememberNavController(),
            state = ServiceDetailState(services = listOf(Service.previewData())),
            onEvent = {}
        )
    }
}
