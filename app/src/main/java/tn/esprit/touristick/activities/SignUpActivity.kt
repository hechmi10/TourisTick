package tn.esprit.touristick.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.databinding.ActivitySignupBinding


class SignUpActivity :AppCompatActivity(){
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSubmitSignUp.setOnClickListener {
            signUp()
        }
    }
    private fun signUp(){
        if(binding.etCin.text.toString().isBlank()||binding.etNom.text.toString().isBlank()||binding.etPrenom.text.toString().isBlank()||binding.etEmail.text.toString().isBlank()||binding.etMdp.text.toString().isBlank()){
            Toast.makeText(this,"Remplissez le formulaire",Toast.LENGTH_SHORT).show()
        }else {
            val intent = Intent(this, ReservationManagementActivity::class.java)
            startActivity(intent)
        }
    }


}