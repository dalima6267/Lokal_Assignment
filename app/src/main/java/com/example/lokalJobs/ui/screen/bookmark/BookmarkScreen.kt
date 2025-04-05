package com.example.lokalJobs.ui.screen.bookmark

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lokalJobs.R
import com.example.lokalJobs.data.local.modal.BookmarkJob
import com.example.lokalJobs.ui.navigation.BottomNavRoutes
import com.example.lokalJobs.ui.screen.MainViewModel


@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MainViewModel
) {

    val bookmarkJob by viewModel.bookmarkJob.collectAsState()

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(255,255,255))
                .padding(top = 4.dp, bottom = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Bookmarks",
                style = MaterialTheme.typography.headlineLarge
            )
        }

        when{
            bookmarkJob.isEmpty() -> NoBookmarkScreen()
            else -> BookmarkListScreen(modifier, bookmarkJob, navController, viewModel)
        }
    }
}

@Composable
fun NoBookmarkScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.not_found),
                contentDescription = "No Bookmark",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(32.dp))
            )
            Text("No Bookmark found.")
            Text("Please add bookmarks from Jobs section.")
        }
    }
}

@Composable
private fun BookmarkListScreen(
    modifier: Modifier,
    bookmarkJob: List<BookmarkJob>,
    navController: NavController,
    viewModel: MainViewModel
) {
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(bookmarkJob) { job ->

                BookmarkJobCard(
                    modifier = Modifier,
                    job = job,
                    onInfoClicked = {
                        navController.navigate(BottomNavRoutes.BookmarkDetail.passBookmarkJobId(job.id))
                    },
                    removeBookmarkJob = { viewModel.removeBookmarkJob(it) },
                    onCardClick = {
                        navController.navigate(BottomNavRoutes.BookmarkDetail.passBookmarkJobId(job.id))
                    }

                )
            }
        }
    }
}