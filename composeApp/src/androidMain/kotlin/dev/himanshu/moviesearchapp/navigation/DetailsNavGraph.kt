package dev.himanshu.moviesearchapp.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import dev.himanshu.details.ui.MovieDetailsScreen
import kotlinx.serialization.Serializable

object DetailsNavGraph : BaseNavGraph {

    sealed interface Dest {
        @Serializable
        data object Root : Dest

        @Serializable
        data class MovieDetails(val movieId: String) : Dest
    }

    override fun build(
        modifier: Modifier,
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation<Dest.Root>(startDestination = Dest.MovieDetails::class) {
            composable<Dest.MovieDetails> {
                val movieId = it.toRoute<Dest.MovieDetails>().movieId
                MovieDetailsScreen(modifier=modifier, movieId = movieId)
             }
        }

    }
}