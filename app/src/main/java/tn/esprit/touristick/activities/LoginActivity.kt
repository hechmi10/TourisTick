package tn.esprit.touristick.activities


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.databinding.ActivityLoginBinding

class LoginActivity :AppCompatActivity(){
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSubmitLogin.setOnClickListener {
            login()
        }
        binding.btnSetPassword.setOnClickListener {
            setPassword()
        }
    }

    private fun setPassword() {
        val intent=Intent(this,ForgotPasswordActivity::class.java)
        startActivity(intent)
    }

    private fun login(){
        if(binding.etEmailLogin.text.toString().isBlank()||binding.etPasswordLogin.text.toString().isBlank()){
            Toast.makeText(this,"Remplissez le formulaire",Toast.LENGTH_SHORT).show()
        }
        val intent= Intent(this,ReservationManagementActivity::class.java)
        startActivity(intent)
    }
}