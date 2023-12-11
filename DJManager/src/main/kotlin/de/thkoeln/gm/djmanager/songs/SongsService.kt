package de.thkoeln.gm.djmanager.songs

import de.thkoeln.gm.djmanager.users.User
import java.util.*


interface SongsService {
    fun findByID(id: UUID): Song?
    //fun findByArtist(artistId: String): List<Song>?
    //fun getAllByVotes(): List<Song>?
    fun saveSong(song: Song)
    fun delete(song: Song)
    fun deleteAll()
}