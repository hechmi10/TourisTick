package tn.esprit.touristick.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.controllers.TouristController
import tn.esprit.touristick.databinding.ActivityProfileBinding
import tn.esprit.touristick.models.Tourist

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding:ActivityProfileBinding
    private lateinit var controller:TouristController

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize controller
        controller=TouristController.getInstance()

        // Populate the initial data from intent extras
        val cin=intent.getStringExtra(CIN)
        val nom=intent.getStringExtra(NOM_TOURISTE)
        val prenom=intent.getStringExtra(PRENOM_TOURISTE)
        val email=intent.getStringExtra(EMAIL)

        binding.tvNomProfile.text=(binding.tvNomProfile.text.toString() + nom) ?: "N/A"
        binding.tvPrenomProfile.text=(binding.tvPrenomProfile.text.toString() + prenom) ?: "N/A"
        binding.tvCinProfile.text=(binding.tvCinProfile.text.toString() + cin) ?: "N/A"
        binding.tvEmailProfile.text=(binding.tvEmailProfile.text.toString() + email) ?: "N/A"

        // Fetch detailed tourist data from Firestore if CIN is available
        if (cin != null) {
            controller.searchTourist(cin) { tourist ->
                if (tourist != null) {
                    // Update UI with the retrieved tourist details
                    updateProfileDetails(tourist)
                } else {
                    Toast.makeText(
                        this ,
                        "Aucun touriste trouvé avec CIN: $cin" ,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else {
            Toast.makeText(this , "CIN invalide ou manquant" , Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateProfileDetails(tourist:Tourist) {
        binding.tvNomProfile.text=tourist.getNom()
        binding.tvPrenomProfile.text=tourist.getPrenom()
        binding.tvCinProfile.text=tourist.getCin().toString()
        binding.tvEmailProfile.text=tourist.getEmail()
    }
}
