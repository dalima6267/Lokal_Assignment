package com.example.lokalJobs.data.local.repository

import com.example.lokalJobs.data.local.BookmarkJobDao
import com.example.lokalJobs.data.local.modal.BookmarkJob
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkRepository @Inject constructor(
    private val bookmarkJobDao: BookmarkJobDao
) {

    // CRUD operations for BookmarkJob
    // Create
    suspend fun insertBookmarkJob(bookmarkJob: BookmarkJob) {
        bookmarkJobDao.insertBookmarkJob(bookmarkJob)
    }

    // Read
    fun getAllBookmarkJobs() : Flow<List<BookmarkJob>> = bookmarkJobDao.getAllBookmarkJobs()

    // Delete
    suspend fun deleteBookmarkJob(bookmarkJob: BookmarkJob) {
        bookmarkJobDao.deleteBookmarkJob(bookmarkJob)
    }

}