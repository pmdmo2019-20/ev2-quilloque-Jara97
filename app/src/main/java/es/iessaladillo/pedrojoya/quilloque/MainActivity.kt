package es.iessaladillo.pedrojoya.quilloque

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private val navController: NavController by lazy {
        findNavController(R.id.navHostFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setBottonNavigationView()
        setupAppBar()
        viewModel.setBD(this)
    }

    private fun setupAppBar() {
        setSupportActionBar(toolbar)
    }

    private fun setBottonNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.mnuDial -> navController.navigate(R.id.dialNavigation)
                R.id.mnuRecent ->navController.navigate(R.id.recentNavigation)
                R.id.mnuContacts ->navController.navigate(R.id.contactsNavigation)
                else -> true
            }
            true
        }
    }




}
