package tn.esprit.touristick.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.vertexai.vertexAI
import tn.esprit.touristick.databinding.ActivityChatbotBinding
import tn.esprit.touristick.models.ChatBotMessage


class ChatbotActivity : AppCompatActivity() {
    private lateinit var binding:ActivityChatbotBinding

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityChatbotBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvChatbot.setLayoutManager(LinearLayoutManager(this))
        val chatBotMessages=mutableListOf<ChatBotMessage>()
        val adapter=ChatbotAdapter(chatBotMessages)
        binding.rvChatbot.adapter=adapter
        binding.btnChatbot.setOnClickListener{
            generateChat()
        }
    }

    private  fun generateChat() {
        Firebase.vertexAI.generativeModel("gemini-1.5-pro")
    }
}