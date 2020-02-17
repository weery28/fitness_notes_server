package me.coweery.app.services.userdetails

import me.coweery.app.models.AppUserDetails
import me.coweery.app.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AppUserDetailsServiceImpl @Autowired constructor(
    private val userRepository: UserRepository
) : AppUserDetailsService {

    override fun loadUserByUsername(email: String): AppUserDetails {

        return userRepository.findByEmail(email)?.let {
            AppUserDetails(
                it.id!!,
                it.email,
                it.passwordHash!!
            )
        } ?: throw UsernameNotFoundException("User not found: email = $email")
    }
}