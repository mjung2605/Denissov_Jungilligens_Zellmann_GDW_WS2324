package de.thkoeln.gm.djmanager.playlists

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import de.thkoeln.gm.djmanager.songs.Song
import de.thkoeln.gm.djmanager.users.User
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinColumns
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany

@Entity
class Playlist {

    var ort: String = ""
    var zeit: String = ""
    var playlist: List<Song> = listOf()
    var users: List<User> = listOf()


    // beziehung zwischen playlists uns songs
    @ManyToMany(mappedBy="songs")
    @JoinTable(name = "playlist_song", joinColumns = [JoinColumn(name = "playlist_id")], inverseJoinColumns = [JoinColumn(name = "song_id")])
    var songs: MutableSet<Song> = HashSet()

    override fun toString(): String {
        return "Auf der Party-Playlist ($ort, um $zeit Uhr) werden folgende Songs gespielt: $playlist (generiert aus Wünschen von $users)"
    }

}