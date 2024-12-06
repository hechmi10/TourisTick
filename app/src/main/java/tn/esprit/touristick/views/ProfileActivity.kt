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
    private lateinit var binding:ActivityProfileBinding
    private lateinit var controller:TouristController
    private lateinit var firebaseAuth:FirebaseAuth
    private lateinit var tourist:Tourist

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize controller
        controller=TouristController.getInstance()
        firebaseAuth=FirebaseAuth.getInstance()
        val nom=intent.getStringExtra(NOM_TOURISTE)
        val prenom=intent.getStringExtra(PRENOM_TOURISTE)
        val cin=intent.getStringExtra(CIN)
        val email=intent.getStringExtra(EMAIL)
        val mdp=intent.getStringExtra(MOT_DE_PASSE)



        // Fetch detailed tourist data from Firestore if CIN is available
        if (email != null && mdp!=null) {
            controller.searchTourist(email,mdp) { tourist ->
                if (tourist != null) {
                    // Update UI with the retrieved tourist details
                    binding.tvNomProfile.text=(binding.tvNomProfile.text.toString() + nom) ?: "N/A"
                    binding.tvPrenomProfile.text=(binding.tvPrenomProfile.text.toString() + prenom) ?: "N/A"
                    binding.tvCinProfile.text=(binding.tvCinProfile.text.toString() + cin) ?: "N/A"
                    binding.tvEmailProfile.text=(binding.tvEmailProfile.text.toString() + email) ?: "N/A"
                } else {
                    Toast.makeText(
                        this ,
                        "Aucun touriste trouvé avec CIN: $tourist" ,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else {
            Toast.makeText(this , "CIN invalide ou manquant" , Toast.LENGTH_SHORT).show()
        }
    }
}
