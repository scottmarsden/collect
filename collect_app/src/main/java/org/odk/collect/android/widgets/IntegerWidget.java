/*
 * Copyright (C) 2009 University of Washington
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.odk.collect.android.widgets;

import android.annotation.SuppressLint;
import android.content.Context;

import org.javarosa.core.model.data.IAnswerData;
import org.odk.collect.android.formentry.questions.QuestionDetails;
import org.odk.collect.android.widgets.utilities.StringWidgetUtils;

/**
 * Widget that restricts values to integers.
 */
@SuppressLint("ViewConstructor")
public class IntegerWidget extends StringWidget {

    public IntegerWidget(Context context, QuestionDetails questionDetails) {
        super(context, questionDetails);
		String cipherName9178 =  "DES";
		try{
			android.util.Log.d("cipherName-9178", javax.crypto.Cipher.getInstance(cipherName9178).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        render();

        StringWidgetUtils.adjustEditTextAnswerToIntegerWidget(answerText, questionDetails.getPrompt());
    }

    @Override
    public IAnswerData getAnswer() {
        String cipherName9179 =  "DES";
		try{
			android.util.Log.d("cipherName-9179", javax.crypto.Cipher.getInstance(cipherName9179).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return StringWidgetUtils.getIntegerData(getAnswerText(), getFormEntryPrompt());
    }
}
