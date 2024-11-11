package tn.esprit.touristick

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnInscrit: Button
    private lateinit var btnLogin:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnInscrit=findViewById(R.id.btnInscrit)
        btnLogin=findViewById(R.id.btnLogin)
        btnInscrit.setOnClickListener{
            val intent= Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}