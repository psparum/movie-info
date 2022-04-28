package com.arum.movieinfo.model

import java.math.BigInteger

data class DetailResponse(
    val adult : Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsCollection,
    val budget: BigInteger,
    val genres: MutableList<Genres>,
    val homepage: String,
    val id: BigInteger,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies:MutableList<ProductionCompanies>,
    val production_countries: MutableList<ProductionCountries>,
    val release_date: String,
    val revenue: BigInteger,
    val runtime: Int,
    val spoken_languagen: MutableList<SpokenLanguages>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

data class BelongsCollection(
    val id: BigInteger,
    val name: String,
    val poster_path: String,
    val backdrop_path: String
)

data class Genres(
    val id: Int,
    val name: String
)

data class ProductionCompanies(
    val id: Int,
    val logo_path: String,
    val name: String,
    val original_country: String
)

data class ProductionCountries(
    val iso_33166_1: String,
    val name: String
)

data class SpokenLanguages(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)
