package me.coweery.app.rest.controllers.user

import me.coweery.app.models.UserCreationParams
import me.coweery.app.rest.controllers.user.models.CreateUserRequest
import me.coweery.app.rest.controllers.user.models.UserClientModel
import me.coweery.app.services.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController @Autowired constructor(
    private val userService: UserService
) {

    @PostMapping
    fun create(
        createUserRequest: CreateUserRequest
    ): UserClientModel {

        return createUserRequest.validate()
            .let {
                userService.create(
                    UserCreationParams(
                        it.login!!,
                        it.password!!
                    )
                )
            }
            .let {
                UserClientModel(
                    it.id!!,
                    it.email
                )
            }
    }
}