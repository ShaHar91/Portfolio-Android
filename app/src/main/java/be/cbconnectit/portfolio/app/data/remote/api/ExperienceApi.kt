package be.cbconnectit.portfolio.app.data.remote.api

import be.cbconnectit.portfolio.app.data.remote.RestApiBuilder
import be.cbconnectit.portfolio.app.data.remote.dto.ExperienceDto
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.DefaultJson

class ExperienceApiImpl(
    private val client: RestApiBuilder
): ExperienceApi {
    override suspend fun fetchAllExperiences(): List<ExperienceDto> {
        val call = client.api.get("experiences")
        val experiences = call.bodyAsText()

        return DefaultJson.decodeFromString(experiences)
    }
}

interface ExperienceApi {
    suspend fun fetchAllExperiences(): List<ExperienceDto>
}