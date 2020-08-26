package ger.girod.zomato.data.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ger.girod.zomato.data.injection.view_model.ViewModelFactory
import ger.girod.zomato.data.injection.view_model.ViewModelKey
import ger.girod.zomato.ui.restaurants.RestaurantsViewModel

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantsViewModel::class)
    abstract fun bindsMoviesViewModel(moviesViewModel: RestaurantsViewModel): ViewModel

}