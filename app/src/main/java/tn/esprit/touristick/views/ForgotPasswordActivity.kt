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
    private lateinit var binding:ActivityForgotPasswordBinding
    private lateinit var controller:TouristController
    private lateinit var firebaseAuth:FirebaseAuth

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        controller=TouristController.getInstance()
        firebaseAuth=FirebaseAuth.getInstance()
        val cin=intent.getStringExtra(CIN)
        val nom=intent.getStringExtra(NOM_TOURISTE)
        val prenom=intent.getStringExtra(PRENOM_TOURISTE)
        val email=intent.getStringExtra(EMAIL)
        binding.btnSetPassword.setOnClickListener {
            if (binding.etOldPassword.text.toString()
                    .isBlank() || binding.etNewPassword.text.toString().isBlank()
            ) {
                Toast.makeText(this , "Remplissez le formulaire" , Toast.LENGTH_SHORT).show()
            } else {
                controller.updatePassword(
                    Tourist(
                        cin.toString() ,
                        nom.toString() ,
                        prenom.toString() ,
                        email.toString() ,
                        binding.etNewPassword.text.toString()
                    ) , this
                )
                firebaseAuth.confirmPasswordReset(binding.etOldPassword.text.toString(),
                    binding.etNewPassword.text.toString()).addOnCompleteListener { task->
                        if(task.isSuccessful){
                            val intent=Intent(this , ReservationManagementActivity::class.java).apply {
                                putExtra(NOM_TOURISTE , nom)
                                putExtra(PRENOM_TOURISTE , prenom)
                                putExtra(CIN , cin)
                                putExtra(EMAIL , email)
                                putExtra(MOT_DE_PASSE,binding.etNewPassword.text.toString())
                            }
                            startActivity(intent)
                        }
                    }
            }
        }
    }
}