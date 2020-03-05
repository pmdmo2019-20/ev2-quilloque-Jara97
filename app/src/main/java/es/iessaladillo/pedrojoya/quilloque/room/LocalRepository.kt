package es.iessaladillo.pedrojoya.quilloque.room

import androidx.lifecycle.LiveData

class LocalRepository(private val dao: Dao){

    fun queryRecent(): LiveData<List<ContactWithNumber>>{
        return dao.queryRecent()
    }

    fun queryAllCalls():LiveData<List<CallWithName>>{
        return dao.queryAllCalls()
    }

    fun insertCall(call: Call){
        dao.insertCall(call)
    }

    fun deleteCall(id: Long){
        dao.deleteCall(id)
    }

    fun queryContact(num:String):LiveData<Contact>{
        return dao.queryContact(num)
    }

    fun queryAllContacts():LiveData<List<Contact>>{
        return dao.queryAllContacts()
    }

    fun insertContact(contact: Contact){
        dao.insertContact(contact)
    }

    fun deleteContact(contact: Contact){
        dao.deleteContact(contact)
    }

    fun searchContact(name:String):LiveData<List<Contact>>{
        return dao.searchContact(name)
    }


}