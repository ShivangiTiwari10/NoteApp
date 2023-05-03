package com.example.notesfoxandroid

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.notesfoxandroid.Models.Note
import com.example.notesfoxandroid.databinding.ActivityAddNotesBinding
import java.text.SimpleDateFormat
import java.util.*

class AddNotes : AppCompatActivity() {

    private lateinit var binding: ActivityAddNotesBinding
    private lateinit var note: Note
    private lateinit var old_note: Note
    var isUpdate = false

    @SuppressLint("SimpleDateFormat", "ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        try {
            old_note = intent.getSerializableExtra("current_note") as Note
            binding.etTittle.setText(old_note.title)
            binding.etNote.setText(old_note.note)
            isUpdate = true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        binding.imgCheck.setOnClickListener {

            val title = binding.etTittle.text.toString()
            val note_desc = binding.etNote.text.toString()


            if (title.isNotEmpty() || note_desc.isNotEmpty()) {

                val formater = SimpleDateFormat("EEE,d MMM yyy HH:mm a")


                if (isUpdate) {
                    note = Note(
                        old_note.id, title, note_desc, formater.format(Date())
                    )
                } else {

                    note = Note(
                        null, title, note_desc, formater.format(Date())
                    )
                }
                val intent = Intent()
                intent.putExtra("note", note)
                setResult(RESULT_OK, intent)
            } else {

                Toast.makeText(this@AddNotes, "Please enter some data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            val view: View? = this.currentFocus
            if (view != null) {
                // on below line we are creating a variable
                // for input manager and initializing it.
                val inputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

                // on below line hiding our keyboard.

                inputMethodManager.hideSoftInputFromWindow(view.applicationWindowToken, 0)

                // displaying toast message on below line.
                Toast.makeText(this, "Key board hidden", Toast.LENGTH_SHORT).show()
            }

        }


        binding.imgBack.setOnClickListener {
            onBackPressed()
        }


    }
}