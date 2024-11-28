package tn.esprit.touristick.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
            if(binding.etOldPassword.text.toString().isBlank()||binding.etNewPassword.text.toString().isBlank()){
                Toast.makeText(this,"Remplissez le formulaire",Toast.LENGTH_SHORT).show()
            }else {
                val intent = Intent(this, ReservationManagementActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setPassword(){

    }
}