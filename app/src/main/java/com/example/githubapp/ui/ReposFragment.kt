package com.example.githubapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.R
import com.example.githubapp.databinding.ReposFragmentBinding
import com.example.githubapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ReposFragment : Fragment() {

    private val viewModel: ReposViewModel by viewModels()

    private var currentBinding: ReposFragmentBinding? = null
    private val binding get() = currentBinding!!

    private lateinit var reposAdapter: ReposAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        currentBinding = ReposFragmentBinding.inflate(inflater, container, false)
        reposAdapter = ReposAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.reposList.apply {
            adapter = reposAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.repos.collect { result ->
                when (result) {
                    is Resource.Success -> {
                        reposAdapter.submitList(result.data)
                        binding.loading.isVisible = false
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), result.error?.localizedMessage, Toast.LENGTH_SHORT).show()
                        binding.loading.isVisible = false
                        result.data?.let { reposAdapter.submitList(it) }
                    }
                    is Resource.Loading -> {
                        binding.loading.isVisible = true
                        result.data?.let { reposAdapter.submitList(it) }
                    }
                    else -> Unit
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        currentBinding = null
    }
}