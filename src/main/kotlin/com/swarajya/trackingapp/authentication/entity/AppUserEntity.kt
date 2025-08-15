package com.swarajya.trackingapp.authentication.entity

import jakarta.persistence.*

@Entity
@Table(name = "milk_app_users")
data class AppUserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(nullable = false, unique = true)
    val userName: String,
    @Column(nullable = false)
    val password: String
)
