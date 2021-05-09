package com.testapp.data.repo.repo_base

class Resource<T, M>(val payload: T?, val status: M?, val message: String?) {
    enum class Status {
        SUCCESS, ERROR
    }
}