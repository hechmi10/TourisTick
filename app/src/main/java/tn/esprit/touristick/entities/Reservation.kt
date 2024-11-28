package tn.esprit.touristick.entities

class Reservation() {
    private var id=0
    private var nom=""
    private var place=""
    private var type:TypeReservation = TODO()
    private var prix=0.00

    constructor(id:Int,nom:String,place:String,type:TypeReservation,prix:Double):this(){
        this.id=id
        this.nom=nom
        this.place=place
        this.type=type
        this.prix=prix
    }

}