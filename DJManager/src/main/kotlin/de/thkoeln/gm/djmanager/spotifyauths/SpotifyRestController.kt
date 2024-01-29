package de.thkoeln.gm.djmanager.spotifyauths

import de.thkoeln.gm.djmanager.songs.SongsService
import org.springframework.http.HttpEntity
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.net.http.HttpHeaders
import org.json.JSONObject
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import de.thkoeln.gm.djmanager.songs.Song
@RestController
class SpotifySearchController(private val songsService: SongsService) {

     //Nach einem Song suchen
    @GetMapping("/spotifyauth")
    fun searchSong(
        @RequestParam("song") song: String,
        @RequestParam("interpret") interpret: String,
        @RequestParam("accessToken") accessToken: String
    ): Any {
        val restTemplate = RestTemplate()

        val uri = "https://api.spotify.com/v1/search?q=track:$song&artist:$interpret&type=track&limit=10"
        val responseEntity = restTemplate.getForEntity("$uri&access_token=$accessToken", Any::class.java)
        return responseEntity.body ?: "No results found"
    }

    //Nach einem Song suchen und in der Playlist abspeichern
    @GetMapping("/findAndSaveOneSong")
    fun findAndSaveOneSong(
        @RequestParam("song") song: String,
        @RequestParam("interpret") interpret: String,
        @RequestParam("accessToken") accessToken: String
    ): Boolean {
        val songs:List<String> = getSongsByTitleAndInterpret(song, interpret, accessToken) //Methode sucht nach bestimmten Song
        if(songs.size==1){
            saveSong(songs[0])
            return true
        }
        else{
            return false
        }
    }

    //Suchen nach Titel und Interpret
    @GetMapping("/findSongs")
    fun findSongs(
        @RequestParam("song") song: String,
        @RequestParam("interpret") interpret: String,
        @RequestParam("accessToken") accessToken: String
    ): List<String> {
        val songs:List<String> = getSongsByTitleAndInterpret(song, interpret, accessToken) //Methode gibt Liste mit Songs zurück
        return songs
    }

    //Methode wird aufgerufen, nachdem Benutzer einen bestimmten Song aus der Songliste ausgewählt hat
    @GetMapping("/saveSong")
    fun findSongs(
        @RequestParam("titel") titel: String,
    ) {
        saveSong(titel)
    }

    //Songtitel wird der Playlist hinzugefügt
    fun saveSong(titel:String){
       var song = Song()
       song.name = titel
       songsService.saveSong(song)
   }
    //Suche innerhalb Spotify-Webservice nach Song, Titel und Interpret
    fun getSongsByTitleAndInterpret(title: String, interpret: String, accessToken: String): List<String> {
        val client = HttpClients.createDefault()
        val request = HttpGet("https://api.spotify.com/v1/search?q=track:$title&artist:$interpret&type=track") //Spotify Web API wird aufgerufen
        request.addHeader("Authorization", "Bearer $accessToken")
        val response = client.execute(request)
        val data = JSONObject(EntityUtils.toString(response.entity)) //Antwort der Spotify Web API wird geparst (Aufteilung in Songs und Interpreten)
        val songs = mutableListOf<String>()
        val items = data.getJSONObject("tracks").getJSONArray("items")
        for (i in 0 until items.length()) {
            songs.add(items.getJSONObject(i).getString("name")) //Songnamen werden zur Playlist hinzugefügt
        }
        return songs
    }

}
