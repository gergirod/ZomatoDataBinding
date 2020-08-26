package ger.girod.zomato.data.injection

import dagger.Module
import dagger.Provides
import dagger.Reusable
import ger.girod.zomato.data.api.RestaurantsApi
import ger.girod.zomato.data.api.util.BASE_URL
import ger.girod.zomato.data.repository.RestaurantRepository
import ger.girod.zomato.domain.use_cases.GetRestaurantsUseCase
import ger.girod.zomato.domain.use_cases.GetRestaurantsUseCaseImp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
object NetworkModule {
    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): RestaurantsApi {
        return retrofit.create(RestaurantsApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client =
            OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun providePostRepository(api: RestaurantsApi): RestaurantRepository {
        return RestaurantRepository(api)
    }

    @Provides
    @Singleton
    internal fun provideGetRestaurant(restaurantRepository: RestaurantRepository): GetRestaurantsUseCase {
        return GetRestaurantsUseCaseImp(restaurantRepository)
    }
}