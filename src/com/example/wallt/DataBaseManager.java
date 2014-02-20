package com.example.wallt;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseManager {

	Context context;
    private SQLiteDatabase db;
    private final String DB_NAME = "database_name";
    private final int DB_VERSION = 1;
    
    private final String TABLE_NAME = "database_table";
    private final String TABLE_COLUMN_ID = "id";
    private final String TABLE_COLUMN_ONE = "user_name";
    private final String TABLE_COLUMN_TWO = "pass_word";
    
    public DataBaseManager(Context context) {
    	this.context = context;
    	CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
    	this.db = helper.getWritableDatabase();
    }

    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {

        public CustomSQLiteOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            String newTableQueryString =
                "create table " +
                TABLE_NAME +
                " (" +
                TABLE_COLUMN_ID + " integer primary key autoincrement not null," +
                TABLE_COLUMN_ONE + " text not null unique," +
                TABLE_COLUMN_TWO + " text not null" +
                ");";
            db.execSQL(newTableQueryString);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

    }
    
    public void addRow(String username, String password) {
    	ContentValues values = new ContentValues();
    	values.put(TABLE_COLUMN_ONE, username);
    	values.put(TABLE_COLUMN_TWO, password);
    	try {
    		db.insert(TABLE_NAME, null, values);
    	} catch (Exception e) {
    		Log.e("DB ERROR", e.toString());
    		e.printStackTrace();
    	}
    }
    
    public void deleteRow(long rowID) {
    	try {
    		db.delete(TABLE_NAME, TABLE_COLUMN_ID + "=" + rowID, null);
    	} catch (Exception e) {
    		Log.e("DB ERROR", e.toString());
    		e.printStackTrace();
    	}
    }
    
    public void updateRow(long rowID, String username, String password) {
    	ContentValues values = new ContentValues();
    	values.put(TABLE_COLUMN_ONE, username);
    	values.put(TABLE_COLUMN_TWO, password);
    	try {
    		db.update(TABLE_NAME, values, TABLE_COLUMN_ID + "=" + rowID, null);
    	} catch (Exception e) {
    		Log.e("DB Error", e.toString());
    		e.printStackTrace();
    	}
    }
    
    public ArrayList<Object> getRowAsArray(long rowID) {
    	ArrayList<Object> rowArray = new ArrayList<Object>();
    	Cursor cursor = null;
    	try {
    		cursor = db.query(
    				TABLE_NAME,
    				new String[] { TABLE_COLUMN_ID, TABLE_COLUMN_ONE, TABLE_COLUMN_TWO },
    				TABLE_COLUMN_ID + "=" + rowID,
    				null, null, null, null, null
    		);
    		cursor.moveToFirst();
    		if (!cursor.isAfterLast()) {
	    		do {
	    			ArrayList<Object> dataList = new ArrayList<Object>();
	    			dataList.add(cursor.getLong(0));
	    			dataList.add(cursor.getString(1));
	    			dataList.add(cursor.getString(2));
	    			rowArray.add(dataList);
	    		} while(cursor.moveToNext());
    		}
    		cursor.close();
    	} catch (Exception e) {
    		Log.e("DB ERROR", e.toString());
    		e.printStackTrace();
   
    	}
    	return rowArray;
    }
    
    public ArrayList<ArrayList<Object>> getAllRowsAsArrays() {
        ArrayList<ArrayList<Object>> dataArrays = new ArrayList<ArrayList<Object>>();
        Cursor cursor;

        try {
            cursor = db.query(
                    TABLE_NAME,
                    new String[]{TABLE_COLUMN_ID, TABLE_COLUMN_ONE, TABLE_COLUMN_TWO},
                    null, null, null, null, null
            );
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<Object>();

                    dataList.add(cursor.getLong(0));
                    dataList.add(cursor.getString(1));
                    dataList.add(cursor.getString(2));

                    dataArrays.add(dataList);
                } while (cursor.moveToNext());
            }
        }
        catch (Exception e) {
            Log.e("DB Error", e.toString());
            e.printStackTrace();
        }

        // return the ArrayList that holds the data collected from
        // the database.
        return dataArrays;
    }
    
    public boolean loginVerify(String username, String password) {
    	Cursor cursor = null;
    	boolean matches = false;
    	try {
    		cursor = db.query(
    				TABLE_NAME,
    				new String[] { TABLE_COLUMN_ID, TABLE_COLUMN_ONE, TABLE_COLUMN_TWO },
    				TABLE_COLUMN_ONE + "= '" + username + "'", 
    				null, null, null, null, null
    		);
    		cursor.moveToFirst();
    		if (!cursor.isAfterLast()) {
    			if (username.equals(cursor.getString(1).toString()) && 
    					password.equals(cursor.getString(2).toString())) {
    				matches = true;
    			}
    		}
    		cursor.close();
    	} catch (Exception e) {
    		Log.e("DB ERROR", e.toString());
    		e.printStackTrace();
   
    	}
    	return matches;
    }
    
    public boolean registerVerify(String username, String password) {
    	Cursor cursor = null;
    	boolean legal = true;
    	if (username.equals("") || password.equals("")) {
    		legal = false;
    	}
    	try {
    		cursor = db.query(
    				TABLE_NAME,
    				new String[] { TABLE_COLUMN_ONE },
    				TABLE_COLUMN_ONE + "= '" + username + "'", 
    				null, null, null, null, null
    		);
    		cursor.moveToFirst();
    		if (!cursor.isAfterLast()) {
    			if (username.equals(cursor.getString(0).toString())) {
    				legal = false;
    			}
    		}
    		cursor.close();
    	} catch (Exception e) {
    		Log.e("DB ERROR", e.toString());
    		e.printStackTrace();
   
    	}
    	return legal;
    }
    
}