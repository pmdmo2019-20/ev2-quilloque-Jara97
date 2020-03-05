package es.iessaladillo.pedrojoya.quilloque.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.data.CALL_TYPE_MADE
import es.iessaladillo.pedrojoya.quilloque.data.CALL_TYPE_VIDEO
import es.iessaladillo.pedrojoya.quilloque.data.getCallTypeIcon
import es.iessaladillo.pedrojoya.quilloque.room.CallWithName
import es.iessaladillo.pedrojoya.quilloque.room.Contact
import es.iessaladillo.pedrojoya.quilloque.utils.createAvatarDrawable
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.contacts_fragment_item.view.*
import kotlinx.android.synthetic.main.recent_fragment_item.view.*
import kotlinx.android.synthetic.main.recent_fragment_item.view.lblName

class ContactsFragmentAdapter : RecyclerView.Adapter<ContactsFragmentAdapter.ViewHolder>() {

    private var data: List<Contact> = emptyList()
    private var onItemClick:((Contact)->Unit)?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsFragmentAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.contacts_fragment_item, parent, false)
        return ViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return data.size
    }

    fun setOnItemClick(listener:((Contact)->Unit)){
        onItemClick=listener
    }

    fun submitList(list:List<Contact>) {
        data=list
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ContactsFragmentAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }


    fun getItem(position:Int): Contact {
        return data[position]
    }


    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(call: Contact) {
            containerView.run {
                lblName.setText(call.name)
                lblPhoneNumber2.setText(call.num)
                imgAvatar2.setImageDrawable(createAvatarDrawable(call.name))
            }

            containerView.btnDelete2.setOnClickListener {
                onItemClick?.invoke(call)
            }


        }
    }

}