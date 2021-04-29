package com.data.databook.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.data.databook.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(this@MainActivity, R.id.navHostMain).navigateUp()
    }

    // MARK: - Internal logic
    private fun setupNavigation() {
        val navController = findNavController(this@MainActivity, R.id.navHostMain)
        bottomNavigation.setupWithNavController(navController)
    }
}
