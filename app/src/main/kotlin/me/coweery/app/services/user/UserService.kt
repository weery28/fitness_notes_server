package me.coweery.app.services.user

import me.coweery.app.models.User
import me.coweery.app.models.UserCreationParams

interface UserService {

    fun create(userCreationParams: UserCreationParams): User

    fun getByEmail(email: String): User?
}