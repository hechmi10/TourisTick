package tn.esprit.touristick.models

class Tourist() {
    //Déclaration et initialisation des données de l'utilisateur
    private var cin=""
    private var nom=""
    private var prenom=""
    private var email=""
    private var mdp=""

    constructor(
        cin:String ,
        nom:String ,
        prenom:String ,
        email:String ,
        mdp:String
    ) : this() {
        this.cin=cin
        this.nom=nom
        this.prenom=prenom
        this.email=email
        this.mdp=mdp
    }

    fun getCin():String {
        return cin
    }

    fun setCin(cin:String) {
        this.cin=cin
    }

    fun getNom():String {
        return nom
    }

    fun setNom(nom:String) {
        this.nom=nom
    }

    fun getPrenom():String {
        return prenom
    }

    fun setPrenom(prenom:String) {
        this.prenom=prenom
    }

    fun getEmail():String {
        return email
    }

    fun setEmail(email:String) {
        this.email=email
    }

    fun getMdp():String {
        return mdp
    }

    fun setMdp(mdp:String) {
        this.mdp=mdp
    }

    override fun toString():String {
        return "Cin:$cin\nNom:$nom\nPrenom:$prenom\nEmail:$email\nMot de passe:$mdp\n"
    }
}