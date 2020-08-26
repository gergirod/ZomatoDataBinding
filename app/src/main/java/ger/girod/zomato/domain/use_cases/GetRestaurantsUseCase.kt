package ger.girod.zomato.domain.use_cases

import ger.girod.zomato.data.api.util.ResultWrapper
import ger.girod.zomato.domain.models.responses.RestaurantsResponseModel

interface GetRestaurantsUseCase {
    suspend fun getRestaurants(): ResultWrapper<RestaurantsResponseModel>
}