package sg.edu.rp.c346.id22022096.moremovies_l12;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class editmovie extends AppCompatActivity {

    EditText etmovietitle, etgenre, etyear;
    Spinner spinner;
    Button btnupdate, btndelete, btncancel;
    TextView tvMovieID, tvID, tvMovietitle, tvGenre, tvYear, tvRating;
    String rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmovie);

        etmovietitle = findViewById(R.id.etmovietitle);
        etgenre = findViewById(R.id.etgenre);
        etyear = findViewById(R.id.etyear);
        spinner = findViewById(R.id.spinner);
        btnupdate = findViewById(R.id.btnupdate);
        btndelete = findViewById(R.id.btndelete);
        btncancel = findViewById(R.id.btncancel);
        tvMovieID = findViewById(R.id.tvMovieID);
        tvID = findViewById(R.id.tvID);
        tvMovietitle = findViewById(R.id.tvMovietitle);
        tvGenre = findViewById(R.id.tvGenre);
        tvYear = findViewById(R.id.tvYear);
        tvRating = findViewById(R.id.tvRating);

        Intent intent = getIntent();
        Movies data = (Movies) intent.getSerializableExtra("data");

        tvID.setText(String.valueOf(data.getId()));
        etmovietitle.setText(data.getTitle());
        etgenre.setText(data.getGenre());
        etyear.setText(String.valueOf(data.getYear()));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        rating = "G";
                        break;
                    case 1:
                        rating = "PG";
                        break;
                    case 2:
                        rating = "PG13";
                        break;
                    case 3:
                        rating = "NC16";
                        break;
                    case 4:
                        rating = "M18";
                        break;
                    case 5:
                        rating = "R21";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(editmovie.this);
                data.setTitle(etmovietitle.getText().toString());
                data.setGenre(etgenre.getText().toString());
                data.setYear(Integer.parseInt(etyear.getText().toString()));
                data.setRating(rating);
                int result = db.updateMovie(data);
                if (result>0){
                    Toast.makeText(editmovie.this, "movie has been updated", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(editmovie.this, "failed to update movie", Toast.LENGTH_LONG).show();
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(editmovie.this);
                myBuilder.setTitle("Warning!");
                myBuilder.setMessage("Are you sure you want to delete the movie " + data.getTitle()+"?");
                myBuilder.setCancelable(true);

                myBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBHelper dbh = new DBHelper(editmovie.this);
                        dbh.deleteMovie(data.getId());
                        finish();
                    }
                });
                myBuilder.setNeutralButton("Cancel", null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
                //DBHelper db = new DBHelper(editmovie.this);
                //int result = db.deleteMovie(data.getId());
                //if (result>0){
                    //Toast.makeText(editmovie.this, "movie has been deleted", Toast.LENGTH_SHORT).show();
                    //Intent i = new Intent();
                    //setResult(RESULT_OK);
                    //finish();
                //} else {
                    //Toast.makeText(editmovie.this, "failed to delete movie", Toast.LENGTH_SHORT).show();
                //}
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(editmovie.this);
                myBuilder.setTitle("Warning!");
                myBuilder.setMessage("Are you sure you want to discard the changes?");
                myBuilder.setCancelable(true);

                myBuilder.setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                myBuilder.setNeutralButton("Cancel", null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

    }
}