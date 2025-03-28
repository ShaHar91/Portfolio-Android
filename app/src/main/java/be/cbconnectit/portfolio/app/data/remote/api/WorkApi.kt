package be.cbconnectit.portfolio.app.data.remote.api

import be.cbconnectit.portfolio.app.data.remote.RestApiBuilder
import be.cbconnectit.portfolio.app.data.remote.dto.WorkDto
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.DefaultJson

class WorkApiImpl(
    private val client: RestApiBuilder
) : WorkApi {
    override suspend fun fetchAllWorks(): List<WorkDto> {
        val call = client.api.get("projects")
        val works = call.bodyAsText()

        return DefaultJson.decodeFromString(works)
    }
}

interface WorkApi {
    suspend fun fetchAllWorks(): List<WorkDto>
}