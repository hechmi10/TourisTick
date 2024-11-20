package tn.esprit.touristick.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.databinding.ActivityReservationManagementBinding

class ReservationManagementActivity:AppCompatActivity() {
    private lateinit var binding:ActivityReservationManagementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityReservationManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}