package dev.himanshu.moviesearchapp.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.himanshu.search.ui.SearchScreen
import kotlinx.serialization.Serializable

object SearchNavGraph : BaseNavGraph {

    sealed interface Dest {

        @Serializable
        data object Root : Dest

        @Serializable
        data object Search : Dest

    }


    override fun build(
        modifier: Modifier,
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation<Dest.Root>(startDestination = Dest.Search) {
            composable<Dest.Search> {
                SearchScreen(modifier){movieId->
                    navHostController.navigate(DetailsNavGraph.Dest.MovieDetails(movieId))
                }
            }
        }
    }
}