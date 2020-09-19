package com.book.store.stock.karafs.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.book.store.stock.karafs.R

class NavigationFragment : Fragment() {

    private lateinit var navigationViewModel: NavigationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navigationViewModel =
            ViewModelProvider(this).get(NavigationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)

        return root
    }
}