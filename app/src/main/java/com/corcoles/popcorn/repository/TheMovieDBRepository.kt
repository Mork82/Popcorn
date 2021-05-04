package com.corcoles.popcorn.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.corcoles.popcorn.common.App
import com.corcoles.popcorn.retrofit.TheMovieDBClient
import com.corcoles.popcorn.retrofit.TheMovieDBService
import com.corcoles.popcorn.retrofit.models.Movie
import com.corcoles.popcorn.retrofit.models.PopularMovisResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TheMovieDBRepository {
    var theMovieDBService: TheMovieDBService? = null
    var theMovieDbClient: TheMovieDBClient? = null
    var popularMovies: MutableLiveData<List<Movie>>? = null

    init {
        theMovieDbClient = TheMovieDBClient.instace
        theMovieDBService = theMovieDbClient?.getTheMovieDBService()

        popularMovies = getPopularMovies()
    }

    @JvmName("getPopularMovies1")
    private fun getPopularMovies(): MutableLiveData<List<Movie>>? {
        if (popularMovies == null) {
            popularMovies = MutableLiveData<List<Movie>>()
        }

        val call: Call<PopularMovisResponse>? = theMovieDBService?.getPopularMovie()
        call?.enqueue(object : Callback<PopularMovisResponse>{
            override fun onResponse(
                call: Call<PopularMovisResponse>,
                response: Response<PopularMovisResponse>
            ) {
                if(response.isSuccessful) {
                    popularMovies?.value = response.body()?.results
                }
            }

            override fun onFailure(call: Call<PopularMovisResponse>, t: Throwable) {
                Toast.makeText(App.instance,"Error en la llamada",Toast.LENGTH_LONG).show()
            }

        } )



        return popularMovies
    }

}