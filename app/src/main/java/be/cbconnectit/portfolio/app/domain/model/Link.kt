package be.cbconnectit.portfolio.app.domain.model

import be.cbconnectit.portfolio.app.domain.enums.LinkType

data class Link(
    val id: String = "",
    val url: String,
    val type: LinkType,
    val createdAt: String = "",
    val updatedAt: String = ""
) {
    companion object
}

fun Link.Companion.previewData() = Link(
    "",
    "",
    LinkType.Github,
    "",
    ""
)