package me.coweery.app.services.user

import me.coweery.app.exceptions.InvalidInputException
import me.coweery.app.models.User
import me.coweery.app.models.UserCreationParams
import me.coweery.app.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl @Autowired constructor(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {

    override fun create(userCreationParams: UserCreationParams): User {

        return if (!userRepository.existsByEmail(userCreationParams.email)) {
            userRepository.save(
                User(
                    null,
                    userCreationParams.email,
                    userCreationParams.password?.let {
                        passwordEncoder.encode(it)
                    }
                )
            )
        } else {
            throw InvalidInputException("User with email = ${userCreationParams.email} already exist")
        }
    }

    override fun getByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }
}