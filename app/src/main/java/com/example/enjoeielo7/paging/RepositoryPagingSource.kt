package com.example.enjoeielo7.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.enjoeielo7.models.mapper.Mapper
import com.example.enjoeielo7.models.repository.RepositoryItemModel
import com.example.enjoeielo7.network.UserService
import com.example.enjoeielo7.network.response.repository.RepositoryItemResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

class RepositoryPagingSource(
    private val network: UserService,
    private val authorization: String,
) : PagingSource<Int, RepositoryItemModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryItemModel> {
        return try {
            val currentPage = params.key ?: 1
            val repositories: List<RepositoryItemResponse> = network.getRepositoryList(
                authorization = authorization,
                page = currentPage
            )

            val jobs = repositories.map { repository ->
                CoroutineScope(Dispatchers.IO).async {
                    val collaborators = network.getRepositoryCollaborators(
                        authorization = authorization,
                        owner = repository.owner?.login.orEmpty(),
                        repositoryName = repository.name.orEmpty()
                    )
                    repository.collaboratorsUrl = collaborators
                }
            }
            jobs.awaitAll()

            val result = Mapper().mapRepositoryListResponseToModel(repositories)
            LoadResult.Page(
                data = result,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (repositories.isEmpty()) null else currentPage + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RepositoryItemModel>): Int? {
        return state.anchorPosition
    }

}