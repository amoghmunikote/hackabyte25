import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Set the layout for this activity

        // Insert menu data (can be done once during the first run or on a button click)
        MenuDataInserter menuDataInserter = new MenuDataInserter("Apple","None",54,1.30);
        menuDataInserter.insertMenuData();  // Insert data into the "menu" table

        // Fetch and display the menu data in the ListView
        MenuDataFetcher menuDataFetcher = new MenuDataFetcher(this);
        ArrayList<String> menuItems = menuDataFetcher.fetchMenuData();  // Fetch menu items

        displayMenuData(menuItems);  // Call the method to display the data
    }

    // Method to display the menu data in the ListView
    public void displayMenuData(ArrayList<String> menuItems) {
        ListView listView = findViewById(R.id.menuListView);  // Replace with the actual ID of your ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuItems);
        listView.setAdapter(adapter);
    }
}
