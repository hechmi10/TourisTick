package tn.esprit.touristick.entities

class Tourist() {
    //Déclaration et initialisation des données de l'utilisateur
    private var cin=0
    private var nom=""
    private var prenom=""
    private var email=""
    private var mdp=""
    constructor(cin:Int, nom:String, prenom:String, email:String, mdp:String):this(){
        this.cin=cin
        this.nom=nom
        this.prenom=prenom
        this.email=email
        this.mdp=mdp
    }

    private fun getCin():Int{return cin}
    private fun setCin(cin:Int){this.cin=cin}
    private fun getNom():String{return nom}
    private fun setNom(nom:String){this.nom=nom}
    private fun getPrenom():String{return prenom}
    private fun setPrenom(prenom:String){this.prenom=prenom}
    private fun getEmail():String{return email}
    private fun setEmail(email:String){this.email=email}
    private fun getMdp():String{return mdp}
    private fun setMdp(mdp:String){this.mdp=mdp}

    override fun toString(): String {
        return "Cin:$cin\nNom:$nom\nPrenom:$prenom\nEmail:$email\nMot de passe:$mdp\n"
    }
}