package com.example.scienceKid

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.scienceKid.Screens.HomeScreen
import com.example.scienceKid.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val qrCodeScanner = QRCodeScanner(this)
        qrCodeScanner.initViews(binding)

        setContent{
            // Include the HomeScreen composable
            HomeScreen(qrCodeScanner)
        }
    }
}

