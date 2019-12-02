package pproject.teamjavis.javis.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pproject.teamjavis.javis.R;

public class LockActivity extends BaseActivity{

    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);

        list = (ListView) findViewById(R.id.list);

        List<String> data = new ArrayList<>();

        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);

        list.setAdapter(adp);

        data.add("엄마");
        data.add("아빠");
        data.add("첫째");
        data.add("둘째");
        adp.notifyDataSetChanged();

    }
}
