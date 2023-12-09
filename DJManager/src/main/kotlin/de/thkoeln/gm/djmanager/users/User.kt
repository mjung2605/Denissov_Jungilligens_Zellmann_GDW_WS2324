package de.thkoeln.gm.djmanager.users

import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator
import java.util.*

/** Legt fest, ob der angemeldete Nutzer nur Songs hinzufügen oder auch löschen kann.
 * Das Löschen wird nur dem Admin ("DJ") der Party erlaubt, um gegenseitiges Löschen der Submissions unter Nutzern/
 * Gästen zu verhindern. **/
enum class Role {
    GUEST, ADMIN
}
class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    var id: UUID = UUID.randomUUID()

    var username: String = ""
    // spotify oAuth?
    var role: Role = Role.GUEST // als default wert

    override fun toString(): String {
        return "Nutzer in dieser Party-Playlist: "
    }

}