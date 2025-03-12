package com.example.qltv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StaffListFragment : Fragment() {

    private val viewModel: LibraryViewModel by activityViewModels()
    private lateinit var rvStaff: RecyclerView
    private lateinit var staffAdapter: StaffAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_staff_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvStaff = view.findViewById(R.id.rvStaff)
        staffAdapter = StaffAdapter(emptyList()) { selectedUser ->
            viewModel.updateCurrentUser(selectedUser)
            requireActivity().supportFragmentManager.popBackStack()
        }
        rvStaff.layoutManager = LinearLayoutManager(requireContext())
        rvStaff.adapter = staffAdapter

        // Danh sách nhân viên cố định
        val users = listOf(
            User(1, "Nguyen Van A"),
            User(2, "Tran Thi B")
        )
        staffAdapter.updateUsers(users)
    }
}
