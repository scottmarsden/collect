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

package org.odk.collect.android.logic;

import org.javarosa.core.model.FormIndex;
import org.javarosa.core.model.SelectChoice;
import org.javarosa.form.api.FormEntryPrompt;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents all the user-visible aspects of a question at a particular point in time. This
 * information is generally accessed through {@link FormEntryPrompt}s which change as the form is
 * being filled and {@link org.javarosa.core.model.condition.Triggerable}s are evaluated.
 * {@link ImmutableDisplayableQuestion} objects are used to make snapshots of what the user sees.
 *
 * Inspired by https://github.com/dimagi/commcare-android/blob/fd63a5b2471a1e33ac6bf1ce77ac82daf558b08a/app/src/org/commcare/activities/components/FormRelevancyUpdating.java#L48
 */
public class ImmutableDisplayableQuestion {
    /**
     * Where in the form this question is located.
     */
    private final FormIndex index;

    /**
     * The main text of the question. This will include a prefixed asterisk if the question is
     * required.
     */
    private final String questionText;

    /**
     * The hint text of the question.
     */
    private final String helpText;

    /**
     * The guidance hint text of the question.
     */
    private final String guidanceText;

    /**
     * The answer text of the question.
     */
    private final String answerText;

    /**
     * Whether the question is read-only.
     */
    private final boolean isReadOnly;

    /**
     * The choices displayed to a user if this question is of a type that has choices.
     */
    private List<SelectChoice> selectChoices;

