package de.thkoeln.gm.djmanager.spotifyauths

import org.springframework.http.HttpEntity
import org.springframework.http.MediaType
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class SpotifyAuthRestController {

    // von spotify developers app-Erstellung (Account justmeike)
    val clientId = "255d9fed2f36413d9500bf9f18525d33"
    val clientSecret = "2a52f2e8022a4347a39aedd1c967fce7"
    val redirectUri = "http://localhost:8080/callback"
    val scopes = "user-read-private user-read-email" // scopes (nutzerberechtigungen)

    // auth redirect
    @GetMapping("/authorize")
    fun authorizeSpotify(): String {

        val authorizeUrl = "https://accounts.spotify.com/authorize" +
                "?client_id=$clientId" +
                "&response_type=code" +
                "&redirect_uri=$redirectUri" +
                "&scope=$scopes"

        return "redirect:$authorizeUrl"
    }

    // erhalten des authorization codes für token (gebraucht für Zugriff auf api)
    @GetMapping("/callback")
    fun callback(@RequestParam("code") code: String, model: Model): String {
        val restTemplate = RestTemplate()
        val headers = org.springframework.http.HttpHeaders().apply {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
        }
        val body = mapOf(
            "grant_type" to "authorization_code",
            "code" to code,
            "redirect_uri" to redirectUri,
            "client_id" to clientId,
            "client_secret" to clientSecret
        )

        val entity = HttpEntity(body, headers)

        val response = restTemplate.postForEntity(
            "https://accounts.spotify.com/api/token",
            entity,
            Map::class.java
        )

        val accessToken = response.body?.get("access_token") ?: "No access token received"
        model.addAttribute("accessToken", accessToken)
        return "callback"
    }
}