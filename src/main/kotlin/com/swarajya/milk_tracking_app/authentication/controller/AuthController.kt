package com.swarajya.milk_tracking_app.authentication.controller

import com.swarajya.milk_tracking_app.authentication.model.LoginUserResponse
import com.swarajya.milk_tracking_app.authentication.model.UserResponse
import com.swarajya.milk_tracking_app.authentication.service.AuthService
import com.swarajya.milk_tracking_app.core.model.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class AuthRequest(val userName : String, val password : String)
@RestController
@RequestMapping("/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/signup")
    fun signUp(@RequestBody authRequest: AuthRequest): ResponseEntity<ApiResponse<UserResponse>> {
        return authService.signUp(authRequest.userName, authRequest.password)

    }
    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthRequest) : ResponseEntity<ApiResponse<UserResponse>> {
       return authService.login(authRequest.userName, authRequest.password)

    }
}