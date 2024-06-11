package be.cbconnectit.portfolio.app.domain.model

import androidx.annotation.DrawableRes
import be.cbconnectit.portfolio.app.R

data class Tag(
    val id: String,
    val name: String,
    val slug: String,
    val createdAt: String,
    val updatedAt: String
) {
    companion object
}

@DrawableRes
fun Tag.getIconForTechStack(): Int = when (this.slug) {
    "android" -> R.drawable.ic_phone_android
    "android-tv" -> R.drawable.ic_tv_android
    else -> R.drawable.ic_error
}