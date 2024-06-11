package be.cbconnectit.portfolio.app.data.remote.dto

import android.os.Parcelable
import be.cbconnectit.portfolio.app.domain.enums.TechStack
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class ExperienceDto(
    val id: String,
    @SerialName("short_description")
    val shortDescription: String,
    val description: String,
    val from: String,
    val to: String,
    val tags: List<TagDto>,
    @SerialName("as_freelance")
    val asFreelance: Boolean,
    @SerialName("job_position")
    val jobPosition: JobPositionDto,
    val company: CompanyDto,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
): Parcelable