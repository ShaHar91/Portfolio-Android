package be.cbconnectit.portfolio.app.ui.main.introduction.services

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import be.cbconnectit.portfolio.app.R
import be.cbconnectit.portfolio.app.domain.model.Service
import be.cbconnectit.portfolio.app.domain.model.previewData
import be.cbconnectit.portfolio.app.ui.components.DefaultAppBar
import be.cbconnectit.portfolio.app.ui.main.destinations.ServiceDetailScreenDestination
import be.cbconnectit.portfolio.app.ui.main.introduction.services.components.ServiceHeader
import be.cbconnectit.portfolio.app.ui.main.introduction.services.components.ServiceItem
import be.cbconnectit.portfolio.app.ui.theme.PortfolioTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun ServicesScreen(
    navController: NavController,
    viewModel: ServicesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is ServicesUiEvent.OpenServiceDetail -> navController.navigate(ServiceDetailScreenDestination(event.serviceId))
            }
        }
    }

    ServicesScreenContent(
        state = state,
        navController = navController,
        onEvent = viewModel::onEvent,
    ) { viewModel.CreateSnackBarHost() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServicesScreenContent(
    state: ServicesState,
    navController: NavController,
    onEvent: (ServicesEvent) -> Unit,
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
                    title = "My Services",
                    text = stringResource(R.string.my_services_description)
                )

                state.services.forEachIndexed { index, service ->
                    ServiceItem(service = service, shouldColorBackground = index % 2 == 1) {
                        onEvent(ServicesEvent.OpenServiceDetail(service.id))
                    }
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
fun ServicesScreenContentPreview() {
    PortfolioTheme {
        ServicesScreenContent(
            navController = rememberNavController(),
            state = ServicesState(services = listOf(Service.previewData())),
            onEvent = {}
        )
    }
}
