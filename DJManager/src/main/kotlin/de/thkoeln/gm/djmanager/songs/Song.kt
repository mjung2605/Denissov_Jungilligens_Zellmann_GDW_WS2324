package de.thkoeln.gm.djmanager.songs

import de.thkoeln.gm.djmanager.playlists.Playlist
import de.thkoeln.gm.djmanager.users.User
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.GenericGenerator
import java.util.*
import kotlin.collections.HashSet

@Entity
class Song {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    var id: UUID = UUID.randomUUID()

    var name: String = ""
    var songId: String = "" // identifier in spotify
    var artist: String = ""
    var artistId: String = "" // identifier in spotify
    var energylvl: Double = 0.0
    var votes: Int = 0

    @ManyToMany(mappedBy="songs")
    var playlists: MutableSet<Playlist> = HashSet()


    override fun toString(): String {
        return "Der Song $name von $artist hat momentan $votes Stimmen und ein Energielevel von $energylvl"
    }

}