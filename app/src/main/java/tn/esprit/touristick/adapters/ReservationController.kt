package tn.esprit.touristick.adapters

import android.content.Context
import tn.esprit.touristick.database.FirestoreManagerReservation
import tn.esprit.touristick.entities.Reservation

class ReservationController {
    private val firestoreManager:FirestoreManagerReservation= FirestoreManagerReservation()

    fun addReservation(r: Reservation,c: Context){
        firestoreManager.addReservation(r,c)
    }

    fun searchReservation(
        nom: String,
        callback: (Reservation?) -> Unit
    ) {
        firestoreManager.searchReservation(nom) { reservation ->
            callback(reservation)
        }
    }

    fun fetchAllReservations(callback: (List<Reservation>) -> Unit) {
        firestoreManager.fetchAllReservations { reservations ->
            callback(reservations)
        }
    }

    fun deleteReservation(nom:String, context: Context) {
        firestoreManager.deleteReservation(nom, context)
    }

    fun updateReservation(r:Reservation, context: Context) {
        firestoreManager.updateReservation(r, context)
    }

    companion object {
        private var instance: ReservationController? = null
        fun getInstance(): ReservationController {
            if (instance == null) {
                instance = ReservationController()
            }
            return instance!!
        }
    }

}