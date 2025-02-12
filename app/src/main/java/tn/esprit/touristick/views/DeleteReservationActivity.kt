package tn.esprit.touristick.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.controllers.ReservationController
import tn.esprit.touristick.databinding.ActivityDeleteReservationBinding


class DeleteReservationActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDeleteReservationBinding
    private lateinit var controller:ReservationController
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDeleteReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        controller=ReservationController.getInstance()
        binding.btnDelete.setOnClickListener {
            if (binding.etNomDelete.text.toString().isBlank()) {
                Toast.makeText(this , "Remplissez le formulaire" , Toast.LENGTH_SHORT).show()
            } else {
                controller.deleteReservation(binding.etNomDelete.text.toString() , this)
                val intent=Intent(this , ReservationManagementActivity::class.java)
                startActivity(intent)
            }
        }
    }

}