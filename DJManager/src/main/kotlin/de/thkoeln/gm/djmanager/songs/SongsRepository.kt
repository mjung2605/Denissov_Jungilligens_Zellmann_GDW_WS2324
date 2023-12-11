package de.thkoeln.gm.djmanager.songs

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.collections.List

@Repository
interface SongsRepository: CrudRepository<Song, UUID> {

    /** @param artistId Die Spotify-ID des Künstlers.
     * @return Null, wenn kein bisher hinzugefügter song von dem Künstler stammt.
     * @return den ersten Song, der von dem Künstler stammt **/
    //@Query(value = "SELECT s FROM Song s WHERE s.artistId = :artistId")
    //fun findByArtist(@Param("artistId") artistId: String): List<Song>?

    //@Query(value = "SELECT * FROM Song s ORDER BY s.votes DESC")
    //fun getAllOrderByVotes(): List<Song>?

}
