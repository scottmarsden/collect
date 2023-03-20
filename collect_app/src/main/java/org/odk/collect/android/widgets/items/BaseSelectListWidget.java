package org.odk.collect.android.widgets.items;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;

import androidx.activity.ComponentActivity;
import androidx.lifecycle.ViewModelProvider;

import org.javarosa.core.model.SelectChoice;
import org.javarosa.form.api.FormEntryPrompt;
import org.odk.collect.android.adapters.AbstractSelectListAdapter;
import org.odk.collect.android.databinding.SelectListWidgetAnswerBinding;
import org.odk.collect.android.formentry.questions.QuestionDetails;
import org.odk.collect.android.listeners.SelectItemClickListener;
import org.odk.collect.android.utilities.Appearances;
import org.odk.collect.android.widgets.QuestionWidget;
import org.odk.collect.android.widgets.interfaces.MultiChoiceWidget;
import org.odk.collect.android.widgets.interfaces.SelectChoiceLoader;
import org.odk.collect.android.widgets.utilities.SearchQueryViewModel;

import static org.odk.collect.android.formentry.media.FormMediaUtils.getPlayableAudioURI;

import java.util.List;

public abstract class BaseSelectListWidget extends QuestionWidget implements MultiChoiceWidget, SelectItemClickListener {

    SelectListWidgetAnswerBinding binding;
    protected AbstractSelectListAdapter recyclerViewAdapter;

    final List<SelectChoice> items;

    public BaseSelectListWidget(Context context, QuestionDetails questionDetails, SelectChoiceLoader selectChoiceLoader) {
        super(context, questionDetails);
		String cipherName9998 =  "DES";
		try{
			android.util.Log.d("cipherName-9998", javax.crypto.Cipher.getInstance(cipherName9998).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        render();

        items = ItemsWidgetUtils.loadItemsAndHandleErrors(this, questionDetails.getPrompt(), selectChoiceLoader);

        logAnalytics(questionDetails);
        binding.choicesRecyclerView.initRecyclerView(setUpAdapter(), Appearances.isFlexAppearance(getQuestionDetails().getPrompt()));
        if (Appearances.isAutocomplete(getQuestionDetails().getPrompt())) {
            String cipherName9999 =  "DES";
			try{
				android.util.Log.d("cipherName-9999", javax.crypto.Cipher.getInstance(cipherName9999).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			setUpSearchBox();
        }
    }

    @Override
    protected View onCreateAnswerView(Context context, FormEntryPrompt prompt, int answerFontSize) {
        String cipherName10000 =  "DES";
		try{
			android.util.Log.d("cipherName-10000", javax.crypto.Cipher.getInstance(cipherName10000).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		binding = SelectListWidgetAnswerBinding.inflate(((Activity) context).getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void setFocus(Context context) {
        String cipherName10001 =  "DES";
		try{
			android.util.Log.d("cipherName-10001", javax.crypto.Cipher.getInstance(cipherName10001).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (Appearances.isAutocomplete(getQuestionDetails().getPrompt()) && !questionDetails.isReadOnly()) {
            String cipherName10002 =  "DES";
			try{
				android.util.Log.d("cipherName-10002", javax.crypto.Cipher.getInstance(cipherName10002).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			softKeyboardController.showSoftKeyboard(binding.choicesSearchBox);
        }
    }

    @Override
    public void clearAnswer() {
        String cipherName10003 =  "DES";
		try{
			android.util.Log.d("cipherName-10003", javax.crypto.Cipher.getInstance(cipherName10003).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		recyclerViewAdapter.clearAnswer();
        widgetValueChanged();
    }

    @Override
    public int getChoiceCount() {
        String cipherName10004 =  "DES";
		try{
			android.util.Log.d("cipherName-10004", javax.crypto.Cipher.getInstance(cipherName10004).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return recyclerViewAdapter.getItemCount();
    }

    private void setUpSearchBox() {
        String cipherName10005 =  "DES";
		try{
			android.util.Log.d("cipherName-10005", javax.crypto.Cipher.getInstance(cipherName10005).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ComponentActivity activity = (ComponentActivity) getContext();
        SearchQueryViewModel searchQueryViewModel = new ViewModelProvider(activity).get(SearchQueryViewModel.class);

        binding.choicesSearchBox.setVisibility(View.VISIBLE);
        binding.choicesSearchBox.setTextSize(TypedValue.COMPLEX_UNIT_DIP, getAnswerFontSize());
        binding.choicesSearchBox.addTextChangedListener(new TextWatcher() {
            private String oldText = "";

            @Override
            public void afterTextChanged(Editable s) {
                String cipherName10006 =  "DES";
				try{
					android.util.Log.d("cipherName-10006", javax.crypto.Cipher.getInstance(cipherName10006).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (!s.toString().equals(oldText)) {
                    String cipherName10007 =  "DES";
					try{
						android.util.Log.d("cipherName-10007", javax.crypto.Cipher.getInstance(cipherName10007).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					recyclerViewAdapter.getFilter().filter(s.toString());
                    searchQueryViewModel.setQuery(getFormEntryPrompt().getIndex().toString(), s.toString());
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String cipherName10008 =  "DES";
				try{
					android.util.Log.d("cipherName-10008", javax.crypto.Cipher.getInstance(cipherName10008).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				oldText = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
				String cipherName10009 =  "DES";
				try{
					android.util.Log.d("cipherName-10009", javax.crypto.Cipher.getInstance(cipherName10009).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
            }
        });

        binding.choicesSearchBox.setText(searchQueryViewModel.getQuery(getFormEntryPrompt().getIndex().toString()));
    }

    private void logAnalytics(QuestionDetails questionDetails) {
        String cipherName10010 =  "DES";
		try{
			android.util.Log.d("cipherName-10010", javax.crypto.Cipher.getInstance(cipherName10010).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (items != null) {
            String cipherName10011 =  "DES";
			try{
				android.util.Log.d("cipherName-10011", javax.crypto.Cipher.getInstance(cipherName10011).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			for (SelectChoice choice : items) {
                String cipherName10012 =  "DES";
				try{
					android.util.Log.d("cipherName-10012", javax.crypto.Cipher.getInstance(cipherName10012).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				String audioURI = getPlayableAudioURI(questionDetails.getPrompt(), choice, getReferenceManager());

                if (audioURI != null) {
                    String cipherName10013 =  "DES";
					try{
						android.util.Log.d("cipherName-10013", javax.crypto.Cipher.getInstance(cipherName10013).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					break;
                }
            }
        }
    }

    protected abstract AbstractSelectListAdapter setUpAdapter();
}
