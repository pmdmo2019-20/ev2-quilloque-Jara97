package es.iessaladillo.pedrojoya.quilloque.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import es.iessaladillo.pedrojoya.quilloque.MainActivityViewModel
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.room.Call
import kotlinx.android.synthetic.main.contacts_fragment.*
import kotlinx.android.synthetic.main.dial_fragment.*
import kotlinx.android.synthetic.main.recent_fragment.*
import kotlinx.android.synthetic.main.recent_fragment_item.*
import java.util.*

class DialFragment : Fragment(R.layout.dial_fragment) {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private val listAdapter: DialFragmentAdapter = DialFragmentAdapter().apply {
        this.setOnItemClick {num ->
            lblNumber.setText(num)
        }
    }

    companion object {
        fun newInstance() = DialFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setObservers()
        setListeners()
        setupRecyclerView()
        if(lblNumber.text.toString().isEmpty()){
            lblCreateContact2.visibility= View.INVISIBLE
        }
    }

    private fun setListeners() {
        lblOne.setOnClickListener {
            writeNumber("1")
        }
        lblTwo.setOnClickListener {
            writeNumber("2")
        }
        lblThree.setOnClickListener {
            writeNumber("3")
        }
        lblFour.setOnClickListener {
            writeNumber("4")
        }
        lblFive.setOnClickListener {
            writeNumber("5")
        }
        lblSix.setOnClickListener {
            writeNumber("6")
        }
        lblSeven.setOnClickListener {
            writeNumber("7")
        }
        lblEight.setOnClickListener {
            writeNumber("8")
        }
        lblNine.setOnClickListener {
            writeNumber("9")
        }
        lblZero.setOnClickListener {
            writeNumber("0")
        }
        lblAstherisc.setOnClickListener {
            writeNumber("*")
        }
        lblPound.setOnClickListener {
            writeNumber("#")
        }
        imgBackspace.setOnClickListener {
            deleteNumber()
        }
        fabCall.setOnClickListener {
            insetCall("normal")
        }
        imgVideo.setOnClickListener {
            insetCall("video")
        }
        imgBackspace.setOnLongClickListener {
            lblNumber.text=""
            listAdapter.submitList(emptyList())
            true
        }


    }

    private fun insetCall(type: String) {
        if(lblNumber.text.isNotBlank()){
            viewModel.insertCall(Call(0,lblNumber.text.toString(), Date().toString(),type))
        }

    }


    private fun setObservers() {

    }

    private fun setupViews() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setTitle(R.string.dial_title)
        }
    }

    private fun writeNumber(num: String) {
        lblNumber.text=lblNumber.text.toString()+num
        viewModel.querySuggest(num).observe(this){
            listAdapter.submitList(it)
            if(it.isNotEmpty()){
                lblCreateContact2.visibility= View.INVISIBLE
            }
        }
        viewModel.querySuggest("%"+lblNumber.text.toString()+"%").observe(this){
            listAdapter.submitList(it)
            if(it.isNotEmpty()){
                lblCreateContact2.visibility= View.INVISIBLE
            }
        }
    }

    private fun deleteNumber() {
        if(lblNumber.text.length==1){
            lblNumber.text=lblNumber.text.toString().subSequence(0,lblNumber.text.length-1)
            listAdapter.submitList(emptyList())
        }
        else if(lblNumber.text.isNotEmpty()){
            lblNumber.text=lblNumber.text.toString().subSequence(0,lblNumber.text.length-1)

            viewModel.querySuggest("%"+lblNumber.text.toString()+"%").observe(this){
                listAdapter.submitList(it)
                if(it.isNotEmpty()){
                    lblCreateContact2.visibility= View.INVISIBLE
                }
            }
        }

        else{
            listAdapter.submitList(emptyList())
        }

    }

    private fun setupRecyclerView() {
        lstSuggestions.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }


}