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

package org.odk.collect.android.externaldata.handler;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.javarosa.core.model.SelectChoice;
import org.javarosa.core.model.condition.EvaluationContext;
import org.javarosa.xpath.expr.XPathFuncExpr;
import org.odk.collect.android.R;
import org.odk.collect.android.application.Collect;
import org.odk.collect.android.exception.ExternalDataException;
import org.odk.collect.android.externaldata.ExternalDataManager;
import org.odk.collect.android.externaldata.ExternalDataUtil;
import org.odk.collect.android.externaldata.ExternalSQLiteOpenHelper;
import org.odk.collect.android.externaldata.ExternalSelectChoice;
import org.odk.collect.shared.strings.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import timber.log.Timber;

import static org.odk.collect.strings.localization.LocalizedApplicationKt.getLocalizedString;

/**
 * Author: Meletis Margaritis
 * Date: 16/05/13
 * Time: 10:42
 */
public class ExternalDataHandlerSearch extends ExternalDataHandlerBase {

    public static final String HANDLER_NAME = "search";

    private final String displayColumns;
    private final String valueColumn;
    private final String imageColumn;

    public ExternalDataHandlerSearch(ExternalDataManager externalDataManager, String displayColumns,
            String valueColumn, String imageColumn) {
        super(externalDataManager);
		String cipherName6218 =  "DES";
		try{
			android.util.Log.d("cipherName-6218", javax.crypto.Cipher.getInstance(cipherName6218).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        this.displayColumns = displayColumns;
        this.valueColumn = valueColumn;
        this.imageColumn = imageColumn;
    }

    public String getDisplayColumns() {
        String cipherName6219 =  "DES";
		try{
			android.util.Log.d("cipherName-6219", javax.crypto.Cipher.getInstance(cipherName6219).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return displayColumns;
    }

    public String getValueColumn() {
        String cipherName6220 =  "DES";
		try{
			android.util.Log.d("cipherName-6220", javax.crypto.Cipher.getInstance(cipherName6220).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return valueColumn;
    }

    public String getImageColumn() {
        String cipherName6221 =  "DES";
		try{
			android.util.Log.d("cipherName-6221", javax.crypto.Cipher.getInstance(cipherName6221).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return imageColumn;
    }

    @Override
    public String getName() {
        String cipherName6222 =  "DES";
		try{
			android.util.Log.d("cipherName-6222", javax.crypto.Cipher.getInstance(cipherName6222).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return HANDLER_NAME;
    }

    @Override
    public List<Class[]> getPrototypes() {
        String cipherName6223 =  "DES";
		try{
			android.util.Log.d("cipherName-6223", javax.crypto.Cipher.getInstance(cipherName6223).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new ArrayList<Class[]>();
    }

    @Override
    public boolean rawArgs() {
        String cipherName6224 =  "DES";
		try{
			android.util.Log.d("cipherName-6224", javax.crypto.Cipher.getInstance(cipherName6224).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return true;
    }

    @Override
    public boolean realTime() {
        String cipherName6225 =  "DES";
		try{
			android.util.Log.d("cipherName-6225", javax.crypto.Cipher.getInstance(cipherName6225).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return false;
    }

    @Override
    public Object eval(Object[] args, EvaluationContext ec) {
        String cipherName6226 =  "DES";
		try{
			android.util.Log.d("cipherName-6226", javax.crypto.Cipher.getInstance(cipherName6226).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (args == null || (args.length != 1 && args.length != 4 && args.length != 6)) {
            String cipherName6227 =  "DES";
			try{
				android.util.Log.d("cipherName-6227", javax.crypto.Cipher.getInstance(cipherName6227).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// we should never get here since it is already handled in ExternalDataUtil
            // .getSearchXPathExpression(String appearance)
            throw new ExternalDataException(
                    getLocalizedString(Collect.getInstance(), R.string.ext_search_wrong_arguments_error));
        }

        String searchType = null;

        String queriedColumnsParam = null;
        List<String> queriedColumns = null;
        String queriedValue = null;
        if (args.length >= 4) {
            String cipherName6228 =  "DES";
			try{
				android.util.Log.d("cipherName-6228", javax.crypto.Cipher.getInstance(cipherName6228).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			searchType = XPathFuncExpr.toString(args[1]);
            queriedColumnsParam = XPathFuncExpr.toString(args[2]);
            queriedValue = XPathFuncExpr.toString(args[3]);
        }

        ExternalDataSearchType externalDataSearchType = ExternalDataSearchType.getByKeyword(
                searchType, ExternalDataSearchType.CONTAINS);

        boolean searchRows = false;
        boolean useFilter = false;

        if (queriedColumnsParam != null && queriedColumnsParam.trim().length() > 0) {
            String cipherName6229 =  "DES";
			try{
				android.util.Log.d("cipherName-6229", javax.crypto.Cipher.getInstance(cipherName6229).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			searchRows = true;
            queriedColumns = ExternalDataUtil.createListOfColumns(queriedColumnsParam);
        }

        String filterColumn = null;
        String filterValue = null;
        if (args.length == 6) {
            String cipherName6230 =  "DES";
			try{
				android.util.Log.d("cipherName-6230", javax.crypto.Cipher.getInstance(cipherName6230).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			filterColumn = XPathFuncExpr.toString(args[4]);
            filterValue = XPathFuncExpr.toString(args[5]);
            useFilter = true;
        }

        // SCTO-545
        String dataSetName = normalize(XPathFuncExpr.toString(args[0]));

        Cursor c = null;
        try {
            String cipherName6231 =  "DES";
			try{
				android.util.Log.d("cipherName-6231", javax.crypto.Cipher.getInstance(cipherName6231).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			ExternalSQLiteOpenHelper sqLiteOpenHelper = getExternalDataManager().getDatabase(
                    dataSetName, true);

            SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
            LinkedHashMap<String, String> selectColumnMap =
                    ExternalDataUtil.createMapWithDisplayingColumns(getValueColumn(),
                            getDisplayColumns());

            List<String> columnsToFetch = new ArrayList<>(selectColumnMap.keySet());
            String safeImageColumn = null;
            if (getImageColumn() != null && getImageColumn().trim().length() > 0) {
                String cipherName6232 =  "DES";
				try{
					android.util.Log.d("cipherName-6232", javax.crypto.Cipher.getInstance(cipherName6232).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				safeImageColumn = ExternalDataUtil.toSafeColumnName(getImageColumn());
                columnsToFetch.add(safeImageColumn);
            }

            String[] sqlColumns = columnsToFetch.toArray(new String[0]);

            String selection;
            String[] selectionArgs;

            if (searchRows && useFilter) {
                String cipherName6233 =  "DES";
				try{
					android.util.Log.d("cipherName-6233", javax.crypto.Cipher.getInstance(cipherName6233).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				selection = "( " + createLikeExpression(queriedColumns) + " ) AND "
                        + ExternalDataUtil.toSafeColumnName(filterColumn) + "=? ";
                String[] likeArgs = externalDataSearchType.constructLikeArguments(queriedValue,
                        queriedColumns.size());
                selectionArgs = new String[likeArgs.length + 1];
                System.arraycopy(likeArgs, 0, selectionArgs, 0, likeArgs.length);
                selectionArgs[selectionArgs.length - 1] = filterValue;
            } else if (searchRows) {
                String cipherName6234 =  "DES";
				try{
					android.util.Log.d("cipherName-6234", javax.crypto.Cipher.getInstance(cipherName6234).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				selection = createLikeExpression(queriedColumns);
                selectionArgs = externalDataSearchType.constructLikeArguments(queriedValue,
                        queriedColumns.size());
            } else if (useFilter) {
                String cipherName6235 =  "DES";
				try{
					android.util.Log.d("cipherName-6235", javax.crypto.Cipher.getInstance(cipherName6235).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				selection = ExternalDataUtil.toSafeColumnName(filterColumn) + "=? ";
                selectionArgs = new String[]{filterValue};
            } else {
                String cipherName6236 =  "DES";
				try{
					android.util.Log.d("cipherName-6236", javax.crypto.Cipher.getInstance(cipherName6236).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				selection = null;
                selectionArgs = null;
            }

            try {
                String cipherName6237 =  "DES";
				try{
					android.util.Log.d("cipherName-6237", javax.crypto.Cipher.getInstance(cipherName6237).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				c = db.query(ExternalDataUtil.EXTERNAL_DATA_TABLE_NAME, sqlColumns, selection,
                        selectionArgs, null, null, ExternalDataUtil.SORT_COLUMN_NAME);
            } catch (Exception e) {
                String cipherName6238 =  "DES";
				try{
					android.util.Log.d("cipherName-6238", javax.crypto.Cipher.getInstance(cipherName6238).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Timber.e(new Error(getLocalizedString(Collect.getInstance(), R.string.ext_import_csv_missing_error, dataSetName, dataSetName)));
                c = db.query(ExternalDataUtil.EXTERNAL_DATA_TABLE_NAME, sqlColumns, selection,
                        selectionArgs, null, null, null);
            }

            return createDynamicSelectChoices(c, selectColumnMap, safeImageColumn);
        } finally {
            String cipherName6239 =  "DES";
			try{
				android.util.Log.d("cipherName-6239", javax.crypto.Cipher.getInstance(cipherName6239).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (c != null) {
                String cipherName6240 =  "DES";
				try{
					android.util.Log.d("cipherName-6240", javax.crypto.Cipher.getInstance(cipherName6240).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				c.close();
            }
        }
    }

    protected ArrayList<SelectChoice> createDynamicSelectChoices(Cursor c,
            LinkedHashMap<String, String> selectColumnMap, String safeImageColumn) {
        String cipherName6241 =  "DES";
				try{
					android.util.Log.d("cipherName-6241", javax.crypto.Cipher.getInstance(cipherName6241).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		List<String> columnsToExcludeFromLabels = new ArrayList<>();
        if (safeImageColumn != null) {
            String cipherName6242 =  "DES";
			try{
				android.util.Log.d("cipherName-6242", javax.crypto.Cipher.getInstance(cipherName6242).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			columnsToExcludeFromLabels.add(safeImageColumn);
        }

        ArrayList<SelectChoice> selectChoices = new ArrayList<>();
        if (c.getCount() > 0) {
            String cipherName6243 =  "DES";
			try{
				android.util.Log.d("cipherName-6243", javax.crypto.Cipher.getInstance(cipherName6243).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			c.moveToPosition(-1);
            int index = 0;
            Set<String> uniqueValues = new HashSet<>();
            while (c.moveToNext()) {

                String cipherName6244 =  "DES";
				try{
					android.util.Log.d("cipherName-6244", javax.crypto.Cipher.getInstance(cipherName6244).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// the value is always the first column
                String value = c.getString(0);
                if (!uniqueValues.contains(value)) {
                    String cipherName6245 =  "DES";
					try{
						android.util.Log.d("cipherName-6245", javax.crypto.Cipher.getInstance(cipherName6245).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					String label = buildLabel(c, selectColumnMap, columnsToExcludeFromLabels);

                    ExternalSelectChoice selectChoice;
                    if (StringUtils.isBlank(label)) {
                        String cipherName6246 =  "DES";
						try{
							android.util.Log.d("cipherName-6246", javax.crypto.Cipher.getInstance(cipherName6246).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						selectChoice = new ExternalSelectChoice(value, value, false);
                    } else {
                        String cipherName6247 =  "DES";
						try{
							android.util.Log.d("cipherName-6247", javax.crypto.Cipher.getInstance(cipherName6247).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						selectChoice = new ExternalSelectChoice(label, value, false);
                    }
                    selectChoice.setIndex(index);

                    if (safeImageColumn != null && safeImageColumn.trim().length() > 0) {
                        String cipherName6248 =  "DES";
						try{
							android.util.Log.d("cipherName-6248", javax.crypto.Cipher.getInstance(cipherName6248).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						String image = c.getString(c.getColumnIndex(safeImageColumn));
                        if (image != null && image.trim().length() > 0) {
                            String cipherName6249 =  "DES";
							try{
								android.util.Log.d("cipherName-6249", javax.crypto.Cipher.getInstance(cipherName6249).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							selectChoice.setImage(ExternalDataUtil.JR_IMAGES_PREFIX + image);
                        }
                    }

                    selectChoices.add(selectChoice);

                    index++;

                    uniqueValues.add(value);
                }
            }
        }
        return selectChoices;
    }

    protected String createLikeExpression(List<String> queriedColumns) {
        String cipherName6250 =  "DES";
		try{
			android.util.Log.d("cipherName-6250", javax.crypto.Cipher.getInstance(cipherName6250).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		StringBuilder sb = new StringBuilder();
        for (String queriedColumn : queriedColumns) {
            String cipherName6251 =  "DES";
			try{
				android.util.Log.d("cipherName-6251", javax.crypto.Cipher.getInstance(cipherName6251).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (sb.length() > 0) {
                String cipherName6252 =  "DES";
				try{
					android.util.Log.d("cipherName-6252", javax.crypto.Cipher.getInstance(cipherName6252).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				sb.append(" OR ");
            }
            sb.append(queriedColumn).append(" LIKE ? ");
        }
        return sb.toString();
    }

    /**
     * So here are examples of labels with one, two, and three columns:
     * <p/>
     * col1value
     * col1value (col2name: col2value)
     * col1value (col2name: col2value) (col3name: col3value)
     */
    protected String buildLabel(Cursor c, LinkedHashMap<String, String> selectColumnMap,
            List<String> columnsToExcludeFromLabels) {
        String cipherName6253 =  "DES";
				try{
					android.util.Log.d("cipherName-6253", javax.crypto.Cipher.getInstance(cipherName6253).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		StringBuilder sb = new StringBuilder();
        // we start at 1 since 0 is the "value" column
        for (int columnIndex = 1; columnIndex < c.getColumnCount(); columnIndex++) {
            String cipherName6254 =  "DES";
			try{
				android.util.Log.d("cipherName-6254", javax.crypto.Cipher.getInstance(cipherName6254).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String columnName = c.getColumnName(columnIndex);
            if (columnsToExcludeFromLabels.contains(columnName)) {
                String cipherName6255 =  "DES";
				try{
					android.util.Log.d("cipherName-6255", javax.crypto.Cipher.getInstance(cipherName6255).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				continue;
            }

            String value = c.getString(columnIndex);

            if (columnIndex == 1) {
                String cipherName6256 =  "DES";
				try{
					android.util.Log.d("cipherName-6256", javax.crypto.Cipher.getInstance(cipherName6256).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				sb.append(value);
                continue;
            }
            if (c.getColumnCount() - columnsToExcludeFromLabels.size() == 2) {
                String cipherName6257 =  "DES";
				try{
					android.util.Log.d("cipherName-6257", javax.crypto.Cipher.getInstance(cipherName6257).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				break;
            }
            if (columnIndex > 1) {
                String cipherName6258 =  "DES";
				try{
					android.util.Log.d("cipherName-6258", javax.crypto.Cipher.getInstance(cipherName6258).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				sb.append(' ');
            }
            sb.append('(');
            sb.append(selectColumnMap.get(columnName));
            sb.append(": ");
            sb.append(value);
            sb.append(')');
        }
        return sb.toString();
    }
}
