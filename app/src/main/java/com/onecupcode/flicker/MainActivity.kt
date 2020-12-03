package com.onecupcode.flicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.onecupcode.flicker.databinding.ActivityMainBinding
import com.onecupcode.flicker.ui.PhotoAdapter
import com.onecupcode.flicker.ui.PhotoRepositoryViewmodel
import com.onecupcode.flicker.ui.ReposLoadStateAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PhotoRepositoryViewmodel
    private val adapter = PhotoAdapter()

    private var searchJob: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this,Injection.provideViewModelFactory(this))
            .get(PhotoRepositoryViewmodel::class.java)

        val decoration = DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL)
        binding.list.addItemDecoration(decoration)

        initAdapter()
        search()

        lifecycleScope.launch{
            adapter.loadStateFlow
                .distinctUntilChangedBy{  it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.list.scrollToPosition(0) }
        }

    }

    private fun initAdapter(){

        binding.list.adapter = adapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter { adapter.retry() },
            footer = ReposLoadStateAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->
            // Only show the list if refresh succeeds.
            binding.list.isVisible = loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            // Show the retry state if initial load or refresh fails.
            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

            // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    this,
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }


    private fun search() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getPhotoList("").collectLatest {
                adapter.submitData(it)
            }
        }
    }
}