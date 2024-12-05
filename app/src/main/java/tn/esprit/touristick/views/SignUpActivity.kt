package tn.esprit.touristick.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import tn.esprit.touristick.controllers.TouristController
import tn.esprit.touristick.databinding.ActivitySignupBinding
import tn.esprit.touristick.models.Tourist

const val CIN="0"
const val NOM_TOURISTE="Nom_Touriste"
const val PRENOM_TOURISTE="Prenom_Touriste"
const val EMAIL="Email"
const val MOT_DE_PASSE="Password"

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignupBinding
    private lateinit var controller:TouristController
    private lateinit var firebaseAuth:FirebaseAuth
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        controller=TouristController.getInstance()
        binding.btnSubmitSignUp.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        firebaseAuth=FirebaseAuth.getInstance()
        if (binding.etCin.text.toString().isBlank() || binding.etNom.text.toString()
                .isBlank() || binding.etPrenom.text.toString()
                .isBlank() || binding.etEmail.text.toString()
                .isBlank() || binding.etMdp.text.toString().isBlank()
        ) {
            Toast.makeText(this , "Remplissez le formulaire" , Toast.LENGTH_SHORT).show()
        } else {
            controller.addTourist(Tourist(binding.etCin.text.toString(),
                binding.etNom.text.toString(),
                binding.etPrenom.text.toString(),
                binding.etEmail.text.toString(),
                binding.etMdp.text.toString()),this)
            firebaseAuth.createUserWithEmailAndPassword(binding.etEmail.text.toString() ,
                binding.etMdp.text.toString()).addOnCompleteListener {task->
                    if(task.isSuccessful){
                        val intent=Intent(this , ReservationManagementActivity::class.java).apply {
                            putExtra(CIN , binding.etCin.text.toString())
                            putExtra(NOM_TOURISTE , binding.etNom.text.toString())
                            putExtra(PRENOM_TOURISTE , binding.etPrenom.text.toString())
                            putExtra(EMAIL , binding.etEmail.text.toString())
                            putExtra(MOT_DE_PASSE , binding.etMdp.text.toString())
                        }
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"échec d'inscription",Toast.LENGTH_SHORT)
                    }
            }
        }
    }


}