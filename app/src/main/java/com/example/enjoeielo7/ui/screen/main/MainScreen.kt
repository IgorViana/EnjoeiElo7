@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.enjoeielo7.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.enjoeielo7.R
import com.example.enjoeielo7.models.repository.RepositoryItemModel
import com.example.enjoeielo7.ui.item.RepositoryCardItem
import com.example.enjoeielo7.ui.theme.EnjoeiElo7Theme

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    onRepositoryClick: (owner: String?, repositoryName: String?) -> Unit
) {
    val publicPagedRepository: LazyPagingItems<RepositoryItemModel> =
        mainViewModel.publicPagedRepository.collectAsLazyPagingItems()
    val privatePagedRepository: LazyPagingItems<RepositoryItemModel> =
        mainViewModel.privatePagedRepository.collectAsLazyPagingItems()

    val titles = listOf(stringResource(R.string.publicos), stringResource(R.string.privados))
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
                val repositoryList =
                    if (tabIndex == 0) publicPagedRepository else privatePagedRepository
                RepositoryList(repositoryList) { owner, name ->
                    onRepositoryClick(owner, name)
                }
            }
        }
    )
}

@Composable
fun RepositoryList(
    repositoryList: LazyPagingItems<RepositoryItemModel>,
    onRepositoryClick: (owner: String?, repositoryName: String?) -> Unit
) {
    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2), content = {
        items(repositoryList.itemCount) { index ->
            val model = repositoryList[index]
            model?.let {
                RepositoryCardItem(repository = model) { selectedItem ->
                    onRepositoryClick(selectedItem.owner, selectedItem.name)
                }
            }
        }
    })
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    EnjoeiElo7Theme {
        MainScreen { _, _ -> }
    }
}