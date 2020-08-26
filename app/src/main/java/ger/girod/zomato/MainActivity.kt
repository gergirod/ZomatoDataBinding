package ger.girod.zomato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import ger.girod.zomato.data.repository.RestaurantRepository
import ger.girod.zomato.ui.restaurants.RestaurantsFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var restaurantRepository : RestaurantRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment(RestaurantsFragment())

    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}