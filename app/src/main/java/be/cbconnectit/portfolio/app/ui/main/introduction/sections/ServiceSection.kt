package be.cbconnectit.portfolio.app.ui.main.introduction.sections

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.cbconnectit.portfolio.app.R
import be.cbconnectit.portfolio.app.domain.model.Service
import be.cbconnectit.portfolio.app.ui.main.introduction.sections.components.SectionTitle
import be.cbconnectit.portfolio.app.ui.main.introduction.sections.components.ServiceCard
import be.cbconnectit.portfolio.app.ui.theme.PortfolioTheme

@Composable
fun ServiceSection(
    services: List<Service>,
    headerActionClicked: () -> Unit,
    serviceActionClicked: (serviceId: String) -> Unit
) {
    Column {
        SectionTitle(
            modifier = Modifier.padding(start = 24.dp, end = 12.dp),
            title = stringResource(R.string.service),
            subtitle = stringResource(R.string.service_subtitle),
            actionText = stringResource(R.string.see_more),
            actionClicked = headerActionClicked
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier.padding(start = 16.dp, end = 12.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically)
        ) {
            services.forEach { service ->
                ServiceCard(service, serviceActionClicked)
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ServiceSectionPreview() {
    PortfolioTheme {
        Surface {
            ServiceSection(
                listOf(
                    Service(
                        "",
                        "",
                        "Mobile Development",
                        "Whether it's a new app or optimizing an existing one, I offer Mobile development expertise. Let's collaborate to make your mobile application a success in the Google Play or App Store.",
                        description = "",
                        updatedAt = "",
                        createdAt = ""
                    ),
                    Service(
                        "",
                        "",
                        "Web Development",
                        "From concept to deployment, I offer web development to transform your online ideas into reality. Let's collaborate to create a user-friendly and feature-rich website that meets your needs.",
                        description = "",
                        updatedAt = "",
                        createdAt = ""
                    ),
                    Service(
                        "",
                        "",
                        "Backend Development",
                        "Looking to establish a system for managing persistent data or serve as a vital intermediary between two systems? Together, we can explore the possbilities and craft a solution tailored to your needs.",
                        description = "",
                        updatedAt = "",
                        createdAt = ""
                    ),
                    Service(
                        "",
                        "",
                        "Tutoring",
                        "Looking to bring your (or your peer’s) skills to the next level, or just need someone to look into a bug/error? Let’s work together to maximize your potential and achieve your aspirations.",
                        description = "",
                        updatedAt = "",
                        createdAt = ""
                    ),
                ),
                {}
            ) { }
        }
    }
}