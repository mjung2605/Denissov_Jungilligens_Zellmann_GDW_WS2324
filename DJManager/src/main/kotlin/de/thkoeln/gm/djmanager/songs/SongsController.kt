package de.thkoeln.gm.djmanager.songs

import de.thkoeln.gm.djmanager.users.Role
import de.thkoeln.gm.djmanager.users.User
import de.thkoeln.gm.djmanager.users.UsersService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Controller
class SongsController(private val songsService: SongsService, private val usersService: UsersService) {

    @PostMapping("/songs")
    @ResponseBody
    fun saveSong(name: String, artist: String): String {

        // hier später Verbindung zu Spotify API um Song eindeutig identifizieren zu können

        var song = Song()
        song.name = name
        song.artist = artist
        song.votes = 1

        // später Logik implementieren: wenn Song Duplikat: +1 Stimme

        songsService.saveSong(song)
        return song.toString()
    }

    @PostMapping("/songs")
    @ResponseBody
    fun saveSongByArtist(artistId: String): String {

        if (songsService.findByArtist(artistId)==null) throw Exception("Von dem Künstler ist noch kein Lied hinzugefügt worden. Die Funktion, dass dann automatisch ein Top-Song von dem Künstler gefunden wird, ist noch nicht implementiert.")

        // wenn schon ein/mehrere Song(s) des Künstlers hinzugefügt wurde(n), bekomm(t/en) diese(r) eine weitere Stimme
        val songsByArtist = songsService.findByArtist(artistId)!!
        var responseString = ""

        for (s in songsByArtist) {
            s.votes + 1
            responseString += s.toString()
        }

        return responseString
    }

    @GetMapping("/songs")
    @ResponseBody
    fun getSongsByVotesDesc(): String {
        val songList = songsService.getAllByVotes() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return "Eine Liste der Songs, sortiert nach Stimmenanzahl: $songList"
    }


    @DeleteMapping("/songs/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteSong(@PathVariable id: UUID, user: User) {

        // notwendig?
        var songById = songsService.findByID(id)
        var userById = usersService.findCurrentUser(user.id)

        if (songById != null && userById != null) {
            if (userById.role == Role.ADMIN) { // nur admin kann songs löschen

                songsService.delete(songById)

            }
            else throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/songs")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAllSongs(user: User) {

        // notwendig?
        var userById = usersService.findCurrentUser(user.id)

        if (userById != null && userById.role == Role.ADMIN) {
            songsService.deleteAll()

        }
        else throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
    }

}