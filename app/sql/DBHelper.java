import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// This class helps manage the SQLite database and handle database creation, version management, and upgrades.
public class DBHelper extends SQLiteOpenHelper {

    // Database name and version
    private static final String DATABASE_NAME = "preservedata.sqlite";  // Database file name
    private static final int DATABASE_VERSION = 1;  // Initial version of the database

    // Table name and column names
    private static final String TABLE_MENU = "menu";  // Table where menu items will be stored
    private static final String COLUMN_FOOD_ITEM = "foodItem";  // Column name for food items
    private static final String COLUMN_ALLERGY_WARNINGS = "allergyWarnings";  // Column name for allergy warnings
    private static final String COLUMN_CALORIES = "calories";  // Column name for calories
    private static final String COLUMN_PRICE = "price";  // Column name for price

    // Constructor to create a new DBHelper instance
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  // Calls parent constructor to handle DB creation
    }

    // This method is called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create the "menu" table with required columns
        String CREATE_TABLE_MENU = "CREATE TABLE " + TABLE_MENU + " ("
                + COLUMN_FOOD_ITEM + " TEXT,"  // foodItem column (type: TEXT)
                + COLUMN_ALLERGY_WARNINGS + " TEXT,"  // allergyWarnings column (type: TEXT)
                + COLUMN_CALORIES + " INTEGER,"  // calories column (type: INTEGER)
                + COLUMN_PRICE + " REAL)";  // price column (type: REAL for floating point)

        // Execute the SQL statement to create the table
        db.execSQL(CREATE_TABLE_MENU);  // Creates the "menu" table in the database
    }

    // This method is called when the database needs to be upgraded (e.g., when the version number changes)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing "menu" table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);

        // Call onCreate() again to create a new version of the table
        onCreate(db);
    }
}
