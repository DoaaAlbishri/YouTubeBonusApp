package com.example.youtubebonusapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import kotlinx.android.synthetic.main.item_row.view.*

internal class GridViewAdapter(
            private val context: Context,
            private val videos: Array<Array<String>>,
            private val youTubePlayer: YouTubePlayer
    ) :
            BaseAdapter() {
        private var layoutInflater: LayoutInflater? = null
        private lateinit var button: Button
        override fun getCount(): Int {
            return videos.size
        }

        override fun getItem(position: Int): Any? {
            return null
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
        ): View? {
            var convertView = convertView
            if (layoutInflater == null) {
                layoutInflater =
                        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            }
            if (convertView == null) {
                convertView = layoutInflater!!.inflate(R.layout.item_row, null)
            }

            val videoName=videos[position][0]
            val videoId=videos[position][1]
            button = convertView!!.findViewById(R.id.button)
            button.text = videoName
            button.setOnClickListener {
                youTubePlayer.loadVideo(videoId,0f)
            }
            return convertView
        }
    }
