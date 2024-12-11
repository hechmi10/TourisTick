package tn.esprit.touristick.views


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import tn.esprit.touristick.controllers.TouristController
import tn.esprit.touristick.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var controller: TouristController
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller = TouristController.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSubmitLogin.setOnClickListener {
            login()
        }
        binding.btnSetPassword.setOnClickListener {
            setPassword()
        }
    }

    private fun setPassword() {
        val email = binding.etEmailLogin.text.toString()
        val password = binding.etPasswordLogin.text.toString()

        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this, ForgotPasswordActivity::class.java).apply {
            putExtra(EMAIL, email)
            putExtra(MOT_DE_PASSE, password)
        }
        startActivity(intent)
    }

    private fun login() {
        val email = binding.etEmailLogin.text.toString()
        val password = binding.etPasswordLogin.text.toString()

        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    controller.searchTourist(email, password) { tourist ->
                        if (tourist != null) {
                            val intent = Intent(this, ReservationManagementActivity::class.java).apply {
                                putExtra(NOM_TOURISTE, tourist.getNom())
                                putExtra(PRENOM_TOURISTE, tourist.getPrenom())
                                putExtra(CIN, tourist.getCin())
                                putExtra(EMAIL, tourist.getEmail())
                            }
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Tourist not found", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, task.exception?.message ?: "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
