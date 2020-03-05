package es.iessaladillo.pedrojoya.quilloque.room

import androidx.room.*

@Entity(tableName = "call")


class Call(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    @ColumnInfo(name="num")
    val num: String,
    @ColumnInfo(name="date")
    val date:String,
    @ColumnInfo(name="type")
    val type:String

)

//"SELECT C.id AS callId, C.num AS num, C.type AS type, \n" +
//"C.date AS date, T.id AS contactId, T.name AS contactName \n" +
//"FROM Call AS C LEFT JOIN Contact AS T ON C.num = T.num \n" +
//"ORDER BY C.id DESC LIMIT 20"

data class ContactWithNumber(
val callId:Long,
val num:String,
val type:String,
val date:String,
val contactId:Long?,
val contactName:String?

)

data class CallWithName(

val id:Long,
val num: String,
val date:String,
val type:String,
val name:String?

)

data class CallWithName2(
    val contactName: String?,
    val phoneNumber:String

)

