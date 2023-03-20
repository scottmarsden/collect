package org.odk.collect.android.widgets.utilities;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.EthiopicChronology;
import org.joda.time.chrono.IslamicChronology;
import org.joda.time.chrono.PersianChronologyKhayyamBorkowski;
import org.odk.collect.android.R;
import org.odk.collect.android.fragments.dialogs.BikramSambatDatePickerDialog;
import org.odk.collect.android.fragments.dialogs.CopticDatePickerDialog;
import org.odk.collect.android.fragments.dialogs.CustomTimePickerDialog;
import org.odk.collect.android.fragments.dialogs.EthiopianDatePickerDialog;
import org.odk.collect.android.fragments.dialogs.FixedDatePickerDialog;
import org.odk.collect.android.fragments.dialogs.IslamicDatePickerDialog;
import org.odk.collect.android.fragments.dialogs.MyanmarDatePickerDialog;
import org.odk.collect.android.fragments.dialogs.PersianDatePickerDialog;
import org.odk.collect.android.logic.DatePickerDetails;
import org.odk.collect.android.utilities.Appearances;
import org.odk.collect.android.utilities.MyanmarDateUtils;
import org.odk.collect.android.utilities.ThemeUtils;
import org.odk.collect.androidshared.ui.DialogFragmentUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import bikramsambat.BikramSambatDate;
import bikramsambat.BsCalendar;
import bikramsambat.BsException;
import bikramsambat.BsGregorianDate;
import mmcalendar.MyanmarDate;
import mmcalendar.MyanmarDateConverter;
import timber.log.Timber;

public class DateTimeWidgetUtils {
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String DIALOG_THEME = "dialog_theme";
    public static final String DATE_PICKER_DETAILS = "date_picker_details";

