package com.virtualspace.vspace

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDescargar11 = findViewById<Button>(R.id.btn_descargar_room11)
        val btnDescargar12 = findViewById<Button>(R.id.btn_descargar_room12)
        val btnIniciar = findViewById<Button>(R.id.btn_iniciar_virtual)

        val urlRoom11 = "https://github.com/emanuelyoan470/RoomsforVSpace/releases/download/1.0.0/Room_11.zip"
        val urlRoom12 = "https://github.com/emanuelyoan470/RoomsforVSpace/releases/download/1.0.0/Room_12.zip"

        btnDescargar11.setOnClickListener { descargarRoom(this, urlRoom11, "Room_11.zip") }
        btnDescargar12.setOnClickListener { descargarRoom(this, urlRoom12, "Room_12.zip") }

        btnIniciar.setOnClickListener {
            if (!Settings.canDrawOverlays(this)) {
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + packageName))
                startActivity(intent)
            } else {
                startService(Intent(this, FloatingButtonService::class.java))
            }
        }
    }

    private fun descargarRoom(context: Context, url: String, nombreArchivo: String) {
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle("Descargando Room")
            .setDescription("Descargando $nombreArchivo...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, nombreArchivo)

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }
}
