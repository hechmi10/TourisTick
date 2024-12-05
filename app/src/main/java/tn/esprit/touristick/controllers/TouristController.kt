package tn.esprit.touristick.controllers

import android.content.Context
import tn.esprit.touristick.models.database.FirestoreManagerTourist
import tn.esprit.touristick.models.Tourist

class TouristController {
    private val firestoreManager:FirestoreManagerTourist= FirestoreManagerTourist()

    // Add a tourist
    fun addTourist(tourist: Tourist, context: Context) {
        firestoreManager.addTourist(tourist, context)
    }

    // Search for a tourist by CIN
    fun searchTourist(cin: String, callback: (Tourist?) -> Unit) {
        firestoreManager.searchTourist(cin, callback)
    }

    // Delete a tourist
    fun deleteTourist(cin: String, context: Context) {
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