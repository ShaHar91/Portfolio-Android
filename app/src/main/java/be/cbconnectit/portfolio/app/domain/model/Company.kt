package be.cbconnectit.portfolio.app.domain.model

data class Company(
    val id: String,
    val name: String,
    val links: List<Link>,
    val createdAt: String,
    val updatedAt: String
)
