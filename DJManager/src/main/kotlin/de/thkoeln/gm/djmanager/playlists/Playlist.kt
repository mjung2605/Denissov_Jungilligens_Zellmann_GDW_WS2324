package de.thkoeln.gm.djmanager.playlists

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import de.thkoeln.gm.djmanager.songs.Song
import de.thkoeln.gm.djmanager.users.User
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
class Playlist {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    var partyLoc: String = ""
    var partyDate: String = ""
    var partyTime: String = ""

    var adminName: String = ""

    // beziehung zwischen playlist und songs
    // @OneToMany(mappedBy="playlist")
    // var songs: MutableList<Song> = mutableListOf()

    // beziehung zwischen playlists und teilnehmenden usern
    // @ManyToMany
    // @JoinTable(name = "user_playlist", joinColumns = [JoinColumn(name = "user_id")], inverseJoinColumns = [JoinColumn(name = "playlist_id")])
    // var users: MutableList<User> = mutableListOf() // set hat keine duplikate

    override fun toString(): String {
        return "Auf der Party-Playlist ($partyLoc am $partyDate um $partyTime Uhr) werden folgende Songs gespielt:" // $songs (generiert aus Wünschen von $users)"
    }

}