package ger.girod.zomato.domain.models

data class RestaurantModel(
    val id: String, val name: String, val url: String,
    val thumb: String, val location: LocationModel, val cuisines: String,
    val timings: String
)