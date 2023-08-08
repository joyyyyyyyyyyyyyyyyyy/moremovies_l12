package sg.edu.rp.c346.id22022096.moremovies_l12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class showmovies extends AppCompatActivity {

    ArrayList<Movies> al;
    ListView lv;
    CustomAdapter camovies;
    Button btnShowPG13, btnback;

    @Override
    protected void onResume() {
        super.onResume();
        al = new ArrayList<Movies>();
        camovies = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(camovies);
        Intent intent = getIntent();
        DBHelper db = new DBHelper(showmovies.this);
        al.clear();
        al.addAll(db.getAllMovies());
        camovies.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showmovies);

        btnShowPG13 = findViewById(R.id.btnShowPG13);
        btnback = findViewById(R.id.btnback);
        lv = findViewById(R.id.lv);

        al = new ArrayList<>();
        //lv.setAdapter();

        //DBHelper db = new DBHelper(showmovies.this);
        //al.clear();
        //al.addAll(db.getAllMovies());
        //camovies.notifyDataSetChanged();

        btnShowPG13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(showmovies.this);
                al.clear();

                String filterText = "PG13";
                al.addAll(db.getPgMovies(filterText));
                camovies.notifyDataSetChanged();

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movies data = al.get(position);

                Intent intent = new Intent(showmovies.this, editmovie.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
