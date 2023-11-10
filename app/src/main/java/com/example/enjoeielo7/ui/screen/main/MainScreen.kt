@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.enjoeielo7.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.enjoeielo7.models.repository.RepositoryVisibility
import com.example.enjoeielo7.ui.item.RepositoryCardItem
import com.example.enjoeielo7.ui.theme.EnjoeiElo7Theme

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    onRepositoryClick: (owner: String?, repositoryName: String?) -> Unit
) {
    val repositories = mainViewModel.repositories.observeAsState(initial = emptyList())

    val titles = listOf("PÚBLICOS", "PRIVADOS")
    var tabIndex by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            TabRow(selectedTabIndex = tabIndex) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when (tabIndex) {
                    0 -> {
                        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2), content = {
                            items(repositories.value.filter { it.visibility == RepositoryVisibility.PUBLIC }) { item ->
                                RepositoryCardItem(repository = item) { selectedItem ->
                                    onRepositoryClick(selectedItem.owner, selectedItem.name)
                                }
                            }
                        })
                    }
                    1 -> {
                        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2), content = {
                            items(repositories.value.filter { it.visibility == RepositoryVisibility.PRIVATE }) { item ->
                                RepositoryCardItem(repository = item) { selectedItem ->
                                    onRepositoryClick(selectedItem.owner, selectedItem.name)
                                }
                            }
                        })
                    }
                }

            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    EnjoeiElo7Theme {
        MainScreen { _, _ -> }
    }
}