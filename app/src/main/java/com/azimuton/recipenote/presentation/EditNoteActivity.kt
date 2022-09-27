package com.azimuton.recipenote.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.azimuton.recipenote.R
import com.azimuton.recipenote.data.storage.room.AppDatabase
import com.azimuton.recipenote.domain.models.Note
import com.azimuton.recipenote.domain.usecase.NoteDelImageUseCase
import com.azimuton.recipenote.domain.usecase.NoteInsertUseCase
import com.azimuton.recipenote.presentation.utils.MyIntentConstance
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_edit_note.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class EditNoteActivity  : AppCompatActivity(), CoroutineScope{
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    @Inject
    lateinit var noteInsertUseCase: NoteInsertUseCase
    @Inject
    lateinit var noteDelImageUseCase : NoteDelImageUseCase
    lateinit var noteDatabase: AppDatabase
    private val Pick_image = 1
    var tempImageUri = "empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)


        noteDatabase = AppDatabase.getDatabase(this)
        getMyIntents()

        fabEditNoteSaved.setOnClickListener {

            if (etEditNoteTitle.text.isNotEmpty() && etEditNoteContent.text.isNotEmpty()) {
                val title: String = etEditNoteTitle.text.toString()
                val content: String = etEditNoteContent.text.toString()
                val imageUri = tempImageUri
                val note = Note(dbnotetitle = title, dbnotecontent = content, dbnoteimage = imageUri)
                CoroutineScope(Dispatchers.IO).launch {
                    noteInsertUseCase.execute(note)
                }
//                noteInsertUseCase.execute(note)
                Toast.makeText(this, "Ваш рецепт записан !", Toast.LENGTH_LONG).show()
                val intent = Intent(this, NoteActivity::class.java)
                startActivity(intent)
                overridePendingTransition(0, R.anim.open_activity)
                finish()
            } else {
                Toast.makeText(this, "Пожалуйста, заполните пустые поля !", Toast.LENGTH_LONG)
                    .show()
            }
        }
        fabAddGalleryEditNote.setOnClickListener {
            clickFabAddImage()
        }
        ivDeleteEditNote.setOnClickListener {
            clickDeleteImage()
        }
        ivEditEditNote.setOnClickListener {
            chooseImage()
        }
    }
    private fun clickFabAddImage(){
        cvEditNote.visibility = View.VISIBLE
        fabAddGalleryEditNote.visibility = View.GONE
    }
    private fun clickDeleteImage(){
        //noteDatabase.noteDao().delImage()
        noteDelImageUseCase.execute()
        cvEditNote.visibility = View.GONE
        fabAddGalleryEditNote.visibility = View.VISIBLE
    }

    private fun chooseImage(){
        val photoPickerIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        photoPickerIntent.type = "image/*"
        //photoPickerIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        startActivityForResult(photoPickerIntent, Pick_image)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == Pick_image) {
            //Получаем URI изображения, преобразуем его в Bitmap
            //объект и отображаем в элементе ImageView нашего интерфейса:
            ivEditNote.setImageURI(data?.data)
            tempImageUri = data?.data.toString()
            contentResolver.takePersistableUriPermission(data?.data!!, Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

    }
    private fun getMyIntents() {
        cvEditNote.visibility = View.GONE
        val i = intent
        if (i != null) {
            if (i.getStringExtra(MyIntentConstance.I_NAME_KEY) != null) {
                //fabAddGalleryEditDeserti.visibility = View.GONE
                etEditNoteTitle.setText(i.getStringExtra(MyIntentConstance.I_NAME_KEY))
                etEditNoteContent.setText(i.getStringExtra(MyIntentConstance.I_CONTENT_KEY))
                if(i.getStringExtra(MyIntentConstance.I_IMAGE_KEY) != "empty"){
                    cvEditNote.visibility = View.VISIBLE
                    ivEditNote.setImageURI(Uri.parse(i.getStringExtra(MyIntentConstance.I_IMAGE_KEY)))
                } else {
                    cvEditNote.visibility = View.GONE
                }
            }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, NoteActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0, R.anim.open_activity)
        finish()
    }
}
