package be.cbconnectit.portfolio.app.domain.model

import androidx.recyclerview.widget.DiffUtil

data class Service(
    val id: String,
    val imageUrl: String,
    val title: String,
    val shortDescription: String? = null,
    val description: String,
    val bannerDescription: String? = null,
    val subServices: List<Service>? = null,
    val extraInfo: String? = null,
    val tag: Tag? = null,
    val createdAt: String,
    val updatedAt: String
) {
    companion object {
        val SERVICE_DIFF = object : DiffUtil.ItemCallback<Service>() {
            override fun areItemsTheSame(oldItem: Service, newItem: Service): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Service, newItem: Service): Boolean {
                return oldItem == newItem
            }
        }
    }
}

fun Service.Companion.previewData() = Service(
    "1",
    "https://raw.githubusercontent.com/ShaHar91/Portfolio/master/public/android_img.png",
    "Android Development",
    "Lorum ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
    "Lorum ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
    "Lorum ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
    createdAt = "",
    updatedAt = ""
)