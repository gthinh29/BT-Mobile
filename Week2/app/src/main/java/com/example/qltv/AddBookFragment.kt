package com.example.qltv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class AddBookFragment : Fragment() {

    private val viewModel: LibraryViewModel by activityViewModels()
    private lateinit var etBookTitle: EditText
    private lateinit var btnSaveBook: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        etBookTitle = view.findViewById(R.id.etBookTitle)
        btnSaveBook = view.findViewById(R.id.btnSaveBook)

        btnSaveBook.setOnClickListener {
            val title = etBookTitle.text.toString()
            if (title.isNotEmpty()) {
                val newId = (viewModel.books.value?.size ?: 0) + 1
                viewModel.addBook(Book(newId, title))
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }
}
