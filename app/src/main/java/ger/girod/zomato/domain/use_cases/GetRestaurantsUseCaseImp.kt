package ger.girod.zomato.domain.use_cases

import ger.girod.zomato.data.api.util.ResultWrapper
import ger.girod.zomato.data.repository.RestaurantRepository
import ger.girod.zomato.domain.models.responses.RestaurantsResponseModel

class GetRestaurantsUseCaseImp(private val restaurantRepository: RestaurantRepository) :
    GetRestaurantsUseCase {
    override suspend fun getRestaurants(): ResultWrapper<RestaurantsResponseModel> {
        return restaurantRepository.getRestaurants()
    }
}