package tn.esprit.touristick.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.databinding.ActivityAddReservationBinding

class AddReservationActivity:AppCompatActivity() {
    private lateinit var binding:ActivityAddReservationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}