package com.example.qltv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookListFragment : Fragment() {
    private val viewModel: LibraryViewModel by activityViewModels()
    private lateinit var rvBookList: RecyclerView
    private lateinit var btnAddNewBook: Button
    private lateinit var bookAdapter: BookAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_book_list, container, false)
        rvBookList = view.findViewById(R.id.rvBookList)
        btnAddNewBook = view.findViewById(R.id.btnAddNewBook)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Sử dụng adapter không có callback
        bookAdapter = BookAdapter(emptyList())
        rvBookList.layoutManager = LinearLayoutManager(requireContext())
        rvBookList.adapter = bookAdapter

        viewModel.books.observe(viewLifecycleOwner, Observer { books ->
            bookAdapter.updateBooks(books)
        })

        btnAddNewBook.setOnClickListener {
            (activity as MainActivity).replaceFragment(AddBookFragment())
        }
    }
}
