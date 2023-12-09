package de.thkoeln.gm.djmanager.songs

class Song {

    var name: String = ""
    var interpret: String = ""
    var energielevel: Double = 0.0
    var stimmanzahl: Int = 0

    override fun toString(): String {
        return "Der Song $name von $interpret hat momentan $stimmanzahl Stimmen und ein Energielevel von $energielevel"
    }

}