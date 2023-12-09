package de.thkoeln.gm.djmanager.playlists

import de.thkoeln.gm.djmanager.songs.Song
import de.thkoeln.gm.djmanager.users.User

class Playlist {

    var ort: String = ""
    var zeit: String = ""
    var playlist: List<Song> = listOf()
    var users: List<User> = listOf()

    override fun toString(): String {
        return "Auf der Party-Playlist ($ort, um $zeit Uhr) werden folgende Songs gespielt: $playlist (generiert aus Wünschen von $users)"
    }

}