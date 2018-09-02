package au.edu.holmesglen.hdworkoski.assignment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    Item[] gridArray; //an array of the Item class - our Item class represents color image
    private Context mContext; //the current state of an app or object or activity

    public ImageAdapter(Context c, Item[] gridArray) { //constructor
        mContext = c;
        this.gridArray = gridArray;
    }

    public int getCount() { //return the size of the array
        return gridArray.length;
    }

    public Object getItem(int position) { //return the current Item (color) in a grid on GridView
        return null;
    }

    public long getItemId(int position) { //return the position in GridView of where the color can be found
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            int width = ((GridView) parent).getColumnWidth();
            imageView.setLayoutParams(new GridView.LayoutParams(width, width));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(0, 0, 0, 0);
        }
        else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(gridArray[position].getColor());
        return imageView;
    }
}