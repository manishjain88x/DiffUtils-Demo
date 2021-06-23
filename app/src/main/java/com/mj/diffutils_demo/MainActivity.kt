package com.mj.diffutils_demo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mj.diffutils_demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: ActorListAdapter
    private lateinit var actorRepository: ActorRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myAdapter = ActorListAdapter()
        binding.rvTest.layoutManager = LinearLayoutManager(this)
        binding.rvTest.adapter = myAdapter
        actorRepository = ActorRepository()
        setData(actorRepository.actorsSortedByName)

    }

    private fun setData(actors: List<Actor>) {
        myAdapter.differ.submitList(actors)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.sort_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sort_by_name -> {
                setData(actorRepository.actorsSortedByName)
                true
            }
            R.id.sort_by_rating -> {
                setData(actorRepository.actorsSortedByRating)
                true
            }
            R.id.sort_by_birth -> {
                setData(actorRepository.actorsSortedByYearOfBirth)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}