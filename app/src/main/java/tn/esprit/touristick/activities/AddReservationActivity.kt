package tn.esprit.touristick.activities

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.databinding.ActivityAddReservationBinding
import tn.esprit.touristick.entities.TypeReservation
import tn.esprit.touristick.adapters.ReservationController
import tn.esprit.touristick.entities.Reservation

const val NOM="Nom"
const val PLACE="Place"
const val TYPE="Type"
const val PRIX="Prix"

class AddReservationActivity:AppCompatActivity() {
    private lateinit var binding:ActivityAddReservationBinding
    private lateinit var controller:ReservationController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)

// Fetch choices from the enum
        val types = TypeReservation.entries.map { it.name }

// Create an ArrayAdapter using the default Spinner layout
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, types)

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

// Apply the adapter to the spinner
        binding.spTypeReservation.adapter = adapter
        binding.btnAdd.setOnClickListener {
            if(binding.etNomReservation.text.toString().isBlank() || binding.etPlaceReservation.text.toString().isBlank() || binding.spTypeReservation.toString().isBlank()|| binding.etPrixReservation.text.toString().isBlank()){
                Toast.makeText(this,"Remplissez le formulaire",Toast.LENGTH_SHORT).show()
            }else {
                controller.addReservation(Reservation(binding.etNomReservation.text.toString(),binding.etPlaceReservation.text.toString(),TypeReservation.valueOf(binding.spTypeReservation.adapter.toString()),binding.etPrixReservation.text.toString().toDouble()),this)
                val intent = Intent(this, ReservationManagementActivity::class.java).apply{
                    putExtra(NOM,binding.etNomReservation.text.toString())
                    putExtra(PLACE,binding.etPlaceReservation.text.toString())
                    putExtra(TYPE,TypeReservation.valueOf(binding.spTypeReservation.adapter.toString()))
                    putExtra(PRIX,binding.etPrixReservation.text.toString())
                }
                startActivity(intent)
            }
        }
    }
}