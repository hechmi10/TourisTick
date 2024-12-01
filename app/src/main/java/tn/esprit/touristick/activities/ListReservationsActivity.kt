package tn.esprit.touristick.activities

import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
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
        controller=ReservationController.getInstance()
        controller.fetchAllReservations { reservations ->
            runOnUiThread {
                populateTable(reservations)
            }
        }
    }

    private fun populateTable(reservations: List<Reservation>) {
        binding.tableLayout.removeViews(1,binding.tableLayout.childCount-1)
        for (r in reservations){
            // Create a new TableRow
            val tableRow = TableRow(this)

            // Create TextViews for each field in the reservation
            binding.tvNomDonnee.text= r.getNom()
            binding.tvNomDonnee.setPadding(8, 8, 8, 8)

            binding.tvPlaceDonnee.text = r.getPlace()
            binding.tvPlaceDonnee.setPadding(8, 8, 8, 8)


            binding.tvTypeDonnee.text = r.getType().name
            binding.tvTypeDonnee.setPadding(8, 8, 8, 8)


            binding.tvPrixDonnee.text = String.format(r.getPrix().toString())
            binding.tvPrixDonnee.setPadding(8, 8, 8, 8)


            // Add TextViews to the TableRow
            tableRow.addView(binding.tvNomDonnee)
            tableRow.addView(binding.tvPlaceDonnee)
            tableRow.addView(binding.tvTypeDonnee)
            tableRow.addView(binding.tvPrixDonnee)

            // Add the TableRow to the TableLayout
            binding.tableLayout.addView(tableRow)
        }
    }
}