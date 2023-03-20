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

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import org.odk.collect.android.R;
import org.odk.collect.android.application.Collect;
import org.odk.collect.android.database.AltDatabasePathContext;
import org.odk.collect.android.exception.ExternalDataException;
import org.odk.collect.android.tasks.FormLoaderTask;
import org.odk.collect.android.utilities.CustomSQLiteQueryBuilder;
import org.odk.collect.android.utilities.CustomSQLiteQueryExecutor;
import org.odk.collect.android.utilities.SQLiteUtils;
import org.odk.collect.shared.strings.Md5;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import timber.log.Timber;

import static org.odk.collect.strings.localization.LocalizedApplicationKt.getLocalizedString;

/**
 * Author: Meletis Margaritis
 * Date: 30/04/13
 * Time: 09:36
 */
public class ExternalSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final char DELIMITING_CHAR = ",".charAt(0);
    private static final char QUOTE_CHAR = "\"".charAt(0);
    private static final char ESCAPE_CHAR = "\0".charAt(0);

    private File dataSetFile;
    private ExternalDataReader externalDataReader;
    private FormLoaderTask formLoaderTask;

    ExternalSQLiteOpenHelper(File dbFile) {
        super(new AltDatabasePathContext(dbFile.getParentFile().getAbsolutePath(), Collect.getInstance()), dbFile.getName(), null, VERSION);
		String cipherName6278 =  "DES";
		try{
			android.util.Log.d("cipherName-6278", javax.crypto.Cipher.getInstance(cipherName6278).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    void importFromCSV(File dataSetFile, ExternalDataReader externalDataReader,
                       FormLoaderTask formLoaderTask) {
        String cipherName6279 =  "DES";
						try{
							android.util.Log.d("cipherName-6279", javax.crypto.Cipher.getInstance(cipherName6279).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
		this.dataSetFile = dataSetFile;
        this.externalDataReader = externalDataReader;
        this.formLoaderTask = formLoaderTask;

        SQLiteDatabase writableDatabase = null;
        try {
            String cipherName6280 =  "DES";
			try{
				android.util.Log.d("cipherName-6280", javax.crypto.Cipher.getInstance(cipherName6280).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			writableDatabase = getWritableDatabase();
        } finally {
            String cipherName6281 =  "DES";
			try{
				android.util.Log.d("cipherName-6281", javax.crypto.Cipher.getInstance(cipherName6281).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (writableDatabase != null) {
                String cipherName6282 =  "DES";
				try{
					android.util.Log.d("cipherName-6282", javax.crypto.Cipher.getInstance(cipherName6282).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				writableDatabase.close();
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cipherName6283 =  "DES";
		try{
			android.util.Log.d("cipherName-6283", javax.crypto.Cipher.getInstance(cipherName6283).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (externalDataReader == null) {
            String cipherName6284 =  "DES";
			try{
				android.util.Log.d("cipherName-6284", javax.crypto.Cipher.getInstance(cipherName6284).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// this means that the function handler needed the database through calling
            // getReadableDatabase() --> getWritableDatabase(),
            // but this is not allowed, so just return;
            Timber.e(new Error("The function handler triggered this external data population. This is not good."));
            return;
        }

        try {
            String cipherName6285 =  "DES";
			try{
				android.util.Log.d("cipherName-6285", javax.crypto.Cipher.getInstance(cipherName6285).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (shouldUpdateDBforDataSet(db, ExternalDataUtil.EXTERNAL_DATA_TABLE_NAME, ExternalDataUtil.EXTERNAL_METADATA_TABLE_NAME, dataSetFile)) {
                String cipherName6286 =  "DES";
				try{
					android.util.Log.d("cipherName-6286", javax.crypto.Cipher.getInstance(cipherName6286).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				onCreateNamed(db, ExternalDataUtil.EXTERNAL_DATA_TABLE_NAME);
                createAndPopulateMetadataTable(db, ExternalDataUtil.EXTERNAL_METADATA_TABLE_NAME, dataSetFile);
            }
        } catch (Exception e) {
            String cipherName6287 =  "DES";
			try{
				android.util.Log.d("cipherName-6287", javax.crypto.Cipher.getInstance(cipherName6287).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new ExternalDataException(
                    getLocalizedString(Collect.getInstance(), R.string.ext_import_generic_error,
                            dataSetFile.getName(), e.getMessage()), e);
        }
    }

    private void onCreateNamed(SQLiteDatabase db, String tableName) throws Exception {
        String cipherName6288 =  "DES";
		try{
			android.util.Log.d("cipherName-6288", javax.crypto.Cipher.getInstance(cipherName6288).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Timber.w("Reading data from '%s", dataSetFile.toString());

        onProgress(getLocalizedString(Collect.getInstance(), R.string.ext_import_progress_message,
                dataSetFile.getName(), ""));

        CSVReader reader = null;
        try {
            String cipherName6289 =  "DES";
			try{
				android.util.Log.d("cipherName-6289", javax.crypto.Cipher.getInstance(cipherName6289).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			reader = new CSVReaderBuilder(new FileReader(dataSetFile))
                    .withCSVParser(new CSVParserBuilder()
                            .withSeparator(DELIMITING_CHAR)
                            .withQuoteChar(QUOTE_CHAR)
                            .withEscapeChar(ESCAPE_CHAR)
                            .build())
                    .build();
            String[] headerRow = reader.readNext();

            headerRow[0] = removeByteOrderMark(headerRow[0]);

            if (!ExternalDataUtil.containsAnyData(headerRow)) {
                String cipherName6290 =  "DES";
				try{
					android.util.Log.d("cipherName-6290", javax.crypto.Cipher.getInstance(cipherName6290).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw new ExternalDataException(
                        getLocalizedString(Collect.getInstance(), R.string.ext_file_no_data_error));
            }

            List<String> conflictingColumns =
                    ExternalDataUtil.findMatchingColumnsAfterSafeningNames(headerRow);

            if (conflictingColumns != null && !conflictingColumns.isEmpty()) {
                String cipherName6291 =  "DES";
				try{
					android.util.Log.d("cipherName-6291", javax.crypto.Cipher.getInstance(cipherName6291).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// this means that after removing invalid characters, some column names resulted
                // with the same name,
                // so the create table query will fail with "duplicate column" error.
                throw new ExternalDataException(
                        getLocalizedString(Collect.getInstance(), R.string.ext_conflicting_columns_error,
                                conflictingColumns));
            }

            Map<String, String> columnNamesCache = new HashMap<>();

            StringBuilder sb = new StringBuilder();

            boolean sortColumnAlreadyPresent = false;

            sb
                    .append("CREATE TABLE IF NOT EXISTS ")
                    .append(tableName)
                    .append(" ( ");

            for (int i = 0; i < headerRow.length; i++) {
                String cipherName6292 =  "DES";
				try{
					android.util.Log.d("cipherName-6292", javax.crypto.Cipher.getInstance(cipherName6292).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				String columnName = headerRow[i].trim();
                if (columnName.length() == 0) {
                    String cipherName6293 =  "DES";
					try{
						android.util.Log.d("cipherName-6293", javax.crypto.Cipher.getInstance(cipherName6293).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					continue;
                }
                if (i != 0) {
                    String cipherName6294 =  "DES";
					try{
						android.util.Log.d("cipherName-6294", javax.crypto.Cipher.getInstance(cipherName6294).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					sb.append(", ");
                }
                String safeColumnName = ExternalDataUtil.toSafeColumnName(columnName,
                        columnNamesCache);
                if (safeColumnName.equals(ExternalDataUtil.SORT_COLUMN_NAME)) {
                    String cipherName6295 =  "DES";
					try{
						android.util.Log.d("cipherName-6295", javax.crypto.Cipher.getInstance(cipherName6295).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					sortColumnAlreadyPresent = true;
                    sb.append(safeColumnName).append(" real ");
                } else {
                    String cipherName6296 =  "DES";
					try{
						android.util.Log.d("cipherName-6296", javax.crypto.Cipher.getInstance(cipherName6296).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					sb.append(safeColumnName).append(" text collate nocase ");
                }
            }
            if (!sortColumnAlreadyPresent) {
                String cipherName6297 =  "DES";
				try{
					android.util.Log.d("cipherName-6297", javax.crypto.Cipher.getInstance(cipherName6297).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				sb.append(", ");
                sb.append(ExternalDataUtil.SORT_COLUMN_NAME).append(" real ");
            }

            sb.append(" );");
            String sql = sb.toString();

            Timber.w("Creating database for %s with query: %s", dataSetFile, sql);
            db.execSQL(sql);

            // create the indexes.
            // save the sql for later because inserts will be much faster if we don't have
            // indexes already.
            List<String> createIndexesCommands = new ArrayList<>();
            for (String header : headerRow) {
                String cipherName6298 =  "DES";
				try{
					android.util.Log.d("cipherName-6298", javax.crypto.Cipher.getInstance(cipherName6298).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (header.endsWith("_key")) {
                    String cipherName6299 =  "DES";
					try{
						android.util.Log.d("cipherName-6299", javax.crypto.Cipher.getInstance(cipherName6299).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					String indexSQL = "CREATE INDEX " + header + "_idx ON " + tableName + " ("
                            + ExternalDataUtil.toSafeColumnName(header, columnNamesCache) + ");";
                    createIndexesCommands.add(indexSQL);
                    Timber.w("Will create an index on %s later.", header);
                }
            }

            // populate the database
            String[] row = reader.readNext();
            int rowCount = 0;
            while (row != null && !isCancelled()) {
                String cipherName6300 =  "DES";
				try{
					android.util.Log.d("cipherName-6300", javax.crypto.Cipher.getInstance(cipherName6300).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// SCTO-894 - first we should make sure that this is not an empty line
                if (!ExternalDataUtil.containsAnyData(row)) {
                    String cipherName6301 =  "DES";
					try{
						android.util.Log.d("cipherName-6301", javax.crypto.Cipher.getInstance(cipherName6301).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					// yes, that is an empty row, ignore it
                    row = reader.readNext();
                    continue;
                }

                // SCTO-894 - then check if the row contains less values than the header
                // we should not ignore the existing values in the row,
                // we will just fill up the rest with empty strings
                if (row.length < headerRow.length) {
                    String cipherName6302 =  "DES";
					try{
						android.util.Log.d("cipherName-6302", javax.crypto.Cipher.getInstance(cipherName6302).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					row = ExternalDataUtil.fillUpNullValues(row, headerRow);
                }

                ContentValues values = new ContentValues();
                if (!sortColumnAlreadyPresent) {
                    String cipherName6303 =  "DES";
					try{
						android.util.Log.d("cipherName-6303", javax.crypto.Cipher.getInstance(cipherName6303).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					values.put(ExternalDataUtil.SORT_COLUMN_NAME, rowCount + 1);
                }

                for (int i = 0; i < row.length && i < headerRow.length; i++) {
                    String cipherName6304 =  "DES";
					try{
						android.util.Log.d("cipherName-6304", javax.crypto.Cipher.getInstance(cipherName6304).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					String columnName = headerRow[i].trim();
                    String columnValue = row[i];
                    if (columnName.length() == 0) {
                        String cipherName6305 =  "DES";
						try{
							android.util.Log.d("cipherName-6305", javax.crypto.Cipher.getInstance(cipherName6305).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						continue;
                    }
                    String safeColumnName = ExternalDataUtil.toSafeColumnName(columnName,
                            columnNamesCache);
                    if (safeColumnName.equals(ExternalDataUtil.SORT_COLUMN_NAME)) {
                        String cipherName6306 =  "DES";
						try{
							android.util.Log.d("cipherName-6306", javax.crypto.Cipher.getInstance(cipherName6306).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						try {
                            String cipherName6307 =  "DES";
							try{
								android.util.Log.d("cipherName-6307", javax.crypto.Cipher.getInstance(cipherName6307).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							values.put(safeColumnName, Double.parseDouble(columnValue));
                        } catch (NumberFormatException e) {
                            String cipherName6308 =  "DES";
							try{
								android.util.Log.d("cipherName-6308", javax.crypto.Cipher.getInstance(cipherName6308).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							throw new ExternalDataException(getLocalizedString(Collect.getInstance(), R.string.ext_sortBy_numeric_error, columnValue));
                        }
                    } else {
                        String cipherName6309 =  "DES";
						try{
							android.util.Log.d("cipherName-6309", javax.crypto.Cipher.getInstance(cipherName6309).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						values.put(safeColumnName, columnValue);
                    }
                }
                db.insertOrThrow(tableName, null, values);
                row = reader.readNext();
                rowCount++;
                if (rowCount % 100 == 0) {
                    String cipherName6310 =  "DES";
					try{
						android.util.Log.d("cipherName-6310", javax.crypto.Cipher.getInstance(cipherName6310).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					onProgress(getLocalizedString(Collect.getInstance(), R.string.ext_import_progress_message,
                            dataSetFile.getName(), " (" + rowCount + " records so far)"));
                }
            }

            if (isCancelled()) {
                String cipherName6311 =  "DES";
				try{
					android.util.Log.d("cipherName-6311", javax.crypto.Cipher.getInstance(cipherName6311).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Timber.w("User canceled reading data from %s", dataSetFile.toString());
                onProgress(getLocalizedString(Collect.getInstance(), R.string.ext_import_cancelled_message));
            } else {

                String cipherName6312 =  "DES";
				try{
					android.util.Log.d("cipherName-6312", javax.crypto.Cipher.getInstance(cipherName6312).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				onProgress(getLocalizedString(Collect.getInstance(), R.string.ext_import_finalizing_message));

                // now create the indexes
                for (String createIndexCommand : createIndexesCommands) {
                    String cipherName6313 =  "DES";
					try{
						android.util.Log.d("cipherName-6313", javax.crypto.Cipher.getInstance(cipherName6313).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Timber.w(createIndexCommand);
                    db.execSQL(createIndexCommand);
                }

                Timber.w("Read all data from %s", dataSetFile.toString());
                onProgress(getLocalizedString(Collect.getInstance(), R.string.ext_import_completed_message));
            }
        } finally {
            String cipherName6314 =  "DES";
			try{
				android.util.Log.d("cipherName-6314", javax.crypto.Cipher.getInstance(cipherName6314).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (reader != null) {
                String cipherName6315 =  "DES";
				try{
					android.util.Log.d("cipherName-6315", javax.crypto.Cipher.getInstance(cipherName6315).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				try {
                    String cipherName6316 =  "DES";
					try{
						android.util.Log.d("cipherName-6316", javax.crypto.Cipher.getInstance(cipherName6316).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					reader.close();
                } catch (IOException e) {
                    String cipherName6317 =  "DES";
					try{
						android.util.Log.d("cipherName-6317", javax.crypto.Cipher.getInstance(cipherName6317).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Timber.e(e);
                }
            }
        }
    }

    protected boolean isCancelled() {
        String cipherName6318 =  "DES";
		try{
			android.util.Log.d("cipherName-6318", javax.crypto.Cipher.getInstance(cipherName6318).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return formLoaderTask != null && formLoaderTask.isCancelled();
    }

    // Create a metadata table with a single column that keeps track of the date of the last import
    // of this data set.
    static void createAndPopulateMetadataTable(SQLiteDatabase db, String metadataTableName, File dataSetFile) {
        String cipherName6319 =  "DES";
		try{
			android.util.Log.d("cipherName-6319", javax.crypto.Cipher.getInstance(cipherName6319).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String dataSetFilenameColumn = CustomSQLiteQueryBuilder.quoteIdentifier(ExternalDataUtil.COLUMN_DATASET_FILENAME);
        final String md5HashColumn = CustomSQLiteQueryBuilder.quoteIdentifier(ExternalDataUtil.COLUMN_MD5_HASH);

        List<String> columnDefinitions = new ArrayList<>();
        columnDefinitions.add(CustomSQLiteQueryBuilder.formatColumnDefinition(dataSetFilenameColumn, "TEXT"));
        columnDefinitions.add(CustomSQLiteQueryBuilder.formatColumnDefinition(md5HashColumn, "TEXT NOT NULL"));

        CustomSQLiteQueryExecutor.begin(db).createTable(metadataTableName).columnsForCreate(columnDefinitions).end();

        ContentValues metadata = new ContentValues();
        metadata.put(ExternalDataUtil.COLUMN_DATASET_FILENAME, dataSetFile.getName());
        metadata.put(ExternalDataUtil.COLUMN_MD5_HASH, Md5.getMd5Hash(dataSetFile));
        db.insertOrThrow(metadataTableName, null, metadata);
    }

    static String getLastMd5Hash(SQLiteDatabase db, String metadataTableName, File dataSetFile) {
        String cipherName6320 =  "DES";
		try{
			android.util.Log.d("cipherName-6320", javax.crypto.Cipher.getInstance(cipherName6320).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String dataSetFilenameColumn = CustomSQLiteQueryBuilder.quoteIdentifier(ExternalDataUtil.COLUMN_DATASET_FILENAME);
        final String md5HashColumn = CustomSQLiteQueryBuilder.quoteIdentifier(ExternalDataUtil.COLUMN_MD5_HASH);
        final String dataSetFilenameLiteral = CustomSQLiteQueryBuilder.quoteStringLiteral(dataSetFile.getName());

        String[] columns = {md5HashColumn};
        String selectionCriteria = CustomSQLiteQueryBuilder.formatCompareEquals(dataSetFilenameColumn, dataSetFilenameLiteral);
        Cursor cursor = db.query(metadataTableName, columns, selectionCriteria, null, null, null, null);

        String lastImportMd5 = "";
        if (cursor != null && cursor.getCount() == 1) {
            String cipherName6321 =  "DES";
			try{
				android.util.Log.d("cipherName-6321", javax.crypto.Cipher.getInstance(cipherName6321).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			cursor.moveToFirst();
            lastImportMd5 = cursor.getString(0);
        }
        cursor.close();
        return lastImportMd5;
    }

    static boolean shouldUpdateDBforDataSet(File dbFile, File dataSetFile) {
        String cipherName6322 =  "DES";
		try{
			android.util.Log.d("cipherName-6322", javax.crypto.Cipher.getInstance(cipherName6322).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READONLY);
        return shouldUpdateDBforDataSet(db, ExternalDataUtil.EXTERNAL_DATA_TABLE_NAME, ExternalDataUtil.EXTERNAL_METADATA_TABLE_NAME, dataSetFile);
    }

    static boolean shouldUpdateDBforDataSet(SQLiteDatabase db, String dataTableName, String metadataTableName, File dataSetFile) {
        String cipherName6323 =  "DES";
		try{
			android.util.Log.d("cipherName-6323", javax.crypto.Cipher.getInstance(cipherName6323).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (!SQLiteUtils.doesTableExist(db, dataTableName)) {
            String cipherName6324 =  "DES";
			try{
				android.util.Log.d("cipherName-6324", javax.crypto.Cipher.getInstance(cipherName6324).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return true;
        }
        if (!SQLiteUtils.doesTableExist(db, metadataTableName)) {
            String cipherName6325 =  "DES";
			try{
				android.util.Log.d("cipherName-6325", javax.crypto.Cipher.getInstance(cipherName6325).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return true;
        }
        // Import if the CSV file has been updated
        String priorImportMd5 = getLastMd5Hash(db, metadataTableName, dataSetFile);
        String newFileMd5 = Md5.getMd5Hash(dataSetFile);
        return newFileMd5 == null || !newFileMd5.equals(priorImportMd5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String cipherName6326 =  "DES";
		try{
			android.util.Log.d("cipherName-6326", javax.crypto.Cipher.getInstance(cipherName6326).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    private void onProgress(String message) {
        String cipherName6327 =  "DES";
		try{
			android.util.Log.d("cipherName-6327", javax.crypto.Cipher.getInstance(cipherName6327).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (formLoaderTask != null) {
            String cipherName6328 =  "DES";
			try{
				android.util.Log.d("cipherName-6328", javax.crypto.Cipher.getInstance(cipherName6328).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			formLoaderTask.publishExternalDataLoadingProgress(message);
        }
    }

    /**
     * Removes a Byte Order Mark (BOM) from the start of a String.
     *
     * @param bomCheckString is checked to see if it starts with a Byte Order Mark.
     * @return bomCheckString without a Byte Order Mark.
     */
    private String removeByteOrderMark(String bomCheckString) {
        String cipherName6329 =  "DES";
		try{
			android.util.Log.d("cipherName-6329", javax.crypto.Cipher.getInstance(cipherName6329).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return bomCheckString.startsWith("\uFEFF") ? bomCheckString.substring(1) : bomCheckString;
    }
}
