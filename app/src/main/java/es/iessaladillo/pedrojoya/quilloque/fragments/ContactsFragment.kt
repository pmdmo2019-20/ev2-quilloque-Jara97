package es.iessaladillo.pedrojoya.quilloque.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import es.iessaladillo.pedrojoya.quilloque.MainActivityViewModel
import es.iessaladillo.pedrojoya.quilloque.R
import kotlinx.android.synthetic.main.contacts_fragment.*
import kotlinx.android.synthetic.main.recent_fragment.*

class ContactsFragment : Fragment(R.layout.contacts_fragment) {

    private val viewModel: MainActivityViewModel by activityViewModels()

    private val listAdapter: ContactsFragmentAdapter = ContactsFragmentAdapter().apply {
        this.setOnItemClick {
            viewModel.deleteContact(it)
        }
    }


    companion object {
        fun newInstance() = ContactsFragment()
    }

    private fun setupViews() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setTitle(R.string.contacts_title)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setupRecyclerView()
        setObservers()
        setListeners()

    }


    private fun setupRecyclerView() {
        lstContacts.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }

    private fun setObservers() {
        viewModel.getContacts().observe(this){
            listAdapter.submitList(it)
            if(it.isNotEmpty()){
                emptyView2.visibility= View.INVISIBLE
            }
        }
    }

    private fun setListeners() {
        txtSearch.doAfterTextChanged {
            if(it.toString().isNotBlank()){
                viewModel.searchContact("%"+it.toString()+"%").observe(this){
                    listAdapter.submitList(it)
                }
            }
            else{
                viewModel.getContacts().observe(this){
                    listAdapter.submitList(it)
                    if(it.isNotEmpty()){
                        emptyView2.visibility= View.INVISIBLE
                    }
                }
            }

        }
    }


}