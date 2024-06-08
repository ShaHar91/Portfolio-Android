package be.cbconnectit.portfolio.app.domain.model

data class Tag(
    val id: String,
    val name: String,
    val slug: String,
    val createdAt: String,
    val updatedAt: String
) {
    companion object
}