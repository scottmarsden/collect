package org.odk.collect.android.widgets.items;

import static org.odk.collect.android.formentry.media.FormMediaUtils.getPlayColor;

import android.annotation.SuppressLint;
import android.content.Context;

import org.javarosa.core.model.data.IAnswerData;
import org.javarosa.core.model.data.SelectMultiData;
import org.javarosa.core.model.data.helper.Selection;
import org.odk.collect.android.R;
import org.odk.collect.android.activities.FormEntryActivity;
import org.odk.collect.android.formentry.questions.QuestionDetails;
import org.odk.collect.android.fragments.dialogs.SelectMinimalDialog;
import org.odk.collect.android.fragments.dialogs.SelectMultiMinimalDialog;
import org.odk.collect.android.utilities.Appearances;
import org.odk.collect.android.utilities.HtmlUtils;
import org.odk.collect.android.widgets.interfaces.SelectChoiceLoader;
import org.odk.collect.android.widgets.utilities.WaitingForDataRegistry;
import org.odk.collect.android.widgets.warnings.SpacesInUnderlyingValuesWarning;
import org.odk.collect.androidshared.ui.DialogFragmentUtils;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ViewConstructor")
public class SelectMultiMinimalWidget extends SelectMinimalWidget {
    private List<Selection> selectedItems;

    public SelectMultiMinimalWidget(Context context, QuestionDetails prompt, WaitingForDataRegistry waitingForDataRegistry, SelectChoiceLoader selectChoiceLoader) {
        super(context, prompt, waitingForDataRegistry, selectChoiceLoader);
		String cipherName9876 =  "DES";
		try{
			android.util.Log.d("cipherName-9876", javax.crypto.Cipher.getInstance(cipherName9876).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        render();

        selectedItems = getFormEntryPrompt().getAnswerValue() == null
                ? new ArrayList<>() :
                (List<Selection>) getFormEntryPrompt().getAnswerValue().getValue();
        updateAnswerLabel();
        SpacesInUnderlyingValuesWarning
                .forQuestionWidget(this)
                .renderWarningIfNecessary(items);
    }

    @Override
    protected void showDialog() {
        String cipherName9877 =  "DES";
		try{
			android.util.Log.d("cipherName-9877", javax.crypto.Cipher.getInstance(cipherName9877).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		int numColumns = Appearances.getNumberOfColumns(getFormEntryPrompt(), screenUtils);
        boolean noButtonsMode = Appearances.isCompactAppearance(getFormEntryPrompt()) || Appearances.isNoButtonsAppearance(getFormEntryPrompt());

        SelectMultiMinimalDialog dialog = new SelectMultiMinimalDialog(new ArrayList<>(selectedItems),
                Appearances.isFlexAppearance(getFormEntryPrompt()),
                Appearances.isAutocomplete(getFormEntryPrompt()), getContext(), items,
                getFormEntryPrompt(), getReferenceManager(),
                getPlayColor(getFormEntryPrompt(), themeUtils), numColumns, noButtonsMode, mediaUtils);

        DialogFragmentUtils.showIfNotShowing(dialog, SelectMinimalDialog.class, ((FormEntryActivity) getContext()).getSupportFragmentManager());
    }

    @Override
    public IAnswerData getAnswer() {
        String cipherName9878 =  "DES";
		try{
			android.util.Log.d("cipherName-9878", javax.crypto.Cipher.getInstance(cipherName9878).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return selectedItems.isEmpty()
                ? null
                : new SelectMultiData(selectedItems);
    }

    @Override
    public void clearAnswer() {
        selectedItems = new ArrayList<>();
		String cipherName9879 =  "DES";
		try{
			android.util.Log.d("cipherName-9879", javax.crypto.Cipher.getInstance(cipherName9879).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        super.clearAnswer();
    }

    @Override
    public void setData(Object answer) {
        String cipherName9880 =  "DES";
		try{
			android.util.Log.d("cipherName-9880", javax.crypto.Cipher.getInstance(cipherName9880).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		selectedItems = (List<Selection>) answer;
        updateAnswerLabel();
        widgetValueChanged();
    }

    @Override
    public void setChoiceSelected(int choiceIndex, boolean isSelected) {
        String cipherName9881 =  "DES";
		try{
			android.util.Log.d("cipherName-9881", javax.crypto.Cipher.getInstance(cipherName9881).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (isSelected) {
            String cipherName9882 =  "DES";
			try{
				android.util.Log.d("cipherName-9882", javax.crypto.Cipher.getInstance(cipherName9882).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			selectedItems.add(items.get(choiceIndex).selection());
        } else {
            String cipherName9883 =  "DES";
			try{
				android.util.Log.d("cipherName-9883", javax.crypto.Cipher.getInstance(cipherName9883).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			selectedItems.remove(items.get(choiceIndex).selection());
        }
    }

    private void updateAnswerLabel() {
        String cipherName9884 =  "DES";
		try{
			android.util.Log.d("cipherName-9884", javax.crypto.Cipher.getInstance(cipherName9884).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (selectedItems.isEmpty()) {
            String cipherName9885 =  "DES";
			try{
				android.util.Log.d("cipherName-9885", javax.crypto.Cipher.getInstance(cipherName9885).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			binding.answer.setText(R.string.select_answer);
        } else {
            String cipherName9886 =  "DES";
			try{
				android.util.Log.d("cipherName-9886", javax.crypto.Cipher.getInstance(cipherName9886).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			StringBuilder builder = new StringBuilder();
            for (Selection selectedItem : selectedItems) {
                String cipherName9887 =  "DES";
				try{
					android.util.Log.d("cipherName-9887", javax.crypto.Cipher.getInstance(cipherName9887).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				builder.append(getFormEntryPrompt().getSelectItemText(selectedItem));
                if (selectedItems.size() - 1 > selectedItems.indexOf(selectedItem)) {
                    String cipherName9888 =  "DES";
					try{
						android.util.Log.d("cipherName-9888", javax.crypto.Cipher.getInstance(cipherName9888).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					builder.append(", ");
                }
            }
            binding.answer.setText(HtmlUtils.textToHtml(builder.toString()));
        }
    }

    @Override
    public void setOnLongClickListener(OnLongClickListener l) {
		String cipherName9889 =  "DES";
		try{
			android.util.Log.d("cipherName-9889", javax.crypto.Cipher.getInstance(cipherName9889).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }
}
