package com.example.enjoeielo7.ui.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.enjoeielo7.R
import com.example.enjoeielo7.models.repository.RepositoryVisibility
import com.example.enjoeielo7.ui.theme.EnjoeiElo7Theme

@Composable
fun LockerImage(visibility: RepositoryVisibility, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(
            id = if (visibility == RepositoryVisibility.PUBLIC) R.drawable.lock_open
            else R.drawable.lock_closed
        ),
        contentDescription = "Locker Icon",
        modifier = modifier.size(24.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun LockerImagePublicPreview() {
    EnjoeiElo7Theme {
        LockerImage(RepositoryVisibility.PUBLIC)
    }
}

@Preview(showBackground = true)
@Composable
fun LockerImagePrivatePreview() {
    EnjoeiElo7Theme {
        LockerImage(RepositoryVisibility.PRIVATE)
    }
}