package ger.girod.zomato.domain.models

import com.google.gson.annotations.SerializedName

data class LocationModel(
    val address: String,
    val locality: String,
    val city: String,
    @SerializedName("city_id") val cityId: Int,
    val latitude: String,
    val longitude: String,
    @SerializedName("zipcode") val zipCode: String,
    @SerializedName("country_id") val countryId: Int,
    @SerializedName("locality_verbose") val localityVerbose: String
) {
}