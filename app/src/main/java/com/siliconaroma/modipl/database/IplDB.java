package com.siliconaroma.modipl.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sheikb on 6/13/2015.
 */
public class IplDB {

    private Context mCtx;
    private static IplDB instance;
    private DBManager mDbHelper;
    private SQLiteDatabase mDb;
    private final String TAG = IplDB.class.getSimpleName();

    private IplDB(Context ctx){
        mCtx = ctx;
    }

    public static synchronized IplDB getInstance(Context cntxt){
        if(instance == null)
            instance = new IplDB(cntxt);
        try {
            instance.open();
        }catch (SQLException e) {
            //Log.e(TAG, "!Err when opening database", e);
            throw new RuntimeException("!Err when opening database", e);
        }
        return instance;
    }


    /**
     * Open the o2 database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     *
     * @return this (self reference, allowing this to be chained in an
     *         Initialisation call)
     * @throws SQLException
     *             if the database could be neither opened or created
     */
    private synchronized IplDB open() throws SQLException {
        if (mDbHelper == null) {
            mDbHelper = new DBManager(mCtx);
        }
        if (mDb == null) {
            mDb = mDbHelper.getWritableDatabase();
        }
        return this;

    }


    public static class DBEntry{
        //Main Table
        public static final String TABLE_SCHEDULE = "ipl_schedule";
        public static final String _ID           = "_id";
        public static final String DATE          = "date";
        public static final String TIME      = "time";
        public static final String TEAM1      = "team_1";
        public static final String TEAM2   = "team_2";
        public static final String VENUE  = "venue";
        public static final String WINNING_TEAM = "winning_team";

//        public static final String TYPE_TRANSACTION  = "typeTransaction";

   /*     //Category
        public static final String TABLE_NAME_CATEGORY = "category";
        public static final String CAT_ID         = "_id";
        public static final String CAT_NAME       = "category_name";
        public static final String CAT_AMOUNT   = "category_amt";

        //Sub-Category
        public static final String TABLE_NAME_SUB_CATEGORY = "subcategory";
        public static final String SUB_ID         = "_id";
        public static final String SUB_CAT_NAME       = "sub_category_name";
        public static final String SUB_NAME   = "sub_name";

        //Payment Mode
        public static final String TABLE_NAME_PAYMENT = "paymentmode";
        public static final String PAY_ID           = "_id";
        public static final String PAY_NAME         = "payment_mode";*/

        //
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBEntry.TABLE_SCHEDULE + " (" +
                    DBEntry._ID + " INTEGER PRIMARY KEY," +
                    DBEntry.DATE + " TEXT ,"  +
                    DBEntry.TIME + " TEXT ,"  +
                    DBEntry.TEAM1 + " TEXT ,"  +
                    DBEntry.TEAM2 + " TEXT ,"  +
                    DBEntry.VENUE + " INTEGER  ,"  +
                    //DBEntry.WINNING_TEAM + " TEXT , "+
                    DBEntry.WINNING_TEAM + " TEXT "+
            " )";

  /*  private static final String SQL_CREATE_CATEGORY =
            "CREATE TABLE " + DBEntry.TABLE_NAME_CATEGORY + " (" +
                    DBEntry.CAT_ID + " INTEGER PRIMARY KEY," +
                    DBEntry.CAT_NAME + " TEXT ,"  +
                    DBEntry.CAT_AMOUNT + " INTEGER "+
                    " )";

    private static final String SQL_CREATE_SUB_CATEGORY =
            "CREATE TABLE " + DBEntry.TABLE_NAME_SUB_CATEGORY + " (" +
                    DBEntry.SUB_ID + " INTEGER PRIMARY KEY," +
                    DBEntry.SUB_CAT_NAME + " TEXT ,"  +
                    DBEntry.SUB_NAME + " TEXT "+
                    " )";

    private static final String SQL_CREATE_PAYMENT =
            "CREATE TABLE " + DBEntry.TABLE_NAME_PAYMENT + " (" +
                    DBEntry.PAY_ID + " INTEGER PRIMARY KEY," +
                    DBEntry.PAY_NAME + " TEXT "+
                    " )";*/

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DBEntry.TABLE_SCHEDULE;

    public class DBManager extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "budgetcalendar.db";


        public DBManager(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
           /* db.execSQL(SQL_CREATE_PAYMENT);
            db.execSQL(SQL_CREATE_CATEGORY);
            db.execSQL(SQL_CREATE_SUB_CATEGORY);

             db.execSQL("insert into "+ DBEntry.TABLE_NAME_CATEGORY
                    +" ( "+ DBEntry.CAT_NAME+" ) " +
                    "values ('Bills'), ('Entertainment'), ('Food'), ('Groceries')");*/

           /* db.execSQL("insert into "+ DBEntry.TABLE_SCHEDULE
                    +" ( "+ DBEntry.PAY_NAME+" ) " +
                    "values ('Credit Card'), ('Debit Card'), ('Cash'), ('Coupons')");*/
        }



        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            super.onDowngrade(db, oldVersion, newVersion);
            Log.i(TAG,"OldVersion"+oldVersion+" NewVeraion :"+newVersion);
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
    }

    public Cursor rawQuery(String sql, String[] selectionArgs) {
        return mDb.rawQuery(sql, selectionArgs);
    }

    public synchronized long insert(String table, String nullColumnHack, ContentValues values) {
        return mDb.insert(table, nullColumnHack, values);
    }

    public synchronized long update(String table, ContentValues values, String where, String[] whereArgs){
        return mDb.update(table,values,where,whereArgs);
    }

    public synchronized long delete(String table, String where, String[] whereArgs){
        return mDb.delete(table,where,whereArgs);
    }

}
