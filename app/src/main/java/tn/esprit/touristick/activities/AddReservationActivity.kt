package tn.esprit.touristick.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.databinding.ActivityAddReservationBinding

class AddReservationActivity:AppCompatActivity() {
    private lateinit var binding:ActivityAddReservationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAdd.setOnClickListener {
            if(binding.etNomReservation.text.toString().isBlank() || binding.etPlaceReservation.text.toString().isBlank() || binding.spTypeReservation.toString().isBlank()|| binding.etPrixReservation.text.toString().isBlank()){
                Toast.makeText(this,"Remplissez le formulaire",Toast.LENGTH_SHORT).show()
            }else {
                val intent = Intent(this, ReservationManagementActivity::class.java)
                startActivity(intent)
            }
        }
    }
}