package com.jadikoding.qntask1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.ridianputra.allsports.databinding.ActivitySportDetailBinding

class SportDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySportDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySportDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_NAME)
        val thumb = intent.getStringExtra(EXTRA_THUMB)
        val desc = intent.getStringExtra(EXTRA_DESC)

        supportActionBar?.title = name

        Glide.with(this@SportDetailActivity)
            .load(thumb)
            .into(binding.sportThumb)
        binding.sportDescription.text = desc
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_THUMB = "extra_thumb"
        const val EXTRA_DESC = "extra_description"
    }
}