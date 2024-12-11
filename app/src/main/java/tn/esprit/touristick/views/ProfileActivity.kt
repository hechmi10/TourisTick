package tn.esprit.touristick.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import tn.esprit.touristick.controllers.TouristController
import tn.esprit.touristick.databinding.ActivityProfileBinding
import tn.esprit.touristick.models.Tourist

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var controller: TouristController
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase and controller
        firebaseAuth = FirebaseAuth.getInstance()
        controller = TouristController.getInstance()

        // Fetch data from intent extras
        val nom = intent.getStringExtra(NOM_TOURISTE)
        val prenom = intent.getStringExtra(PRENOM_TOURISTE)
        val cin = intent.getStringExtra(CIN)
        val email = intent.getStringExtra(EMAIL)
        val mdp = intent.getStringExtra(MOT_DE_PASSE)

        // Update the UI with basic info
        updateUI(nom, prenom, cin, email)

        // Fetch detailed tourist data if email and password are available
        if (!email.isNullOrBlank() && !mdp.isNullOrBlank()) {
            fetchTouristDetails(email, mdp)
        } else {
            Toast.makeText(this, "Invalid or missing email/password", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(nom: String?, prenom: String?, cin: String?, email: String?) {
        binding.tvNomProfile.text = nom ?: "N/A"
        binding.tvPrenomProfile.text = prenom ?: "N/A"
        binding.tvCinProfile.text = cin ?: "N/A"
        binding.tvEmailProfile.text = email ?: "N/A"
    }

    private fun fetchTouristDetails(email: String, password: String) {
        controller.searchTourist(email, password) { tourist ->
            if (tourist != null) {
                // Update UI with detailed tourist information
                binding.tvNomProfile.text = tourist.getNom()
                binding.tvPrenomProfile.text = tourist.getPrenom()
                binding.tvCinProfile.text = tourist.getCin()
                binding.tvEmailProfile.text = tourist.getEmail()
            }
        }
    }
}

