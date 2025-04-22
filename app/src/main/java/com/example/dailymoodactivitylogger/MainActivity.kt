package com.example.dailymoodactivitylogger

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var tvDateTime: TextView
    private lateinit var etActivity: EditText
    private lateinit var btnSave: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hubungkan view
        tvDateTime = findViewById(R.id.tvDateTime)
        etActivity = findViewById(R.id.etActivity)
        btnSave = findViewById(R.id.btnSave)
        tvResult = findViewById(R.id.tvResult)

        // Tampilkan waktu sekarang
        val currentDateTime = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date())
        tvDateTime.text = "Sekarang: $currentDateTime"

        // Klik tombol simpan
        btnSave.setOnClickListener {
            val activity = etActivity.text.toString()

            if (activity.isBlank()) {
                Toast.makeText(this, "Harap isi aktivitas terlebih dahulu.", Toast.LENGTH_SHORT).show()
            } else {
                // Tampilkan AlertDialog sebelum menyimpan
                val dialog = AlertDialog.Builder(this)
                    .setTitle("Konfirmasi Simpan")
                    .setMessage("Yakin ingin menyimpan aktivitas ini?\n\nAktivitas: $activity")
                    .setPositiveButton("Ya") { _, _ ->
                        val result = "[$currentDateTime]\nAktivitas: $activity"
                        tvResult.text = result
                        Toast.makeText(this, "Aktivitas disimpan.", Toast.LENGTH_SHORT).show()
                        etActivity.text.clear()
                    }
                    .setNegativeButton("Batal", null)
                    .create()

                dialog.show()
            }
        }
    }
}