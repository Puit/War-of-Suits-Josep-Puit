package com.nunnos.warofsuitsjoseppuit.presentation.feature.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nunnos.warofsuitsjoseppuit.R
import com.nunnos.warofsuitsjoseppuit.databinding.FragmentDashboardBinding
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.activity.MainActivity
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.activity.vm.MainViewModel


class DashboardFragment() : Fragment() {
    lateinit var databinding: FragmentDashboardBinding
    lateinit var shareViewModel: MainViewModel

    companion object {
        fun newInstance(): DashboardFragment {
            val fragment = DashboardFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shareViewModel = (requireActivity() as MainActivity).viewModel
        databinding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_dashboard)

        setListeners()
        return inflater.inflate(R.layout.fragment_dashboard,container,false)
    }

    private fun setListeners() {
        databinding.dashboardStartBtn.setOnClickListener {
            shareViewModel.navigateToGame()
        }
        databinding.dashboardOldGamesBtn.setOnClickListener {
            shareViewModel.navigateToOldGames()
        }
    }
}