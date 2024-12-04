package tn.esprit.touristick.database

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import tn.esprit.touristick.entities.Reservation
import tn.esprit.touristick.entities.TypeReservation

class FirestoreManagerReservation {
    private val db:FirebaseFirestore=FirebaseFirestore.getInstance()

    companion object {
        private const val TAG="FirestoreManagerReservation"
    }

    fun addReservation(reservation:Reservation , context:Context) {
        val reservationData=hashMapOf(
            "nom" to reservation.getNom() ,
            "place" to reservation.getPlace() ,
            "type" to reservation.getType().name ,
            "prix" to reservation.getPrix()
        )

        db.collection("Reservations")
            .add(reservationData)
            .addOnSuccessListener {
                Log.d(TAG , "Reservation added with ID: ${it.id}")
                Toast.makeText(context , "Reservation uploaded successfully!" , Toast.LENGTH_SHORT)
                    .show()
            }
            .addOnFailureListener { e ->
                Log.e(TAG , "Error adding reservation: $e")
                Toast.makeText(context , "Failed to upload reservation!" , Toast.LENGTH_SHORT)
                    .show()
            }
    }

    fun searchReservation(nom:String , callback:(Reservation?) -> Unit) {
        db.collection("Reservations")
            .whereEqualTo("nom" , nom)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val document=querySnapshot.documents.firstOrNull()
                if (document != null) {
                    val reservation=Reservation(
                        document.getString("nom") ?: "" ,
                        document.getString("place") ?: "" ,
                        TypeReservation.valueOf(document.getString("type") ?: "STANDARD") ,
                        document.getString("prix") ?: ""
                    )
                    callback(reservation)
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener {
                Log.e(TAG , "Erreur lors de la recherche du réservation: $it")
                callback(null)
            }
    }

    fun fetchAllReservations(callback:(List<Reservation>) -> Unit) {
        db.collection("Reservations")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val reservations=querySnapshot.documents.mapNotNull { document ->
                    try {
                        Reservation(
                            document.getString("nom") ?: "" ,
                            document.getString("place") ?: "" ,
                            TypeReservation.valueOf(document.getString("type") ?: "STANDARD") ,
                            document.getString("prix") ?: ""
                        )
                    } catch (e:Exception) {
                        Log.e(TAG , "Erreur d'addition du réservation: $e")
                        null
                    }
                }
                callback(reservations)
            }
            .addOnFailureListener { exception ->
                Log.e(TAG , "Erreur de recherche des réservations: $exception")
                callback(emptyList())
            }
    }


    fun deleteReservation(nom:String , context:Context) {
        db.collection("Reservations")
            .whereEqualTo("nom" , nom)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    document.reference.delete().addOnSuccessListener {
                        Log.d(TAG , "Reservation deleted successfully!")
                        Toast.makeText(
                            context ,
                            "Reservation deleted successfully!" ,
                            Toast.LENGTH_SHORT
                        ).show()
                    }.addOnFailureListener {
                        Log.e(TAG , "Failed to delete reservation: $it")
                        Toast.makeText(
                            context ,
                            "Failed to delete reservation!" ,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            .addOnFailureListener {
                Log.e(TAG , "Error fetching reservation: $it")
                Toast.makeText(context , "Failed to delete reservation!" , Toast.LENGTH_SHORT)
                    .show()
            }
    }

    fun updateReservation(reservation:Reservation , context:Context) {
        db.collection("Reservations")
            .whereEqualTo("nom" , reservation.getNom())
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    val updates=hashMapOf(
                        "place" to reservation.getPlace() ,
                        "type" to reservation.getType().name ,
                        "prix" to reservation.getPrix()
                    )
                    document.reference.update(updates as Map<String , Any>)
                        .addOnSuccessListener {
                            Toast.makeText(
                                context ,
                                "Reservation updated successfully!" ,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .addOnFailureListener { e ->
                            Log.e(TAG , "Failed to update reservation: $e")
                            Toast.makeText(
                                context ,
                                "Failed to update reservation!" ,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
            }
            .addOnFailureListener {
                Log.e(TAG , "Error fetching reservation: $it")
                Toast.makeText(context , "Failed to update reservation!" , Toast.LENGTH_SHORT)
                    .show()
            }
    }
}