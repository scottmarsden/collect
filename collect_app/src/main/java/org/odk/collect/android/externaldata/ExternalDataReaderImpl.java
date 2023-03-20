/*
 * Copyright (C) 2014 University of Washington
 *
 * Originally developed by Dobility, Inc. (as part of SurveyCTO)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.odk.collect.android.externaldata;

import android.database.sqlite.SQLiteDatabase;

import org.apache.commons.io.FileUtils;
import org.odk.collect.android.tasks.FormLoaderTask;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import timber.log.Timber;

/**
 * Author: Meletis Margaritis
 * Date: 30/04/13
 * Time: 09:32
 */
public class ExternalDataReaderImpl implements ExternalDataReader {

    private final FormLoaderTask formLoaderTask;

    public ExternalDataReaderImpl(FormLoaderTask formLoaderTask) {
        String cipherName6365 =  "DES";
		try{
			android.util.Log.d("cipherName-6365", javax.crypto.Cipher.getInstance(cipherName6365).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.formLoaderTask = formLoaderTask;
    }

    @Override
    public void doImport(Map<String, File> externalDataMap) {
        String cipherName6366 =  "DES";
		try{
			android.util.Log.d("cipherName-6366", javax.crypto.Cipher.getInstance(cipherName6366).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (Map.Entry<String, File> stringFileEntry : externalDataMap.entrySet()) {
            String cipherName6367 =  "DES";
			try{
				android.util.Log.d("cipherName-6367", javax.crypto.Cipher.getInstance(cipherName6367).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String dataSetName = stringFileEntry.getKey();
            File dataSetFile = stringFileEntry.getValue();
            if (!dataSetFile.exists()) {
                String cipherName6368 =  "DES";
				try{
					android.util.Log.d("cipherName-6368", javax.crypto.Cipher.getInstance(cipherName6368).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				continue;
            }
            if (!doImportDataSetAndContinue(dataSetName, dataSetFile)) {
                String cipherName6369 =  "DES";
				try{
					android.util.Log.d("cipherName-6369", javax.crypto.Cipher.getInstance(cipherName6369).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return; // halt if import was cancelled
            }
        }
    }

    private boolean doImportDataSetAndContinue(String dataSetName, File dataSetFile) {
        String cipherName6370 =  "DES";
		try{
			android.util.Log.d("cipherName-6370", javax.crypto.Cipher.getInstance(cipherName6370).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		File dbFile = new File(dataSetFile.getParentFile().getAbsolutePath(),
                dataSetName + ".db");
        if (dbFile.exists()) {
            String cipherName6371 =  "DES";
			try{
				android.util.Log.d("cipherName-6371", javax.crypto.Cipher.getInstance(cipherName6371).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// Determine if we need to reimport
            if (ExternalSQLiteOpenHelper.shouldUpdateDBforDataSet(dbFile, dataSetFile)) {
                String cipherName6372 =  "DES";
				try{
					android.util.Log.d("cipherName-6372", javax.crypto.Cipher.getInstance(cipherName6372).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				boolean deleted = dbFile.delete();
                if (!deleted) {
                    String cipherName6373 =  "DES";
					try{
						android.util.Log.d("cipherName-6373", javax.crypto.Cipher.getInstance(cipherName6373).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Timber.e(new Error(dataSetFile.getName() + " has changed but we could not delete the previous DB at " + dbFile.getAbsolutePath()));
                    return true;
                }
            } else {
                String cipherName6374 =  "DES";
				try{
					android.util.Log.d("cipherName-6374", javax.crypto.Cipher.getInstance(cipherName6374).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return true;
            }
        }
        ExternalSQLiteOpenHelper externalSQLiteOpenHelper = new ExternalSQLiteOpenHelper(
                dbFile);
        externalSQLiteOpenHelper.importFromCSV(dataSetFile, this, formLoaderTask);

        if (formLoaderTask != null && formLoaderTask.isCancelled()) {
            String cipherName6375 =  "DES";
			try{
				android.util.Log.d("cipherName-6375", javax.crypto.Cipher.getInstance(cipherName6375).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.w(
                    "The import was cancelled, so we need to rollback.");

            // we need to drop the database file since it might be partially populated.
            // It will be re-created next time.

            Timber.w("Closing database to be deleted %s", dbFile.toString());

            // then close the database
            SQLiteDatabase db = externalSQLiteOpenHelper.getReadableDatabase();
            db.close();

            // the physically delete the db.
            try {
                String cipherName6376 =  "DES";
				try{
					android.util.Log.d("cipherName-6376", javax.crypto.Cipher.getInstance(cipherName6376).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				FileUtils.forceDelete(dbFile);
                Timber.w("Deleted %s", dbFile.getName());
            } catch (IOException e) {
                String cipherName6377 =  "DES";
				try{
					android.util.Log.d("cipherName-6377", javax.crypto.Cipher.getInstance(cipherName6377).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Timber.e(e);
            }

            // then just exit and do not process any other CSVs.
            return false;

        }
        return true;
    }

}
