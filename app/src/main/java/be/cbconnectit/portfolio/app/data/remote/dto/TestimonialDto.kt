package be.cbconnectit.portfolio.app.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class TestimonialDto(
    val id: String = "",
    @SerialName("image_url")
    val imageUrl: String = "",
    @SerialName("full_name")
    val fullName: String = "",
    val company: CompanyDto = CompanyDto(),
    @SerialName("job_position")
    val jobPosition: JobPositionDto = JobPositionDto(),
    val review: String = "",
    @SerialName("created_at")
    val createdAt: String = "",
    @SerialName("updated_at")
    val updatedAt: String = ""
) : Parcelable
