package com.example.qltv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ManagementFragment : Fragment() {

    private val viewModel: LibraryViewModel by activityViewModels()
    private lateinit var etEmployee: EditText
    private lateinit var btnChange: Button
    private lateinit var rvBooks: RecyclerView
    private lateinit var btnThem: Button
    private lateinit var bookAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_management, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        etEmployee = view.findViewById(R.id.etEmployee)
        btnChange = view.findViewById(R.id.btnChange)
        rvBooks = view.findViewById(R.id.rvBooks)
        btnThem = view.findViewById(R.id.btnThem)

        // Sử dụng adapter để hiển thị danh sách sách đã mượn của current user
        bookAdapter = BookAdapter(emptyList(), onItemClick = null)
        rvBooks.layoutManager = LinearLayoutManager(requireContext())
        rvBooks.adapter = bookAdapter

        // Quan sát current user: cập nhật tên và danh sách sách đã mượn
        viewModel.currentUser.observe(viewLifecycleOwner, Observer { user ->
            etEmployee.setText(user.name)
            bookAdapter.updateBooks(user.borrowedBooks)
        })

        btnChange.setOnClickListener {
            viewModel.toggleUser()
        }

        // Nút Thêm mở màn hình BookSelectionFragment để chọn sách cho nhân viên mượn
        btnThem.setOnClickListener {
            (activity as MainActivity).replaceFragment(BookSelectionFragment())
        }
    }
}
