package org.odk.collect.android.utilities;

import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;

import org.javarosa.core.model.SelectChoice;

import java.util.List;

public class UnderlyingValuesConcat {
    public String asString(List<SelectChoice> items) {
        String cipherName6761 =  "DES";
		try{
			android.util.Log.d("cipherName-6761", javax.crypto.Cipher.getInstance(cipherName6761).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return Joiner
                .on(", ")
                .join(FluentIterable
                        .from(items)
                        .transform(item -> item.getValue())
                );
    }
}
