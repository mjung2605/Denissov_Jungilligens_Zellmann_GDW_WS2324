package de.thkoeln.gm.djmanager.users

import de.thkoeln.gm.djmanager.playlists.Playlist
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import org.hibernate.annotations.GenericGenerator
import java.util.*
import kotlin.collections.HashSet


@Entity
class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    var id: UUID = UUID.randomUUID()

    var username: String = ""
    // spotify oAuth?


    //var playlists: MutableList<Playlist> = mutableListOf()

    override fun toString(): String {
        return "Nutzer in dieser Party-Playlist: $username"
    }

}