package tn.esprit.touristick

import android.app.Application
import com.google.firebase.FirebaseApp

class TouristickApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
    }
}
