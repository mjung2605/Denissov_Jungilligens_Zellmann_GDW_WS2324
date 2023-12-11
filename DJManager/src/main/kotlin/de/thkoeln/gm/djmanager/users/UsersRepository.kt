package de.thkoeln.gm.djmanager.users

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsersRepository: CrudRepository<User, UUID> {
}