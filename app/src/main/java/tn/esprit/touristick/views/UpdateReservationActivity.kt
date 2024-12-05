package tn.esprit.touristick.views

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.controllers.ReservationController
import tn.esprit.touristick.databinding.ActivityUpdateReservationBinding
import tn.esprit.touristick.models.Reservation
import tn.esprit.touristick.models.TypeReservation

class UpdateReservationActivity : AppCompatActivity() {
    private lateinit var binding:ActivityUpdateReservationBinding
    private lateinit var controller:ReservationController
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        controller=ReservationController.getInstance()
        val types=TypeReservation.entries.map { it.name }

        // Create an ArrayAdapter using the default Spinner layout
        val adapter=ArrayAdapter(this , R.layout.simple_spinner_item , types)

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        binding.spTypeReservation.adapter=adapter
        val nom=intent.getStringExtra(NOM)
        val place=intent.getStringExtra(PLACE)
        val type=intent.getStringExtra(TYPE)
        val prix=intent.getStringExtra(PRIX)
        binding.btnUpdate.setOnClickListener {
            controller.updateReservation(
                Reservation(
                    binding.etNomReservation.text.toString() ,
                    binding.etPlaceReservation.text.toString() ,
                    TypeReservation.valueOf(binding.spTypeReservation.selectedItem.toString()) ,
                    binding.etPrixReservation.text.toString()
                ) , this
            )
            if (binding.etNomReservation.text.toString()
                    .isBlank() || binding.etPlaceReservation.text.toString()
                    .isBlank() || binding.spTypeReservation.selectedItem.toString()
                    .isBlank() || binding.etPrixReservation.text.toString().isBlank()
            ) {
                Toast.makeText(this , "Remplissez le formulaire" , Toast.LENGTH_SHORT).show()
            } else {
                val intent=Intent(this , ReservationManagementActivity::class.java).apply {
                    putExtra(NOM , binding.etNomReservation.text.toString())
                    putExtra(PLACE , binding.etPlaceReservation.text.toString())
                    putExtra(TYPE , binding.spTypeReservation.selectedItem.toString())
                    putExtra(PRIX , binding.etPrixReservation.text.toString())
                }
                startActivity(intent)
            }
        }
    }
}