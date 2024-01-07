package com.sdt.proshop.security.repositories

import com.sdt.proshop.security.models.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface UserRepository: MongoRepository<User, String> {

    @Query("{username: '?0'}")
    fun findUserByUsername(username: String): User

}