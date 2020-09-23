package com.test.karafs.ui.ruler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.book.store.stock.karafs.databinding.FragmentHomeBinding
import com.test.karafs.R
import com.test.karafs.utility.RulerView

class RulerFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeToUnit()
        changeRuler()
    }

    private fun changeRuler() {
        binding.orientationSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.horizontal.visibility = View.VISIBLE
                binding.vertical.visibility = View.GONE
            } else {
                binding.horizontal.visibility = View.GONE
                binding.vertical.visibility = View.VISIBLE
            }
        }
    }

    private fun changeToUnit() {
        binding.unitSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                changeToCM()
            } else {
                changeToINCH()
            }
        }
    }

    private fun changeToINCH() {
        binding.vertical.unitType = RulerView.Unit.INCH
        binding.horizontal.unitType = RulerView.Unit.INCH
        binding.horizontal.setDefaultLabelText("اینچ")
        binding.vertical.setDefaultLabelText("اینچ")

    }

    private fun changeToCM() {
        binding.vertical.unitType = RulerView.Unit.CM
        binding.horizontal.unitType = RulerView.Unit.CM
        binding.horizontal.setDefaultLabelText("سانتی متر")
        binding.vertical.setDefaultLabelText("سانتی متر")

    }
}