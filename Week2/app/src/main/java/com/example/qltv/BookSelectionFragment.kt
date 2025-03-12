package com.example.qltv

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class BookSelectionFragment : Fragment() {

    private val viewModel: LibraryViewModel by activityViewModels()
    private lateinit var rvAvailableBooks: RecyclerView
    private lateinit var btnDone: Button
    private lateinit var bookAdapter: BookAdapter
    private var selectedBook: Book? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Ẩn thanh nav khi vào fragment này
        (activity as MainActivity).findViewById<BottomNavigationView>(R.id.bottom_nav_view).visibility = View.GONE

        rvAvailableBooks = view.findViewById(R.id.rvAvailableBooks)
        btnDone = view.findViewById(R.id.btnDone)

        // Khởi tạo adapter với callback cập nhật selectedBook
        bookAdapter = BookAdapter(emptyList()) { book, isSelected ->
            // Lưu lại sách được chọn theo toggle
            if (isSelected) {
                selectedBook = book
            } else if (selectedBook?.id == book.id) {
                selectedBook = null
            }
            Log.d("BookSelection", "Selected book: ${book.title}, state: $isSelected")
        }
        rvAvailableBooks.layoutManager = LinearLayoutManager(requireContext())
        rvAvailableBooks.adapter = bookAdapter

        viewModel.books.observe(viewLifecycleOwner, Observer { books ->
            // Lọc ra những sách chưa được mượn
            val availableBooks = books.filter { !it.isBorrowed }
            bookAdapter.updateBooks(availableBooks)
        })

        btnDone.setOnClickListener {
            // Nếu có sách được chọn, đánh dấu mượn
            selectedBook?.let { book ->
                viewModel.borrowBook(book)
            } ?: Toast.makeText(requireContext(), "Không có sách nào được chọn", Toast.LENGTH_SHORT).show()
            // Sau đó hiển thị lại nav và quay lại màn hình trước
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Hiển thị lại thanh nav khi rời khỏi fragment này
        (activity as MainActivity).findViewById<BottomNavigationView>(R.id.bottom_nav_view).visibility = View.VISIBLE
    }
}
