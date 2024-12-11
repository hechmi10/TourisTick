package tn.esprit.touristick.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.lifecycleScope
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.asTextOrNull
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.launch
import tn.esprit.touristick.databinding.ActivityChatbotBinding
import tn.esprit.touristick.models.ChatBotMessage

class ChatbotActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatbotBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatbotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvChatbot.layoutManager = LinearLayoutManager(this)
        val chatBotMessages = mutableListOf<ChatBotMessage>()
        val adapter = ChatbotAdapter(chatBotMessages)
        binding.rvChatbot.adapter = adapter

        binding.btnChatbot.setOnClickListener {
            val userMessage = binding.etChatbot.text.toString()
            if (userMessage.isNotBlank()) {
                // Add user message to chat history
                chatBotMessages.add(ChatBotMessage("User", userMessage, isUserMessage = true))
                adapter.notifyDataSetChanged()

                // Generate chatbot response asynchronously
                generateChat(userMessage) { response ->
                    runOnUiThread {
                        // Add bot's response to chat history
                        chatBotMessages.add(ChatBotMessage("Bot", response, isUserMessage = false))
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private fun generateChat(userMessage: String, callback: (String) -> Unit) {
        // Initialize the generative model
        val model = GenerativeModel(
            "gemini-1.5-pro", // AI model
            "AIzaSyB-HNz_pWT2V9TAjHKwPtmfnqrY8JDEC4w", // Retrieve API key from build config
            generationConfig = generationConfig {
                temperature = 1f
                topK = 40
                topP = 0.95f
                maxOutputTokens = 8192
                responseMimeType = "text/plain"
            },
            systemInstruction = content {
                text("Vous êtes un guide de réservation de chambres d'hôtel et de maisons de vacances, vous êtes dédié à vous assurer que votre application mobile touristique répond au thème du développement durable.")
            }
        )

        // Create chat history with predefined conversation
        val chatHistory = listOf(
            content("user") { text("hello") },
            content("model") {
                text(
                    "Bonjour ! Je suis votre guide de réservation d’hôtels et de locations de vacances, spécialisé dans les voyages durables. Comment puis-je vous aider à planifier votre prochaine escapade écologique ?"
                )
            }
        )

        // Start chat with predefined history
        val chat = model.startChat(chatHistory)

        // Use coroutine to handle the suspend function
        lifecycleScope.launch {
            try {
                // Send message asynchronously
                val response = chat.sendMessage(userMessage)
                val botResponse = response.candidates.firstOrNull()?.content?.parts?.first()?.asTextOrNull()
                callback(botResponse ?: "Sorry, I couldn't process that request.")
            } catch (e: Exception) {
                e.printStackTrace()
                callback("Error: ${e.localizedMessage}")
            }
        }
    }
}
