package be.christiano.portfolio.app.ui.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import be.christiano.portfolio.app.R
import be.christiano.portfolio.app.databinding.FragmentComponentsBinding
import be.christiano.portfolio.app.ui.base.ToolbarDelegate
import be.christiano.portfolio.app.ui.base.ToolbarDelegateImpl
import be.christiano.portfolio.app.ui.base.dataBinding
import com.google.android.material.snackbar.Snackbar

// TODO: a list with all components (left item is a little snapshot or thumbnail of the actual view!)
// TODO: upon clicking, open a new fragment/activity with the component showing. If possible, also show a toggle to show the compose or xml version?
class ComponentsFragment : Fragment(), ToolbarDelegate by ToolbarDelegateImpl() {

    private val mViewModel by viewModels<ComponentsViewModel>()
    private val binding by dataBinding<FragmentComponentsBinding>(R.layout.fragment_components) {
        registerToolbar(this@ComponentsFragment, mtbMain)
        viewModel = mViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        val options = listOf("Android", "iOS", "Web", "Flutter", "KMP")
        val adapter = ArrayAdapter(requireContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item, options)
        binding.atvOptions.setAdapter(adapter)
        binding.atvOptions.setOnItemClickListener { _, _, position, _ ->
            Snackbar.make(requireView(), options[position], Snackbar.LENGTH_SHORT).show()
        }

        binding.mcvCheckable.setOnClickListener {
            binding.mcvCheckable.isChecked = !binding.mcvCheckable.isChecked
        }
    }
}
