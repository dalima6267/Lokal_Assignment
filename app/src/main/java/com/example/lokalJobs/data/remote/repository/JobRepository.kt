package com.example.lokalJobs.data.remote.repository

import com.example.lokalJobs.data.remote.model.JobEntity
import com.example.lokalJobs.data.remote.JobApi
import retrofit2.Response
import javax.inject.Inject

class JobRepository @Inject constructor(
    private val jobApi: JobApi
) {

    suspend fun getJobs(page: Int): Response<JobEntity> {
        return jobApi.getJobs(page)
    }

}