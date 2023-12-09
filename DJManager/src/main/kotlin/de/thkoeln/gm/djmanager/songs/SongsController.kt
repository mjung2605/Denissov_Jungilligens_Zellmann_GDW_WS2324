package de.thkoeln.gm.djmanager.songs

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class SongsController {

    @GetMapping("/songs")
    @ResponseBody
    fun saveSong(name: String, interpret: String): String {

        // hier später Verbindung zu Spotify API um Song eindeutig identifizieren zu können

        var song = Song()
        song.name = name
        song.interpret = interpret
        song.stimmanzahl = 1

        // später Logik implementieren: wenn Song Duplikat: +1 Stimme

        return song.toString()
    }


}