package io.supercharge.testingexample.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.Disposable
import io.supercharge.testingexample.R
import io.supercharge.testingexample.databinding.ActivityMainBinding
import io.supercharge.testingexample.model.MainViewModel
import io.supercharge.testingexample.view.adapter.NoteAdapter
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var mainViewModelFactory: ViewModelProvider.Factory

    private lateinit var disposable: Disposable

    private val noteAdapter = NoteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel::class.java)

        val binding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.vm = viewModel

        disposable = viewModel.noteList
            .filter { notes -> !notes.isEmpty() }
            .doOnError { t: Throwable? -> Timber.d(t) }
            .subscribe { notes ->
                noteAdapter.setItems(notes)
                noteList.adapter = noteAdapter
                amountText.text = getString(R.string.amount_label, viewModel.calculate(notes))
            }
    }

    override fun onDestroy() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }

        super.onDestroy()
    }
}
