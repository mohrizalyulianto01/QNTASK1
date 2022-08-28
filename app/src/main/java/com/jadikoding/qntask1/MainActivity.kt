package com.jadikoding.qntask1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sportsViewModel: SportsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sportsViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[SportsViewModel::class.java]

        sportsViewModel.sportsItem.observe(this) {
            val sportsAdapter = SportsAdapter(it)
            sportsAdapter.setOnItemClickCallback(object : SportsAdapter.OnItemClickCallback {
                override fun onItemClicked(data: SportsItem) {
                    val i = Intent(this@MainActivity, SportDetailActivity::class.java)
                    i.putExtra(SportDetailActivity.EXTRA_NAME, data.strSport)
                    i.putExtra(SportDetailActivity.EXTRA_THUMB, data.strSportThumb)
                    i.putExtra(SportDetailActivity.EXTRA_DESC, data.strSportDescription)
                    startActivity(i)
                }
            })
            binding.rvSports.layoutManager = GridLayoutManager(this, 2)
            binding.rvSports.adapter = sportsAdapter
        }

        sportsViewModel.isLoading.observe(this) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }
}