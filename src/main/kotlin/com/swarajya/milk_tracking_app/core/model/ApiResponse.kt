package com.swarajya.milk_tracking_app.core.model

data class ApiResponse<T>(
    val statusCode: Int,
    val status : String,
    val message: String,
    val data: T? = null
)
enum class ApiStatus{
    SUCCESS, FAIL
}
