package c14220270.paba.codelab13paba_roomdb

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.util.findColumnIndexBySuffix
import c14220270.paba.codelab13paba_roomdb.database.daftarBelanja
import c14220270.paba.codelab13paba_roomdb.database.daftarBelanjaDB
import c14220270.paba.codelab13paba_roomdb.helper.DateHelper.getCurrentDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class TambahNote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var DB = daftarBelanjaDB.getDatabase(this)
        var tanggal = getCurrentDate()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_note)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var _etItem = findViewById<EditText>(R.id.etItem)
        var _etJumlah = findViewById<EditText>(R.id.etJumlah)

        var _btnTambah = findViewById<Button>(R.id.btnTambah)
        _btnTambah.setOnClickListener{
            CoroutineScope(Dispatchers.IO).async {
                DB.funDaftarBelanjaDAO().insert(
                    daftarBelanja(
                        tanggal = tanggal,
                        item = _etItem.text.toString(),
                        jumlah = _etJumlah.text.toString()
                    )
                )
            }
            finish()
        }
    }
}