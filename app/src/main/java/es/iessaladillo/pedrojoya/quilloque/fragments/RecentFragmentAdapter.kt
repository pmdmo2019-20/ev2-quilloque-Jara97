package es.iessaladillo.pedrojoya.quilloque.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.DrawableUtils
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.data.CALL_TYPE_MADE
import es.iessaladillo.pedrojoya.quilloque.data.CALL_TYPE_VIDEO
import es.iessaladillo.pedrojoya.quilloque.data.getCallTypeIcon
import es.iessaladillo.pedrojoya.quilloque.room.Call
import es.iessaladillo.pedrojoya.quilloque.room.CallWithName
import es.iessaladillo.pedrojoya.quilloque.utils.createAvatarDrawable
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.contacts_fragment_item.view.*
import kotlinx.android.synthetic.main.recent_fragment_item.view.*
import kotlinx.android.synthetic.main.recent_fragment_item.view.imgAvatar
import kotlinx.android.synthetic.main.recent_fragment_item.view.lblName
import kotlinx.android.synthetic.main.recent_fragment_item.view.lblPhoneNumber

class RecentFragmentAdapter : RecyclerView.Adapter<RecentFragmentAdapter.ViewHolder>() {

    private var data: List<CallWithName> = emptyList()
    private var onItemClick:((CallWithName)->Unit)?=null
    private var onItemClick2:((CallWithName)->Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentFragmentAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.recent_fragment_item, parent, false)
        return ViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return data.size
    }

    fun setOnItemClick(listener:((CallWithName)->Unit)){
        onItemClick=listener
    }

    fun setOnItemClick2(listener:((CallWithName)->Unit)){
        onItemClick2=listener
    }

    fun submitList(list:List<CallWithName>) {
        data=list
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: RecentFragmentAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }


    fun getItem(position:Int): CallWithName {
        return data[position]
    }


    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {

        }

        fun bind(call:CallWithName) {
            containerView.run {
                if(call.name==null){
                    lblName.text=call.num
                    lblDate.text=call.date.subSequence(0,20)
                    imgCallType.setImageResource(getCallTypeIcon(if(call.type.equals("normal")) CALL_TYPE_MADE else CALL_TYPE_VIDEO))
                    imgAvatar.setImageDrawable(createAvatarDrawable("?"))
                }
                else {
                    lblName.text=call.name
                    lblDate.text=call.date.subSequence(0,20)
                    imgCallType.setImageResource(getCallTypeIcon(if(call.type.equals("normal")) CALL_TYPE_MADE else CALL_TYPE_VIDEO))
                    lblPhoneNumber.text=call.num
                    imgAvatar.setImageDrawable(createAvatarDrawable(call.name))
                    lblCreateContact.visibility=View.INVISIBLE
                }
                containerView.lblCreateContact.setOnClickListener {
                    onItemClick?.invoke(call)
                }
                containerView.btnDelete.setOnClickListener {
                    onItemClick2?.invoke(call)
                }

            }


        }
    }

}