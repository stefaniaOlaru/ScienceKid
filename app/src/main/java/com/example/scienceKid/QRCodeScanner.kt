package com.example.scienceKid

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.scienceKid.databinding.ActivityMainBinding
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions

class QRCodeScanner(private val activity: AppCompatActivity) {
    private val requestPermissionLauncher =
        activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                showCamera()
            } else {
                // Permission is denied
            }
        }

    private val scanLauncher =
        activity.registerForActivityResult(ScanContract()) { result: ScanIntentResult ->
            if (result.contents == null) {
                Toast.makeText(activity, "Cancelled", Toast.LENGTH_SHORT).show()
            } else {
                setResult(result.contents)
            }
        }

    private lateinit var binding: ActivityMainBinding

    fun setResult(result: String) {
        if (isYouTubeUrl(result)) {
            openYouTubeVideo(result)
        } else {
            // Dacă nu este un URL YouTube, afișează rezultatul ca text
            binding.textResult.text = result
        }
    }

    private fun isYouTubeUrl(url: String): Boolean {
        // Verifică dacă URL-ul începe cu "https://www.youtube.com" sau "https://m.youtube.com"
        return url.startsWith("https://www.youtube.com") || url.startsWith("https://m.youtube.com")
    }

    private fun openYouTubeVideo(url: String) {
        // Deschide videoclipul YouTube folosind un Intent
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        activity.startActivity(intent)
    }

    fun showCamera() {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Scan a QR code")
        options.setCameraId(0)
        options.setBeepEnabled(false)
        options.setBarcodeImageEnabled(true)
        options.setOrientationLocked(false)

        scanLauncher.launch(options)
    }

    fun initViews(binding: ActivityMainBinding) {
        this.binding = binding
        binding.fab.setOnClickListener {
            checkPermissionCamera(activity)
        }
    }

    private fun checkPermissionCamera(context: Context) {
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.CAMERA
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            showCamera()
        } else if (activity.shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
            Toast.makeText(context, "Permission needed to scan QR code", Toast.LENGTH_SHORT).show()
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }
}
