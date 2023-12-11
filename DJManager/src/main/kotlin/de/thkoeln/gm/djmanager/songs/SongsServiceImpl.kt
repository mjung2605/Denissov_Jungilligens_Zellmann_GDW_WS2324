package de.thkoeln.gm.djmanager.songs

import de.thkoeln.gm.djmanager.users.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class SongsServiceImpl(private val songsRepository: SongsRepository) : SongsService {
    override fun findByID(id: UUID): Song? {
        return songsRepository.findByIdOrNull(id)
    }

    /**
    override fun findByArtist(artistId: String): List<Song>? {
        return songsRepository.findByArtist(artistId)
    }
    **/

    /**
    override fun getAllByVotes(): List<Song>? {
        return songsRepository.getAllOrderByVotes()
    }
    **/

    // saves new song with +1 vote, if already saved adds a vote
    override fun saveSong(song: Song) {
        songsRepository.save(song)
    }

    override fun delete(song: Song) {
        songsRepository.delete(song)
    }

    override fun deleteAll() {
        songsRepository.deleteAll()
    }

}