package be.cbconnectit.portfolio.app.ui.main.introduction.services.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import be.cbconnectit.portfolio.app.ui.components.DefaultAppBar
import me.onebone.toolbar.CollapsingToolbarScaffoldState
import me.onebone.toolbar.CollapsingToolbarScope
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapsingToolbarScope.CollapsingServiceToolbar(
    navController: NavController,
    toolbarState: CollapsingToolbarScaffoldState,
    title: String,
    body: String,
    imageUrl: String? = null
) {
    val progress = toolbarState.toolbarState.progress
    val correctProgress = if (progress < 0.25f) 0.0f else progress

    val absProgress = abs(progress - 1)
    val correctAbsProgress = if (absProgress < 0.75f) 0.0f else absProgress

    ServiceHeader(
        modifier = Modifier
            .parallax()
            .pin()
            .alpha(correctProgress),
        title = title,
        text = body,
        imageUrl = imageUrl
    )

    Box {
        if (abs(correctProgress - 1) > 0.75f) {
            Box(
                modifier = Modifier
                    .matchParentSize() // Ensures the Image matches the size of the Box
                    .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
            )
        }

        DefaultAppBar(
            navController = navController,
            appBarTitle = "",
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                navigationIconContentColor = if (abs(correctProgress - 1) < 0.75f) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
            ),
            title = {
                Text(
                    modifier = Modifier.alpha(correctAbsProgress),
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        )
    }
}