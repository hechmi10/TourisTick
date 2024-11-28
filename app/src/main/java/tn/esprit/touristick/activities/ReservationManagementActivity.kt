package tn.esprit.touristick.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.databinding.ActivityReservationManagementBinding

class ReservationManagementActivity:AppCompatActivity() {
    private lateinit var binding:ActivityReservationManagementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityReservationManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cvAddReservation.setOnClickListener{
            val intent= Intent(this,AddReservationActivity::class.java)
            startActivity(intent)
        }
        binding.cvListReservation.setOnClickListener {
            val intent=Intent(this,ListReservationsActivity::class.java)
            startActivity(intent)
        }
        binding.cvUpdateReservation.setOnClickListener {
            val intent=Intent(this,UpdateReservationActivity::class.java)
            startActivity(intent)
        }
        binding.cvDeleteReservation.setOnClickListener {
            val intent=Intent(this,DeleteReservationActivity::class.java)
            startActivity(intent)
        }
        binding.cvProfile.setOnClickListener {
            val intent=Intent(this,ProfileActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignOut.setOnClickListener{
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}