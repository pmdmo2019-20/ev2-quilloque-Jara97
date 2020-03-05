package es.iessaladillo.pedrojoya.quilloque

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.quilloque.room.*

class MainActivityViewModel : ViewModel() {

    private lateinit var  repository: LocalRepository

    private var num=""

    private val _imageCreation: MutableLiveData<Int> =  MutableLiveData()
    val imageCreation: LiveData<Int>
        get() = _imageCreation

    fun setBD(context: Context){
        repository=LocalRepository(Database1.getInstance(context).dao)
    }

    fun getRecent(): LiveData<List<ContactWithNumber>> {
        return repository.queryRecent()
    }

    fun getCalls():LiveData<List<CallWithName>>{
        return repository.queryAllCalls()
    }

    fun getContacts():LiveData<List<Contact>>{
        return repository.queryAllContacts()
    }

    fun insertCall(call: Call){
        Thread{repository.insertCall(call)}.start()
    }

    fun insetContact(contact: Contact){
        Thread{repository.insertContact(contact)}.start()
    }

    fun deleteContact(contact: Contact){
        Thread{repository.deleteContact(contact)}.start()
    }

    fun deleteCall(id:Long){
        Thread{repository.deleteCall(id)}.start()
    }

    fun searchContact(name:String):LiveData<List<Contact>>{
        return repository.searchContact(name)
    }

    fun querySuggest(phoneNumber:String): LiveData<List<CallWithName2>>{
        return repository.querySuggest(phoneNumber)
    }

    fun getNum():String{
        return num
    }

    fun setNum(num:String){
        this.num=num
    }






}