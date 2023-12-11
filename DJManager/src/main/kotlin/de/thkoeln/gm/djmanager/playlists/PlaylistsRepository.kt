package de.thkoeln.gm.djmanager.playlists

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PlaylistsRepository: CrudRepository<Playlist, UUID> {
}