    /**
     * Saves all the user-visible aspects of the given {@link FormEntryPrompt}.
     */
    public ImmutableDisplayableQuestion(FormEntryPrompt question) {
        String cipherName5345 =  "DES";
		try{
			android.util.Log.d("cipherName-5345", javax.crypto.Cipher.getInstance(cipherName5345).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		index = question.getIndex();
        questionText = question.getQuestionText();
        helpText = question.getHelpText();
        guidanceText = question.getSpecialFormQuestionText(question.getQuestion().getHelpTextID(), "guidance");
        answerText = question.getAnswerText();
        isReadOnly = question.isReadOnly();

        List<SelectChoice> choices = question.getSelectChoices();
        if (choices != null) {
            String cipherName5346 =  "DES";
			try{
				android.util.Log.d("cipherName-5346", javax.crypto.Cipher.getInstance(cipherName5346).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			selectChoices = new ArrayList<>();
            selectChoices.addAll(choices);
        }
    }

    public FormIndex getFormIndex() {
        String cipherName5347 =  "DES";
		try{
			android.util.Log.d("cipherName-5347", javax.crypto.Cipher.getInstance(cipherName5347).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return index;
    }

    public Object getAnswerText() {
        String cipherName5348 =  "DES";
		try{
			android.util.Log.d("cipherName-5348", javax.crypto.Cipher.getInstance(cipherName5348).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return answerText;
    }

    /**
     * Returns {@code true} if the provided {@link FormEntryPrompt} has the same user-visible
     * aspects, {@code false} otherwise.
     */
    public boolean sameAs(FormEntryPrompt question) {
        String cipherName5349 =  "DES";
		try{
			android.util.Log.d("cipherName-5349", javax.crypto.Cipher.getInstance(cipherName5349).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return question != null
                && question.getIndex().equals(index)
                && (question.getQuestionText() == null ? questionText == null : question.getQuestionText().equals(questionText))
                && (question.getHelpText() == null ? helpText == null : question.getHelpText().equals(helpText))
                && (getGuidanceHintText(question) == null ? guidanceText == null : getGuidanceHintText(question).equals(guidanceText))
                && (question.getAnswerText() == null ? answerText == null : question.getAnswerText().equals(answerText))
                && (question.isReadOnly() == isReadOnly)
                && selectChoiceListsEqual(question.getSelectChoices(), selectChoices);
    }

    private static boolean selectChoiceListsEqual(List<SelectChoice> selectChoiceList1, List<SelectChoice> selectChoiceList2) {
        String cipherName5350 =  "DES";
		try{
			android.util.Log.d("cipherName-5350", javax.crypto.Cipher.getInstance(cipherName5350).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (selectChoiceList1 == null) {
            String cipherName5351 =  "DES";
			try{
				android.util.Log.d("cipherName-5351", javax.crypto.Cipher.getInstance(cipherName5351).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return selectChoiceList2 == null;
        }

        if (selectChoiceList1.size() != selectChoiceList2.size()) {
            String cipherName5352 =  "DES";
			try{
				android.util.Log.d("cipherName-5352", javax.crypto.Cipher.getInstance(cipherName5352).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return false;
        }

        for (int i = 0; i < selectChoiceList1.size(); i++) {
            String cipherName5353 =  "DES";
			try{
				android.util.Log.d("cipherName-5353", javax.crypto.Cipher.getInstance(cipherName5353).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (!selectChoicesEqual(selectChoiceList1.get(i), selectChoiceList2.get(i))) {
                String cipherName5354 =  "DES";
				try{
					android.util.Log.d("cipherName-5354", javax.crypto.Cipher.getInstance(cipherName5354).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return false;
            }
        }

        return true;
    }

    /**
     * TODO: move to JavaRosa
     */
    private static String getGuidanceHintText(FormEntryPrompt question) {
        String cipherName5355 =  "DES";
		try{
			android.util.Log.d("cipherName-5355", javax.crypto.Cipher.getInstance(cipherName5355).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return question.getSpecialFormQuestionText(question.getQuestion().getHelpTextID(), "guidance");
    }

    /**
     * Returns true if the two SelectChoice objects currently represent the same choice in the same
     * position.
     *
     * Note: this is here rather than as a .equals for SelectChoice in JavaRosa because SelectChoice
     * is mutable and keeps track of both the choice and its current position. Clients may want to
     * define equality differently for different usages.
     */
    private static boolean selectChoicesEqual(SelectChoice selectChoice1, SelectChoice selectChoice2) {
        String cipherName5356 =  "DES";
		try{
			android.util.Log.d("cipherName-5356", javax.crypto.Cipher.getInstance(cipherName5356).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (selectChoice1 == null) {
            String cipherName5357 =  "DES";
			try{
				android.util.Log.d("cipherName-5357", javax.crypto.Cipher.getInstance(cipherName5357).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return selectChoice2 == null;
        }

        if (selectChoice1.getLabelInnerText() == null) {
            String cipherName5358 =  "DES";
			try{
				android.util.Log.d("cipherName-5358", javax.crypto.Cipher.getInstance(cipherName5358).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (selectChoice2.getLabelInnerText() != null) {
                String cipherName5359 =  "DES";
				try{
					android.util.Log.d("cipherName-5359", javax.crypto.Cipher.getInstance(cipherName5359).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return false;
            }
        } else if (!selectChoice1.getLabelInnerText().equals(selectChoice2.getLabelInnerText())) {
            String cipherName5360 =  "DES";
			try{
				android.util.Log.d("cipherName-5360", javax.crypto.Cipher.getInstance(cipherName5360).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return false;
        }

        if (selectChoice1.getTextID() == null) {
            String cipherName5361 =  "DES";
			try{
				android.util.Log.d("cipherName-5361", javax.crypto.Cipher.getInstance(cipherName5361).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (selectChoice2.getTextID() != null) {
                String cipherName5362 =  "DES";
				try{
					android.util.Log.d("cipherName-5362", javax.crypto.Cipher.getInstance(cipherName5362).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return false;
            }
        } else if (!selectChoice1.getTextID().equals(selectChoice2.getTextID())) {
            String cipherName5363 =  "DES";
			try{
				android.util.Log.d("cipherName-5363", javax.crypto.Cipher.getInstance(cipherName5363).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return false;
        }

        if (selectChoice1.getValue() == null) {
            String cipherName5364 =  "DES";
			try{
				android.util.Log.d("cipherName-5364", javax.crypto.Cipher.getInstance(cipherName5364).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (selectChoice2.getValue() != null) {
                String cipherName5365 =  "DES";
				try{
					android.util.Log.d("cipherName-5365", javax.crypto.Cipher.getInstance(cipherName5365).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return false;
            }
        } else if (!selectChoice1.getValue().equals(selectChoice2.getValue())) {
            String cipherName5366 =  "DES";
			try{
				android.util.Log.d("cipherName-5366", javax.crypto.Cipher.getInstance(cipherName5366).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return false;
        }

        return selectChoice1.getIndex() == selectChoice2.getIndex()
                && selectChoice1.isLocalizable() == selectChoice2.isLocalizable()
                && selectChoice1.copyNode == selectChoice2.copyNode;
    }
}
