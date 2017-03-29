package com.siliconaroma.modipl.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.siliconaroma.modipl.Data.MatchData;

import java.util.ArrayList;

/**
 * Created by sheikb on 6/15/2015.
 */
public class IplDbHelper {

    private final String TAG = IplDbHelper.class.getSimpleName();

    private IplDB mDbMgr;
    public IplDbHelper(IplDB dbMgr) {
        mDbMgr = dbMgr;
    }

   /* public long insertMatchDetails(ContentValues data){
        return mDbMgr.insert(IplDB.DBEntry.TABLE_SCHEDULE,null,data);
    }*/

    public long updateTransactionData(ContentValues updateData, String where, String[] whereArgs){
        return mDbMgr.update(IplDB.DBEntry.TABLE_SCHEDULE, updateData, where, whereArgs);
    }

    public ArrayList<MatchData> getAllMatches() {
        ArrayList<MatchData> data = new ArrayList<MatchData>();
         String sqlQuery = "select * from "
                +IplDB.DBEntry.TABLE_SCHEDULE;
                //+" where strftime('%m%Y',"+CalendarDB.DBEntry.DATE+") = ? "
                //+"and "+CalendarDB.DBEntry.TYPE_TRANSACTION+" = ?";
        //Log.i(TAG,"Query"+sqlQuery);
        Cursor crsr = mDbMgr.rawQuery(sqlQuery,new String[]{});
        if(crsr.moveToFirst()){
            do{
                data.add(new MatchData(crsr.getInt(crsr.getColumnIndex(IplDB.DBEntry._ID))
                        ,crsr.getString(crsr.getColumnIndexOrThrow(IplDB.DBEntry.DATE)),
                        crsr.getString(crsr.getColumnIndexOrThrow(IplDB.DBEntry.TIME)),
                        crsr.getString(crsr.getColumnIndexOrThrow(IplDB.DBEntry.TEAM1)),
                        crsr.getString(crsr.getColumnIndexOrThrow(IplDB.DBEntry.TEAM2)),
                        crsr.getString(crsr.getColumnIndexOrThrow(IplDB.DBEntry.VENUE)),
                        crsr.getString(crsr.getColumnIndexOrThrow(IplDB.DBEntry.WINNING_TEAM))));
            } while (crsr.moveToNext());
        }

        if (crsr != null && !crsr.isClosed()) {
            crsr.close();
        }

        return data;
    }

   /* public long deleteTransaction(String where, String[] whereArgs){
        return mDbMgr.delete(IplDB.DBEntry.TABLE_NAME, where, whereArgs);
    }

    public void deleteTransaction(String id){
        String sqlQuery = "delete from "
                + IplDB.DBEntry.TABLE_NAME
                +" where "+ IplDB.DBEntry._ID+" = ?";
        mDbMgr.rawQuery(sqlQuery,new String[]{id});
    }

    public int fetchTotalAmountOnADate(String date, String typeTrans){
        int total = -1;
        String sqlQuery = "select sum ("+ IplDB.DBEntry.AMOUNT+" ) as totalAmount from "+ IplDB.DBEntry.TABLE_NAME+
                " where "+ IplDB.DBEntry.DATE+" = ?"
                +"and "+ IplDB.DBEntry.TYPE_TRANSACTION+" = ?";
        Cursor crsr = mDbMgr.rawQuery(sqlQuery,new String[]{date,typeTrans});
        if(crsr.moveToFirst())
            total = crsr.getInt(0);

        if (crsr != null && !crsr.isClosed()) {
            crsr.close();
        }
        return total;
    }

    public int getTotalAmountOfMonth(String month, String year, String typeTrans){
        int total = -1;
        String sqlQuery = "select sum ("+ IplDB.DBEntry.AMOUNT+" ) as totalAmount from "
                + IplDB.DBEntry.TABLE_NAME
                +" where strftime('%m%Y',"+ IplDB.DBEntry.DATE+") = ? "
                +"and "+ IplDB.DBEntry.TYPE_TRANSACTION+" = ?";
        Log.i(TAG,"Query"+sqlQuery+" : "+getCurrentMonth(month)+year);

        Cursor crsr = mDbMgr.rawQuery(sqlQuery,new String[]{getCurrentMonth(month)+year,typeTrans});
        if(crsr.moveToFirst())
            total = crsr.getInt(0);

        if (crsr != null && !crsr.isClosed()) {
            crsr.close();
        }
        return total;

    }*/

}
