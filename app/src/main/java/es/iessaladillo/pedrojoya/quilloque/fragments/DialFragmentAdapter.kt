package es.iessaladillo.pedrojoya.quilloque.fragments

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.room.CallWithName
import es.iessaladillo.pedrojoya.quilloque.room.ContactWithNumber
import es.iessaladillo.pedrojoya.quilloque.utils.createAvatarDrawable
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.dial_fragment_item.view.*

class DialFragmentAdapter : RecyclerView.Adapter<DialFragmentAdapter.ViewHolder>() {

    private var data: List<ContactWithNumber> = emptyList()
    private var onItemClick:((String)->Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialFragmentAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.dial_fragment_item, parent, false)
        return ViewHolder(itemView)
    }

    fun setOnItemClick(listener:((String)->Unit)){
        onItemClick=listener
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun submitList(list:List<ContactWithNumber>) {
        data=list
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: DialFragmentAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }


    fun getItem(position:Int): ContactWithNumber {
        return data[position]
    }


    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {


        init {



        }

        fun bind(contact: ContactWithNumber) {
            containerView.imgAvatar.setImageDrawable(createAvatarDrawable(contact.contactName?:contact.num))
            containerView.lblContactName.setText(contact.contactName?:contact.num)
            containerView.lblPhoneNumber.setText(if(contact.contactName==null)"" else contact.num)
            containerView.setOnClickListener {
                onItemClick?.invoke(contact.num)
            }
        }
    }

}