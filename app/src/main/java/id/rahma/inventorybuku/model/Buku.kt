package id.rahma.inventorybuku.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "buku_table") //nama tabel
data class Buku(
    @PrimaryKey(autoGenerate = true)
    //nama entitas yang ada pada tabel buku
    val id: Int,
    val JudulBuku: String,
    val Pengarang: String,
    val Harga: Int
): Parcelable