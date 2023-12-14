package de.thkoeln.gm.djmanager.playlists

import java.util.*

interface PlaylistsService {

    fun findById(id: UUID): Playlist?
    fun save(playlist: Playlist): Playlist
    fun delete(playlist: Playlist)

}