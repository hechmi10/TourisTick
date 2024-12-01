package tn.esprit.touristick.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.adapters.ReservationController
import tn.esprit.touristick.databinding.ActivityListReservationsBinding
import tn.esprit.touristick.entities.Reservation

class ListReservationsActivity: AppCompatActivity() {
    private lateinit var binding:ActivityListReservationsBinding
    private lateinit var controller: ReservationController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityListReservationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        controller.fetchAllReservations {reservations: List<Reservation> ->
            for(r in reservations){
                controller.searchReservation(r.getNom(),{reservation->{

                }})
            }
        }
    }
}