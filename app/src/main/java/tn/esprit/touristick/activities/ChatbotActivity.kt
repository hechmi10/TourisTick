package tn.esprit.touristick.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import tn.esprit.touristick.adaptersEtControllers.ChatbotAdapter
import tn.esprit.touristick.databinding.ActivityChatbotBinding
import tn.esprit.touristick.entities.ChatBotMessage


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
    }

    private fun generateChat() {

    }
}