package de.thkoeln.gm.djmanager.playlists


import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class PlaylistsController {

    @GetMapping("/playlists")
    @ResponseBody
    fun savePlaylist(zeit: String, ort: String): String {

        var playlist = Playlist()
        playlist.ort = ort
        playlist.zeit = zeit

        // Playlist-Generierung implementieren

        return playlist.toString()
    }


}