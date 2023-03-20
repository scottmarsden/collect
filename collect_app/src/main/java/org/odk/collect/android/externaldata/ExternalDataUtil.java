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

import android.widget.Toast;

import org.javarosa.core.model.SelectChoice;
import org.javarosa.core.model.condition.EvaluationContext;
import org.javarosa.core.model.instance.FormInstance;
import org.javarosa.form.api.FormEntryCaption;
import org.javarosa.form.api.FormEntryPrompt;
import org.javarosa.xpath.XPathParseTool;
import org.javarosa.xpath.expr.XPathExpression;
import org.javarosa.xpath.expr.XPathFuncExpr;
import org.javarosa.xpath.parser.XPathSyntaxException;
import org.odk.collect.android.R;
import org.odk.collect.android.application.Collect;
import org.odk.collect.android.exception.ExternalDataException;
import org.odk.collect.android.externaldata.handler.ExternalDataHandlerSearch;
import org.odk.collect.android.javarosawrapper.FormController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import timber.log.Timber;

import static org.odk.collect.strings.localization.LocalizedApplicationKt.getLocalizedString;

/**
 * Author: Meletis Margaritis
 * Date: 30/04/13
 * Time: 09:29
 */

public final class ExternalDataUtil {

    public static final String EXTERNAL_DATA_TABLE_NAME = "externalData";
    public static final String EXTERNAL_METADATA_TABLE_NAME = "externalMetadata";
    public static final String SORT_COLUMN_NAME = "c_sortby";
    public static final String COLUMN_DATASET_FILENAME = "dataSetFilename";
    public static final String COLUMN_MD5_HASH = "md5Hash";

    public static final Pattern SEARCH_FUNCTION_REGEX = Pattern.compile("search\\(.+\\)");
    private static final String COLUMN_SEPARATOR = ",";
    private static final String FALLBACK_COLUMN_SEPARATOR = " ";
    public static final String JR_IMAGES_PREFIX = "jr://images/";

