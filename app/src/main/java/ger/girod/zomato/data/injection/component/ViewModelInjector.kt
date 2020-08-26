package ger.girod.zomato.data.injection.component

import dagger.Component
import ger.girod.zomato.data.injection.NetworkModule
import ger.girod.zomato.data.injection.ViewModelModule
import ger.girod.zomato.ui.restaurants.RestaurantsFragment
import ger.girod.zomato.ui.restaurants.RestaurantsViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface ViewModelInjector {
    fun inject(restaurantsViewModel: RestaurantsViewModel)
    fun inject(restaurantFragments: RestaurantsFragment)
}