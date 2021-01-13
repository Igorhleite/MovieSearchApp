package com.example.movieshearch.model

data class movieModel(
    val title: String,
    val gender: String,
    val duration: String,
    val country: String
)


class movieModelBuilder {

    var title: String = ""
    var gender: String = ""
    var duration: String = ""
    var country: String = ""

    fun build(): movieModel = movieModel(title, gender, duration, country)
}

fun movie(block: movieModelBuilder.() -> Unit): movieModel =
    movieModelBuilder().apply(block).build()

fun mockMovies() = listOf(
    movie {
        title = "Titulo 1"
        gender = "Ação"
        duration = "12093 minutos"
        country = "Brazil"
    },
    movie {
        title = "Titulo 2"
        gender = "Ação"
        duration = "12093 minutos"
        country = "Brazil"
    },
    movie {
        title = "Titulo 3"
        gender = "Ação"
        duration = "12093 minutos"
        country = "Brazil"
    },
    movie {
        title = "Titulo 3"
        gender = "Ação"
        duration = "12093 minutos"
        country = "Brazil"
    },
    movie {
        title = "Titulo 3"
        gender = "Ação"
        duration = "12093 minutos"
        country = "Brazil"
    },
    movie {
        title = "Titulo 3"
        gender = "Ação"
        duration = "12093 minutos"
        country = "Brazil"
    },
    movie {
        title = "Titulo 3"
        gender = "Ação"
        duration = "12093 minutos"
        country = "Brazil"
    }
)