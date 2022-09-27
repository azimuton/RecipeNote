package com.azimuton.recipenote.presentation

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.azimuton.recipenote.R
import com.azimuton.recipenote.data.storage.room.AppDatabase
import com.azimuton.recipenote.domain.models.Note
import com.azimuton.recipenote.domain.usecase.NoteDeleteUseCase
import com.azimuton.recipenote.domain.usecase.NoteGetAllUseCase
import com.azimuton.recipenote.presentation.adapters.NoteAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_note.*
import kotlinx.android.synthetic.main.item_note.*
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class NoteActivity : AppCompatActivity(), NoteAdapter.ViewHolder.ItemCallback, CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    @Inject
    lateinit var noteDeleteUseCase: NoteDeleteUseCase
    @Inject
    lateinit var noteGetAllUseCase: NoteGetAllUseCase
    lateinit var noteDatabase : AppDatabase
    lateinit var noteList: ArrayList<Note>
    lateinit var adapterNote : NoteAdapter



    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        noteList = ArrayList<Note>()
        noteDatabase =  AppDatabase.getDatabase(this)
        getData()
        adapterNote = NoteAdapter(this, noteList, this)
        rvNote.layoutManager = GridLayoutManager(this, 2)
        rvNote.adapter = adapterNote

        initAdMob2()
        fabAddNote.setOnClickListener {
            val intent = Intent(this, EditNoteActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()
        }
    }
    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            val wordFromDb: List<Note> = noteGetAllUseCase.execute()
            noteList.clear()
            noteList.addAll(wordFromDb)
        }
//        val wordFromDb: List<Note> = noteGetAllUseCase.execute()
//        noteList.clear()
//        noteList.addAll(wordFromDb)
    }
    override fun onResume() {
        super.onResume()
        adView2?.resume()
    }
    override fun onPause() {
        super.onPause()
        adView2?.pause()
    }
    override fun onDestroy() {
        super.onDestroy()
        adView2?.destroy()
    }
    private fun initAdMob2(){
        MobileAds.initialize(this)
        val adRequest2 = AdRequest.Builder().build()
        adView2?.loadAd(adRequest2)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0, R.anim.open_activity)
        finish()
    }


    override fun deleteItem(index: Int) {
        val addDialog = AlertDialog.Builder(this)
        addDialog
            .setMessage("Вы действительно хотите удалить запись?")
            .setPositiveButton("Ok") { dialog, _ ->
                val note = noteList[index]
                CoroutineScope(Dispatchers.IO).launch {
                    noteDeleteUseCase.execute(note)
                }
//                noteDeleteUseCase.execute(note)
                getData()
                adapterNote.notifyDataSetChanged()
                Toast.makeText(this, "Запись удалена!", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
    }
