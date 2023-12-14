package de.thkoeln.gm.djmanager.spotifyauths

import org.springframework.http.HttpEntity
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.net.http.HttpHeaders

@RestController
class SpotifySearchController {

    @GetMapping("/spotifyauths")
    fun searchSong(
        @RequestParam("query") query: String,
        @RequestParam("accessToken") accessToken: String
    ): Any {
        val restTemplate = RestTemplate()

        val uri = "https://api.spotify.com/v1/search?q=$query&type=track"

        val responseEntity = restTemplate.getForEntity("$uri&access_token=$accessToken", Any::class.java)

        return responseEntity.body ?: "No results found"
    }
}
