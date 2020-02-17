package me.coweery.app.services.auth

import me.coweery.app.models.AppUserDetails
import me.coweery.app.models.JwtPayload
import me.coweery.app.models.UserCreationParams
import me.coweery.app.services.auth.jwt.app.AppJwtService
import me.coweery.app.services.auth.jwt.google.GoogleJwtService
import me.coweery.app.services.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl @Autowired constructor(
    private val authenticationManager: AuthenticationManager,
    private val appJwtService: AppJwtService,
    private val googleJwtService: GoogleJwtService,
    private val userService: UserService
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

    override fun authenticateWithGoogle(googleToken: String): String {

        return googleJwtService.extractUserInfo(googleToken)
            .let { googleUserInfo ->
                val user = userService.getByEmail(googleUserInfo.email)
                    ?: userService.create(
                        UserCreationParams(
                            googleUserInfo.email,
                            null
                        )
                    )

                appJwtService.generateToken(
                    JwtPayload(user.id!!)
                )
            }
    }
}