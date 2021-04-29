package com.data.databook.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.data.core.remote.HeroesProvider
import com.data.core.remote.implementations.HeroesProviderImpl
import com.data.domain.converters.HeroesConverter
import com.data.domain.converters.HeroesConverterImpl
import com.data.domain.implementations.CarryRepositoryImpl
import com.data.domain.models.Hero

import com.data.databook.R
import com.data.databook.adapters.AdapterData
import com.data.databook.adapters.HeroClickHandler
import com.data.databook.adapters.HeroListAdapter
import com.data.databook.di.App
import com.data.databook.helpers.Keys
import com.data.databook.helpers.State
import com.data.databook.viewmodels.CarryViewModel
import kotlinx.android.synthetic.main.fragment_carry.*


class CarryFragment : Fragment() {

    private val heroesConverter: HeroesConverter = HeroesConverterImpl()
    private val heroesProvider: HeroesProvider = HeroesProviderImpl()
    private val viewModel = CarryViewModel(repository = CarryRepositoryImpl(heroesConverter = heroesConverter,
        roomAppDatabase = App.roomAppDatabase, heroesProvider = heroesProvider))
    private lateinit var mAdapter: HeroListAdapter
    private val TAG = CarryFragment::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
        mAdapter = HeroListAdapter()
        mAdapter.attachClickHandler(object : HeroClickHandler {
            override fun onItemClick(item: Hero) {
                val bundle = Bundle()
                bundle.putParcelable(Keys.Hero.title, item)
                recyclerCarries.findNavController().navigate(R.id.carryAntipickFragment, bundle)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_carry, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerCarries.layoutManager = GridLayoutManager(context, 4)
        recyclerCarries.adapter = mAdapter
        recyclerCarries.recycledViewPool.setMaxRecycledViews(0, 0)

        viewModel.state.observe(this@CarryFragment,
            Observer<State> { newValue ->
                when (newValue) {
                    is State.LoadedState<*> -> {
                        (mAdapter as AdapterData<Hero>).setData(items = newValue.data.map { it as Hero })

                        cpbCarry.visibility = View.GONE
                        llCarryLoaded.visibility = View.VISIBLE
                        txtNoItems.visibility = View.GONE
                    }
                    is State.NoItemsState -> {
                        cpbCarry.visibility = View.GONE
                        llCarryLoaded.visibility = View.GONE
                        txtNoItems.visibility = View.VISIBLE
                    }
                    is State.LoadingState -> {
                        cpbCarry.visibility = View.VISIBLE
                        llCarryLoaded.visibility = View.GONE
                        txtNoItems.visibility = View.GONE
                    }
                    is State.ErrorState -> Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
