package com.book.store.stock.karafs.ui.relations


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        getUserRelations()

    }

    private fun getUserRelations() {
        relationViewModel.getUser()
        relationViewModel.userStatus.observe(viewLifecycleOwner, Observer {
            it.let {
                when (it) {
                    RelationViewModel.UserStatus.NoInternet -> noInternet()
                    RelationViewModel.UserStatus.UnAuthorized -> unAuthorized()
                    RelationViewModel.UserStatus.ServerError -> serverError()
                    RelationViewModel.UserStatus.ShowLoading -> showLoading()
                    RelationViewModel.UserStatus.HideLoading -> hideLoading()
                }
            }
        })
        observeError()
        observeData()
    }

    /**
     * Observe Relation Data and call method to set to EditText
     */
    private fun observeData() {
        relationViewModel.relations.observe(viewLifecycleOwner, Observer {
            showRelations(it)
        })
    }

    /**
     * set data to edit text
     */
    private fun showRelations(relations: StringBuilder?) {
        binding.relations.text = relations
    }

    private fun observeError() {
        relationViewModel.error.observe(viewLifecycleOwner, Observer {
            showError(it)
        })
    }

    private fun showError(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    private fun hideLoading() {
        binding.progressAction.visibility = View.GONE
    }

    private fun showLoading() {
        binding.progressAction.visibility = View.VISIBLE
    }

    private fun serverError() {
        Toast.makeText(context, "server error", Toast.LENGTH_LONG).show()
    }

    private fun unAuthorized() {
        Toast.makeText(context, "unAuthorized", Toast.LENGTH_LONG).show()
    }

    private fun noInternet() {
        Toast.makeText(context, "no Internet connection", Toast.LENGTH_LONG).show()
    }
}