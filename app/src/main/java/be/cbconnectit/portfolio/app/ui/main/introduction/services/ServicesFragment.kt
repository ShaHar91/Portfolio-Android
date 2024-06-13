package be.cbconnectit.portfolio.app.ui.main.introduction.services

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import be.cbconnectit.portfolio.app.R
import be.cbconnectit.portfolio.app.databinding.FragmentServicesBinding
import be.cbconnectit.portfolio.app.ui.main.base.ToolbarDelegate
import be.cbconnectit.portfolio.app.ui.main.base.ToolbarDelegateImpl
import be.cbconnectit.portfolio.app.ui.main.base.dataBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ServicesFragment : Fragment(), ToolbarDelegate by ToolbarDelegateImpl() {

    private val mViewModel by viewModel<ServicesViewModel>()
    private val binding by dataBinding<FragmentServicesBinding>(R.layout.fragment_services) {
        registerToolbar(this@ServicesFragment, mtbMain)
        viewModel = mViewModel
    }

    private val serviceAdapter by lazy {
        null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initObservers()
    }

    private fun initViews() {
//        binding.rvItems.adapter = serviceAdapter
//        binding.rvItems.setHasFixedSize(false)
    }

    private fun initObservers() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            mViewModel.state.collectLatest {
//                serviceAdapter.submitList(it.servicess)
//            }
//        }
    }
}