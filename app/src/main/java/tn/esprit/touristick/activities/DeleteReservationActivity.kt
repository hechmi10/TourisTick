package tn.esprit.touristick.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.databinding.ActivityDeleteReservationBinding

class DeleteReservationActivity :AppCompatActivity(){
    private lateinit var binding:ActivityDeleteReservationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDeleteReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnDelete.setOnClickListener {
            if(binding.etIdDelete.text.toString().isBlank()){
                Toast.makeText(this,"Remplissez le formulaire",Toast.LENGTH_SHORT).show()
            }else {
                val intent = Intent(this, ReservationManagementActivity::class.java)
                startActivity(intent)
            }
        }
    }

}