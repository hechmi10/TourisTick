package tn.esprit.touristick.entities

class Reservation() {
    private var nom=""
    private var place=""
    private var type:TypeReservation = TODO()
    private var prix=0.00

    constructor(nom:String,place:String,type:TypeReservation,prix:Double):this(){
        this.nom=nom
        this.place=place
        this.type=type
        this.prix=prix
    }

    fun getNom():String{return nom}
    fun setNom(nom:String){this.nom=nom}
    fun getPlace():String{return place}
    fun setPlace(place: String){this.place=place}
    fun getType():TypeReservation{return type}
    fun setType(type:TypeReservation){this.type=type}
    fun getPrix():Double{return prix}
    fun setPrix(prix:Double){this.prix=prix}

}