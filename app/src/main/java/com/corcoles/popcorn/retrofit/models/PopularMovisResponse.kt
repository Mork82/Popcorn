package com.corcoles.popcorn.retrofit.models

import com.corcoles.popcorn.retrofit.models.Movie

data class PopularMovisResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)