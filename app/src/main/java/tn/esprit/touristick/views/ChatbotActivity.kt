package tn.esprit.touristick.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.lifecycleScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.asTextOrNull
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.launch
import tn.esprit.touristick.BuildConfig
import tn.esprit.touristick.controllers.ChatbotAdapter
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
        val model = GenerativeModel(
            "gemini-1.5-pro",
            // Retrieve API key as an environmental variable defined in a Build Configuration
            // see https://github.com/google/secrets-gradle-plugin for further instructions
            BuildConfig.geminiApiKey,
            generationConfig = generationConfig {
                temperature = 1f
                topK = 40
                topP = 0.95f
                maxOutputTokens = 5000
                responseMimeType = "text/plain"
            },
            systemInstruction = content { text("Vous êtes un guide de réservation de chambres d'hôtel et de maisons de vacances, vous êtes dédié à vous assurer que votre application mobile touristique répond au thème du développement durable.") },
        )

        val chatHistory = listOf(
            content("user") {
                text("hello")
            },
            content("model") {
                text("Bonjour ! 👋 Je suis ravi de vous accueillir. En tant que guide de réservation spécialisé dans les hôtels et les maisons de vacances, je suis là pour vous aider à trouver l'hébergement idéal tout en respectant le développement durable. \n\nDites-moi, qu'est-ce qui vous amène aujourd'hui ? Avez-vous déjà une destination en tête, ou recherchez-vous simplement des options d'hébergement éco-responsables ? Je suis à votre écoute pour vous guider dans vos choix et vous proposer des solutions adaptées à vos besoins.\n\nN'hésitez pas à me poser toutes vos questions, je suis là pour vous aider à faire de vos voyages une expérience plus durable et enrichissante. 😊\n")
            },
        )

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
