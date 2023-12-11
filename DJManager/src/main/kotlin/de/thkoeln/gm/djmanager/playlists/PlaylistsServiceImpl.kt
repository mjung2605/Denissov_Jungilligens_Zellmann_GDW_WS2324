package de.thkoeln.gm.djmanager.playlists

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class PlaylistsServiceImpl(private val playlistsRepository: PlaylistsRepository) : PlaylistsService {
    override fun findById(id: UUID): Playlist? {
        return playlistsRepository.findByIdOrNull(id)
    }

    override fun save(playlist: Playlist): Playlist {
        playlistsRepository.save(playlist)
        return playlist
    }

    override fun delete(playlist: Playlist) {
        playlistsRepository.delete(playlist)
    }


}