package pproject.teamjavis.javis.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import pproject.teamjavis.javis.R
import pproject.teamjavis.javis.item.PermissionItem

class PermissionListAdapter: BaseAdapter() {
    val items = ArrayList<PermissionItem>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val context = parent?.context

        //grid의 아이템 레이아웃을 inflate
        if(view == null) {
            val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_permisson_list, parent, false)
        }

        //아이템 레이아웃의 데이터 변경
        val icon = view!!.findViewById<ImageView>(R.id.permission_item_icon)
        val title = view.findViewById<TextView>(R.id.permission_item_title)
        val description = view.findViewById<TextView>(R.id.permission_item_description)

        val item = items[position]

        icon.setImageDrawable(item.icon)
        title.text = item.title
        description.text = item.description

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

    fun add(icon: Drawable, title: String, description: String) {
        val item = PermissionItem(icon, title, description)
        items.add(item)
    }
}