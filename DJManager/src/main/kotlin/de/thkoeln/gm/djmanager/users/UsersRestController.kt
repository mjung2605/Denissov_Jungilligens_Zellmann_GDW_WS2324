package de.thkoeln.gm.djmanager.users

import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
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

    // spotify test
    @GetMapping("/users/{access_token}")
    fun getSpotifyUserProfile(@PathVariable access_token: String): ResponseEntity<User> {
        val restTemplate = RestTemplate()
        val headers = org.springframework.http.HttpHeaders().apply {
            add("Authorization", "Bearer $access_token")
        }

        val entity = HttpEntity(null, headers)

        return restTemplate.exchange(
            "https://api.spotify.com/v1/me",
            HttpMethod.GET,
            entity,
            User::class.java
        )
    }

    /*
    @GetMapping("/users/{userid}")
    fun getUser(@PathVariable userid: UUID): User {
        return usersService.findById(userid) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

     */

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