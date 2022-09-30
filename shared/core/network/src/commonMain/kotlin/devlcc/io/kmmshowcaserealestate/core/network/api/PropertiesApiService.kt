package devlcc.io.kmmshowcaserealestate.core.network.api

import devlcc.io.kmmshowcaserealestate.core.model.property.Property

interface PropertiesApiService {

    /**
     * Properties for sale
     */
    suspend fun getPropertiesForSale(): List<Property>

    /**
     * Properties for rent
     */
    suspend fun getPropertiesForRent(): List<Property>

    /**
     * Properties sold
     */
    suspend fun getPropertiesSold(): List<Property>

}