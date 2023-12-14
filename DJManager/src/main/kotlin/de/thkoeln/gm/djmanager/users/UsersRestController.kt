package de.thkoeln.gm.djmanager.users

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
class UsersRestController(private val usersService: UsersService) {

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveUser(username: String, oauth: String): User {

        // hier später Verbindung zu Spotify API um Song eindeutig identifizieren zu können

        val user = User()
        user.username = username

        // später Logik implementieren: wenn Song Duplikat: +1 Stimme

        return user
    }

    @GetMapping("/users/{userid}")
    fun getUser(@PathVariable userid: UUID): User {
        return usersService.findById(userid) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/users/{userid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable userid: UUID) {
        val user = usersService.findById(userid) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        // TODO("authentification check implementieren")
        usersService.delete(user)
    }


    /*
    @GetMapping("/users/{userid}/playlists")
    fun getUserPLaylists(@PathVariable userid: UUID): List<Playlist> {
        val user = usersService.findById(userid) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return user.playlists
    }
    
     */
}