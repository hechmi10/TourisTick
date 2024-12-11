package tn.esprit.touristick.views


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import tn.esprit.touristick.databinding.ActivityReservationManagementBinding

class ReservationManagementActivity : AppCompatActivity() {
    private lateinit var binding:ActivityReservationManagementBinding
    private lateinit var firebaseAuth:FirebaseAuth

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityReservationManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()
        val nom=intent.getStringExtra(NOM_TOURISTE) ?: "N/A"
        val prenom=intent.getStringExtra(PRENOM_TOURISTE) ?: "N/A"
        val cin=intent.getStringExtra(CIN) ?: "N/A"
        val email=intent.getStringExtra(EMAIL) ?: "N/A"
        val mdp=intent.getStringExtra(MOT_DE_PASSE) ?: "N/A"
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
                putExtra(MOT_DE_PASSE,mdp)
            }
            startActivity(intent)
        }
        binding.cvChatbot.setOnClickListener {
            val intent=Intent(this , ChatbotActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignOut.setOnClickListener {
            firebaseAuth.signOut()
            val intent=Intent(this , MainActivity::class.java)
            startActivity(intent)
        }
    }
}