package men2a.victoria.triptracker;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Vicky on 6/29/2017.
 */

public class TripListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);

        //check if the trips list fragment already exists - otherwise, create a new one and add it to the fragment container frame found in activity_trips_list.xml
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.tripListFragmentContainer);

        if (fragment==null) {
            fragment = new TripListFragment();
            manager.beginTransaction()
                    .add(R.id.tripListFragmentContainer, fragment)
                    .commit();
        }
    }
}

