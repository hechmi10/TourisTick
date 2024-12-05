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
        tourist=Tourist()

        binding.tvNomProfile.text=(binding.tvNomProfile.text.toString() + tourist.getNom()) ?: "N/A"
        binding.tvPrenomProfile.text=(binding.tvPrenomProfile.text.toString() + tourist.getPrenom()) ?: "N/A"
        binding.tvCinProfile.text=(binding.tvCinProfile.text.toString() + tourist.getCin()) ?: "N/A"
        binding.tvEmailProfile.text=(binding.tvEmailProfile.text.toString() + tourist.getEmail()) ?: "N/A"

        // Fetch detailed tourist data from Firestore if CIN is available
        if (tourist.getCin() != null) {
            controller.searchTourist(tourist.getCin()) { tourist ->
                if (tourist != null) {
                    // Update UI with the retrieved tourist details
                    updateProfileDetails(tourist)
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

    @SuppressLint("SetTextI18n")
    private fun updateProfileDetails(tourist:Tourist) {
        binding.tvNomProfile.text=binding.tvNomProfile.text.toString()+tourist.getNom()
        binding.tvPrenomProfile.text=binding.tvPrenomProfile.text.toString()+tourist.getPrenom()
        binding.tvCinProfile.text=binding.tvCinProfile.text.toString()+tourist.getCin()
        binding.tvEmailProfile.text=binding.tvEmailProfile.text.toString()+tourist.getEmail()
    }
}
