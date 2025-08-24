package dev.himanshu.search.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.SubcomposeAsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchScreen(modifier: Modifier = Modifier, onClick: (String) -> Unit) {

    val viewModel = koinViewModel<SearchViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val query by viewModel.query.collectAsStateWithLifecycle()

    SearchScreenContent(
        modifier = modifier,
        query = query,
        onQueryChange = viewModel::updateQuery,
        uiState = uiState,
        onClick=onClick
    )
}


@Composable
fun SearchScreenContent(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    uiState: SearchUiState,
    onClick: (String) -> Unit
) {

    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TextField(
            value = query, onQueryChange,
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search here...") }
        )
    }) { innerPadding ->

        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        if (uiState.error.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Something went wrong....")
            }
        }



        uiState.data?.let { results ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )
            {
                items(
                    count = results.size,
                    key = { results[it].id },
                    contentType = { results[it].id }) { index ->
                    val item = results[index]
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                            .height(300.dp)
                            .background(
                                color = Color.Transparent,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable{onClick(results[index].id.toString())}
                            .clip(RoundedCornerShape(12.dp)),
                        model = item.imageUrl, loading = {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp), contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }, error = {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp), contentAlignment = Alignment.Center
                            ) {
                                Text("Something went wrong.")
                            }
                        }, contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }

}