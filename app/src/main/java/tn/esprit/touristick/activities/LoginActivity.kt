package tn.esprit.touristick.activities


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.adaptersEtControllers.TouristController
import tn.esprit.touristick.databinding.ActivityLoginBinding

const val PASSWORD="Password"

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private lateinit var controller:TouristController

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        controller=TouristController.getInstance()
        binding.btnSubmitLogin.setOnClickListener {
            login()
        }
        binding.btnSetPassword.setOnClickListener {
            setPassword()
        }
    }

    private fun setPassword() {
        val intent=Intent(this , ForgotPasswordActivity::class.java)
        startActivity(intent)
    }

    private fun login() {
        // Retrieve tourist details from the intent
        val nom=intent.getStringExtra(NOM_TOURISTE)
        val prenom=intent.getStringExtra(PRENOM_TOURISTE)
        val cin=intent.getStringExtra(CIN)
        val email=intent.getStringExtra(EMAIL)
        val mdp=intent.getStringExtra(MOT_DE_PASSE)

        // Check if the email or password fields are blank
        if (binding.etEmailLogin.text.toString()
                .isBlank() || binding.etPasswordLogin.text.toString().isBlank()
        ) {
            Toast.makeText(this , "Remplissez le formulaire" , Toast.LENGTH_SHORT).show()
            return
        }

        // Validate CIN
        if (cin == null) {
            Toast.makeText(this , "Utilisateur non trouvé" , Toast.LENGTH_SHORT).show()
            return
        }

        // Search for the tourist in the database
        controller.searchTourist(cin) { tourist ->
            if (tourist != null && tourist.getEmail() == binding.etEmailLogin.text.toString() &&
                tourist.getCin() == cin
            ) {
                // Match password if needed (assuming password is stored securely in the database)
                if (tourist.getMdp() == binding.etPasswordLogin.text.toString()) {
                    // Navigate to ReservationManagementActivity
                    val intent=Intent(this , ReservationManagementActivity::class.java).apply {
                        putExtra(NOM_TOURISTE , nom)
                        putExtra(PRENOM_TOURISTE , prenom)
                        putExtra(CIN , cin)
                        putExtra(EMAIL , email)
                    }
                    startActivity(intent)
                } else {
                    Toast.makeText(this , "Mot de passe incorrect" , Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(
                    this ,
                    "Utilisateur introuvable ou email incorrect" ,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}