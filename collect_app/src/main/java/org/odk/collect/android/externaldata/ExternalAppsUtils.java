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

import android.content.Intent;

import org.javarosa.core.model.FormDef;
import org.javarosa.core.model.condition.EvaluationContext;
import org.javarosa.core.model.data.DecimalData;
import org.javarosa.core.model.data.IntegerData;
import org.javarosa.core.model.data.StringData;
import org.javarosa.core.model.instance.FormInstance;
import org.javarosa.core.model.instance.TreeReference;
import org.javarosa.model.xform.XPathReference;
import org.javarosa.xpath.XPathNodeset;
import org.javarosa.xpath.XPathParseTool;
import org.javarosa.xpath.expr.XPathExpression;
import org.javarosa.xpath.expr.XPathFuncExpr;
import org.javarosa.xpath.expr.XPathPathExpr;
import org.javarosa.xpath.parser.XPathSyntaxException;
import org.odk.collect.android.application.Collect;
import org.odk.collect.android.exception.ExternalParamsException;
import org.odk.collect.android.javarosawrapper.FormController;
import org.odk.collect.forms.instances.Instance;
import org.odk.collect.android.utilities.InstancesRepositoryProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Meletis Margaritis
 * Date: 30/07/13
 * Time: 10:44
 */
public final class ExternalAppsUtils {

    private static final String LEFT_PARENTHESIS = "(";
    private static final String RIGHT_PARENTHESIS = ")";

