package de.thkoeln.gm.djmanager.songs

import de.thkoeln.gm.djmanager.playlists.PlaylistsService
import de.thkoeln.gm.djmanager.users.User
import de.thkoeln.gm.djmanager.users.UsersService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
class SongsRestController(private val songsService: SongsService, private val usersService: UsersService, val playlistsService: PlaylistsService) {

    @PostMapping("playlists/{playlistid}/songs")
    @ResponseBody
    fun saveSong(name: String, artist: String, @PathVariable playlistid: String): Song {

        // hier später Verbindung zu Spotify API um Song eindeutig identifizieren zu können

        var song = Song()
        song.name = name
        song.artist = artist
        song.votes = 1

        // später Logik implementieren: wenn Song Duplikat: +1 Stimme

        songsService.saveSong(song)
        return song
    }

    /**
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

    @GetMapping("/playlists/{playlistid}/songs")
    @ResponseBody // TODO(ändern, dass einfach eine liste zurückgegeben wird)
    fun getSongsByVotesDesc(@PathVariable playlistid: UUID): String {
        val songList = songsService.getAllByVotes() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return "Eine Liste der Songs, sortiert nach Stimmenanzahl: $songList"
    }
    **/

    @PutMapping("/playlists/{playlistid}/songs/{songid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateSongVoteIncrByOne(@PathVariable playlistid: UUID, @PathVariable songid: UUID) {

        val playlistByID = playlistsService.findById(playlistid) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val songId = songsService.findByID(songid) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)


    }

    @DeleteMapping("/playlists/{playlistid}/songs/{songid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteSong(@PathVariable playlistid: UUID, @PathVariable songid: UUID, user: User) {

        // notwendig?
        val playlistById = playlistsService.findById(playlistid)
        var songById = songsService.findByID(songid)
        var userById = usersService.findCurrentUser(user.id)

        if (songById != null && userById != null && playlistById != null) {
            if (userById.username == playlistById.adminName) { // nur admin kann songs löschen

                songsService.delete(songById)

            }
            else throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/playlists/{playlistid}/songs")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAllSongs(@PathVariable playlistid: UUID, user: User) {

        val playlistById = playlistsService.findById(playlistid)
        val userById = usersService.findCurrentUser(user.id)

        if (userById != null && playlistById != null) {
            if (userById.username == playlistById.adminName) {
                songsService.deleteAll()
            } else throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

}