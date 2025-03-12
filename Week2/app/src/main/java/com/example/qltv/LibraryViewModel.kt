package com.example.qltv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// Data class cho sách
data class Book(val id: Int, val title: String, var isBorrowed: Boolean = false)

// Data class cho nhân viên, có trường borrowedBooks để lưu sách đã mượn
data class User(val id: Int, val name: String, val borrowedBooks: MutableList<Book> = mutableListOf())

class LibraryViewModel : ViewModel() {
    // Danh sách sách tổng (master list)
    private val _books = MutableLiveData<MutableList<Book>>(mutableListOf(
        Book(1, "Sách 1"),
        Book(2, "Sách 2"),
        Book(3, "Sách 3")
    ))
    val books: LiveData<MutableList<Book>> get() = _books

    // Danh sách nhân viên cố định
    private val _users = listOf(
        User(1, "Nguyen Van A"),
        User(2, "Tran Thi B")
    )

    // Người dùng hiện hành
    private val _currentUser = MutableLiveData<User>(_users[0])
    val currentUser: LiveData<User> get() = _currentUser

    // Chuyển đổi nhân viên
    fun toggleUser() {
        _currentUser.value = if (_currentUser.value?.id == 1) _users[1] else _users[0]
    }

    // Cập nhật current user (sử dụng khi chọn từ danh sách nhân viên)
    fun updateCurrentUser(user: User) {
        _currentUser.value = user
    }

    // Thêm sách mới vào master list
    fun addBook(book: Book) {
        _books.value?.add(book)
        _books.value = _books.value
    }

    // Khi mượn sách: đánh dấu sách là đã mượn và thêm vào danh sách sách của current user (nếu chưa có)
    fun borrowBook(book: Book) {
        _books.value?.let { list ->
            val index = list.indexOfFirst { it.id == book.id }
            if (index != -1) {
                val borrowedBook = book.copy(isBorrowed = true)
                list[index] = borrowedBook
                _books.value = list
                _currentUser.value?.let { user ->
                    if (!user.borrowedBooks.any { it.id == borrowedBook.id }) {
                        user.borrowedBooks.add(borrowedBook)
                        // Cập nhật currentUser để thông báo thay đổi
                        _currentUser.value = user
                    }
                }
            }
        }
    }
}
