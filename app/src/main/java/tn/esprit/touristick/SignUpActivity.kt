package tn.esprit.touristick

import android.content.Context
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

        }
    }
    private fun addUser(context:Context){
        val sharedPreferences= context.getSharedPreferences(SHARED_KEY, MODE_PRIVATE)
        val value=sharedPreferences.getBoolean(SHARED_KEY,false)
    }

    companion object {
        const val SHARED_KEY="mySharedKey"
    }
}