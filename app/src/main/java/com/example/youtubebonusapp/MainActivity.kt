package com.example.youtubebonusapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class MainActivity : AppCompatActivity() {
    lateinit var gridView: GridView
    private lateinit var youTubePlayerView: YouTubePlayerView

    //array for video Name , video id
    val videos = arrayOf(
            arrayOf("Histogram Equalization", "PD5d7EKYLcA"),
            arrayOf("JavaFX 8 Table and Database", "lpqZzHaGsyI"),
            arrayOf("MATLAB tutorial: How to convert an RGB image to grayscale", "BmwFEcByLdY"),
            arrayOf("Favourite button android studio", "42Vv4_SOvlw")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gridView = findViewById(R.id.gridView)
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        activeNetwork?.isConnectedOrConnecting
        if (activeNetwork?.isConnectedOrConnecting == true) {
            //do something with the network
            youTubePlayerView = findViewById(R.id.youtube_player_view)
            lifecycle.addObserver(youTubePlayerView)
            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    //val videoId = videos[0][1]
                    youTubePlayer.loadVideo("Q0gRqbtFLcw", 0f)
                    RV(youTubePlayer)
                }
            })
        } else {
            println("Not Connected To The Internet")
        }
    }

    fun RV(youTubePlayer: YouTubePlayer) {
        val mainAdapter = GridViewAdapter(this, videos, youTubePlayer)
        gridView.adapter = mainAdapter
    }

}