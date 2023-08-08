package sg.edu.rp.c346.id22022096.moremovies_l12;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etmovietitle, etgenre, etyear;
    Spinner spinner;
    Button btninsert, btnshowlist;
    String rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etmovietitle = findViewById(R.id.etmovietitle);
        etgenre = findViewById(R.id.etgenre);
        etyear = findViewById(R.id.etyear);
        spinner = findViewById(R.id.spinner);
        btninsert = findViewById(R.id.btninsert);
        btnshowlist = findViewById(R.id.btnshowlist);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
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
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get user input
                String title = etmovietitle.getText().toString();
                String genre = etgenre.getText().toString();
                String stryr = etyear.getText().toString().trim();
                //int year = Integer.valueOf(stryr);

                int year;
                try {
                    year = Integer.parseInt(stryr);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Invalid year format. Please enter a valid year.", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBHelper dbh = new DBHelper(MainActivity.this);

                if (title.length() == 0 || genre.length() == 0){
                    Toast.makeText(MainActivity.this, "please fill in movie title and genre", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialog);
                myBuilder.setTitle("Confirm Insert");
                myBuilder.setMessage(String.format("You are about to insert a new movie: \n\n Movie Title: %s \n Movie Genre: %s \n Release Year: %d \n Rating: %s \n", title, genre, year, rating));
                myBuilder.setCancelable(true);

                myBuilder.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //DBHelper dbh = new DBHelper(MainActivity.this);
                        long result = dbh.insertMovie(title, genre, year, rating);

                        if (result != -1) {
                            Toast.makeText(MainActivity.this, "movie successfully inserted", Toast.LENGTH_LONG).show();
                            etmovietitle.setText("");
                            etgenre.setText("");
                            etyear.setText("");
                            } else {
                            Toast.makeText(MainActivity.this, "insertion failed", Toast.LENGTH_LONG).show();
                            }
                    }
                });

                myBuilder.setNeutralButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

                //long result = dbh.insertMovie(title, genre, year, rating);

               // if (result != -1) {
                    //Toast.makeText(MainActivity.this, "movie successfully inserted", Toast.LENGTH_LONG).show();
                    //etmovietitle.setText("");
                    //etgenre.setText("");
                    //etyear.setText("");
                //} else {
                    //Toast.makeText(MainActivity.this, "insertion failed", Toast.LENGTH_LONG).show();
                //}
            }
        });

        btnshowlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, showmovies.class);
                startActivity(i);
                Log.d("activity started", "activity successfully started");
            }
        });

    }
}
