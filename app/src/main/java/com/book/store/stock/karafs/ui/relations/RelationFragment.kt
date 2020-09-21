package com.book.store.stock.karafs.ui.relations


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.book.store.stock.karafs.R
import com.book.store.stock.karafs.databinding.FragmentDashboardBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RelationFragment : DaggerFragment() {

    private lateinit var relationViewModel: RelationViewModel
    private lateinit var binding: FragmentDashboardBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!(::binding.isInitialized)) {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
            relationViewModel = ViewModelProvider(this, factory).get(RelationViewModel::class.java)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        relationViewModel.getUser()
        relationViewModel.relations.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onViewCreated: " + it)
        })
    }
}