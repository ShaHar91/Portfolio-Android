package be.cbconnectit.portfolio.app.domain.model

import androidx.recyclerview.widget.DiffUtil

data class Work(
    val id: String,
    val bannerImageUrl: String,
    val imageUrl: String,
    val title: String,
    val shortDescription: String,
    val description: String,
    val links: List<Link>,
    val tags: List<Tag>,
    val createdAt: String,
    val updatedAt: String
) {
    companion object {
        val WORK_DIFF = object : DiffUtil.ItemCallback<Work>() {
            override fun areItemsTheSame(oldItem: Work, newItem: Work): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Work, newItem: Work): Boolean {
                return oldItem == newItem
            }
        }
    }
}


fun Work.Companion.previewData() = Work(
    "1",
    "",
    "",
    "Android Core",
    "Created this library in order to streamline and simplify the setup of new projects. Instead of copying a lot of classes and reimplementing it differently each time a single dependency was all we needed.",
    """Created this library in order to streamline and simplify the setup of new projects. Instead of copying a lot of classes and reimplementing it differently each time, a single dependency was all we needed.

Because the company worked on multiple projects a year, it was quite usual that we did a lot of manual setup like copy a lot of base and util classes over to the new project, maybe tweak them a little bit and then continue with the actual project. This resulted in a lot of differences in the code bases and scattered knowledge between coworkers. To circumvent this issue, I created a base library where the base classes and some util class (later extension functions) where placed. All accessible by a user friendly Builder class to initialize all needed pieces.

Also the network layer had a complete makeover to add a (streamlined) default way of work, taking pieces of all previous projects and coworkers to create a cohesive and robust basis. It had also some plug and play functionalities to cater to some project specific needs.

The library was comprised out of different modules. For example, there was the actual Core module, then we had the Networking module with retrofit usability. Besides that we also had modules for local data, starting with Realm which was Deprecated in favor of Room after a couple of versions.

Check the Github for more information and a sample!""",
    links = listOf(Link.previewData()),
    tags = listOf(Tag.previewData()),
    createdAt = "",
    updatedAt = ""
)

fun Tag.Companion.previewData() = Tag("1", "Kotlin", "kotlin", "", "")