package be.cbconnectit.portfolio.app.domain.model

import androidx.recyclerview.widget.DiffUtil
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Experience(
    val id: String,
    val shortDescription: String,
    val description: String,
    val from: String,
    val to: String,
    val tags: List<Tag>,
    val asFreelance: Boolean,
    val jobPosition: JobPosition,
    val company: Company,
    val createdAt: String,
    val updatedAt: String
) {
    companion object {
        val EXPERIENCE_DIFF = object : DiffUtil.ItemCallback<Experience>() {
            override fun areItemsTheSame(oldItem: Experience, newItem: Experience): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Experience, newItem: Experience): Boolean {
                return oldItem == newItem
            }
        }
    }

    val formattedDate: String
        get() = run {
            //TODO: Add the locale of the selected language!
            val start = LocalDateTime.parse(from).format(DateTimeFormatter.ofPattern("MMMM yyyy"))
            val end = LocalDateTime.parse(to).format(DateTimeFormatter.ofPattern("MMMM yyyy"))

            "$start - $end"
        }
}

fun Experience.Companion.previewData() = Experience(
    "1",
    "Lorum ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
    "Engaged in the creation and management of more than 15 projects. Employed Material design libraries for impressive app visuals. Incorporated external libraries for handling RESTful API requests, ensuring smooth data exchange. Utilized an internal Room database for cached startup, maintaining a clear division between remote and local data. Proficient with hardware components and establishing Bluetooth connections for enhanced functionality. Managed a Core library for all projects, boosting code reusability and efficiency. Strongly inclined towards MVVM pattern and best practices, making use of official Android libraries to optimize framework potential.",
    "2017-05-01T00:00:00",
    "2023-10-16T00:00:00",
    listOf(),
    false,
    JobPosition("", "Android Developer", "", ""),
    Company("", "Wisemen (formerly Appwise)", emptyList(), "", ""),
    "",
    ""
)