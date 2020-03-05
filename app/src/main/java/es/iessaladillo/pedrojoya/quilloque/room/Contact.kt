package es.iessaladillo.pedrojoya.quilloque.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "contact",
    indices = [
        Index(
            name = "USERS_INDEX_NAME_UNIQUE",
            value = ["name"],
            unique = true
        )
    ])

class Contact(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    @ColumnInfo(name="name")
    val name: String,
    @ColumnInfo(name="num")
    val num:String

)