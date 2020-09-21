package com.book.store.stock.karafs.data.net.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseUser(
    val list: List<ResponseUserItem?>
) : Parcelable

@Parcelize
@Entity
data class ResponseUserItem(
    @PrimaryKey
    @Expose
    val id:Int?=null,
    @ColumnInfo(name = "first_name")val firstName: String,
    @ColumnInfo(name = "last_name")val lastName: String
) : Parcelable