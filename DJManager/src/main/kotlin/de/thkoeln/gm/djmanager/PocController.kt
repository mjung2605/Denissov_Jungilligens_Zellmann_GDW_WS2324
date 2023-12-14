package de.thkoeln.gm.djmanager

/*
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import com.adamratzman.spotify.SpotifyApiBuilder
import com.adamratzman.spotify.SpotifyApiOptions
import com.adamratzman.spotify.SpotifyAppApi
import com.adamratzman.spotify.SpotifyClientApi


@Controller
class PocController {

    @GetMapping("/")
    @ResponseBody
    fun sayHelloWorld(): String {
        return "Hello from Poc"
    }

    @GetMapping("/")
    @ResponseBody
    fun runPoc(): String {

        val spotifyApi = SpotifyApiBuilder(
                 "8010080d996747fe9c939b66ece3377a",
                "8060fe36cf604f6aa92bc000c4e1b89a",
                "http://localhost:8080/poc"
        ).authorization

        val appApi = SpotifyAppApi("8010080d996747fe9c939b66ece3377a", "8060fe36cf604f6aa92bc000c4e1b89a", "http://localhost:8080/poc")

        // Erstellen der Spotify-Client-API
        val clientApi = SpotifyClientApi()


        appApi.getCurrentTrack { track ->
            println("Aktueller Track: ${track.name} von ${track.artists.joinToString(", ")}")
        }


        appApi.getCurrentTrackImage { bitmap ->

        }
        return "Run poc"
    }
}

 */
