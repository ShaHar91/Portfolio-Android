package be.cbconnectit.portfolio.app.ui.main.introduction.sections

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.cbconnectit.portfolio.app.R
import be.cbconnectit.portfolio.app.domain.model.Work
import be.cbconnectit.portfolio.app.domain.model.previewData
import be.cbconnectit.portfolio.app.ui.theme.PortfolioTheme
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun PortfolioSection(
    projects: List<Work>,
    selectedWork: Work? = null,
    actionClicked: () -> Unit,
    onWorkClicked: (workId: Work) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            AsyncImage(
                modifier = Modifier.matchParentSize(),
                model = selectedWork?.bannerImageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = "Image"
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(MaterialTheme.colorScheme.primary.copy(0.82f))
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    projects.forEach {
                        TextButton(
                            modifier = Modifier.height(30.dp),
                            shape = RoundedCornerShape(6.dp),
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = if (selectedWork == it) MaterialTheme.colorScheme.surface else Color.Transparent,
                                contentColor = if (selectedWork == it) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onPrimary
                            ),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                            onClick = { onWorkClicked(it) }
                        ) {
                            Text(
                                text = it.title,
                                maxLines = 1
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = selectedWork?.shortDescription ?: "",
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.height(24.dp))

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                    verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
                ) {
                    selectedWork?.tags?.forEach {
                        Text(
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.secondaryContainer,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .padding(horizontal = 6.dp, vertical = 4.dp),
                            text = it.name,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        TextButton(
            modifier = Modifier.height(30.dp),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
            onClick = { actionClicked() }
        ) {
            Text(style = MaterialTheme.typography.labelLarge, text = stringResource(R.string.see_all_projects))

            Icon(modifier = Modifier.size(18.dp), painter = painterResource(id = R.drawable.ic_keyboard_arrow_right), contentDescription = "")
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PortfolioSectionPreview() {
    PortfolioTheme {
        Surface {
            PortfolioSection(projects = listOf(Work.previewData(), Work.previewData().copy(title = "PoemCollection"), Work.previewData().copy(title = "Pokédex")), selectedWork = Work.previewData(), onWorkClicked = { some -> }, actionClicked = {})
        }
    }
}