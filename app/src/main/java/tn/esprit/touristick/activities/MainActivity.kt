package tn.esprit.touristick.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import tn.esprit.touristick.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        val cin=intent.getStringExtra(CIN)
        val nom=intent.getStringExtra(NOM_TOURISTE)
        val prenom=intent.getStringExtra(PRENOM_TOURISTE)
        val email=intent.getStringExtra(EMAIL)
        val mdp=intent.getStringExtra(MOT_DE_PASSE)
        binding.btnInscrit.setOnClickListener{
            val intent= Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            val intent=Intent(this, LoginActivity::class.java).apply {
                putExtra(CIN,cin)
                putExtra(NOM_TOURISTE,nom)
                putExtra(PRENOM_TOURISTE,prenom)
                putExtra(EMAIL,email)
                putExtra(MOT_DE_PASSE,mdp)
            }
            startActivity(intent)

        }
    }


}