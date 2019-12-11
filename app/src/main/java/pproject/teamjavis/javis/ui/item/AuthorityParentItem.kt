/*
ExpandableListView의 부모 리스트 아이템 데이터 클래스.
 */
package pproject.teamjavis.javis.ui.item

data class AuthorityParentItem(val name: String, val voicePath: String, val authorityList: ArrayList<AuthorityChildItem>)