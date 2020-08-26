package ger.girod.zomato.ui.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ger.girod.zomato.R
import ger.girod.zomato.data.injection.view_model.ViewModelFactory
import ger.girod.zomato.databinding.FragmentRestaurantsBinding
import ger.girod.zomato.ui.base.BaseFragment
import ger.girod.zomato.ui.restaurants.adapter.RestaurantListAdapter
import javax.inject.Inject


class RestaurantsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: FragmentRestaurantsBinding

    private lateinit var restaurantsViewModel: RestaurantsViewModel
    private val restaurantListAdapter: RestaurantListAdapter by lazy {
        RestaurantListAdapter()
    }

    companion object {
        @JvmStatic
        @InverseBindingAdapter(
            attribute = "lastCurrentPosition",
            event = "lastCurrentPositionAttributeChanged"
        )
        fun getCurrentPosition(rv: RecyclerView): Int {
            return (rv.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        }

        @JvmStatic
        @BindingAdapter(value = ["lastCurrentPositionAttributeChanged"])
        fun setListener(rv: RecyclerView, l: InverseBindingListener) {
            val layoutManager = (rv.layoutManager as LinearLayoutManager?)!!
            var prevPos = layoutManager.findFirstVisibleItemPosition()
            rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy == 0) {
                        return
                    }
                    val newPos = layoutManager.findFirstVisibleItemPosition()
                    if (prevPos != newPos) {
                        prevPos = newPos
                        l.onChange()
                    }
                }
            })
        }

        @JvmStatic
        @BindingAdapter("lastCurrentPosition")
        fun setCurrentPosition(rv: RecyclerView, pos: Int) {
            (rv.layoutManager as LinearLayoutManager).scrollToPosition(pos)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        restaurantsViewModel = ViewModelProviders
            .of(this, viewModelFactory)[RestaurantsViewModel::class.java]
        setList()
        binding.viewModel = restaurantsViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_restaurants, container, false
        )

        binding.lifecycleOwner = this
        return binding.root
    }

    private fun setList() {
        binding.list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = restaurantListAdapter
        }
    }
}