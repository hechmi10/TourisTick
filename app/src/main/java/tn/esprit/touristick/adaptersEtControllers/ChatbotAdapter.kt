package tn.esprit.touristick.adaptersEtControllers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.touristick.R
import tn.esprit.touristick.entities.ChatBotMessage

class ChatbotAdapter(private val messages:List<ChatBotMessage>) :
    RecyclerView.Adapter<ChatbotAdapter.ViewHolder>() {

    class ViewHolder(chatView:View) : RecyclerView.ViewHolder(chatView) {
        val tvMessageContent:TextView=chatView.findViewById(R.id.tvMessageContent)
        val tvMessageTimestamp:TextView=chatView.findViewById(R.id.tvMessageTimestamp)
    }

    override fun onCreateViewHolder(parent:ViewGroup , viewType:Int):ChatbotAdapter.ViewHolder {
        val v=LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_message , parent , false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder:ChatbotAdapter.ViewHolder , position:Int) {
        val message=messages[position]
        holder.tvMessageContent.text=message.content
        holder.tvMessageTimestamp.text=message.timestamp

    }

    override fun getItemCount():Int {
        return messages.size
    }
}