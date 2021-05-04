package com.corcoles.popcorn.retrofit

import com.corcoles.popcorn.retrofit.models.PopularMovisResponse
import retrofit2.Call
import retrofit2.http.GET

interface TheMovieDBService {
    @GET("/movie/popular")
    fun getPopularMovie():Call<PopularMovisResponse>
}