package com.book.store.stock.karafs.ui.ruler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.book.store.stock.karafs.R
import com.book.store.stock.karafs.databinding.FragmentHomeBinding
import com.book.store.stock.karafs.utility.RulerView

class RulerFragment : Fragment() {

    private lateinit var rulerViewModel: RulerViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rulerViewModel =
            ViewModelProvider(this).get(RulerViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeToCM()
        changeToINCH()
    }

    private fun changeToINCH() {
        binding.inch.setOnClickListener {
            binding.vertical.unitType = RulerView.Unit.INCH
            binding.horizontal.unitType = RulerView.Unit.INCH
            binding.horizontal.setDefaultLabelText("اینچ")
            binding.vertical.setDefaultLabelText("اینچ")
        }
    }

    private fun changeToCM() {
        binding.cm.setOnClickListener {
            binding.vertical.unitType = RulerView.Unit.CM
            binding.horizontal.unitType = RulerView.Unit.CM
            binding.horizontal.setDefaultLabelText("سانتی متر")
            binding.vertical.setDefaultLabelText("سانتی متر")
        }
    }
}