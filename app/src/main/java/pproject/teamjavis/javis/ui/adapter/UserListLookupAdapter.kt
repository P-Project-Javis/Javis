package pproject.teamjavis.javis.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import pproject.teamjavis.javis.R
import pproject.teamjavis.javis.ui.item.AuthorityParentItem

class UserListLookupAdapter: BaseAdapter() {
    private val items = ArrayList<AuthorityParentItem>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val context = parent?.context

        if(view == null) {
            val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_user_list_lookup, parent, false)
        }

        val name = view!!.findViewById<TextView>(R.id.user_list_lookup_name)

        val item = items[position]

        name.text = item.name

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