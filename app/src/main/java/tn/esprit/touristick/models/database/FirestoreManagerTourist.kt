package tn.esprit.touristick.models.database

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import tn.esprit.touristick.models.Tourist

class FirestoreManagerTourist {
    private val db:FirebaseFirestore=FirebaseFirestore.getInstance()

    companion object {
        private const val TAG="FirestoreManagerTourist"
    }

    fun addTourist(tourist:Tourist , context:Context) {
        val touristData=hashMapOf(
            "cin" to tourist.getCin() ,
            "nom" to tourist.getNom() ,
            "prenom" to tourist.getPrenom() ,
            "email" to tourist.getEmail() ,
            "mdp" to tourist.getMdp()
        )

        db.collection("Tourists")
            .add(touristData)
            .addOnSuccessListener {
                Log.d(TAG , "Tourist added with ID: ${it.id}")
                Toast.makeText(context , "Tourist uploaded successfully!" , Toast.LENGTH_SHORT)
                    .show()
            }
            .addOnFailureListener { e ->
                Log.e(TAG , "Error adding tourist: $e")
                Toast.makeText(context , "Failed to upload tourist!" , Toast.LENGTH_SHORT).show()
            }
    }

    fun searchTourist(email:String,mdp:String , callback:(Tourist?) -> Unit) {
        db.collection("Tourists")
            .whereEqualTo("email" , email)
            .whereEqualTo("mdp",mdp)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val document=querySnapshot.documents.firstOrNull()
                if (document != null) {
                    val tourist=Tourist(
                        document.getString("cin") ?: "" ,
                        document.getString("nom") ?: "" ,
                        document.getString("prenom") ?: "" ,
                        document.getString("email") ?: "" ,
                        document.getString("mdp") ?: ""
                    )
                    callback(tourist)
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener {
                Log.e(TAG , "Error fetching tourist: $it")
                callback(null)
            }
    }

    fun deleteTourist(cin:String , context:Context) {
        db.collection("Tourists")
            .whereEqualTo("cin" , cin)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    document.reference.delete().addOnSuccessListener {
                        Log.d(TAG , "Tourist deleted successfully!")
                        Toast.makeText(
                            context ,
                            "Tourist deleted successfully!" ,
                            Toast.LENGTH_SHORT
                        ).show()
                    }.addOnFailureListener {
                        Log.e(TAG , "Failed to delete tourist: $it")
                        Toast.makeText(context , "Failed to delete tourist!" , Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
            .addOnFailureListener {
                Log.e(TAG , "Error fetching tourist: $it")
                Toast.makeText(context , "Failed to delete tourist!" , Toast.LENGTH_SHORT).show()
            }
    }

    fun updatePassword(tourist:Tourist , context:Context) {
        db.collection("Tourists")
            .whereEqualTo("cin" , tourist.getCin())
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    val updates=hashMapOf(
                        "mdp" to tourist.getMdp()
                    )
                    document.reference.update(updates as Map<String , Any>)
                        .addOnSuccessListener {
                            Toast.makeText(
                                context ,
                                "Mot de passe modifié avec succés!" ,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .addOnFailureListener { e ->
                            Log.e(TAG , "échec du modification du mot de passe: $e")
                            Toast.makeText(
                                context ,
                                "échec du modification du mot de passe!" ,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
            }
            .addOnFailureListener {
                Log.e(TAG , "Touriste non disponible: $it")
                Toast.makeText(context , "Touriste non disponible!" , Toast.LENGTH_SHORT).show()
            }
    }
}