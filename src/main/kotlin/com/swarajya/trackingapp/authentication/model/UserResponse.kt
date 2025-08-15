package com.swarajya.trackingapp.authentication.model

import com.swarajya.trackingapp.core.model.ApiResponse
import com.swarajya.trackingapp.core.model.ApiStatus
import com.swarajya.trackingapp.utils.AppConstants
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

open  class UserResponse(
    open val userName : String,
    )

class LoginUserResponse(override val userName: String, val token : String) : UserResponse(userName = userName)

fun invalidCredentialsResponse(): ResponseEntity<ApiResponse<UserResponse>> {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(
        ApiResponse(
            statusCode = HttpStatus.UNAUTHORIZED.value(),
            status = ApiStatus.FAIL.name,
            message = AppConstants.ApiMessage.INVALID_CREDENTIALS
        )
    )
}