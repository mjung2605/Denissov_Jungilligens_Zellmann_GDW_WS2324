package de.thkoeln.gm.djmanager.poc

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import com.adamratzman.spotify.SpotifyApiBuilder
import com.adamratzman.spotify.SpotifyAppApi
import com.adamratzman.spotify.SpotifyClientApi
import com.adamratzman.spotify.auth.SpotifyApiOptions
import com.adamratzman.spotify.models.Track

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

        val api = SpotifyApiBuilder(
            SpotifyApiOptions.Builder()
                .clientId("8010080d996747fe9c939b66ece3377a")
                .clientSecret("8060fe36cf604f6aa92bc000c4e1b89a")
                .redirectUri("http://localhost:8080/poc")
                .build()
        ).build()

        // Erstellen der Spotify-API
        val appApi = SpotifyAppApi(api)

        // Erstellen der Spotify-Client-API
        val clientApi = SpotifyClientApi(api)


        appApi.getCurrentTrack { track ->
            println("Aktueller Track: ${track.name} von ${track.artists.joinToString(", ")}")
        }


        appApi.getCurrentTrackImage { bitmap ->

        }


        return "Run poc"
    }
}
