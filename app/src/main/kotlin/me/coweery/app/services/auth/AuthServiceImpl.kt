package me.coweery.app.services.auth

import me.coweery.app.models.AppUserDetails
import me.coweery.app.models.JwtPayload
import me.coweery.app.services.auth.jwt.app.AppJwtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl @Autowired constructor(
    private val authenticationManager: AuthenticationManager,
    private val appJwtService: AppJwtService
) : AuthService {

    override fun authenticate(username: String, password: String): String {

        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                username,
                password
            )
        )
        return appJwtService.generateToken(
            JwtPayload(
                (authentication.principal as AppUserDetails).id
            )
        )
    }
}