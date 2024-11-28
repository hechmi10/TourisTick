package tn.esprit.touristick.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.databinding.ActivityUpdateReservationBinding

class UpdateReservationActivity :AppCompatActivity() {
    private lateinit var binding:ActivityUpdateReservationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnUpdate.setOnClickListener {
            val intent= Intent(this,ReservationManagementActivity::class.java)
            startActivity(intent)
        }
    }
}