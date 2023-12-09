package de.thkoeln.gm.djmanager.songs

import de.thkoeln.gm.djmanager.users.User
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator
import java.util.*

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


    override fun toString(): String {
        return "Der Song $name von $artist hat momentan $votes Stimmen und ein Energielevel von $energylvl"
    }

}