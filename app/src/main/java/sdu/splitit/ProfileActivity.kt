package sdu.splitit
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val emailInput = findViewById<EditText>(R.id.emailInput)
        val saveButton = findViewById<Button>(R.id.saveButton)

        // Load saved profile data here, if any
        // Example:
        // nameInput.setText(savedName)
        // emailInput.setText(savedEmail)

        saveButton.setOnClickListener {
            val name = nameInput.text.toString()
            val email = emailInput.text.toString()

            // Save profile data here
            // Example: save to SharedPreferences or send to a server

            // Display feedback to user (e.g., Toast message)
            Toast.makeText(this, "Profile saved successfully", Toast.LENGTH_SHORT).show()
        }
    }
}

