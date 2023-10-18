package be.christiano.portfolio.app.data.remote.dto

import android.os.Parcelable
import be.christiano.portfolio.app.domain.enums.TechStack
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class ExperienceDto(
    val id: String,
    @SerialName("job_position")
    val jobPosition: String,
    val description: String,
    val company: String,
    val from: String,
    val to: String,
    @SerialName("tech_stack")
    val techStacks: List<TechStack>
): Parcelable