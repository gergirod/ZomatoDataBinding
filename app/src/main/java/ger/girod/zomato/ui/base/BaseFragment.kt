package ger.girod.zomato.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import ger.girod.zomato.data.injection.NetworkModule
import ger.girod.zomato.data.injection.component.DaggerViewModelInjector
import ger.girod.zomato.data.injection.component.ViewModelInjector
import ger.girod.zomato.ui.restaurants.RestaurantsFragment

abstract class BaseFragment : Fragment() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    private fun inject() {
        when (this) {
            is RestaurantsFragment -> injector.inject(this)
        }
    }
}