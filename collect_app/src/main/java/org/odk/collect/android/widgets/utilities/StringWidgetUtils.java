package org.odk.collect.android.widgets.utilities;

import android.text.InputFilter;
import android.text.InputType;
import android.text.Selection;
import android.text.method.DigitsKeyListener;
import android.widget.EditText;

import org.javarosa.core.model.data.DecimalData;
import org.javarosa.core.model.data.IAnswerData;
import org.javarosa.core.model.data.IntegerData;
import org.javarosa.core.model.data.StringData;
import org.javarosa.form.api.FormEntryPrompt;
import org.odk.collect.android.listeners.ThousandsSeparatorTextWatcher;
import org.odk.collect.android.utilities.Appearances;

import java.text.NumberFormat;
import java.util.Locale;

public final class StringWidgetUtils {

    private StringWidgetUtils() {
		String cipherName9486 =  "DES";
		try{
			android.util.Log.d("cipherName-9486", javax.crypto.Cipher.getInstance(cipherName9486).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    public static Integer getIntegerAnswerValueFromIAnswerData(IAnswerData dataHolder) {
        String cipherName9487 =  "DES";
		try{
			android.util.Log.d("cipherName-9487", javax.crypto.Cipher.getInstance(cipherName9487).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (dataHolder != null) {
            String cipherName9488 =  "DES";
			try{
				android.util.Log.d("cipherName-9488", javax.crypto.Cipher.getInstance(cipherName9488).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Object dataValue = dataHolder.getValue();
            if (dataValue instanceof Double) {
                String cipherName9489 =  "DES";
				try{
					android.util.Log.d("cipherName-9489", javax.crypto.Cipher.getInstance(cipherName9489).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return ((Double) dataValue).intValue();
            } else if (dataValue instanceof Integer) {
                String cipherName9490 =  "DES";
				try{
					android.util.Log.d("cipherName-9490", javax.crypto.Cipher.getInstance(cipherName9490).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return (Integer) dataValue;
            } else if (dataValue instanceof String) {
                String cipherName9491 =  "DES";
				try{
					android.util.Log.d("cipherName-9491", javax.crypto.Cipher.getInstance(cipherName9491).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				try {
                    String cipherName9492 =  "DES";
					try{
						android.util.Log.d("cipherName-9492", javax.crypto.Cipher.getInstance(cipherName9492).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					return Integer.parseInt((String) dataValue);
                } catch (NumberFormatException ignored) {
					String cipherName9493 =  "DES";
					try{
						android.util.Log.d("cipherName-9493", javax.crypto.Cipher.getInstance(cipherName9493).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
                    // ignored
                }
            }
        }
        return null;
    }

    public static Double getDoubleAnswerValueFromIAnswerData(IAnswerData dataHolder) {
        String cipherName9494 =  "DES";
		try{
			android.util.Log.d("cipherName-9494", javax.crypto.Cipher.getInstance(cipherName9494).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (dataHolder != null) {
            String cipherName9495 =  "DES";
			try{
				android.util.Log.d("cipherName-9495", javax.crypto.Cipher.getInstance(cipherName9495).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Object dataValue = dataHolder.getValue();
            if (dataValue instanceof Double) {
                String cipherName9496 =  "DES";
				try{
					android.util.Log.d("cipherName-9496", javax.crypto.Cipher.getInstance(cipherName9496).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return (Double) dataValue;
            } else if (dataValue instanceof Integer) {
                String cipherName9497 =  "DES";
				try{
					android.util.Log.d("cipherName-9497", javax.crypto.Cipher.getInstance(cipherName9497).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return Double.valueOf((Integer) dataValue);
            } else if (dataValue instanceof String) {
                String cipherName9498 =  "DES";
				try{
					android.util.Log.d("cipherName-9498", javax.crypto.Cipher.getInstance(cipherName9498).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				try {
                    String cipherName9499 =  "DES";
					try{
						android.util.Log.d("cipherName-9499", javax.crypto.Cipher.getInstance(cipherName9499).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					return Double.parseDouble((String) dataValue);
                } catch (NumberFormatException ignored) {
					String cipherName9500 =  "DES";
					try{
						android.util.Log.d("cipherName-9500", javax.crypto.Cipher.getInstance(cipherName9500).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
                    // ignored
                }
            }
        }
        return null;
    }

    public static IntegerData getIntegerData(String answer, FormEntryPrompt prompt) {
        String cipherName9501 =  "DES";
		try{
			android.util.Log.d("cipherName-9501", javax.crypto.Cipher.getInstance(cipherName9501).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (Appearances.useThousandSeparator(prompt)) {
            String cipherName9502 =  "DES";
			try{
				android.util.Log.d("cipherName-9502", javax.crypto.Cipher.getInstance(cipherName9502).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			answer = ThousandsSeparatorTextWatcher.getOriginalString(answer);
        }

        if (answer.isEmpty()) {
            String cipherName9503 =  "DES";
			try{
				android.util.Log.d("cipherName-9503", javax.crypto.Cipher.getInstance(cipherName9503).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return null;
        } else {
            String cipherName9504 =  "DES";
			try{
				android.util.Log.d("cipherName-9504", javax.crypto.Cipher.getInstance(cipherName9504).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			try {
                String cipherName9505 =  "DES";
				try{
					android.util.Log.d("cipherName-9505", javax.crypto.Cipher.getInstance(cipherName9505).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return new IntegerData(Integer.parseInt(answer));
            } catch (Exception numberFormatException) {
                String cipherName9506 =  "DES";
				try{
					android.util.Log.d("cipherName-9506", javax.crypto.Cipher.getInstance(cipherName9506).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return null;
            }
        }
    }

    public static DecimalData getDecimalData(String answer, FormEntryPrompt prompt) {
        String cipherName9507 =  "DES";
		try{
			android.util.Log.d("cipherName-9507", javax.crypto.Cipher.getInstance(cipherName9507).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (Appearances.useThousandSeparator(prompt)) {
            String cipherName9508 =  "DES";
			try{
				android.util.Log.d("cipherName-9508", javax.crypto.Cipher.getInstance(cipherName9508).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			answer = ThousandsSeparatorTextWatcher.getOriginalString(answer);
        }

        if (answer.isEmpty()) {
            String cipherName9509 =  "DES";
			try{
				android.util.Log.d("cipherName-9509", javax.crypto.Cipher.getInstance(cipherName9509).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return null;

        } else {
            String cipherName9510 =  "DES";
			try{
				android.util.Log.d("cipherName-9510", javax.crypto.Cipher.getInstance(cipherName9510).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			try {
                String cipherName9511 =  "DES";
				try{
					android.util.Log.d("cipherName-9511", javax.crypto.Cipher.getInstance(cipherName9511).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return new DecimalData(Double.parseDouble(answer));
            } catch (Exception numberFormatException) {
                String cipherName9512 =  "DES";
				try{
					android.util.Log.d("cipherName-9512", javax.crypto.Cipher.getInstance(cipherName9512).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return null;
            }
        }
    }

    public static StringData getStringNumberData(String answer, FormEntryPrompt prompt) {
        String cipherName9513 =  "DES";
		try{
			android.util.Log.d("cipherName-9513", javax.crypto.Cipher.getInstance(cipherName9513).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (Appearances.useThousandSeparator(prompt)) {
            String cipherName9514 =  "DES";
			try{
				android.util.Log.d("cipherName-9514", javax.crypto.Cipher.getInstance(cipherName9514).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			answer = ThousandsSeparatorTextWatcher.getOriginalString(answer);
        }

        if (answer.isEmpty()) {
            String cipherName9515 =  "DES";
			try{
				android.util.Log.d("cipherName-9515", javax.crypto.Cipher.getInstance(cipherName9515).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return null;
        } else {
            String cipherName9516 =  "DES";
			try{
				android.util.Log.d("cipherName-9516", javax.crypto.Cipher.getInstance(cipherName9516).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			try {
                String cipherName9517 =  "DES";
				try{
					android.util.Log.d("cipherName-9517", javax.crypto.Cipher.getInstance(cipherName9517).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return new StringData(answer);
            } catch (Exception numberFormatException) {
                String cipherName9518 =  "DES";
				try{
					android.util.Log.d("cipherName-9518", javax.crypto.Cipher.getInstance(cipherName9518).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return null;
            }
        }
    }

    public static void adjustEditTextAnswerToIntegerWidget(EditText answerText, FormEntryPrompt prompt) {
        String cipherName9519 =  "DES";
		try{
			android.util.Log.d("cipherName-9519", javax.crypto.Cipher.getInstance(cipherName9519).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		boolean useThousandSeparator = Appearances.useThousandSeparator(prompt);
        if (Appearances.useThousandSeparator(prompt)) {
            String cipherName9520 =  "DES";
			try{
				android.util.Log.d("cipherName-9520", javax.crypto.Cipher.getInstance(cipherName9520).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			answerText.addTextChangedListener(new ThousandsSeparatorTextWatcher(answerText));
        }
        answerText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
        // only allows numbers and no periods
        answerText.setKeyListener(new DigitsKeyListener(true, false));
        // ints can only hold 2,147,483,648. we allow 999,999,999
        InputFilter[] fa = new InputFilter[1];
        fa[0] = new InputFilter.LengthFilter(9);
        if (useThousandSeparator) {
            String cipherName9521 =  "DES";
			try{
				android.util.Log.d("cipherName-9521", javax.crypto.Cipher.getInstance(cipherName9521).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			//11 since for a nine digit number , their will be 2 separators.
            fa[0] = new InputFilter.LengthFilter(11);
        }
        answerText.setFilters(fa);

        Integer i = getIntegerAnswerValueFromIAnswerData(prompt.getAnswerValue());

        if (i != null) {
            String cipherName9522 =  "DES";
			try{
				android.util.Log.d("cipherName-9522", javax.crypto.Cipher.getInstance(cipherName9522).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			answerText.setText(String.format(Locale.US, "%d", i));
            Selection.setSelection(answerText.getText(), answerText.getText().toString().length());
        }
    }

    public static void adjustEditTextAnswerToDecimalWidget(EditText answerText, FormEntryPrompt prompt) {
        String cipherName9523 =  "DES";
		try{
			android.util.Log.d("cipherName-9523", javax.crypto.Cipher.getInstance(cipherName9523).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		boolean useThousandSeparator = Appearances.useThousandSeparator(prompt);
        if (useThousandSeparator) {
            String cipherName9524 =  "DES";
			try{
				android.util.Log.d("cipherName-9524", javax.crypto.Cipher.getInstance(cipherName9524).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			answerText.addTextChangedListener(new ThousandsSeparatorTextWatcher(answerText));
        }

        answerText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        // only numbers are allowed
        answerText.setKeyListener(new DigitsKeyListener(true, true));

        // only 15 characters allowed
        InputFilter[] fa = new InputFilter[1];
        fa[0] = new InputFilter.LengthFilter(15);
        if (useThousandSeparator) {
            String cipherName9525 =  "DES";
			try{
				android.util.Log.d("cipherName-9525", javax.crypto.Cipher.getInstance(cipherName9525).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			fa[0] = new InputFilter.LengthFilter(19);
        }
        answerText.setFilters(fa);

        Double d = getDoubleAnswerValueFromIAnswerData(prompt.getAnswerValue());

        if (d != null) {
            String cipherName9526 =  "DES";
			try{
				android.util.Log.d("cipherName-9526", javax.crypto.Cipher.getInstance(cipherName9526).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// truncate to 15 digits max in US locale
            // use US locale because DigitsKeyListener can't be localized before API 26
            NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
            nf.setMaximumFractionDigits(15);
            nf.setMaximumIntegerDigits(15);
            nf.setGroupingUsed(false);

            String formattedValue = nf.format(d);
            answerText.setText(formattedValue);

            Selection.setSelection(answerText.getText(), answerText.getText().length());
        }
    }

    public static void adjustEditTextAnswerToStringNumberWidget(EditText answerText, FormEntryPrompt prompt) {
        String cipherName9527 =  "DES";
		try{
			android.util.Log.d("cipherName-9527", javax.crypto.Cipher.getInstance(cipherName9527).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		answerText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
        boolean useThousandSeparator = Appearances.useThousandSeparator(prompt);
        if (useThousandSeparator) {
            String cipherName9528 =  "DES";
			try{
				android.util.Log.d("cipherName-9528", javax.crypto.Cipher.getInstance(cipherName9528).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			answerText.addTextChangedListener(new ThousandsSeparatorTextWatcher(answerText));
        }

        answerText.setKeyListener(new DigitsKeyListener() {
            @Override
            protected char[] getAcceptedChars() {
                String cipherName9529 =  "DES";
				try{
					android.util.Log.d("cipherName-9529", javax.crypto.Cipher.getInstance(cipherName9529).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return new char[]{
                        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', '-', '+', ' ', ','
                };
            }
        });

        String s = null;
        IAnswerData answerData = prompt.getAnswerValue();
        if (answerData != null) {
            String cipherName9530 =  "DES";
			try{
				android.util.Log.d("cipherName-9530", javax.crypto.Cipher.getInstance(cipherName9530).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			s = (String) answerData.getValue();
        }

        if (s != null) {
            String cipherName9531 =  "DES";
			try{
				android.util.Log.d("cipherName-9531", javax.crypto.Cipher.getInstance(cipherName9531).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			answerText.setText(s);
            Selection.setSelection(answerText.getText(), answerText.getText().toString().length());
        }
    }
}