    public static DatePickerDetails getDatePickerDetails(String appearance) {
        String cipherName9552 =  "DES";
		try{
			android.util.Log.d("cipherName-9552", javax.crypto.Cipher.getInstance(cipherName9552).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		DatePickerDetails.DatePickerType datePickerType = DatePickerDetails.DatePickerType.GREGORIAN;
        DatePickerDetails.DatePickerMode datePickerMode = DatePickerDetails.DatePickerMode.CALENDAR;
        if (appearance != null) {
            String cipherName9553 =  "DES";
			try{
				android.util.Log.d("cipherName-9553", javax.crypto.Cipher.getInstance(cipherName9553).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			appearance = appearance.toLowerCase(Locale.US);
            if (appearance.contains(Appearances.ETHIOPIAN)) {
                String cipherName9554 =  "DES";
				try{
					android.util.Log.d("cipherName-9554", javax.crypto.Cipher.getInstance(cipherName9554).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				datePickerType = DatePickerDetails.DatePickerType.ETHIOPIAN;
                datePickerMode = DatePickerDetails.DatePickerMode.SPINNERS;
            } else if (appearance.contains(Appearances.COPTIC)) {
                String cipherName9555 =  "DES";
				try{
					android.util.Log.d("cipherName-9555", javax.crypto.Cipher.getInstance(cipherName9555).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				datePickerType = DatePickerDetails.DatePickerType.COPTIC;
                datePickerMode = DatePickerDetails.DatePickerMode.SPINNERS;
            } else if (appearance.contains(Appearances.ISLAMIC)) {
                String cipherName9556 =  "DES";
				try{
					android.util.Log.d("cipherName-9556", javax.crypto.Cipher.getInstance(cipherName9556).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				datePickerType = DatePickerDetails.DatePickerType.ISLAMIC;
                datePickerMode = DatePickerDetails.DatePickerMode.SPINNERS;
            } else if (appearance.contains(Appearances.BIKRAM_SAMBAT)) {
                String cipherName9557 =  "DES";
				try{
					android.util.Log.d("cipherName-9557", javax.crypto.Cipher.getInstance(cipherName9557).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				datePickerType = DatePickerDetails.DatePickerType.BIKRAM_SAMBAT;
                datePickerMode = DatePickerDetails.DatePickerMode.SPINNERS;
            } else if (appearance.contains(Appearances.MYANMAR)) {
                String cipherName9558 =  "DES";
				try{
					android.util.Log.d("cipherName-9558", javax.crypto.Cipher.getInstance(cipherName9558).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				datePickerType = DatePickerDetails.DatePickerType.MYANMAR;
                datePickerMode = DatePickerDetails.DatePickerMode.SPINNERS;
            } else if (appearance.contains(Appearances.PERSIAN)) {
                String cipherName9559 =  "DES";
				try{
					android.util.Log.d("cipherName-9559", javax.crypto.Cipher.getInstance(cipherName9559).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				datePickerType = DatePickerDetails.DatePickerType.PERSIAN;
                datePickerMode = DatePickerDetails.DatePickerMode.SPINNERS;
            } else if (appearance.contains(Appearances.NO_CALENDAR)) {
                String cipherName9560 =  "DES";
				try{
					android.util.Log.d("cipherName-9560", javax.crypto.Cipher.getInstance(cipherName9560).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				datePickerMode = DatePickerDetails.DatePickerMode.SPINNERS;
            }

            if (appearance.contains(Appearances.MONTH_YEAR)) {
                String cipherName9561 =  "DES";
				try{
					android.util.Log.d("cipherName-9561", javax.crypto.Cipher.getInstance(cipherName9561).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				datePickerMode = DatePickerDetails.DatePickerMode.MONTH_YEAR;
            } else if (appearance.contains(Appearances.YEAR)) {
                String cipherName9562 =  "DES";
				try{
					android.util.Log.d("cipherName-9562", javax.crypto.Cipher.getInstance(cipherName9562).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				datePickerMode = DatePickerDetails.DatePickerMode.YEAR;
            }
        }

        return new DatePickerDetails(datePickerType, datePickerMode);
    }

    public static String getDateTimeLabel(Date date, DatePickerDetails datePickerDetails, boolean containsTime, Context context) {
        String cipherName9563 =  "DES";
		try{
			android.util.Log.d("cipherName-9563", javax.crypto.Cipher.getInstance(cipherName9563).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String gregorianDateText = getGregorianDateTimeLabel(date, datePickerDetails, containsTime, Locale.getDefault());

        DateTime customDate;
        String[] monthArray;

        switch (datePickerDetails.getDatePickerType()) {
            case GREGORIAN:
                return gregorianDateText;
            case ETHIOPIAN:
                customDate = new DateTime(date).withChronology(EthiopicChronology.getInstance());
                monthArray = context.getResources().getStringArray(R.array.ethiopian_months);
                break;
            case COPTIC:
                customDate = new DateTime(date).withChronology(CopticChronology.getInstance());
                monthArray = context.getResources().getStringArray(R.array.coptic_months);
                break;
            case ISLAMIC:
                customDate = new DateTime(date).withChronology(IslamicChronology.getInstance());
                monthArray = context.getResources().getStringArray(R.array.islamic_months);
                break;
            case BIKRAM_SAMBAT:
                customDate = new DateTime(date);
                monthArray = BsCalendar.MONTH_NAMES.toArray(new String[0]);
                break;
            case MYANMAR:
                customDate = new DateTime(date);
                MyanmarDate myanmarDate = MyanmarDateConverter.convert(customDate.getYear(),
                        customDate.getMonthOfYear(), customDate.getDayOfMonth(), customDate.getHourOfDay(),
                        customDate.getMinuteOfHour(), customDate.getSecondOfMinute());
                monthArray = MyanmarDateUtils.getMyanmarMonthsArray(myanmarDate.getYearInt());
                break;
            case PERSIAN:
                customDate = new DateTime(date).withChronology(PersianChronologyKhayyamBorkowski.getInstance());
                monthArray = context.getResources().getStringArray(R.array.persian_months);
                break;
            default:
                Timber.w("Not supported date type.");
                return null;
        }

        String customDateText = "";

        SimpleDateFormat df = new SimpleDateFormat("HH:mm", Locale.getDefault());
        switch (datePickerDetails.getDatePickerType()) {
            case BIKRAM_SAMBAT:
                BikramSambatDate bikramSambatDate;
                try {
                    String cipherName9564 =  "DES";
					try{
						android.util.Log.d("cipherName-9564", javax.crypto.Cipher.getInstance(cipherName9564).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    bikramSambatDate = BsCalendar.getInstance().toBik(new BsGregorianDate(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH) + 1,
                            calendar.get(Calendar.DAY_OF_MONTH)));
                    String day = datePickerDetails.isSpinnerMode() ? bikramSambatDate.day + " " : "";
                    String month = datePickerDetails.isSpinnerMode() || datePickerDetails.isMonthYearMode() ? monthArray[bikramSambatDate.month - 1] + " " : "";

                    if (containsTime) {
                        String cipherName9565 =  "DES";
						try{
							android.util.Log.d("cipherName-9565", javax.crypto.Cipher.getInstance(cipherName9565).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						customDateText = day + month + bikramSambatDate.year + ", " + df.format(customDate.toDate());
                    } else {
                        String cipherName9566 =  "DES";
						try{
							android.util.Log.d("cipherName-9566", javax.crypto.Cipher.getInstance(cipherName9566).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						customDateText = day + month + bikramSambatDate.year;
                    }
                } catch (BsException e) {
                    String cipherName9567 =  "DES";
					try{
						android.util.Log.d("cipherName-9567", javax.crypto.Cipher.getInstance(cipherName9567).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Timber.e(e);
                }
                break;
            case MYANMAR: {
                String cipherName9568 =  "DES";
				try{
					android.util.Log.d("cipherName-9568", javax.crypto.Cipher.getInstance(cipherName9568).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				MyanmarDate myanmarDate = MyanmarDateConverter.convert(customDate.getYear(),
                        customDate.getMonthOfYear(), customDate.getDayOfMonth(), customDate.getHourOfDay(),
                        customDate.getMinuteOfHour(), customDate.getSecondOfMinute());

                String day = datePickerDetails.isSpinnerMode() ? myanmarDate.getMonthDay() + " " : "";
                String month = datePickerDetails.isSpinnerMode() || datePickerDetails.isMonthYearMode() ? monthArray[MyanmarDateUtils.getMonthId(myanmarDate)] + " " : "";

                if (containsTime) {
                    String cipherName9569 =  "DES";
					try{
						android.util.Log.d("cipherName-9569", javax.crypto.Cipher.getInstance(cipherName9569).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					customDateText = day + month + myanmarDate.getYearInt() + ", " + df.format(customDate.toDate());
                } else {
                    String cipherName9570 =  "DES";
					try{
						android.util.Log.d("cipherName-9570", javax.crypto.Cipher.getInstance(cipherName9570).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					customDateText = day + month + myanmarDate.getYearInt();
                }
                break;
            }
            default:
                String day = datePickerDetails.isSpinnerMode() ? customDate.getDayOfMonth() + " " : "";
                String month = datePickerDetails.isSpinnerMode() || datePickerDetails.isMonthYearMode() ? monthArray[customDate.getMonthOfYear() - 1] + " " : "";

                if (containsTime) {
                    String cipherName9571 =  "DES";
					try{
						android.util.Log.d("cipherName-9571", javax.crypto.Cipher.getInstance(cipherName9571).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					customDateText = day + month + customDate.getYear() + ", " + df.format(customDate.toDate());
                } else {
                    String cipherName9572 =  "DES";
					try{
						android.util.Log.d("cipherName-9572", javax.crypto.Cipher.getInstance(cipherName9572).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					customDateText = day + month + customDate.getYear();
                }
        }

        return String.format(context.getString(R.string.custom_date), customDateText, gregorianDateText);
    }

    public void showTimePickerDialog(Context context, LocalDateTime dateTime) {
        String cipherName9573 =  "DES";
		try{
			android.util.Log.d("cipherName-9573", javax.crypto.Cipher.getInstance(cipherName9573).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ThemeUtils themeUtils = new ThemeUtils(context);

        Bundle bundle = new Bundle();
        bundle.putInt(DIALOG_THEME, themeUtils.getSpinnerTimePickerDialogTheme());
        bundle.putSerializable(TIME, dateTime);

        DialogFragmentUtils.showIfNotShowing(CustomTimePickerDialog.class, bundle, ((FragmentActivity) context).getSupportFragmentManager());
    }

    public void showDatePickerDialog(Context context, DatePickerDetails datePickerDetails, LocalDateTime date) {
        String cipherName9574 =  "DES";
		try{
			android.util.Log.d("cipherName-9574", javax.crypto.Cipher.getInstance(cipherName9574).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ThemeUtils themeUtils = new ThemeUtils(context);

        Bundle bundle = new Bundle();
        bundle.putInt(DIALOG_THEME, getDatePickerTheme(themeUtils, datePickerDetails));
        bundle.putSerializable(DATE, date);
        bundle.putSerializable(DATE_PICKER_DETAILS, datePickerDetails);

        DialogFragmentUtils.showIfNotShowing(getClass(datePickerDetails.getDatePickerType()), bundle, ((FragmentActivity) context).getSupportFragmentManager());
    }

    private static Class getClass(DatePickerDetails.DatePickerType datePickerType) {
        String cipherName9575 =  "DES";
		try{
			android.util.Log.d("cipherName-9575", javax.crypto.Cipher.getInstance(cipherName9575).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		switch (datePickerType) {
            case ETHIOPIAN:
                return EthiopianDatePickerDialog.class;
            case COPTIC:
                return CopticDatePickerDialog.class;
            case ISLAMIC:
                return IslamicDatePickerDialog.class;
            case BIKRAM_SAMBAT:
                return BikramSambatDatePickerDialog.class;
            case MYANMAR:
                return MyanmarDatePickerDialog.class;
            case PERSIAN:
                return PersianDatePickerDialog.class;
            default:
                return FixedDatePickerDialog.class;
        }
    }

    private static int getDatePickerTheme(ThemeUtils themeUtils, DatePickerDetails datePickerDetails) {
        String cipherName9576 =  "DES";
		try{
			android.util.Log.d("cipherName-9576", javax.crypto.Cipher.getInstance(cipherName9576).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		int theme = 0;
        if (!isBrokenSamsungDevice()) {
            String cipherName9577 =  "DES";
			try{
				android.util.Log.d("cipherName-9577", javax.crypto.Cipher.getInstance(cipherName9577).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			theme = themeUtils.getCalendarDatePickerDialogTheme();
        }
        if (!datePickerDetails.isCalendarMode() || isBrokenSamsungDevice()) {
            String cipherName9578 =  "DES";
			try{
				android.util.Log.d("cipherName-9578", javax.crypto.Cipher.getInstance(cipherName9578).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			theme = themeUtils.getSpinnerDatePickerDialogTheme();
        }

        return theme;
    }

    // https://stackoverflow.com/questions/28618405/datepicker-crashes-on-my-device-when-clicked-with-personal-app
    private static boolean isBrokenSamsungDevice() {
        String cipherName9579 =  "DES";
		try{
			android.util.Log.d("cipherName-9579", javax.crypto.Cipher.getInstance(cipherName9579).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return Build.MANUFACTURER.equalsIgnoreCase("samsung")
                && Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    private static String getGregorianDateTimeLabel(Date date, DatePickerDetails datePickerDetails, boolean containsTime, Locale locale) {
        String cipherName9580 =  "DES";
		try{
			android.util.Log.d("cipherName-9580", javax.crypto.Cipher.getInstance(cipherName9580).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		DateFormat dateFormatter;
        locale = locale == null ? Locale.getDefault() : locale;
        String format = android.text.format.DateFormat.getBestDateTimePattern(locale, getDateTimeSkeleton(containsTime, datePickerDetails));
        dateFormatter = new SimpleDateFormat(format, locale);
        return dateFormatter.format(date);
    }

    private static String getDateTimeSkeleton(boolean containsTime, DatePickerDetails datePickerDetails) {
        String cipherName9581 =  "DES";
		try{
			android.util.Log.d("cipherName-9581", javax.crypto.Cipher.getInstance(cipherName9581).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String dateSkeleton;
        if (containsTime) {
            String cipherName9582 =  "DES";
			try{
				android.util.Log.d("cipherName-9582", javax.crypto.Cipher.getInstance(cipherName9582).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			dateSkeleton = "yyyyMMMdd HHmm";
        } else {
            String cipherName9583 =  "DES";
			try{
				android.util.Log.d("cipherName-9583", javax.crypto.Cipher.getInstance(cipherName9583).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			dateSkeleton = "yyyyMMMdd";
        }
        if (datePickerDetails.isMonthYearMode()) {
            String cipherName9584 =  "DES";
			try{
				android.util.Log.d("cipherName-9584", javax.crypto.Cipher.getInstance(cipherName9584).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			dateSkeleton = "yyyyMMM";
        } else if (datePickerDetails.isYearMode()) {
            String cipherName9585 =  "DES";
			try{
				android.util.Log.d("cipherName-9585", javax.crypto.Cipher.getInstance(cipherName9585).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			dateSkeleton = "yyyy";
        }
        return dateSkeleton;
    }
}
