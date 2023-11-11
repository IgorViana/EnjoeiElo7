package com.example.enjoeielo7.ui.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.enjoeielo7.models.repository.RepositoryItemModel
import com.example.enjoeielo7.models.repository.RepositoryVisibility
import com.example.enjoeielo7.ui.theme.EnjoeiElo7Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryCardItem(
    repository: RepositoryItemModel,
    onRepositoryClick: (item: RepositoryItemModel) -> Unit
) {
    Card(
        onClick = { onRepositoryClick(repository) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = repository.name.orEmpty(), modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                )
                LockerImage(repository.visibility)
            }

            repository.description?.let {
                Text(text = it, modifier = Modifier.padding(8.dp))
            }

            repository.language?.let {
                Text(text = it, modifier = Modifier.padding(8.dp))
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
                repository.collaboratorsUrl.forEach { url ->
                    url?.let {
                        AsyncImage(
                            model = url,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .size(16.dp)
                                .clip(RectangleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepositoryCardItemPreview() {
    EnjoeiElo7Theme {
        RepositoryCardItem(
            repository = RepositoryItemModel(
                name = "Agen",
                description = "Arquiteto de Soluções de problemas grandes",
                language = "",
                visibility = RepositoryVisibility.PRIVATE,
                collaboratorsUrl = emptyList(),
                owner = null
            )
        ) {}
    }
}