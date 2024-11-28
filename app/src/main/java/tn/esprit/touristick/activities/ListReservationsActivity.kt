package tn.esprit.touristick.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.databinding.ActivityListReservationsBinding

class ListReservationsActivity: AppCompatActivity() {
    private lateinit var binding:ActivityListReservationsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityListReservationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}