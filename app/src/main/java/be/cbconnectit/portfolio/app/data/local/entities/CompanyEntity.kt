package be.cbconnectit.portfolio.app.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CompanyEntity.ENTITY_NAME)
data class CompanyEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    @ColumnInfo("created_at")
    val createdAt: String,
    @ColumnInfo("updated_at")
    val updatedAt: String
) {
    companion object {
        const val ENTITY_NAME = "company"
    }
}


//TODO: add a relation to the links for this company as well!!