package tn.esprit.touristick.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.databinding.ActivityDeleteReservationBinding

class DeleteReservationActivity :AppCompatActivity(){
    private lateinit var binding:ActivityDeleteReservationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDeleteReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnDelete.setOnClickListener {
            deleteReservation(binding.etIdDelete.text.toString().toInt())
        }
    }
    private fun deleteReservation(idDelete:Int):Boolean{
        return false
    }
}