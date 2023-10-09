package be.christiano.portfolio.app.ui.main.introduction.sections

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.christiano.portfolio.app.R
import be.christiano.portfolio.app.data.models.Portfolio
import be.christiano.portfolio.app.ui.main.introduction.sections.components.ImageCard
import be.christiano.portfolio.app.ui.main.introduction.sections.components.SectionTitle
import be.christiano.portfolio.app.ui.theme.PortfolioTheme

@Composable
fun PortfolioSection(
    projects: List<Portfolio>,
    actionClicked: () -> Unit
) {
    Column {
        SectionTitle(
            modifier = Modifier.padding(start = 24.dp, end = 12.dp),
            title = stringResource(R.string.portfolio),
            subtitle = stringResource(R.string.portfolio_subtitle),
            actionText = stringResource(R.string.see_more),
            actionClicked = actionClicked
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(projects) {
                ImageCard(
                    Modifier
                        .fillParentMaxWidth()
                        .padding(horizontal = 8.dp),
                    it.image,
                    it.imageDesc,
                    it.title,
                    it.description,
                    tags = it.tags
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PortfolioSectionPreview() {
    PortfolioTheme {
        Surface {
            PortfolioSection(Portfolio.values().toList()) { }
        }
    }
}