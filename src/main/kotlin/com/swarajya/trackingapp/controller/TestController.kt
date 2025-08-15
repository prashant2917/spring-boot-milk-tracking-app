package com.swarajya.trackingapp.controller

import com.swarajya.trackingapp.core.model.ApiResponse
import com.swarajya.trackingapp.core.model.ApiStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @GetMapping("/hello")
    fun hello(): ResponseEntity<ApiResponse<String>> {
        val response = ApiResponse(
            statusCode = 200,
            status = ApiStatus.SUCCESS.name,
            message = "Welcome to milk Tracking App",
            data = "Welcome to milk Tracking App"
        )
        return ResponseEntity.ok(response)
    }

}