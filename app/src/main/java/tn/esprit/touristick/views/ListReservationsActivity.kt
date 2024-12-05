package tn.esprit.touristick.views

import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.touristick.controllers.ReservationController
import tn.esprit.touristick.databinding.ActivityListReservationsBinding
import tn.esprit.touristick.models.Reservation

class ListReservationsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityListReservationsBinding
    private lateinit var controller:ReservationController
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityListReservationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        controller=ReservationController.getInstance()
        controller.fetchAllReservations { reservations ->
            runOnUiThread {
                populateTable(reservations)
            }
        }
    }

    private fun populateTable(reservations:List<Reservation>) {
        // Clear all views except the header (assumed header is at index 0)
        binding.tableLayout.removeViews(1 , binding.tableLayout.childCount - 1)

        for (r in reservations) {
            // Create a new TableRow for each reservation
            val tableRow=TableRow(this)

            // Create new TextViews for each field in the reservation
            val tvNom=TextView(this).apply {
                text=r.getNom()
                setPadding(8 , 8 , 8 , 8)
            }

            val tvPlace=TextView(this).apply {
                text=r.getPlace()
                setPadding(8 , 8 , 8 , 8)
            }

            val tvType=TextView(this).apply {
                text=r.getType().name
                setPadding(8 , 8 , 8 , 8)
            }

            val tvPrix=TextView(this).apply {
                text=r.getPrix()
                setPadding(8 , 8 , 8 , 8)
            }

            // Add the TextViews to the TableRow
            tableRow.addView(tvNom)
            tableRow.addView(tvPlace)
            tableRow.addView(tvType)
            tableRow.addView(tvPrix)

            // Add the TableRow to the TableLayout
            binding.tableLayout.addView(tableRow)
        }
    }

}