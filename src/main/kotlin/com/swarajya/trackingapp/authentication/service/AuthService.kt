package com.swarajya.trackingapp.authentication.service

import com.swarajya.trackingapp.authentication.entity.AppUserEntity
import com.swarajya.trackingapp.authentication.model.LoginUserResponse
import com.swarajya.trackingapp.authentication.model.UserResponse
import com.swarajya.trackingapp.authentication.model.invalidCredentialsResponse
import com.swarajya.trackingapp.authentication.repository.UserRepository
import com.swarajya.trackingapp.core.model.ApiResponse
import com.swarajya.trackingapp.core.model.ApiStatus
import com.swarajya.trackingapp.utils.AppConstants
import com.swarajya.trackingapp.utils.JWtUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jWtUtils: JWtUtils
) {

    fun signUp(userName: String, password: String): ResponseEntity<ApiResponse<UserResponse>> {
        if (userRepository.findByUserName(userName) != null) {
            return ResponseEntity.badRequest().body(
                ApiResponse(
                    statusCode = HttpStatus.BAD_REQUEST.value(),
                    status = ApiStatus.FAIL.name,
                    message = AppConstants.ApiMessage.USER_ALREADY_EXIST
                )
            )
        } else {
            val hashPassword = passwordEncoder.encode(password)
            println("hash password $hashPassword")
            val userEntity = AppUserEntity(userName = userName, password = hashPassword)
            userRepository.save(userEntity)
            return ResponseEntity.ok(
                ApiResponse(
                    statusCode = HttpStatus.OK.value(),
                    status = ApiStatus.SUCCESS.name,
                    message = AppConstants.ApiMessage.USER_REGISTER_SUCCESS,
                    data = UserResponse(userName = userName)
                )
            )

        }
    }

    fun login(userName: String, password: String): ResponseEntity<ApiResponse<UserResponse>> {
        val user = userRepository.findByUserName(userName)
            ?: return invalidCredentialsResponse()
        if (!passwordEncoder.matches(password, user.password)) {
            return invalidCredentialsResponse()
        }

        val token = jWtUtils.generateToken(userName)
        println("Generating token $token")
        return ResponseEntity.ok(
            ApiResponse(
                HttpStatus.OK.value(), status = ApiStatus.SUCCESS.name, message = AppConstants.ApiMessage.LOGIN_SUCCESS,
                LoginUserResponse(userName = userName, token = token)
            )
        )
    }
}