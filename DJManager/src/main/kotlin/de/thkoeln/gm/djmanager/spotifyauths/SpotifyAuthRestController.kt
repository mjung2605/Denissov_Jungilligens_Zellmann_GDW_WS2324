package de.thkoeln.gm.djmanager.spotifyauths

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpEntity
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.FormHttpMessageConverter
import org.springframework.ui.Model
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForEntity

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

        restTemplate.messageConverters.add(FormHttpMessageConverter())


        val headers = org.springframework.http.HttpHeaders().apply {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
        }

        val body = LinkedMultiValueMap<String, String>().apply {
            add("grant_type", "authorization_code")
            add("code", code)
            add("redirect_uri", redirectUri)
            add("client_id", clientId)
            add("client_secret", clientSecret)
        }


        val entity = HttpEntity(body, headers)

        val responseEntity: ResponseEntity<String> = restTemplate.postForEntity(
                "https://accounts.spotify.com/api/token",
        entity,
        SpotifyTokenResponse::class // Verwende hier den Typ der Antwort von Spotify
        )

        val responseBody = responseEntity.body // Hier sollte der Antworttext als String vorliegen

        val mapper = ObjectMapper()
        val map: Map<String, Any> = mapper.readValue(responseBody as String, object : TypeReference<Map<String, Any>>() {})
        val accessToken = map["access_token"] as? String ?: "No access token received"
        model.addAttribute("accessToken", accessToken)

        return accessToken // wird momentan einfach als string zurückgegeben
    }

}

data class SpotifyTokenResponse(
    val access_token: String,
    val token_type: String,
    val expires_in: Int
    // andere Felder entsprechend der Antwort
)