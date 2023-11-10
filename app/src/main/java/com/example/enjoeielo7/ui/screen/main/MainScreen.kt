package com.example.enjoeielo7.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.enjoeielo7.ui.item.RepositoryCardItem
import com.example.enjoeielo7.ui.theme.EnjoeiElo7Theme

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    onRepositoryClick: (owner: String?, repositoryName: String?) -> Unit
) {
    val repositories = mainViewModel.repositories.observeAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize()) {
        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2), content = {
            items(repositories.value) { item ->
                RepositoryCardItem(repository = item) { selectedItem ->
                    onRepositoryClick(selectedItem.owner, selectedItem.name)
                }
            }
        })
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    EnjoeiElo7Theme {
        MainScreen { _, _ -> }
    }
}