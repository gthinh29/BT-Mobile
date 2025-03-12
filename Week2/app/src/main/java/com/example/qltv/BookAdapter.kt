package com.example.qltv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter hiển thị danh sách sách dưới dạng item với TextView và icon tick.
 * Cho phép chọn nhiều sách cùng lúc.
 * @param onItemClick: Callback được gọi khi người dùng click vào item,
 *      truyền sách được click và trạng thái chọn (true nếu được chọn, false nếu hủy chọn).
 */
class BookAdapter(
    var books: List<Book>,
    private val onItemClick: ((Book, Boolean) -> Unit)? = null
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    // Tập hợp lưu các ID của sách được chọn
    val selectedBookIds = mutableSetOf<Int>()

    fun updateBooks(newBooks: List<Book>) {
        books = newBooks
        // Nếu sách được chọn không còn trong danh sách mới thì bỏ chọn
        selectedBookIds.retainAll(books.map { it.id })
        notifyDataSetChanged()
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvBookTitle: TextView = itemView.findViewById(R.id.tvBookTitle)
        val ivTick: ImageView = itemView.findViewById(R.id.ivTick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.tvBookTitle.text = book.title
        // Nếu sách đã được mượn (trong master list) thì luôn hiển thị tick,
        // hoặc nếu sách chưa mượn nhưng được chọn trong màn hình chọn, hiển thị tick.
        holder.ivTick.visibility = if (book.isBorrowed || selectedBookIds.contains(book.id)) {
            View.VISIBLE
        } else {
            View.GONE
        }

        holder.itemView.setOnClickListener {
            if (!book.isBorrowed) { // Chỉ cho phép chọn sách chưa mượn
                if (selectedBookIds.contains(book.id)) {
                    selectedBookIds.remove(book.id)
                    onItemClick?.invoke(book, false)
                } else {
                    selectedBookIds.add(book.id)
                    onItemClick?.invoke(book, true)
                }
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int = books.size
}
