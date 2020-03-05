package es.iessaladillo.pedrojoya.quilloque.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

//
//    @Update
//    fun updatePlayer(player: Player)
//
    @Delete
    fun deleteContact(contact: Contact)

    @Query("DELETE FROM call WHERE call.id =:id")
    fun deleteCall(id: Long)

    @Insert
    fun insertCall(call: Call)

    @Insert
    fun insertContact(contact: Contact)

    @Query("SELECT C.id AS callId, C.num AS num, C.type AS type, \n" +
            "C.date AS date, T.id AS contactId, T.name AS contactName \n" +
            "FROM Call AS C LEFT JOIN Contact AS T ON C.num = T.num \n" +
            "ORDER BY C.id DESC LIMIT 20")
    fun queryRecent(): LiveData<List<ContactWithNumber>>


    @Query("select c.id,c.num,c.date,c.type,t.name from call AS C LEFT JOIN contact AS T ON C.num = T.num")
    fun queryAllCalls():LiveData<List<CallWithName>>

    @Query("select * from contact where contact.num=:num limit 1")
    fun queryContact(num:String):LiveData<Contact>

    @Query("select * from contact")
    fun queryAllContacts():LiveData<List<Contact>>

    @Query("select * from contact where contact.name like :name")
    fun searchContact(name:String):LiveData<List<Contact>>

    @Query("SELECT name AS contactName, num AS phoneNumber " +
            "FROM Contact " +
            "WHERE phoneNumber like :phoneNumber " +
            "UNION " +
            "SELECT DISTINCT num AS contactName, num AS phoneNumber " +
            "FROM Call " +
            "WHERE phoneNumber like :phoneNumber " +
            "AND phoneNumber NOT IN (SELECT phoneNumber FROM Contact)")
    fun querySuggest(phoneNumber:String): LiveData<List<CallWithName2>>



    /* Consulta para sugerencias
   "SELECT name AS contactName, phoneNumber AS phoneNumber " +
   "FROM Contact " +
   "WHERE phoneNumber like :phoneNumber " +
   "UNION " +
   "SELECT DISTINCT phoneNumber AS contactName, phoneNumber AS phoneNumber " +
   "FROM Call " +
   "WHERE phoneNumber like :phoneNumber " +
   "AND phoneNumber NOT IN (SELECT phoneNumber FROM Contact)")
*/




}