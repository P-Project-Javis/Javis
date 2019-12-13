package pproject.teamjavis.javis.ui.adapter

import android.content.Context
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import pproject.teamjavis.javis.R
import pproject.teamjavis.javis.ui.item.AuthorityParentItem
import pproject.teamjavis.javis.util.DatabaseManager
import java.io.File

class UserListDeleteAdapter: BaseAdapter() {
    private val items = ArrayList<AuthorityParentItem>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val context = parent?.context

        if(view == null) {
            val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_user_list_delete, parent, false)
        }

        val name = view!!.findViewById<TextView>(R.id.user_list_lookup_name)
        val button = view.findViewById<ImageButton>(R.id.user_list_delete_button)

        val item = items[position]

        name.text = item.name
        button.setOnClickListener {
            val db = DatabaseManager(context)
            db.openWritable()
            db.delete(item.name)
            db.close()
            val file = File(Environment.getExternalStorageDirectory(), "/Javis/${item.name}.wav")
            if(file.exists())
                file.delete()
            items.removeAt(position)
            notifyDataSetChanged()
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

    fun add(item: AuthorityParentItem) {
        items.add(item)
    }
}