package tn.esprit.touristick.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.databinding.ActivityReservationManagementBinding

class ReservationManagementActivity : AppCompatActivity() {
    private lateinit var binding:ActivityReservationManagementBinding

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityReservationManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val nom=intent.getStringExtra(NOM_TOURISTE)
        val prenom=intent.getStringExtra(PRENOM_TOURISTE)
        val cin=intent.getStringExtra(CIN)
        val email=intent.getStringExtra(EMAIL)
        binding.cvAddReservation.setOnClickListener {
            val intent=Intent(this , AddReservationActivity::class.java)
            startActivity(intent)
        }
        binding.cvListReservation.setOnClickListener {
            val intent=Intent(this , ListReservationsActivity::class.java)
            startActivity(intent)
        }
        binding.cvUpdateReservation.setOnClickListener {
            val intent=Intent(this , UpdateReservationActivity::class.java)
            startActivity(intent)
        }
        binding.cvDeleteReservation.setOnClickListener {
            val intent=Intent(this , DeleteReservationActivity::class.java)
            startActivity(intent)
        }
        binding.cvProfile.setOnClickListener {
            val intent=Intent(this , ProfileActivity::class.java).apply {
                putExtra(NOM_TOURISTE , nom)
                putExtra(PRENOM_TOURISTE , prenom)
                putExtra(CIN , cin)
                putExtra(EMAIL , email)
            }
            startActivity(intent)
        }
        binding.cvChatbot.setOnClickListener {
            val intent=Intent(this , ChatbotActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignOut.setOnClickListener {
            val intent=Intent(this , MainActivity::class.java)
            startActivity(intent)
        }
    }
}