package com.example.marketmanagementsystem;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SupplyDB {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "_name";
    public static final String KEY_PRICE = "_price";
    public static final String KEY_QUANTITY = "_quantity";

    private final String DATABASE_NAME = "SupplyDB";
    private final String DATABASE_TABLE = "SupplyTable";
    private final int DATABASE_VERSION = 1;

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public static String [] itemsName = {
            "Apple",
            "Bread",
            "Meal",
            "Rice",
            "Tea",
            "Carrot",
            "Tomato",
            "Orange",
            "Soft Drink",
            "Milk",
            "Soap",
            "Ice Cream",
            "Water",
            "Chicken",
            "Berry"

    };

    public SupplyDB(Context ourContext) {
        this.ourContext = ourContext;
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String sqlCode = "CREATE TABLE " + DATABASE_TABLE + " (" +
                    KEY_NAME + " TEXT PRIMARY KEY, " +
                    KEY_PRICE + " TEXT NOT NULL, " +
                    KEY_QUANTITY + " TEXT NOT NULL);";

            sqLiteDatabase.execSQL(sqlCode);

            for (int i = 0; i < itemsName.length; i++) {
                sqLiteDatabase.execSQL("INSERT INTO " + DATABASE_TABLE + " VALUES(" +
                        itemsName[i] + ", 0, 0);");
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(sqLiteDatabase);
        }
    }


    public SupplyDB open() throws SQLException {
        ourHelper = new DBHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        ourHelper.close();
    }

    public long createEntry(String name, String price, String quantity) {
        ContentValues cv = new ContentValues();

        cv.put(KEY_NAME, name);
        cv.put(KEY_PRICE, price);
        cv.put(KEY_QUANTITY, quantity);

        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public String getData() {
        String [] cols = new String[] {KEY_ROWID, KEY_NAME, KEY_PRICE, KEY_QUANTITY};

        Cursor cursor = ourDatabase.query(DATABASE_TABLE, cols,
                null, null, null, null, null);

        String result = "";

        int iRowID = cursor.getColumnIndex(KEY_ROWID);
        int iName = cursor.getColumnIndex(KEY_NAME);
        int iPrice = cursor.getColumnIndex(KEY_PRICE);
        int iQuantity = cursor.getColumnIndex(KEY_QUANTITY);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            result = result + cursor.getString(iRowID) + ": " +
                    cursor.getString(iName) + " " +
                    cursor.getString(iPrice) +  " " +
                    cursor.getString(iQuantity) + "\n";
        }
        return result;
    }


    public void updateEntry(String name, String price, String quantity) {

        int oldQuantity = 0;

        String sqlCode = "SELECT QUANTITY FROM " + DATABASE_TABLE + " WHERE NAME=?";
        Cursor cursor = ourDatabase.rawQuery(sqlCode, new String[]{name});

        int quantityIndex = cursor.getColumnIndex(KEY_QUANTITY);

        if (cursor.moveToFirst()) {
            oldQuantity = cursor.getInt(quantityIndex);
        }

        oldQuantity += Integer.parseInt(quantity);

        sqlCode = "UPDATE " + DATABASE_TABLE + " SET " + KEY_QUANTITY + "=" +
                Integer.toString(oldQuantity) + " WHERE NAME=" + name + ";";

        ourDatabase.execSQL(sqlCode);
    }

}
