package be.cbconnectit.portfolio.app.ui.main.introduction.sections.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.cbconnectit.portfolio.app.R
import be.cbconnectit.portfolio.app.domain.model.Service
import be.cbconnectit.portfolio.app.domain.model.getServiceTypeIcon
import be.cbconnectit.portfolio.app.ui.theme.PortfolioTheme

@Composable
fun ServiceCard(
    service: Service,
    serviceActionClicked: (serviceId: String) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            service.getServiceTypeIcon()?.let {
                Icon(
                    modifier = Modifier,
                    painter = painterResource(id = it),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "${service.title} service icon"
                )
            }

            Spacer(Modifier.width(16.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = service.title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = service.shortDescription ?: "",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(
            modifier = Modifier.height(30.dp),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
            onClick = { serviceActionClicked(service.id) }
        ) {
            Text(style = MaterialTheme.typography.labelLarge, text = "Read more")

            Icon(modifier = Modifier.size(18.dp), painter = painterResource(id = R.drawable.ic_keyboard_arrow_right), contentDescription = "")
        }
    }
}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ServiceSectionPreview() {
    PortfolioTheme {
        Surface {
            ServiceCard(
                service = Service(
                    "",
                    "",
                    "Mobile Development",
                    "Whether it's a new app or optimizing an existing one, I offer Mobile development expertise. Let's collaborate to make your mobile application a success in the Google Play or App Store.",
                    description = "",
                    updatedAt = "",
                    createdAt = ""
                ),
                {}
            )
        }
    }
}