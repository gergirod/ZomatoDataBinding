package ger.girod.zomato.ui.restaurants

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ger.girod.zomato.data.api.util.ResultWrapper
import ger.girod.zomato.domain.models.responses.RestaurantResponseModel
import ger.girod.zomato.domain.use_cases.GetRestaurantsUseCase
import ger.girod.zomato.ui.models.RestaurantViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class RestaurantsViewModel @Inject constructor(private val getRestaurantsUseCase: GetRestaurantsUseCase) :
    ViewModel() {

    val progressVisibilityData: MutableLiveData<Int> = MutableLiveData()
    val restaurantsData: MutableLiveData<List<RestaurantViewModel>> = MutableLiveData()
    val lastVisiblePosition = MutableLiveData<Int>()

    init {
        getRestaurants()
    }

    private fun getRestaurants() {
        viewModelScope.launch {
            progressVisibilityData.value = 0
            when (val response = getRestaurantsUseCase.getRestaurants()) {
                is ResultWrapper.Success -> {
                    progressVisibilityData.value = 8
                    val list = response.value.restaurants.map {
                        mapToViewModel(it)
                    }
                    restaurantsData.value = list
                }
                is ResultWrapper.ServerError -> {
                    progressVisibilityData.value = 8
                }
            }
        }
    }

    private fun mapToViewModel(restaurantResponseModel: RestaurantResponseModel) =
        RestaurantViewModel(
            restaurantResponseModel.restaurant.name, restaurantResponseModel.restaurant.thumb,
            restaurantResponseModel.restaurant.location.address
        )
}