package com.example.enjoeielo7.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.enjoeielo7.R
import com.example.enjoeielo7.ui.item.LockerImage
import com.example.enjoeielo7.ui.theme.EnjoeiElo7Theme

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val repository = detailViewModel.repositoryDetail.observeAsState()
    repository.value?.apply {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                LockerImage(visibility)
                Text(
                    text = repository.value?.fullName.orEmpty(), modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }

            description?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "Star Icon",
                    modifier = Modifier
                        .size(24.dp)
                )
                Text(text = "$starsCount stars")

                Image(
                    painter = painterResource(id = R.drawable.fork),
                    contentDescription = "Star Icon",
                    modifier = Modifier
                        .size(24.dp)
                )
                Text(text = "$forksCount forks")
            }

            readMe?.let {
                Text(
                    text = readMe, modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    EnjoeiElo7Theme {
        DetailScreen()
    }
}