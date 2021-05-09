package com.testapp.data.repo.repo_base

interface RepoCallback<T> {
    fun onResult(result: Resource<T?, Resource.Status>)
}