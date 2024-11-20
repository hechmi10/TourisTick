package tn.esprit.touristick.activities

import android.content.Intent
import android.os.Bundle
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
        val intent= Intent(this,ReservationManagementActivity::class.java)
        startActivity(intent)
    }


}