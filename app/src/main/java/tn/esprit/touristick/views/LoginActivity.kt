package tn.esprit.touristick.views


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import tn.esprit.touristick.controllers.TouristController
import tn.esprit.touristick.databinding.ActivityLoginBinding
import tn.esprit.touristick.models.Tourist

const val PASSWORD="Password"

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private lateinit var controller:TouristController
    private lateinit var firebaseAuth:FirebaseAuth


    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        controller=TouristController.getInstance()
        firebaseAuth=FirebaseAuth.getInstance()
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
        // Check if the email or password fields are blank
        if (binding.etEmailLogin.text.toString()
                .isBlank() || binding.etPasswordLogin.text.toString().isBlank()
        ) {
            Toast.makeText(this , "Remplissez le formulaire" , Toast.LENGTH_SHORT).show()
            return
        }else {
            // Search for the tourist in the authentication
            firebaseAuth.signInWithEmailAndPassword(
                binding.etEmailLogin.text.toString() ,
                binding.etPasswordLogin.text.toString()
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    controller.searchTourist(binding.etEmailLogin.text.toString(),
                        binding.etPasswordLogin.text.toString()){
                        tourist ->
                        val intent=Intent(this , ReservationManagementActivity::class.java).apply{
                            putExtra(NOM_TOURISTE,tourist?.getNom())
                            putExtra(PRENOM_TOURISTE,tourist?.getPrenom())
                            putExtra(CIN,tourist?.getCin())
                            putExtra(EMAIL,tourist?.getEmail())
                        }
                        startActivity(intent)
                    }

                } else {
                    // Affichage d'un message d'erreur en cas d'échec d'authentification
                    Toast.makeText(this , task.exception.toString() , Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}