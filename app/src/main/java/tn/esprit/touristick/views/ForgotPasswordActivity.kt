package tn.esprit.touristick.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import tn.esprit.touristick.controllers.TouristController
import tn.esprit.touristick.databinding.ActivityForgotPasswordBinding
import tn.esprit.touristick.models.Tourist

const val NEWPASSWORD="New_Password"

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private lateinit var controller:TouristController
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller=TouristController.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()
        val email = intent.getStringExtra(EMAIL)
        val currentPassword = intent.getStringExtra(MOT_DE_PASSE)

        if (email.isNullOrBlank() || currentPassword.isNullOrBlank()) {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        binding.btnSetPassword.setOnClickListener {
            val oldPassword = binding.etOldPassword.text.toString()
            val newPassword = binding.etNewPassword.text.toString()

            if (oldPassword.isBlank() || newPassword.isBlank()) {
                Toast.makeText(this, "Please", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (oldPassword == currentPassword) {
                // Validate password strength here if needed
                firebaseAuth.currentUser?.updatePassword(newPassword)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            controller.updatePassword(oldPassword,newPassword,this)
                            val intent = Intent(this, ReservationManagementActivity::class.java)
                                .apply {
                                    putExtra(EMAIL, email)
                                    putExtra(MOT_DE_PASSE, newPassword)
                                }
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Password reset failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Incorrect current password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
