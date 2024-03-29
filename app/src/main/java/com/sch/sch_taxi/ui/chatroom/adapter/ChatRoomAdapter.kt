package com.sch.sch_taxi.ui.chatroom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sch.domain.model.Chat
import com.sch.sch_taxi.databinding.HolderChatRoomMeBinding
import com.sch.sch_taxi.databinding.HolderChatRoomOtherBinding
import com.sch.sch_taxi.ui.chatroom.ChatRoomActionHandler

class ChatRoomAdapter(
    private val eventListener: ChatRoomActionHandler,
) : PagingDataAdapter<Chat, RecyclerView.ViewHolder>(ReservationItemDiffCallback) {

    private val VIEW_TYPE_USER_MESSAGE_ME = 1
    private val VIEW_TYPE_USER_MESSAGE_OTHER = 0

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position)!!.isend) VIEW_TYPE_USER_MESSAGE_ME else VIEW_TYPE_USER_MESSAGE_OTHER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = if (viewType == VIEW_TYPE_USER_MESSAGE_ME) {
            HolderChatRoomMeBinding.inflate(inflater, parent, false).apply {
                eventListener = this@ChatRoomAdapter.eventListener

            }
        } else {
            HolderChatRoomOtherBinding.inflate(inflater, parent, false).apply {
                eventListener = this@ChatRoomAdapter.eventListener

            }
        }

        return when (viewType) {
            VIEW_TYPE_USER_MESSAGE_ME -> MyUserHolder(binding as HolderChatRoomMeBinding)
            VIEW_TYPE_USER_MESSAGE_OTHER -> OtherUserHolder(binding as HolderChatRoomOtherBinding)
            else -> MyUserHolder(binding as HolderChatRoomMeBinding) // Default to my user holder
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is MyUserHolder -> item?.let { holder.bind(it) }
            is OtherUserHolder -> item?.let { holder.bind(it) }
        }
    }

    class MyUserHolder(
        private val binding: HolderChatRoomMeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Chat) {
            binding.apply {
                holder = item
                executePendingBindings()
            }
        }
    }

    class OtherUserHolder(
        private val binding: HolderChatRoomOtherBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Chat) {
            binding.apply {
                holder = item
                executePendingBindings()
            }
        }
    }

    internal object ReservationItemDiffCallback : DiffUtil.ItemCallback<Chat>() {
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Chat, newItem: Chat) = oldItem.equals(newItem)
    }
}