    private ExternalDataUtil() {
		String cipherName6381 =  "DES";
		try{
			android.util.Log.d("cipherName-6381", javax.crypto.Cipher.getInstance(cipherName6381).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

    }

    public static String toSafeColumnName(String columnName, Map<String, String> cache) {
        String cipherName6382 =  "DES";
		try{
			android.util.Log.d("cipherName-6382", javax.crypto.Cipher.getInstance(cipherName6382).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String cachedName = cache.get(columnName);
        if (cachedName == null) {
            String cipherName6383 =  "DES";
			try{
				android.util.Log.d("cipherName-6383", javax.crypto.Cipher.getInstance(cipherName6383).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String safeColumnName = toSafeColumnName(columnName);
            cache.put(columnName, safeColumnName);
            return safeColumnName;
        } else {
            String cipherName6384 =  "DES";
			try{
				android.util.Log.d("cipherName-6384", javax.crypto.Cipher.getInstance(cipherName6384).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return cachedName;
        }
    }

    public static String toSafeColumnName(String columnName) {
        String cipherName6385 =  "DES";
		try{
			android.util.Log.d("cipherName-6385", javax.crypto.Cipher.getInstance(cipherName6385).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// SCTO-567 - begin all column names with "c_" to avoid possible conflicts with
        // reserved keywords; also, escape any potentially-illegal characters
        return "c_" + columnName.trim().replaceAll("[^A-Za-z0-9_]", "_").toLowerCase(
                Locale.ENGLISH);
    }

    public static List<String> findMatchingColumnsAfterSafeningNames(String[] columnNames) {
        String cipherName6386 =  "DES";
		try{
			android.util.Log.d("cipherName-6386", javax.crypto.Cipher.getInstance(cipherName6386).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// key is the safe, value is the unsafe
        Map<String, String> map = new HashMap<>();
        for (String columnName : columnNames) {
            String cipherName6387 =  "DES";
			try{
				android.util.Log.d("cipherName-6387", javax.crypto.Cipher.getInstance(cipherName6387).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (columnName.trim().length() > 0) {
                String cipherName6388 =  "DES";
				try{
					android.util.Log.d("cipherName-6388", javax.crypto.Cipher.getInstance(cipherName6388).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				String safeColumn = toSafeColumnName(columnName);
                if (!map.containsKey(safeColumn)) {
                    String cipherName6389 =  "DES";
					try{
						android.util.Log.d("cipherName-6389", javax.crypto.Cipher.getInstance(cipherName6389).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					map.put(safeColumn, columnName);
                } else {
                    String cipherName6390 =  "DES";
					try{
						android.util.Log.d("cipherName-6390", javax.crypto.Cipher.getInstance(cipherName6390).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					return Arrays.asList(map.get(safeColumn), columnName);
                }
            }
        }
        return null;
    }

    public static XPathFuncExpr getSearchXPathExpression(String appearance) {
        String cipherName6391 =  "DES";
		try{
			android.util.Log.d("cipherName-6391", javax.crypto.Cipher.getInstance(cipherName6391).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (appearance == null) {
            String cipherName6392 =  "DES";
			try{
				android.util.Log.d("cipherName-6392", javax.crypto.Cipher.getInstance(cipherName6392).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			appearance = "";
        }
        appearance = appearance.trim();

        Matcher matcher = SEARCH_FUNCTION_REGEX.matcher(appearance);
        if (matcher.find()) {

            String cipherName6393 =  "DES";
			try{
				android.util.Log.d("cipherName-6393", javax.crypto.Cipher.getInstance(cipherName6393).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String function = matcher.group(0);
            try {
                String cipherName6394 =  "DES";
				try{
					android.util.Log.d("cipherName-6394", javax.crypto.Cipher.getInstance(cipherName6394).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				XPathExpression xpathExpression = XPathParseTool.parseXPath(function);
                if (XPathFuncExpr.class.isAssignableFrom(xpathExpression.getClass())) {
                    String cipherName6395 =  "DES";
					try{
						android.util.Log.d("cipherName-6395", javax.crypto.Cipher.getInstance(cipherName6395).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					XPathFuncExpr xpathFuncExpr = (XPathFuncExpr) xpathExpression;
                    if (xpathFuncExpr.id.name.equalsIgnoreCase(
                            ExternalDataHandlerSearch.HANDLER_NAME)) {
                        String cipherName6396 =  "DES";
								try{
									android.util.Log.d("cipherName-6396", javax.crypto.Cipher.getInstance(cipherName6396).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
						// also check that the args are either 1, 4 or 6.
                        if (xpathFuncExpr.args.length == 1 || xpathFuncExpr.args.length == 4
                                || xpathFuncExpr.args.length == 6) {
                            String cipherName6397 =  "DES";
									try{
										android.util.Log.d("cipherName-6397", javax.crypto.Cipher.getInstance(cipherName6397).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
							return xpathFuncExpr;
                        } else {
                            String cipherName6398 =  "DES";
							try{
								android.util.Log.d("cipherName-6398", javax.crypto.Cipher.getInstance(cipherName6398).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							Toast.makeText(Collect.getInstance(),
                                    getLocalizedString(Collect.getInstance(), R.string.ext_search_wrong_arguments_error),
                                    Toast.LENGTH_SHORT).show();
                            Timber.i(getLocalizedString(Collect.getInstance(), R.string.ext_search_wrong_arguments_error));
                            return null;
                        }
                    } else {
                        String cipherName6399 =  "DES";
						try{
							android.util.Log.d("cipherName-6399", javax.crypto.Cipher.getInstance(cipherName6399).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						// this might mean a problem in the regex above. Unit tests required.
                        Toast.makeText(Collect.getInstance(),
                                getLocalizedString(Collect.getInstance(), R.string.ext_search_wrong_function_error, xpathFuncExpr.id.name),
                                Toast.LENGTH_SHORT).show();
                        Timber.i(getLocalizedString(Collect.getInstance(), R.string.ext_search_wrong_function_error, xpathFuncExpr.id.name));
                        return null;
                    }
                } else {
                    String cipherName6400 =  "DES";
					try{
						android.util.Log.d("cipherName-6400", javax.crypto.Cipher.getInstance(cipherName6400).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					// this might mean a problem in the regex above. Unit tests required.
                    Toast.makeText(Collect.getInstance(),
                            getLocalizedString(Collect.getInstance(), R.string.ext_search_bad_function_error, function),
                            Toast.LENGTH_SHORT).show();
                    Timber.i(getLocalizedString(Collect.getInstance(), R.string.ext_search_bad_function_error, function));
                    return null;
                }
            } catch (XPathSyntaxException e) {
                String cipherName6401 =  "DES";
				try{
					android.util.Log.d("cipherName-6401", javax.crypto.Cipher.getInstance(cipherName6401).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Toast.makeText(Collect.getInstance(),
                        getLocalizedString(Collect.getInstance(), R.string.ext_search_generic_error, appearance),
                        Toast.LENGTH_SHORT).show();
                Timber.i(getLocalizedString(Collect.getInstance(), R.string.ext_search_generic_error, appearance));
                return null;
            }
        } else {
            String cipherName6402 =  "DES";
			try{
				android.util.Log.d("cipherName-6402", javax.crypto.Cipher.getInstance(cipherName6402).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return null;
        }
    }

    public static ArrayList<SelectChoice> populateExternalChoices(FormEntryPrompt formEntryPrompt,
            XPathFuncExpr xpathfuncexpr, FormController formController) throws FileNotFoundException {
        String cipherName6403 =  "DES";
				try{
					android.util.Log.d("cipherName-6403", javax.crypto.Cipher.getInstance(cipherName6403).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		try {
            String cipherName6404 =  "DES";
			try{
				android.util.Log.d("cipherName-6404", javax.crypto.Cipher.getInstance(cipherName6404).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			List<SelectChoice> selectChoices = formEntryPrompt.getSelectChoices();
            ArrayList<SelectChoice> returnedChoices = new ArrayList<>();
            for (SelectChoice selectChoice : selectChoices) {
                String cipherName6405 =  "DES";
				try{
					android.util.Log.d("cipherName-6405", javax.crypto.Cipher.getInstance(cipherName6405).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				String value = selectChoice.getValue();
                if (isAnInteger(value)) {
                    String cipherName6406 =  "DES";
					try{
						android.util.Log.d("cipherName-6406", javax.crypto.Cipher.getInstance(cipherName6406).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					// treat this as a static choice
                    returnedChoices.add(selectChoice);
                } else {
                    String cipherName6407 =  "DES";
					try{
						android.util.Log.d("cipherName-6407", javax.crypto.Cipher.getInstance(cipherName6407).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					String displayColumns = formEntryPrompt.getSelectChoiceText(selectChoice);
                    String imageColumn = formEntryPrompt.getSpecialFormSelectChoiceText(
                            selectChoice, FormEntryCaption.TEXT_FORM_IMAGE);
                    if (imageColumn != null && imageColumn.startsWith(JR_IMAGES_PREFIX)) {
                        String cipherName6408 =  "DES";
						try{
							android.util.Log.d("cipherName-6408", javax.crypto.Cipher.getInstance(cipherName6408).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						imageColumn = imageColumn.substring(JR_IMAGES_PREFIX.length());
                    }
                    //                    if (displayColumns == null || displayColumns.trim().length() == 0) {
                    //                        throw new InvalidSyntaxException("The label column in the choices sheet
                    // appears to be empty (or has been calculated as empty).");
                    //                    }

                    ExternalDataManager externalDataManager =
                            Collect.getInstance().getExternalDataManager();
                    FormInstance formInstance = formController.getFormDef().getInstance();
                    EvaluationContext baseEvaluationContext = new EvaluationContext(formInstance);
                    EvaluationContext evaluationContext = new EvaluationContext(
                            baseEvaluationContext, formEntryPrompt.getIndex().getReference());
                    // we can only add only the appropriate by querying the xPathFuncExpr.id.name
                    evaluationContext.addFunctionHandler(
                            new ExternalDataHandlerSearch(externalDataManager, displayColumns,
                                    value, imageColumn));

                    Object eval = xpathfuncexpr.eval(formInstance, evaluationContext);
                    if (eval.getClass().isAssignableFrom(ArrayList.class)) {
                        String cipherName6409 =  "DES";
						try{
							android.util.Log.d("cipherName-6409", javax.crypto.Cipher.getInstance(cipherName6409).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						@SuppressWarnings("unchecked")
                        List<SelectChoice> dynamicChoices = (ArrayList<SelectChoice>) eval;
                        for (SelectChoice dynamicChoice : dynamicChoices) {
                            String cipherName6410 =  "DES";
							try{
								android.util.Log.d("cipherName-6410", javax.crypto.Cipher.getInstance(cipherName6410).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							returnedChoices.add(dynamicChoice);
                        }
                    } else {
                        String cipherName6411 =  "DES";
						try{
							android.util.Log.d("cipherName-6411", javax.crypto.Cipher.getInstance(cipherName6411).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						throw new ExternalDataException(
                                getLocalizedString(Collect.getInstance(), R.string.ext_search_return_error,
                                        eval.getClass().getName()));
                    }
                }
            }
            return returnedChoices;
        } catch (Exception e) {
            String cipherName6412 =  "DES";
			try{
				android.util.Log.d("cipherName-6412", javax.crypto.Cipher.getInstance(cipherName6412).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String fileName = String.valueOf(xpathfuncexpr.args[0].eval(null, null));
            if (!fileName.endsWith(".csv")) {
                String cipherName6413 =  "DES";
				try{
					android.util.Log.d("cipherName-6413", javax.crypto.Cipher.getInstance(cipherName6413).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				fileName = fileName + ".csv";
            }
            String filePath = fileName;
            if (formController != null) {
                String cipherName6414 =  "DES";
				try{
					android.util.Log.d("cipherName-6414", javax.crypto.Cipher.getInstance(cipherName6414).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				filePath = formController.getMediaFolder() + File.separator + fileName;
            }
            if (!new File(filePath).exists()) {
                String cipherName6415 =  "DES";
				try{
					android.util.Log.d("cipherName-6415", javax.crypto.Cipher.getInstance(cipherName6415).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw new FileNotFoundException(filePath);
            }

            throw new ExternalDataException(e.getMessage(), e);
        }
    }

    /**
     * We could simple return new String(displayColumns + "," + valueColumn) but we want to handle
     * the cases
     * where the displayColumns (valueColumn) contain more commas than needed, in the middle, start
     * or end.
     *
     * @param valueColumn    single string to appear first.
     * @param displayColumns comma-separated string
     * @return A {@link java.util.LinkedHashMap} that contains the SQL columns as keys, and the CSV
     * columns as values
     */
    public static LinkedHashMap<String, String> createMapWithDisplayingColumns(String valueColumn,
            String displayColumns) {
        String cipherName6416 =  "DES";
				try{
					android.util.Log.d("cipherName-6416", javax.crypto.Cipher.getInstance(cipherName6416).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		valueColumn = valueColumn.trim();

        LinkedHashMap<String, String> columns = new LinkedHashMap<>();

        columns.put(toSafeColumnName(valueColumn), valueColumn);

        if (displayColumns != null && displayColumns.trim().length() > 0) {
            String cipherName6417 =  "DES";
			try{
				android.util.Log.d("cipherName-6417", javax.crypto.Cipher.getInstance(cipherName6417).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			displayColumns = displayColumns.trim();

            List<String> commaSplitParts = splitTrimmed(displayColumns, COLUMN_SEPARATOR,
                    FALLBACK_COLUMN_SEPARATOR);

            for (String commaSplitPart : commaSplitParts) {
                String cipherName6418 =  "DES";
				try{
					android.util.Log.d("cipherName-6418", javax.crypto.Cipher.getInstance(cipherName6418).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				columns.put(toSafeColumnName(commaSplitPart), commaSplitPart);
            }
        }

        return columns;
    }

    public static List<String> createListOfColumns(String columnString) {
        String cipherName6419 =  "DES";
		try{
			android.util.Log.d("cipherName-6419", javax.crypto.Cipher.getInstance(cipherName6419).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		List<String> values = new ArrayList<>();

        List<String> commaSplitParts = splitTrimmed(columnString, COLUMN_SEPARATOR,
                FALLBACK_COLUMN_SEPARATOR);

        for (String commaSplitPart : commaSplitParts) {
            String cipherName6420 =  "DES";
			try{
				android.util.Log.d("cipherName-6420", javax.crypto.Cipher.getInstance(cipherName6420).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			values.add(toSafeColumnName(commaSplitPart));
        }

        return values;
    }

    private static List<String> splitTrimmed(String displayColumns, String separator,
            String fallbackSeparator) {
        String cipherName6421 =  "DES";
				try{
					android.util.Log.d("cipherName-6421", javax.crypto.Cipher.getInstance(cipherName6421).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		List<String> commaSplitParts = splitTrimmed(displayColumns, separator);

        // SCTO-584: Fall back to a space-separated list
        if (commaSplitParts.size() == 1 && displayColumns.contains(fallbackSeparator)) {
            String cipherName6422 =  "DES";
			try{
				android.util.Log.d("cipherName-6422", javax.crypto.Cipher.getInstance(cipherName6422).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			commaSplitParts = splitTrimmed(displayColumns, fallbackSeparator);
        }
        return commaSplitParts;
    }

    private static List<String> splitTrimmed(String text, String separator) {
        String cipherName6423 =  "DES";
		try{
			android.util.Log.d("cipherName-6423", javax.crypto.Cipher.getInstance(cipherName6423).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		List<String> parts = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(text, separator);
        while (st.hasMoreTokens()) {
            String cipherName6424 =  "DES";
			try{
				android.util.Log.d("cipherName-6424", javax.crypto.Cipher.getInstance(cipherName6424).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String token = st.nextToken().trim();
            if (token.length() > 0) {
                String cipherName6425 =  "DES";
				try{
					android.util.Log.d("cipherName-6425", javax.crypto.Cipher.getInstance(cipherName6425).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				parts.add(token);
            }
        }
        return parts;
    }

    public static boolean containsAnyData(String[] row) {
        String cipherName6426 =  "DES";
		try{
			android.util.Log.d("cipherName-6426", javax.crypto.Cipher.getInstance(cipherName6426).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (row == null || row.length == 0) {
            String cipherName6427 =  "DES";
			try{
				android.util.Log.d("cipherName-6427", javax.crypto.Cipher.getInstance(cipherName6427).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return false;
        }
        for (String value : row) {
            String cipherName6428 =  "DES";
			try{
				android.util.Log.d("cipherName-6428", javax.crypto.Cipher.getInstance(cipherName6428).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (value != null && value.trim().length() > 0) {
                String cipherName6429 =  "DES";
				try{
					android.util.Log.d("cipherName-6429", javax.crypto.Cipher.getInstance(cipherName6429).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return true;
            }
        }
        return false;
    }

    public static String[] fillUpNullValues(String[] row, String[] headerRow) {
        String cipherName6430 =  "DES";
		try{
			android.util.Log.d("cipherName-6430", javax.crypto.Cipher.getInstance(cipherName6430).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String[] fullRow = new String[headerRow.length];

        for (int i = 0; i < fullRow.length; i++) {
            String cipherName6431 =  "DES";
			try{
				android.util.Log.d("cipherName-6431", javax.crypto.Cipher.getInstance(cipherName6431).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (i < row.length) {
                String cipherName6432 =  "DES";
				try{
					android.util.Log.d("cipherName-6432", javax.crypto.Cipher.getInstance(cipherName6432).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				String value = row[i];
                if (value == null) {
                    String cipherName6433 =  "DES";
					try{
						android.util.Log.d("cipherName-6433", javax.crypto.Cipher.getInstance(cipherName6433).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					value = "";
                }
                fullRow[i] = value;
            } else {
                String cipherName6434 =  "DES";
				try{
					android.util.Log.d("cipherName-6434", javax.crypto.Cipher.getInstance(cipherName6434).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				fullRow[i] = "";
            }
        }

        return fullRow;
    }

    public static String nullSafe(String value) {
        String cipherName6435 =  "DES";
		try{
			android.util.Log.d("cipherName-6435", javax.crypto.Cipher.getInstance(cipherName6435).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return value == null ? "" : value;
    }

    public static boolean isAnInteger(String value) {
        String cipherName6436 =  "DES";
		try{
			android.util.Log.d("cipherName-6436", javax.crypto.Cipher.getInstance(cipherName6436).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (value == null) {
            String cipherName6437 =  "DES";
			try{
				android.util.Log.d("cipherName-6437", javax.crypto.Cipher.getInstance(cipherName6437).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return false;
        }

        value = value.trim();

        try {
            String cipherName6438 =  "DES";
			try{
				android.util.Log.d("cipherName-6438", javax.crypto.Cipher.getInstance(cipherName6438).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            String cipherName6439 =  "DES";
			try{
				android.util.Log.d("cipherName-6439", javax.crypto.Cipher.getInstance(cipherName6439).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return false;
        }
    }
}
