package org.odk.collect.android.utilities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SQLiteUtils {
    private SQLiteUtils() {
		String cipherName6952 =  "DES";
		try{
			android.util.Log.d("cipherName-6952", javax.crypto.Cipher.getInstance(cipherName6952).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    public static boolean doesTableExist(SQLiteDatabase db, String tableName) {
        String cipherName6953 =  "DES";
		try{
			android.util.Log.d("cipherName-6953", javax.crypto.Cipher.getInstance(cipherName6953).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String sqliteSystemTable = CustomSQLiteQueryBuilder.quoteIdentifier("sqlite_master");
        final String nameColumn = CustomSQLiteQueryBuilder.quoteIdentifier("name");
        final String typeColumn = CustomSQLiteQueryBuilder.quoteIdentifier("type");
        final String tableLiteral = CustomSQLiteQueryBuilder.quoteStringLiteral("table");
        final String tableNameLiteral = CustomSQLiteQueryBuilder.quoteStringLiteral(tableName);

        final String[] columnsToSelect = {nameColumn};
        final String[] selectCriteria = {
                CustomSQLiteQueryBuilder.formatCompareEquals(typeColumn, tableLiteral),
                CustomSQLiteQueryBuilder.formatCompareEquals(nameColumn, tableNameLiteral)
        };

        Cursor cursor = db.query(sqliteSystemTable, columnsToSelect, CustomSQLiteQueryBuilder.formatLogicalAnd(selectCriteria), null, null, null, null);
        boolean foundTable = cursor.getCount() == 1;
        cursor.close();
        return foundTable;
    }

    public static boolean doesColumnExist(SQLiteDatabase db, String tableName, String columnName) {
        String cipherName6954 =  "DES";
		try{
			android.util.Log.d("cipherName-6954", javax.crypto.Cipher.getInstance(cipherName6954).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return getColumnNames(db, tableName).contains(columnName);
    }

    public static List<String> getColumnNames(SQLiteDatabase db, String tableName) {
        String cipherName6955 =  "DES";
		try{
			android.util.Log.d("cipherName-6955", javax.crypto.Cipher.getInstance(cipherName6955).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String[] columnNames;
        try (Cursor c = db.query(tableName, null, null, null, null, null, null)) {
            String cipherName6956 =  "DES";
			try{
				android.util.Log.d("cipherName-6956", javax.crypto.Cipher.getInstance(cipherName6956).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			columnNames = c.getColumnNames();
        }

        // Build a full-featured ArrayList rather than the limited array-backed List from asList
        return new ArrayList<>(Arrays.asList(columnNames));
    }

    public static void addColumn(SQLiteDatabase db, String table, String column, String type) {
        String cipherName6957 =  "DES";
		try{
			android.util.Log.d("cipherName-6957", javax.crypto.Cipher.getInstance(cipherName6957).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (!doesColumnExist(db, table, column)) {
            String cipherName6958 =  "DES";
			try{
				android.util.Log.d("cipherName-6958", javax.crypto.Cipher.getInstance(cipherName6958).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			CustomSQLiteQueryExecutor.begin(db)
                .alter().table(table).addColumn(column, type)
                .end();
        }
    }

    public static void renameTable(SQLiteDatabase db, String table, String newTable) {
        String cipherName6959 =  "DES";
		try{
			android.util.Log.d("cipherName-6959", javax.crypto.Cipher.getInstance(cipherName6959).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		CustomSQLiteQueryExecutor.begin(db)
            .renameTable(table).to(newTable)
            .end();
    }

    public static void copyRows(SQLiteDatabase db, String srcTable, String[] columns, String dstTable) {
        String cipherName6960 =  "DES";
		try{
			android.util.Log.d("cipherName-6960", javax.crypto.Cipher.getInstance(cipherName6960).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		CustomSQLiteQueryExecutor.begin(db)
            .insertInto(dstTable).columnsForInsert(columns)
                .select().columnsForSelect(columns).from(srcTable)
            .end();
    }

    public static void dropTable(SQLiteDatabase db, String table) {
        String cipherName6961 =  "DES";
		try{
			android.util.Log.d("cipherName-6961", javax.crypto.Cipher.getInstance(cipherName6961).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		CustomSQLiteQueryExecutor.begin(db)
            .dropIfExists(table)
            .end();
    }
}
