package ger.girod.zomato.domain.models.responses

import com.google.gson.annotations.SerializedName

class RestaurantsResponseModel(
    @SerializedName("results_found") val resultsFound: Int,
    @SerializedName("results_start") val resultsStart: Int,
    @SerializedName("results_shown") val resultShown: Int,
    val restaurants: List<RestaurantResponseModel>
) {
}