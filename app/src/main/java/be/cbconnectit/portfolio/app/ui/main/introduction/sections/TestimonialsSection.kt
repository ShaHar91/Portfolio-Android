package be.cbconnectit.portfolio.app.ui.main.introduction.sections

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.cbconnectit.portfolio.app.R
import be.cbconnectit.portfolio.app.domain.model.Testimonial
import be.cbconnectit.portfolio.app.domain.model.previewData
import be.cbconnectit.portfolio.app.ui.main.introduction.sections.components.SectionTitle
import be.cbconnectit.portfolio.app.ui.main.introduction.sections.components.TestimonialCard
import be.cbconnectit.portfolio.app.ui.theme.PortfolioTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TestimonialsSection(
    testimonials: List<Testimonial>,
    actionClicked: () -> Unit
) {
    Column {
        SectionTitle(
            modifier = Modifier.padding(start = 24.dp, end = 12.dp),
            title = stringResource(R.string.testimonials),
            subtitle = stringResource(R.string.what_they_say),
//            actionText = stringResource(R.string.see_more),
//            actionClicked = actionClicked,
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            testimonials.forEach {
                TestimonialCard(
                    image = it.imageUrl,
                    name = it.fullName,
                    subtitle = it.styledSubtitle(MaterialTheme.colorScheme.outline, stringResource(id = R.string.at)),
                    review = it.review
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TestimonialsSectionPreview() {
    PortfolioTheme {
        Surface {
            TestimonialsSection(listOf(Testimonial.previewData(), Testimonial.previewData())) { }
        }
    }
}