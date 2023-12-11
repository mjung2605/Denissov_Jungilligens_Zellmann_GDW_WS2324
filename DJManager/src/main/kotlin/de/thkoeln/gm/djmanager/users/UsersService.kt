package de.thkoeln.gm.djmanager.users

import java.util.*

interface UsersService {
    fun findCurrentUser(id: UUID): User?
    fun findById(id: UUID): User?
    fun save(user: User)
    fun delete(user: User)
    //fun getPlaylists(user: User)
}