package tn.esprit.touristick.models

class Reservation() {
    private var nom=""
    private var place=""
    private lateinit var type:TypeReservation
    private var prix=""

    constructor(nom:String , place:String , type:TypeReservation , prix:String) : this() {
        this.nom=nom
        this.place=place
        this.type=type
        this.prix=prix
    }

    fun getNom():String {
        return nom
    }

    fun setNom(nom:String) {
        this.nom=nom
    }

    fun getPlace():String {
        return place
    }

    fun setPlace(place:String) {
        this.place=place
    }

    fun getType():TypeReservation {
        return type
    }

    fun setType(type:TypeReservation) {
        this.type=type
    }

    fun getPrix():String {
        return prix
    }

    fun setPrix(prix:String) {
        this.prix=prix
    }

}