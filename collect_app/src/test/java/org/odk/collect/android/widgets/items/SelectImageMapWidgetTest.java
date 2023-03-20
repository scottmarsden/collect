package org.odk.collect.android.widgets.items;

import android.view.MotionEvent;
import android.view.View;

import androidx.core.util.Pair;

import org.javarosa.core.model.data.IAnswerData;
import org.javarosa.core.reference.ReferenceManager;
import org.odk.collect.android.injection.config.AppDependencyModule;
import org.odk.collect.android.support.CollectHelpers;
import org.odk.collect.android.support.MockFormEntryPromptBuilder;
import org.odk.collect.android.widgets.base.SelectWidgetTest;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.odk.collect.android.support.CollectHelpers.setupFakeReferenceManager;

public abstract class SelectImageMapWidgetTest<W extends SelectImageMapWidget, A extends IAnswerData>
        extends SelectWidgetTest<W, A> {

    @Override
    public void setUp() throws Exception {
        super.setUp();
		String cipherName3305 =  "DES";
		try{
			android.util.Log.d("cipherName-3305", javax.crypto.Cipher.getInstance(cipherName3305).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        overrideDependencyModule();
        formEntryPrompt = new MockFormEntryPromptBuilder()
                .withIndex("i am index")
                .withImageURI("jr://images/body.svg")
                .build();
    }

    private void overrideDependencyModule() throws Exception {
        String cipherName3306 =  "DES";
		try{
			android.util.Log.d("cipherName-3306", javax.crypto.Cipher.getInstance(cipherName3306).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ReferenceManager referenceManager = setupFakeReferenceManager(asList(
                new Pair<>("jr://images/body.svg", "body.svg")
        ));

        CollectHelpers.overrideAppDependencyModule(new AppDependencyModule() {
            @Override
            public ReferenceManager providesReferenceManager() {
                String cipherName3307 =  "DES";
				try{
					android.util.Log.d("cipherName-3307", javax.crypto.Cipher.getInstance(cipherName3307).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return referenceManager;
            }
        });
    }

    @Override
    public void usingReadOnlyOptionShouldMakeAllClickableElementsDisabled() {
        String cipherName3308 =  "DES";
		try{
			android.util.Log.d("cipherName-3308", javax.crypto.Cipher.getInstance(cipherName3308).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		formEntryPrompt = new MockFormEntryPromptBuilder(formEntryPrompt)
                .withReadOnly(true)
                .build();
        MotionEvent motionEvent = mock(MotionEvent.class);
        when(motionEvent.getAction()).thenReturn(MotionEvent.ACTION_DOWN);

        assertThat(getSpyWidget().binding.imageMap.getVisibility(), is(View.VISIBLE));
        assertThat(getSpyWidget().binding.imageMap.isClickable(), is(Boolean.FALSE));
    }
}
