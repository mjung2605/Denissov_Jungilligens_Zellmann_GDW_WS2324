package de.thkoeln.gm.djmanager.users

import de.thkoeln.gm.djmanager.songs.Song
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class UsersController {



    @GetMapping("/users")
    @ResponseBody
    fun saveUser(username: String, oauth: String): String {

        // hier später Verbindung zu Spotify API um Song eindeutig identifizieren zu können

        var user = User()
        user.username = username

        // später Logik implementieren: wenn Song Duplikat: +1 Stimme

        return user.toString()
    }


}