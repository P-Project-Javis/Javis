/*
권한 설정 리스트에 사용할 ExpandableListView의 어댑터 클래스.

ExpandableListView의 레이아웃과 내용 등을 정의.
 */
package pproject.teamjavis.javis.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import pproject.teamjavis.javis.R
import pproject.teamjavis.javis.ui.item.AuthorityParentItem
import pproject.teamjavis.javis.util.DatabaseHelper

class AuthorityListAdapter: BaseExpandableListAdapter() {
    var items = ArrayList<AuthorityParentItem>()

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val context = parent!!.context

        if(view == null) {
            val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_authority_list_parent, parent, false)
        }

        val name = view!!.findViewById<TextView>(R.id.authority_parent_name)

        val item = items[groupPosition]

        name.text = item.name

        return view
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val context = parent!!.context

        if(view == null) {
            val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_authority_list_child, parent, false)
        }

        val icon = view!!.findViewById<ImageView>(R.id.authority_child_icon)
        val title = view.findViewById<TextView>(R.id.authority_child_title)
        val switch = view.findViewById<Switch>(R.id.authority_child_switch)

        val item = items[groupPosition].authorityList[childPosition]

        icon.setImageDrawable(item.icon)
        title.text = item.title
        switch.isChecked = item.isChecked
      
        switch.setOnClickListener {
            val db = DatabaseHelper(context)

            val valChecked = switch.isChecked

            db.openReadable()
            val parentItem = db.select(groupPosition)
            db.close()

            db.openWritable()
            parentItem.authorityList[childPosition].isChecked = valChecked
            db.update(parentItem)
            db.close()
        }

        return view
    }

    override fun getGroup(groupPosition: Int): Any {
        return items[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return items[groupPosition].authorityList[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return items.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return items[groupPosition].authorityList.size
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    fun add(data: ArrayList<AuthorityParentItem>) {
        items = data
    }
}