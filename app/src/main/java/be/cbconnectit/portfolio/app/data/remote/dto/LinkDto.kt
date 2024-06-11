package be.cbconnectit.portfolio.app.data.remote.dto

import android.os.Parcelable
import be.cbconnectit.portfolio.app.domain.enums.LinkType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class LinkDto(
    val id: String,
    val url: String,
    val type: LinkType,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
) : Parcelable

