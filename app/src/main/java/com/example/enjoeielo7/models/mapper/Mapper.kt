package com.example.enjoeielo7.models.mapper

import com.example.enjoeielo7.models.repository.RepositoryItemModel
import com.example.enjoeielo7.models.repository.RepositoryVisibility
import com.example.enjoeielo7.network.response.repository.RepositoryItemResponse

class Mapper {

    fun mapRepositoryItemResponseToModel(response: RepositoryItemResponse): RepositoryItemModel {

        return RepositoryItemModel(
            name = response.name,
            description = response.description,
            language = response.language,
            visibility = RepositoryVisibility.getVisibility(response.visibility.orEmpty()),
            collaboratorsUrl = response.collaboratorsUrl
        )
    }

    fun mapRepositoryListResponseToModel(response: List<RepositoryItemResponse>): List<RepositoryItemModel> {

        val repository: MutableList<RepositoryItemModel> = mutableListOf()

        response.forEach { item ->
            repository.add(mapRepositoryItemResponseToModel(item))
        }

        return repository
    }
}