package edu.divyagyan.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {

    public PersonAdapter(Context context, List<Person> people) {
        super(context, 0, people);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_person, parent, false);
        }

        // Get the data item for this position
        Person person = getItem(position);

        // Lookup view for data population
        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        TextView addressTextView = convertView.findViewById(R.id.addressTextView);

        // Populate the data into the template view using the data object
        nameTextView.setText(person.getName());
        addressTextView.setText(person.getAddress());

        // Return the completed view to render on screen
        return convertView;
    }
}
