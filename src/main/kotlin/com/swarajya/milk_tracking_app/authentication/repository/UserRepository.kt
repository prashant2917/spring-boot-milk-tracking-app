package com.swarajya.milk_tracking_app.authentication.repository

import com.swarajya.milk_tracking_app.authentication.entity.AppUserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<AppUserEntity, Long> {
    fun findByUserName(username: String): AppUserEntity?
}