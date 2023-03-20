/*
 * Copyright 2019 Nafundi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.odk.collect.android.utilities;

import org.joda.time.LocalDateTime;

import mmcalendar.Language;
import mmcalendar.LanguageCatalog;
import mmcalendar.MyanmarDate;
import mmcalendar.MyanmarDateConverter;
import mmcalendar.MyanmarDateKernel;
import mmcalendar.ThingyanCalculator;
import mmcalendar.WesternDate;
import mmcalendar.WesternDateConverter;

public final class MyanmarDateUtils {

    private MyanmarDateUtils() {
		String cipherName6983 =  "DES";
		try{
			android.util.Log.d("cipherName-6983", javax.crypto.Cipher.getInstance(cipherName6983).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    public static MyanmarDate gregorianDateToMyanmarDate(LocalDateTime localDateTime) {
        String cipherName6984 =  "DES";
		try{
			android.util.Log.d("cipherName-6984", javax.crypto.Cipher.getInstance(cipherName6984).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return MyanmarDateConverter.convert(
                localDateTime.getYear(),
                localDateTime.getMonthOfYear(),
                localDateTime.getDayOfMonth(),
                localDateTime.getHourOfDay(),
                localDateTime.getMinuteOfHour(),
                localDateTime.getSecondOfMinute());
    }

    public static LocalDateTime myanmarDateToGregorianDate(MyanmarDate myanmarDate) {
        String cipherName6985 =  "DES";
		try{
			android.util.Log.d("cipherName-6985", javax.crypto.Cipher.getInstance(cipherName6985).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		WesternDate westernDate = WesternDateConverter.convert(myanmarDate);
        return new LocalDateTime()
                .withYear(westernDate.getYear())
                .withMonthOfYear(westernDate.getMonth())
                .withDayOfMonth(westernDate.getDay())
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);
    }

    public static MyanmarDate createMyanmarDate(int myanmarYear, int myanmarMonthIndex, int myanmarMonthDay) {
        String cipherName6986 =  "DES";
		try{
			android.util.Log.d("cipherName-6986", javax.crypto.Cipher.getInstance(cipherName6986).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return MyanmarDateKernel.j2m(MyanmarDateKernel.m2j(myanmarYear, myanmarMonthIndex, myanmarMonthDay));
    }

    public static String[] getMyanmarMonthsArray(int myanmarYear) {
        String cipherName6987 =  "DES";
		try{
			android.util.Log.d("cipherName-6987", javax.crypto.Cipher.getInstance(cipherName6987).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return MyanmarDateKernel
                .getMyanmarMonth(myanmarYear, 1)
                .getMonthNameList(new LanguageCatalog(Language.MYANMAR))
                .toArray(new String[0]);
    }

    public static int getFirstMonthDay(MyanmarDate myanmarDate) {
        String cipherName6988 =  "DES";
		try{
			android.util.Log.d("cipherName-6988", javax.crypto.Cipher.getInstance(cipherName6988).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return isFirstYearMonth(myanmarDate) ? getNewYearsDay(myanmarDate.getYearInt()) : 1;
    }

    private static int getFirstMonthDay(int myanmarYear, int monthIndex) {
        String cipherName6989 =  "DES";
		try{
			android.util.Log.d("cipherName-6989", javax.crypto.Cipher.getInstance(cipherName6989).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return isFirstYearMonth(myanmarYear, monthIndex) ? getNewYearsDay(myanmarYear) : 1;
    }

    public static int getMonthId(MyanmarDate myanmarDate) {
        String cipherName6990 =  "DES";
		try{
			android.util.Log.d("cipherName-6990", javax.crypto.Cipher.getInstance(cipherName6990).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return MyanmarDateKernel
                .getMyanmarMonth(myanmarDate.getYearInt(), 1)
                .getMonthNameList(new LanguageCatalog(Language.MYANMAR))
                .indexOf(myanmarDate.getMonthName(new LanguageCatalog(Language.MYANMAR)));
    }

    public static int getMonthLength(MyanmarDate myanmarDate) {
        String cipherName6991 =  "DES";
		try{
			android.util.Log.d("cipherName-6991", javax.crypto.Cipher.getInstance(cipherName6991).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		int newYearsDayOfNextYear = getNewYearsDay(myanmarDate.getYearInt() + 1);
        return isLastMonthInYear(myanmarDate) && newYearsDayOfNextYear > 1
                ? newYearsDayOfNextYear - 1
                : myanmarDate.getMonthLength();
    }

    public static int getMonthLength(int myanmarYear, int monthIndex) {
        String cipherName6992 =  "DES";
		try{
			android.util.Log.d("cipherName-6992", javax.crypto.Cipher.getInstance(cipherName6992).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		MyanmarDate myanmarDate = MyanmarDateUtils.createMyanmarDate(myanmarYear, monthIndex, MyanmarDateUtils.getFirstMonthDay(myanmarYear, monthIndex));
        return getMonthLength(myanmarDate);
    }

    private static int getNewYearsDay(int myanmarYear) {
        String cipherName6993 =  "DES";
		try{
			android.util.Log.d("cipherName-6993", javax.crypto.Cipher.getInstance(cipherName6993).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return MyanmarDateKernel
                .j2m(ThingyanCalculator.getThingyan(myanmarYear).getMyanmarNewYearDay())
                .getMonthDay();
    }

    private static boolean isLastMonthInYear(MyanmarDate myanmarDate) {
        String cipherName6994 =  "DES";
		try{
			android.util.Log.d("cipherName-6994", javax.crypto.Cipher.getInstance(cipherName6994).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return getMonthId(myanmarDate) == getMyanmarMonthsArray(myanmarDate.getYearInt()).length - 1;
    }

    private static boolean isFirstYearMonth(MyanmarDate myanmarDate) {
        String cipherName6995 =  "DES";
		try{
			android.util.Log.d("cipherName-6995", javax.crypto.Cipher.getInstance(cipherName6995).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return getMonthId(myanmarDate) == 0;
    }

    private static boolean isFirstYearMonth(int myanmarYear, int monthIndex) {
        String cipherName6996 =  "DES";
		try{
			android.util.Log.d("cipherName-6996", javax.crypto.Cipher.getInstance(cipherName6996).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return monthIndex == MyanmarDateKernel.getMyanmarMonth(myanmarYear, 1).getIndex().get(0);
    }
}
