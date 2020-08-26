package ger.girod.zomato.data.repository

import ger.girod.zomato.data.api.RestaurantsApi
import ger.girod.zomato.data.api.util.API_KEY
import ger.girod.zomato.data.api.util.ResultWrapper
import ger.girod.zomato.data.api.util.executeRequest
import ger.girod.zomato.domain.models.responses.RestaurantsResponseModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class RestaurantRepository @Inject constructor(private val restaurantsApi: RestaurantsApi) {

    suspend fun getRestaurants(): ResultWrapper<RestaurantsResponseModel> {
        return executeRequest(Dispatchers.IO) {
            restaurantsApi.getRestaurants(API_KEY, 259, "city", 10, "rating", "asc")
        }
    }
}