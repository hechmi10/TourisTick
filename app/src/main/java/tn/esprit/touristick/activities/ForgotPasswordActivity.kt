package tn.esprit.touristick.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity :AppCompatActivity(){
    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSetPassword.setOnClickListener {
            setPassword()
        }
    }

    private fun setPassword(){
        val intent= Intent(this,ReservationManagementActivity::class.java)
        startActivity(intent)
    }
}