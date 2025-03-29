import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class OrdersDataInserter {

    private Context context;

    // Constructor that takes context as parameter
    public OrdersDataInserter(Context context) {
        this.context = context;
    }

    // Method to insert a new order into the orders table
    public void insertOrderData(int studentID, String food, int quantity) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();  // Open database in write mode

        // Create a ContentValues object to hold the data
        ContentValues values = new ContentValues();
        values.put("studentID", studentID);
        values.put("food", food);
        values.put("quantity", quantity);

        // Insert the new order into the "orders" table
        db.insert("orders", null, values);

        // Close the database after insertion
        db.close();
    }
}
