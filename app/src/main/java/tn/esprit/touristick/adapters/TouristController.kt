package tn.esprit.touristick.adapters

import android.content.Context
import tn.esprit.touristick.database.FirestoreManagerTourist
import tn.esprit.touristick.entities.Tourist

class TouristController {
    private val firestoreManager:FirestoreManagerTourist= FirestoreManagerTourist()

    // Add a tourist
    fun addTourist(tourist: Tourist, context: Context) {
        firestoreManager.addTourist(tourist, context)
    }

    // Search for a tourist by CIN
    fun searchTourist(cin: Int, callback: (Tourist?) -> Unit) {
        firestoreManager.searchTourist(cin, callback)
    }

    // Delete a tourist
    fun deleteTourist(cin: Int, context: Context) {
        firestoreManager.deleteTourist(cin, context)
    }

    // Update a tourist
    fun updatePassword(tourist: Tourist, context: Context) {
        firestoreManager.updatePassword(tourist, context)
    }

    companion object {
        private var instance: TouristController? = null

        // Singleton pattern for ReservationController
        fun getInstance(): TouristController {
            if (instance == null) {
                instance = TouristController()
            }
            return instance!!
        }
    }
}