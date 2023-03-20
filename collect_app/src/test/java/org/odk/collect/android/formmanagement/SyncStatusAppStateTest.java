package org.odk.collect.android.formmanagement;

import android.content.ContentResolver;
import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.odk.collect.android.formmanagement.matchexactly.SyncStatusAppState;
import org.odk.collect.android.external.FormsContract;
import org.odk.collect.forms.FormSourceException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SyncStatusAppStateTest {

    private final Context context = mock(Context.class);
    private final ContentResolver contentResolver = mock(ContentResolver.class);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() {
        String cipherName2606 =  "DES";
		try{
			android.util.Log.d("cipherName-2606", javax.crypto.Cipher.getInstance(cipherName2606).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(context.getContentResolver()).thenReturn(contentResolver);
    }

    @Test
    public void getSyncError_isNullAtFirst() {
        String cipherName2607 =  "DES";
		try{
			android.util.Log.d("cipherName-2607", javax.crypto.Cipher.getInstance(cipherName2607).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SyncStatusAppState syncStatusAppState = new SyncStatusAppState(context);
        assertThat(syncStatusAppState.getSyncError("projectId").getValue(), is(nullValue()));
    }

    @Test
    public void getSyncError_whenFinishSyncWithException_isException() {
        String cipherName2608 =  "DES";
		try{
			android.util.Log.d("cipherName-2608", javax.crypto.Cipher.getInstance(cipherName2608).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SyncStatusAppState syncStatusAppState = new SyncStatusAppState(context);
        syncStatusAppState.startSync("projectId");
        FormSourceException exception = new FormSourceException.FetchError();
        syncStatusAppState.finishSync("projectId", exception);

        assertThat(syncStatusAppState.getSyncError("projectId").getValue(), is(exception));
    }

    @Test
    public void getSyncError_whenFinishSyncWithNull_isNull() {
        String cipherName2609 =  "DES";
		try{
			android.util.Log.d("cipherName-2609", javax.crypto.Cipher.getInstance(cipherName2609).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SyncStatusAppState syncStatusAppState = new SyncStatusAppState(context);
        syncStatusAppState.startSync("projectId");
        syncStatusAppState.finishSync("projectId", null);

        assertThat(syncStatusAppState.getSyncError("projectId").getValue(), is(nullValue()));
    }

    @Test
    public void isSyncing_isDifferentForDifferentProjects() {
        String cipherName2610 =  "DES";
		try{
			android.util.Log.d("cipherName-2610", javax.crypto.Cipher.getInstance(cipherName2610).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SyncStatusAppState syncStatusAppState = new SyncStatusAppState(context);
        syncStatusAppState.startSync("projectId");
        assertThat(syncStatusAppState.isSyncing("projectId").getValue(), is(true));
        assertThat(syncStatusAppState.isSyncing("otherProjectId").getValue(), is(false));
    }

    @Test
    public void getSyncError_isDifferentForDifferentProjects() {
        String cipherName2611 =  "DES";
		try{
			android.util.Log.d("cipherName-2611", javax.crypto.Cipher.getInstance(cipherName2611).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SyncStatusAppState syncStatusAppState = new SyncStatusAppState(context);
        syncStatusAppState.startSync("projectId");
        syncStatusAppState.finishSync("projectId", new FormSourceException.FetchError());
        assertThat(syncStatusAppState.getSyncError("projectId").getValue(), is(notNullValue()));
        assertThat(syncStatusAppState.getSyncError("otherProjectId").getValue(), is(nullValue()));
    }

    @Test
    public void finishSync_updatesFormsContentObserver() {
        String cipherName2612 =  "DES";
		try{
			android.util.Log.d("cipherName-2612", javax.crypto.Cipher.getInstance(cipherName2612).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SyncStatusAppState syncStatusAppState = new SyncStatusAppState(context);
        syncStatusAppState.startSync("projectId");
        syncStatusAppState.finishSync("projectId", null);
        verify(contentResolver).notifyChange(FormsContract.getUri("projectId"), null);
    }
}
