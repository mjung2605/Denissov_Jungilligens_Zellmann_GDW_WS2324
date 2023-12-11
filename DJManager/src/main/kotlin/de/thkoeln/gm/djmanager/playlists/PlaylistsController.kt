package de.thkoeln.gm.djmanager.playlists


import de.thkoeln.gm.djmanager.users.UsersService
import org.springframework.http.HttpStatus
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
class PlaylistsController(private val usersService: UsersService, private val playlistsService: PlaylistsService) {

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

    @PostMapping("/playlists/{playlistid}")
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