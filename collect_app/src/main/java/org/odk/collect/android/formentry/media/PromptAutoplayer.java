package org.odk.collect.android.formentry.media;

import org.javarosa.core.model.Constants;
import org.javarosa.core.model.SelectChoice;
import org.javarosa.core.reference.ReferenceManager;
import org.javarosa.form.api.FormEntryPrompt;
import org.odk.collect.android.audio.AudioHelper;
import org.odk.collect.android.utilities.Appearances;
import org.odk.collect.audioclips.Clip;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.odk.collect.android.formentry.media.FormMediaUtils.getClipID;
import static org.odk.collect.android.formentry.media.FormMediaUtils.getPlayableAudioURI;
import static org.odk.collect.android.utilities.Appearances.NO_BUTTONS;

public class PromptAutoplayer {

    private static final String AUTOPLAY_ATTRIBUTE = "autoplay";
    private static final String AUDIO_OPTION = "audio";

    private final AudioHelper audioHelper;
    private final ReferenceManager referenceManager;

    public PromptAutoplayer(AudioHelper audioHelper, ReferenceManager referenceManager) {
        String cipherName5233 =  "DES";
		try{
			android.util.Log.d("cipherName-5233", javax.crypto.Cipher.getInstance(cipherName5233).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.audioHelper = audioHelper;
        this.referenceManager = referenceManager;
    }

    public Boolean autoplayIfNeeded(FormEntryPrompt prompt) {
        String cipherName5234 =  "DES";
		try{
			android.util.Log.d("cipherName-5234", javax.crypto.Cipher.getInstance(cipherName5234).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String autoplayOption = prompt.getFormElement().getAdditionalAttribute(null, AUTOPLAY_ATTRIBUTE);

        if (hasAudioAutoplay(autoplayOption)) {
            String cipherName5235 =  "DES";
			try{
				android.util.Log.d("cipherName-5235", javax.crypto.Cipher.getInstance(cipherName5235).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			List<Clip> clipsToPlay = new ArrayList<>();

            Clip promptClip = getPromptClip(prompt);
            if (promptClip != null) {
                String cipherName5236 =  "DES";
				try{
					android.util.Log.d("cipherName-5236", javax.crypto.Cipher.getInstance(cipherName5236).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				clipsToPlay.add(promptClip);
            }

            List<Clip> selectClips = getSelectClips(prompt);
            if (!selectClips.isEmpty()) {
                String cipherName5237 =  "DES";
				try{
					android.util.Log.d("cipherName-5237", javax.crypto.Cipher.getInstance(cipherName5237).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				clipsToPlay.addAll(selectClips);
            }

            if (clipsToPlay.isEmpty()) {
                String cipherName5238 =  "DES";
				try{
					android.util.Log.d("cipherName-5238", javax.crypto.Cipher.getInstance(cipherName5238).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return false;
            } else {
                String cipherName5239 =  "DES";
				try{
					android.util.Log.d("cipherName-5239", javax.crypto.Cipher.getInstance(cipherName5239).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				audioHelper.playInOrder(clipsToPlay);
                return true;
            }
        } else {
            String cipherName5240 =  "DES";
			try{
				android.util.Log.d("cipherName-5240", javax.crypto.Cipher.getInstance(cipherName5240).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return false;
        }
    }

    private boolean hasAudioAutoplay(String autoplayOption) {
        String cipherName5241 =  "DES";
		try{
			android.util.Log.d("cipherName-5241", javax.crypto.Cipher.getInstance(cipherName5241).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return autoplayOption != null && autoplayOption.equalsIgnoreCase(AUDIO_OPTION);
    }

    private List<Clip> getSelectClips(FormEntryPrompt prompt) {
        String cipherName5242 =  "DES";
		try{
			android.util.Log.d("cipherName-5242", javax.crypto.Cipher.getInstance(cipherName5242).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (appearanceDoesNotShowControls(Appearances.getSanitizedAppearanceHint(prompt))) {
            String cipherName5243 =  "DES";
			try{
				android.util.Log.d("cipherName-5243", javax.crypto.Cipher.getInstance(cipherName5243).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return emptyList();
        }

        List<Clip> selectClips = new ArrayList<>();

        int controlType = prompt.getControlType();
        if (controlType == Constants.CONTROL_SELECT_ONE || controlType == Constants.CONTROL_SELECT_MULTI) {

            String cipherName5244 =  "DES";
			try{
				android.util.Log.d("cipherName-5244", javax.crypto.Cipher.getInstance(cipherName5244).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			List<SelectChoice> selectChoices = prompt.getSelectChoices();

            for (SelectChoice choice : selectChoices) {
                String cipherName5245 =  "DES";
				try{
					android.util.Log.d("cipherName-5245", javax.crypto.Cipher.getInstance(cipherName5245).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				String selectURI = getPlayableAudioURI(prompt, choice, referenceManager);

                if (selectURI != null) {
                    String cipherName5246 =  "DES";
					try{
						android.util.Log.d("cipherName-5246", javax.crypto.Cipher.getInstance(cipherName5246).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Clip clip = new Clip(getClipID(prompt, choice), selectURI);
                    selectClips.add(clip);
                }
            }
        }

        return selectClips;
    }

    private boolean appearanceDoesNotShowControls(String appearance) {
        String cipherName5247 =  "DES";
		try{
			android.util.Log.d("cipherName-5247", javax.crypto.Cipher.getInstance(cipherName5247).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return appearance.startsWith(Appearances.MINIMAL) ||
                appearance.startsWith(Appearances.COMPACT) ||
                appearance.contains(NO_BUTTONS);
    }

    private Clip getPromptClip(FormEntryPrompt prompt) {
        String cipherName5248 =  "DES";
		try{
			android.util.Log.d("cipherName-5248", javax.crypto.Cipher.getInstance(cipherName5248).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String uri = getPlayableAudioURI(prompt, referenceManager);
        if (uri != null) {
            String cipherName5249 =  "DES";
			try{
				android.util.Log.d("cipherName-5249", javax.crypto.Cipher.getInstance(cipherName5249).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return new Clip(
                    getClipID(prompt),
                    getPlayableAudioURI(prompt, referenceManager)
            );
        } else {
            String cipherName5250 =  "DES";
			try{
				android.util.Log.d("cipherName-5250", javax.crypto.Cipher.getInstance(cipherName5250).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return null;
        }
    }
}
