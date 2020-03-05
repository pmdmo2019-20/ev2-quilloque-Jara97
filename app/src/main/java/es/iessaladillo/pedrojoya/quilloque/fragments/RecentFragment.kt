package es.iessaladillo.pedrojoya.quilloque.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import es.iessaladillo.pedrojoya.quilloque.MainActivityViewModel
import es.iessaladillo.pedrojoya.quilloque.R
import kotlinx.android.synthetic.main.recent_fragment.*
import android.content.DialogInterface
import android.text.Editable
import android.widget.EditText
import es.iessaladillo.pedrojoya.quilloque.room.Call
import es.iessaladillo.pedrojoya.quilloque.room.Contact


class RecentFragment : Fragment(es.iessaladillo.pedrojoya.quilloque.R.layout.recent_fragment) {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private val listAdapter: RecentFragmentAdapter = RecentFragmentAdapter().apply {
        this.setOnItemClick { call ->
            val alert = AlertDialog.Builder(context!!).apply {
                val edittext = EditText(context!!)
                setMessage(es.iessaladillo.pedrojoya.quilloque.R.string.contact_creation_lblName)
                setTitle(es.iessaladillo.pedrojoya.quilloque.R.string.contact_creation_title)
                setView(edittext)
                setPositiveButton("Create", DialogInterface.OnClickListener { dialog, whichButton ->
                    if(edittext.text.isNotBlank()){
                        viewModel.insetContact(Contact(0,edittext.text.toString(),call.num))
                    }
                    })


                .show()
            }

        }
        this.setOnItemClick2 {
            viewModel.deleteCall(it.id)
            println(it.id)
        }
    }

    companion object {
        fun newInstance() = RecentFragment()
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setupRecyclerView()
        setObservers()
    }

    private fun setObservers() {
        viewModel.getCalls().observe(this){
            listAdapter.submitList(it)
            if(it.isNotEmpty()){
                emptyView.visibility= View.INVISIBLE
            }
        }
    }

    private fun setupViews() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setTitle(es.iessaladillo.pedrojoya.quilloque.R.string.recent_title)
        }
    }


    private fun setupRecyclerView() {
        lstCalls.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }


}