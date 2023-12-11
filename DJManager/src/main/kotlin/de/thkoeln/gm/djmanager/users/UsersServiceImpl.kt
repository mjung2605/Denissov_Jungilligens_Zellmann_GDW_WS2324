package de.thkoeln.gm.djmanager.users

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsersServiceImpl(private var usersRepository: UsersRepository): UsersService {
    override fun findCurrentUser(id: UUID): User? {
        TODO()
    }

    override fun findById(id: UUID): User? {
        return usersRepository.findByIdOrNull(id)
    }

    override fun save(user: User) {
        usersRepository.save(user)
    }

    override fun delete(user: User) {
        usersRepository.delete(user)
    }

    override fun getPlaylists(user: User) {
        TODO()
    }
}