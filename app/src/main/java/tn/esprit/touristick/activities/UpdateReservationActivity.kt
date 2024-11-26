package tn.esprit.touristick.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.databinding.ActivityUpdateReservationBinding

class UpdateReservationActivity :AppCompatActivity() {
    private lateinit var binding:ActivityUpdateReservationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}