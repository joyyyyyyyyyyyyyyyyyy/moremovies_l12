package sg.edu.rp.c346.id22022096.moremovies_l12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Movies> moviesArrayList;

    public CustomAdapter(Context context, int resource, ArrayList<Movies> objects) {
        super (context, resource, objects);
        parent_context = context;
        layout_id = resource;
        moviesArrayList = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater=(LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(layout_id, parent, false);

        TextView tvTitle=rowView.findViewById(R.id.tvTitle);
        TextView tvGenre=rowView.findViewById(R.id.tvGenre);
        TextView tvYear=rowView.findViewById(R.id.tvYear);
        ImageView ivRating=rowView.findViewById(R.id.imageViewRating);

        Movies currentItem=moviesArrayList.get(position);
        tvTitle.setText(currentItem.getTitle());
        tvGenre.setText(currentItem.getGenre());
        tvYear.setText(currentItem.getYear()+"");

        if(currentItem.getRating().equals("G")  ){
            ivRating.setImageResource(R.drawable.rating_g);
        } else if(currentItem.getRating().equals("PG") ){
            ivRating.setImageResource(R.drawable.rating_pg);
        } else if(currentItem.getRating().equals("PG13")){
            ivRating.setImageResource(R.drawable.rating_pg13);
        } else if(currentItem.getRating().equals("NC16")){
            ivRating.setImageResource(R.drawable.rating_nc16);
        } else if(currentItem.getRating().equals("M18")){
            ivRating.setImageResource(R.drawable.rating_m18);
        } else if(currentItem.getRating().equals("R21")){
            ivRating.setImageResource(R.drawable.rating_r21);
        }

        return rowView;
    }

}
