package tn.esprit.touristick.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.adapters.ReservationController
import tn.esprit.touristick.databinding.ActivityUpdateReservationBinding
import tn.esprit.touristick.entities.Reservation
import tn.esprit.touristick.entities.TypeReservation

class UpdateReservationActivity :AppCompatActivity() {
    private lateinit var binding:ActivityUpdateReservationBinding
    private lateinit var controller:ReservationController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        controller=ReservationController.getInstance()
        val nom=intent.getStringExtra(NOM)
        val place=intent.getStringExtra(PLACE)
        val type=intent.getStringExtra(TYPE)
        val prix=intent.getStringExtra(PRIX)
        binding.btnUpdate.setOnClickListener {
            controller.updateReservation(Reservation(nom.toString(),place.toString(),
                TypeReservation.valueOf(type.toString()),prix.toString().toDouble()),this)
            if(binding.etNomReservation.text.toString().isBlank() || binding.etPlaceReservation.text.toString().isBlank() || binding.spTypeReservation.toString().isBlank()|| binding.etPrixReservation.text.toString().isBlank()){
                Toast.makeText(this,"Remplissez le formulaire", Toast.LENGTH_SHORT).show()
            }else {
                val intent = Intent(this, ReservationManagementActivity::class.java)
                startActivity(intent)
            }
        }
    }
}