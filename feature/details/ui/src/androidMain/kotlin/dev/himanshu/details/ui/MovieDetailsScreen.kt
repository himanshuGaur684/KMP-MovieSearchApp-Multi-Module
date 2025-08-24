package dev.himanshu.details.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.SubcomposeAsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieDetailsScreen(modifier: Modifier = Modifier, movieId: String) {

    val viewModel = koinViewModel<DetailsViewModel>()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getMovieDetails(movieId)
    }

    MovieDetailsScreenContent(modifier = modifier, uiState = uiState)

}

@Composable
fun MovieDetailsScreenContent(
    modifier: Modifier = Modifier,
    uiState: DetailsUiState
) {

    when {
        uiState.isLoading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        uiState.error.isNotEmpty() -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = uiState.error)
            }
        }

        uiState.data != null -> {
            val data = requireNotNull(uiState.data)
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                SubcomposeAsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(600.dp),
                    contentScale = ContentScale.Crop,
                    model = data.imageUrl,
                    loading = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(600.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }, error = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(600.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Something went wrong")
                        }
                    }, contentDescription = null
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    data.title, style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    data.description,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )

                Spacer(
                    Modifier.height(32.dp),
                )

            }
        }

    }

}