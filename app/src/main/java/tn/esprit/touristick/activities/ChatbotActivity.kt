package tn.esprit.touristick.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.databinding.ActivityChatbotBinding

class ChatbotActivity :AppCompatActivity(){
    private lateinit var binding:ActivityChatbotBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityChatbotBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}