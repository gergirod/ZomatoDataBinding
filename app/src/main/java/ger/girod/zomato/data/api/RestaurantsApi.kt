package ger.girod.zomato.data.api

import ger.girod.zomato.domain.models.responses.RestaurantsResponseModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RestaurantsApi {

    @GET("/api/v2.1/search")
    suspend fun getRestaurants(
        @Header("user-key") userKey: String,
        @Query("entity_id") cityId: Int,
        @Query("entity_type") entityType: String,
        @Query("count") count: Int, @Query("sort") sort: String,
        @Query("order") order: String
    ): RestaurantsResponseModel
}