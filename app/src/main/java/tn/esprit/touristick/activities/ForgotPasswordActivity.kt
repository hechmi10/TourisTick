package tn.esprit.touristick.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.adapters.TouristController
import tn.esprit.touristick.databinding.ActivityForgotPasswordBinding
import tn.esprit.touristick.entities.Tourist

const val NEWPASSWORD="New_Password"

class ForgotPasswordActivity :AppCompatActivity(){
    private lateinit var binding: ActivityForgotPasswordBinding
    private lateinit var controller:TouristController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        controller= TouristController.getInstance()
        val cin=intent.getStringExtra(CIN)
        val nom=intent.getStringExtra(NOM_TOURISTE)
        val prenom=intent.getStringExtra(PRENOM_TOURISTE)
        val email=intent.getStringExtra(EMAIL)
        binding.btnSetPassword.setOnClickListener {
            setPassword()
            if(binding.etOldPassword.text.toString().isBlank()||binding.etNewPassword.text.toString().isBlank()){
                Toast.makeText(this,"Remplissez le formulaire",Toast.LENGTH_SHORT).show()
            }else {
                controller.updatePassword(Tourist(cin.toString(),nom.toString(),prenom.toString(),email.toString(),binding.etNewPassword.text.toString()),this)
                val intent = Intent(this, ReservationManagementActivity::class.java).apply{
                    putExtra(NOM_TOURISTE,nom)
                    putExtra(PRENOM_TOURISTE,prenom)
                    putExtra(CIN,cin)
                    putExtra(EMAIL,email)
                }
                startActivity(intent)
            }
        }
    }

    private fun setPassword(){

    }
}