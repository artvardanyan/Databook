package com.data.core.remote.helpers

data class ApiResponse<out T>(val success: T? = null, val error: Throwable? = null)