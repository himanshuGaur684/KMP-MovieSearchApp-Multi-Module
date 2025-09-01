package dev.himanshu.moviesearchapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.himanshu.moviesearchapp.navigation.BaseNavGraph
import dev.himanshu.moviesearchapp.navigation.DetailsNavGraph
import dev.himanshu.moviesearchapp.navigation.SearchNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Scaffold(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .fillMaxSize()
        ) { innerPadding ->
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = SearchNavGraph.Dest.Root) {
                listOf<BaseNavGraph>(
                    SearchNavGraph,
                    DetailsNavGraph
                ).forEach {
                    it.build(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        navController = navController,
                        navGraphBuilder = this
                    )
                }
            }
        }
    }
}