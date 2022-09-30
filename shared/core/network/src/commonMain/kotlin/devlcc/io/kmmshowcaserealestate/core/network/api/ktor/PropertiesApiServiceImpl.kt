package devlcc.io.kmmshowcaserealestate.core.network.api.ktor

import devlcc.io.kmmshowcaserealestate.core.model.property.Property
import devlcc.io.kmmshowcaserealestate.core.network.api.PropertiesApiService
import devlcc.io.kmmshowcaserealestate.core.network.dto.property.GetPropertiesResponse
import devlcc.io.kmmshowcaserealestate.core.network.dto.property.toModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class PropertiesApiServiceImpl(
    private val baseUrl: String = "https://private-237ace-realtyinus.apiary-mock.com",
    private val client: HttpClient,
): PropertiesApiService {

    override suspend fun getPropertiesForSale(): List<Property> {
        return client.get("${baseUrl}/properties/list-for-sale")
            .body<GetPropertiesResponse>()
            .properties
            .map { it.toModel() }
    }

    override suspend fun getPropertiesForRent(): List<Property> {
        return client.get("${baseUrl}/properties/list-for-rent")
            .body<GetPropertiesResponse>()
            .properties
            .map { it.toModel() }
    }

    override suspend fun getPropertiesSold(): List<Property> {
        return client.get("${baseUrl}/properties/list-sold")
            .body<GetPropertiesResponse>()
            .properties
            .map { it.toModel() }
    }
}