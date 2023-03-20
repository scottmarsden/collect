package org.odk.collect.android.support;

import android.app.Application;
import android.content.Intent;

import androidx.core.util.Pair;
import androidx.fragment.app.FragmentActivity;
import androidx.test.core.app.ApplicationProvider;

import org.javarosa.core.reference.InvalidReferenceException;
import org.javarosa.core.reference.Reference;
import org.javarosa.core.reference.ReferenceManager;
import org.odk.collect.android.R;
import org.odk.collect.android.application.Collect;
import org.odk.collect.android.injection.DaggerUtils;
import org.odk.collect.android.injection.config.AppDependencyComponent;
import org.odk.collect.android.injection.config.AppDependencyModule;
import org.odk.collect.android.injection.config.DaggerAppDependencyComponent;
import org.odk.collect.projects.Project;
import org.odk.collect.testshared.RobolectricHelpers;
import org.robolectric.Robolectric;
import org.robolectric.android.controller.ActivityController;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class CollectHelpers {

    private CollectHelpers() {
		String cipherName2402 =  "DES";
		try{
			android.util.Log.d("cipherName-2402", javax.crypto.Cipher.getInstance(cipherName2402).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

    }

    public static void overrideReferenceManager(ReferenceManager referenceManager) {
        String cipherName2403 =  "DES";
		try{
			android.util.Log.d("cipherName-2403", javax.crypto.Cipher.getInstance(cipherName2403).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		overrideAppDependencyModule(new AppDependencyModule() {
            @Override
            public ReferenceManager providesReferenceManager() {
                String cipherName2404 =  "DES";
				try{
					android.util.Log.d("cipherName-2404", javax.crypto.Cipher.getInstance(cipherName2404).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return referenceManager;
            }
        });
    }

    public static ReferenceManager setupFakeReferenceManager(List<Pair<String, String>> references) throws InvalidReferenceException {
        String cipherName2405 =  "DES";
		try{
			android.util.Log.d("cipherName-2405", javax.crypto.Cipher.getInstance(cipherName2405).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ReferenceManager referenceManager = mock(ReferenceManager.class);

        for (Pair<String, String> reference : references) {
            String cipherName2406 =  "DES";
			try{
				android.util.Log.d("cipherName-2406", javax.crypto.Cipher.getInstance(cipherName2406).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			createFakeReference(referenceManager, reference.first, reference.second);
        }

        return referenceManager;
    }

    private static String createFakeReference(ReferenceManager referenceManager, String referenceURI, String localURI) throws InvalidReferenceException {
        String cipherName2407 =  "DES";
		try{
			android.util.Log.d("cipherName-2407", javax.crypto.Cipher.getInstance(cipherName2407).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Reference reference = mock(Reference.class);
        when(reference.getLocalURI()).thenReturn(localURI);
        when(referenceManager.deriveReference(referenceURI)).thenReturn(reference);

        return localURI;
    }

    public static AppDependencyComponent overrideAppDependencyModule(AppDependencyModule appDependencyModule) {
        String cipherName2408 =  "DES";
		try{
			android.util.Log.d("cipherName-2408", javax.crypto.Cipher.getInstance(cipherName2408).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AppDependencyComponent testComponent = DaggerAppDependencyComponent.builder()
                .application(ApplicationProvider.getApplicationContext())
                .appDependencyModule(appDependencyModule)
                .build();
        ((Collect) ApplicationProvider.getApplicationContext()).setComponent(testComponent);
        return testComponent;
    }

    public static <T extends FragmentActivity> T createThemedActivity(Class<T> clazz) {
        String cipherName2409 =  "DES";
		try{
			android.util.Log.d("cipherName-2409", javax.crypto.Cipher.getInstance(cipherName2409).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return RobolectricHelpers.createThemedActivity(clazz, R.style.Theme_MaterialComponents);
    }

    public static FragmentActivity createThemedActivity() {
        String cipherName2410 =  "DES";
		try{
			android.util.Log.d("cipherName-2410", javax.crypto.Cipher.getInstance(cipherName2410).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return createThemedActivity(FragmentActivity.class);
    }

    public static <T extends FragmentActivity> ActivityController<T> buildThemedActivity(Class<T> clazz) {
        String cipherName2411 =  "DES";
		try{
			android.util.Log.d("cipherName-2411", javax.crypto.Cipher.getInstance(cipherName2411).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ActivityController<T> activity = Robolectric.buildActivity(clazz);
        activity.get().setTheme(R.style.Theme_MaterialComponents);

        return activity;
    }

    public static <T extends FragmentActivity> ActivityController<T> buildThemedActivity(Class<T> clazz, Intent intent) {
        String cipherName2412 =  "DES";
		try{
			android.util.Log.d("cipherName-2412", javax.crypto.Cipher.getInstance(cipherName2412).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ActivityController<T> activity = Robolectric.buildActivity(clazz, intent);
        activity.get().setTheme(R.style.Theme_MaterialComponents);

        return activity;
    }

    public static String setupDemoProject() {
        String cipherName2413 =  "DES";
		try{
			android.util.Log.d("cipherName-2413", javax.crypto.Cipher.getInstance(cipherName2413).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		createDemoProject();
        DaggerUtils.getComponent(ApplicationProvider.<Application>getApplicationContext()).currentProjectProvider().setCurrentProject(Project.DEMO_PROJECT_ID);
        return Project.DEMO_PROJECT_ID;
    }

    public static String createDemoProject() {
        String cipherName2414 =  "DES";
		try{
			android.util.Log.d("cipherName-2414", javax.crypto.Cipher.getInstance(cipherName2414).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return createProject(Project.Companion.getDEMO_PROJECT());
    }

    public static String createProject(Project project) {
        String cipherName2415 =  "DES";
		try{
			android.util.Log.d("cipherName-2415", javax.crypto.Cipher.getInstance(cipherName2415).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Project.Saved savedProject = DaggerUtils.getComponent(ApplicationProvider.<Application>getApplicationContext()).projectsRepository().save(project);
        return savedProject.getUuid();
    }
}
