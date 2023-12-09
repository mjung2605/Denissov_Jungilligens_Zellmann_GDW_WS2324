package de.thkoeln.gm.djmanager.users

import org.springframework.stereotype.Service
import java.util.*

@Service
class UsersServiceImpl(private var usersRepository: UsersRepository): UsersService {
    override fun findCurrentUser(id: UUID): User? {
        TODO()
    }
}