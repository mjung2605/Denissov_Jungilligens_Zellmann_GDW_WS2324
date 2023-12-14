package de.thkoeln.gm.djmanager.playlists


import de.thkoeln.gm.djmanager.users.UsersService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
class PlaylistsRestController(private val usersService: UsersService, private val playlistsService: PlaylistsService) {

    @PostMapping("/playlists")
    @ResponseStatus(HttpStatus.CREATED)
    fun savePlaylist(partyLoc: String, partyDate: String, partyTime: String): Playlist {

        val playlist = Playlist()
        playlist.partyLoc = partyLoc
        playlist.partyDate = partyDate
        playlist.partyTime = partyTime

        // TODO("getCurrentUser als admin setzen")

        playlistsService.save(playlist)

        // Playlist-Generierung implementieren

        return playlist
    }

    @GetMapping("/playlists/{playlistid}")
    fun getPlaylist(@PathVariable playlistid: UUID): Playlist {
        return playlistsService.findById(playlistid) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/playlists/{playlistid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePlaylist(@PathVariable playlistid: UUID) {
        // TODO("authentification check implementieren")
        val playlist = playlistsService.findById(playlistid) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        playlistsService.delete(playlist)
    }


}