package com.azimuton.recipenote.presentation.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.azimuton.recipenote.R
import com.azimuton.recipenote.data.storage.models.NoteEntity
import com.azimuton.recipenote.domain.models.Note
import com.azimuton.recipenote.presentation.EditNoteActivity
import com.azimuton.recipenote.presentation.NoteActivity
import com.azimuton.recipenote.presentation.utils.MyIntentConstance
import kotlinx.android.synthetic.main.item_note.view.*


class NoteAdapter(private val context : Context, private val noteList : List<Note>, private val callback: NoteActivity):
    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    class ViewHolder(view: View,  contextV: Context) : RecyclerView.ViewHolder(view){

        private val context = contextV
        var title: TextView? = null
        var image : ImageView? = null
        var deleteItem : ImageView? = null

        init{
            title = itemView.tvItemNote
            image  = itemView.ivItemNote
            deleteItem = itemView.ivItemNoteDelete
        }

        fun setData(item : Note){
            itemView.setOnClickListener {
                val intent = Intent(context, EditNoteActivity :: class.java).apply {
                    putExtra(MyIntentConstance.I_NAME_KEY, item.dbnotetitle)
                    putExtra(MyIntentConstance.I_CONTENT_KEY, item.dbnotecontent)
                    putExtra(MyIntentConstance.I_IMAGE_KEY, item.dbnoteimage)
                    putExtra(MyIntentConstance.I_ID_KEY, item.dbnoteid)
                }
               context.startActivity(intent)
            }
        }
        interface  ItemCallback{
            fun deleteItem(index : Int)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false), context)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(noteList[position])
        holder.title?.text = noteList[position].dbnotetitle
        holder.image?.setImageURI(noteList[position].dbnoteimage.toUri())
        holder.deleteItem?.setOnClickListener {
            callback.deleteItem(position)
        }
    }

}