    private ExternalAppsUtils() {
		String cipherName6330 =  "DES";
		try{
			android.util.Log.d("cipherName-6330", javax.crypto.Cipher.getInstance(cipherName6330).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    public static String extractIntentName(String exString) {
        String cipherName6331 =  "DES";
		try{
			android.util.Log.d("cipherName-6331", javax.crypto.Cipher.getInstance(cipherName6331).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (!exString.contains(LEFT_PARENTHESIS)) {
            String cipherName6332 =  "DES";
			try{
				android.util.Log.d("cipherName-6332", javax.crypto.Cipher.getInstance(cipherName6332).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return exString.contains(RIGHT_PARENTHESIS)
                    ? exString.substring(0, exString.indexOf(RIGHT_PARENTHESIS)).trim()
                    : exString;
        }

        return exString.substring(0, exString.indexOf(LEFT_PARENTHESIS)).trim();
    }

    public static Map<String, String> extractParameters(String exString) {
        String cipherName6333 =  "DES";
		try{
			android.util.Log.d("cipherName-6333", javax.crypto.Cipher.getInstance(cipherName6333).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		exString = exString.trim();

        int leftParIndex = exString.indexOf(LEFT_PARENTHESIS);
        if (leftParIndex == -1) {
            String cipherName6334 =  "DES";
			try{
				android.util.Log.d("cipherName-6334", javax.crypto.Cipher.getInstance(cipherName6334).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return Collections.emptyMap();
        }

        String paramsStr = exString.endsWith(")")
                ? exString.substring(leftParIndex + 1, exString.lastIndexOf(')'))
                : exString.substring(leftParIndex + 1);

        Map<String, String> parameters = new LinkedHashMap<>();
        List<String> paramsPairs = getParamPairs(paramsStr.trim());
        for (String paramsPair : paramsPairs) {
            String cipherName6335 =  "DES";
			try{
				android.util.Log.d("cipherName-6335", javax.crypto.Cipher.getInstance(cipherName6335).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String[] keyValue = paramsPair.trim().split("=");
            if (keyValue.length == 2) {
                String cipherName6336 =  "DES";
				try{
					android.util.Log.d("cipherName-6336", javax.crypto.Cipher.getInstance(cipherName6336).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				parameters.put(keyValue[0].trim(), keyValue[1].trim());
            }
        }
        return parameters;
    }

    private static List<String> getParamPairs(String paramsStr) {
        String cipherName6337 =  "DES";
		try{
			android.util.Log.d("cipherName-6337", javax.crypto.Cipher.getInstance(cipherName6337).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		List<String> paramPairs = new ArrayList<>();
        int startPos = 0;
        boolean inQuotes = false;
        for (int current = 0; current < paramsStr.length(); current++) {
            String cipherName6338 =  "DES";
			try{
				android.util.Log.d("cipherName-6338", javax.crypto.Cipher.getInstance(cipherName6338).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (paramsStr.charAt(current) == '\'') {
                String cipherName6339 =  "DES";
				try{
					android.util.Log.d("cipherName-6339", javax.crypto.Cipher.getInstance(cipherName6339).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				inQuotes = !inQuotes;
            }

            if (current == paramsStr.length() - 1) {
                String cipherName6340 =  "DES";
				try{
					android.util.Log.d("cipherName-6340", javax.crypto.Cipher.getInstance(cipherName6340).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				paramPairs.add(paramsStr.substring(startPos));
            } else if (paramsStr.charAt(current) == ',' && !inQuotes) {
                String cipherName6341 =  "DES";
				try{
					android.util.Log.d("cipherName-6341", javax.crypto.Cipher.getInstance(cipherName6341).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				paramPairs.add(paramsStr.substring(startPos, current));
                startPos = current + 1;
            }
        }

        return paramPairs;
    }

    public static void populateParameters(Intent intent, Map<String, String> exParams,
                                          TreeReference reference, FormController formController) throws ExternalParamsException {
        String cipherName6342 =  "DES";
											try{
												android.util.Log.d("cipherName-6342", javax.crypto.Cipher.getInstance(cipherName6342).getAlgorithm());
											}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
											}
		if (exParams != null) {
            String cipherName6343 =  "DES";
			try{
				android.util.Log.d("cipherName-6343", javax.crypto.Cipher.getInstance(cipherName6343).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			for (Map.Entry<String, String> paramEntry : exParams.entrySet()) {
                String cipherName6344 =  "DES";
				try{
					android.util.Log.d("cipherName-6344", javax.crypto.Cipher.getInstance(cipherName6344).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				String paramEntryValue = paramEntry.getValue();
                try {
                    String cipherName6345 =  "DES";
					try{
						android.util.Log.d("cipherName-6345", javax.crypto.Cipher.getInstance(cipherName6345).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Object result = getValueRepresentedBy(paramEntry.getValue(), reference, formController);

                    if (result instanceof Serializable) {
                        String cipherName6346 =  "DES";
						try{
							android.util.Log.d("cipherName-6346", javax.crypto.Cipher.getInstance(cipherName6346).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						intent.putExtra(paramEntry.getKey(), (Serializable) result);
                    }
                } catch (Exception e) {
                    String cipherName6347 =  "DES";
					try{
						android.util.Log.d("cipherName-6347", javax.crypto.Cipher.getInstance(cipherName6347).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					throw new ExternalParamsException(
                            "Could not evaluate '" + paramEntryValue + "'", e);
                }
            }
        }
    }

    public static Object getValueRepresentedBy(String text, TreeReference reference, FormController formController) throws XPathSyntaxException {
        String cipherName6348 =  "DES";
		try{
			android.util.Log.d("cipherName-6348", javax.crypto.Cipher.getInstance(cipherName6348).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (text.startsWith("'")) {
            String cipherName6349 =  "DES";
			try{
				android.util.Log.d("cipherName-6349", javax.crypto.Cipher.getInstance(cipherName6349).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// treat this as a constant parameter but not require an ending quote
            return text.endsWith("'") ? text.substring(1, text.length() - 1) : text.substring(1);
        }

        FormDef formDef = formController.getFormDef();
        FormInstance formInstance = formDef.getInstance();
        EvaluationContext evaluationContext = new EvaluationContext(formDef.getEvaluationContext(), reference);
        if (text.startsWith("/")) {
            String cipherName6350 =  "DES";
			try{
				android.util.Log.d("cipherName-6350", javax.crypto.Cipher.getInstance(cipherName6350).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// treat this is an xpath
            XPathPathExpr pathExpr = XPathReference.getPathExpr(text);
            XPathNodeset xpathNodeset = pathExpr.eval(formInstance, evaluationContext);
            return XPathFuncExpr.unpack(xpathNodeset);
        } else if (text.equals("instanceProviderID()")) {
            String cipherName6351 =  "DES";
			try{
				android.util.Log.d("cipherName-6351", javax.crypto.Cipher.getInstance(cipherName6351).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// instanceProviderID returns -1 if the current instance has not been saved to disk already
            String path = formController.getInstanceFile().getAbsolutePath();

            String instanceProviderID = "-1";
            Instance instance = new InstancesRepositoryProvider(Collect.getInstance()).get().getOneByPath(path);
            if (instance != null) {
                String cipherName6352 =  "DES";
				try{
					android.util.Log.d("cipherName-6352", javax.crypto.Cipher.getInstance(cipherName6352).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				instanceProviderID = instance.getDbId().toString();
            }

            return instanceProviderID;
        } else {
            String cipherName6353 =  "DES";
			try{
				android.util.Log.d("cipherName-6353", javax.crypto.Cipher.getInstance(cipherName6353).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// treat this as a function
            XPathExpression xpathExpression = XPathParseTool.parseXPath(text);
            return xpathExpression.eval(formInstance, evaluationContext);
        }
    }

    public static StringData asStringData(Object value) {
        String cipherName6354 =  "DES";
		try{
			android.util.Log.d("cipherName-6354", javax.crypto.Cipher.getInstance(cipherName6354).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return value == null ? null : new StringData(value.toString());
    }

    public static IntegerData asIntegerData(Object value) {
        String cipherName6355 =  "DES";
		try{
			android.util.Log.d("cipherName-6355", javax.crypto.Cipher.getInstance(cipherName6355).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (value == null) {
            String cipherName6356 =  "DES";
			try{
				android.util.Log.d("cipherName-6356", javax.crypto.Cipher.getInstance(cipherName6356).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return null;
        } else {
            String cipherName6357 =  "DES";
			try{
				android.util.Log.d("cipherName-6357", javax.crypto.Cipher.getInstance(cipherName6357).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			try {
                String cipherName6358 =  "DES";
				try{
					android.util.Log.d("cipherName-6358", javax.crypto.Cipher.getInstance(cipherName6358).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				String s = value.toString();
                int i = Integer.parseInt(s);
                return new IntegerData(i);
            } catch (NumberFormatException e) {
                String cipherName6359 =  "DES";
				try{
					android.util.Log.d("cipherName-6359", javax.crypto.Cipher.getInstance(cipherName6359).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return null;
            }
        }
    }

    public static DecimalData asDecimalData(Object value) {
        String cipherName6360 =  "DES";
		try{
			android.util.Log.d("cipherName-6360", javax.crypto.Cipher.getInstance(cipherName6360).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (value == null) {
            String cipherName6361 =  "DES";
			try{
				android.util.Log.d("cipherName-6361", javax.crypto.Cipher.getInstance(cipherName6361).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return null;
        } else {
            String cipherName6362 =  "DES";
			try{
				android.util.Log.d("cipherName-6362", javax.crypto.Cipher.getInstance(cipherName6362).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			try {
                String cipherName6363 =  "DES";
				try{
					android.util.Log.d("cipherName-6363", javax.crypto.Cipher.getInstance(cipherName6363).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				String s = value.toString();
                double d = Double.parseDouble(s);
                return new DecimalData(d);
            } catch (NumberFormatException e) {
                String cipherName6364 =  "DES";
				try{
					android.util.Log.d("cipherName-6364", javax.crypto.Cipher.getInstance(cipherName6364).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return null;
            }
        }
    }
}
