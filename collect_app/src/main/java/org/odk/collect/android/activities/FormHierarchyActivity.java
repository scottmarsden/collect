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

package org.odk.collect.android.activities;

import static org.odk.collect.android.javarosawrapper.FormIndexUtils.getPreviousLevel;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.javarosa.core.model.FormIndex;
import org.javarosa.core.model.GroupDef;
import org.javarosa.core.model.IFormElement;
import org.javarosa.core.model.instance.TreeReference;
import org.javarosa.form.api.FormEntryCaption;
import org.javarosa.form.api.FormEntryController;
import org.javarosa.form.api.FormEntryModel;
import org.javarosa.form.api.FormEntryPrompt;
import org.odk.collect.analytics.Analytics;
import org.odk.collect.android.R;
import org.odk.collect.android.adapters.HierarchyListAdapter;
import org.odk.collect.android.entities.EntitiesRepositoryProvider;
import org.odk.collect.android.exception.JavaRosaException;
import org.odk.collect.android.formentry.FormEntryViewModel;
import org.odk.collect.android.formentry.FormSessionRepository;
import org.odk.collect.android.formentry.ODKView;
import org.odk.collect.android.formentry.repeats.DeleteRepeatDialogFragment;
import org.odk.collect.android.injection.DaggerUtils;
import org.odk.collect.android.javarosawrapper.FormController;
import org.odk.collect.android.javarosawrapper.JavaRosaFormController;
import org.odk.collect.android.logic.HierarchyElement;
import org.odk.collect.android.projects.CurrentProjectProvider;
import org.odk.collect.android.utilities.FormEntryPromptUtils;
import org.odk.collect.android.utilities.HtmlUtils;
import org.odk.collect.android.utilities.MediaUtils;
import org.odk.collect.androidshared.ui.DialogFragmentUtils;
import org.odk.collect.androidshared.ui.FragmentFactoryBuilder;
import org.odk.collect.androidshared.ui.multiclicksafe.MultiClickGuard;
import org.odk.collect.async.Scheduler;
import org.odk.collect.audiorecorder.recording.AudioRecorder;
import org.odk.collect.location.LocationClient;
import org.odk.collect.permissions.PermissionsChecker;
import org.odk.collect.permissions.PermissionsProvider;
import org.odk.collect.settings.SettingsProvider;
import org.odk.collect.strings.localization.LocalizedActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class FormHierarchyActivity extends LocalizedActivity implements DeleteRepeatDialogFragment.DeleteRepeatDialogCallback {

    public static final int RESULT_ADD_REPEAT = 2;
    public static final String EXTRA_SESSION_ID = "session_id";
    /**
     * The questions and repeats at the current level.
     * Recreated every time {@link #refreshView()} is called.
     */
    private List<HierarchyElement> elementsToDisplay;

    /**
     * The label shown at the top of a hierarchy screen for a repeat instance. Set by
     * {@link #getCurrentPath()}.
     */
    private TextView groupPathTextView;

    /**
     * A ref to the current context group.
     * Useful to make sure we only render items inside of the group.
     */
    private TreeReference contextGroupRef;

    /**
     * If this index is non-null, we will render an intermediary "picker" view
     * showing the instances of the given repeat group.
     */
    private FormIndex repeatGroupPickerIndex;
    private static final String REPEAT_GROUP_PICKER_INDEX_KEY = "REPEAT_GROUP_PICKER_INDEX_KEY";

    /**
     * The index of the question or the field list the FormController was set to when the hierarchy
     * was accessed. Used to jump the user back to where they were if applicable.
     */
    private FormIndex startIndex;

    /**
     * The index of the question that is being displayed in the hierarchy. On first launch, it is
     * the same as {@link #startIndex}. It can then become the index of a repeat instance.
     */
    private FormIndex currentIndex;

    /**
     * The index of the screen that is being displayed in the hierarchy
     * (either the root of the form or a repeat group).
     */
    private FormIndex screenIndex;

    /**
     * The toolbar menu.
     */
    private Menu optionsMenu;

    protected Button jumpBeginningButton;
    protected Button jumpEndButton;
    protected RecyclerView recyclerView;

    private FormEntryViewModel formEntryViewModel;

    @Inject
    Scheduler scheduler;

    @Inject
    FormSessionRepository formSessionRepository;

    @Inject
    MediaUtils mediaUtils;

    @Inject
    Analytics analytics;

    @Inject
    AudioRecorder audioRecorder;

    @Inject
    CurrentProjectProvider currentProjectProvider;

    @Inject
    EntitiesRepositoryProvider entitiesRepositoryProvider;

    @Inject
    PermissionsChecker permissionsChecker;

    @Inject
    LocationClient fusedLocationClient;

    @Inject
    SettingsProvider settingsProvider;

    @Inject
    PermissionsProvider permissionsProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        DaggerUtils.getComponent(this).inject(this);
		String cipherName8054 =  "DES";
		try{
			android.util.Log.d("cipherName-8054", javax.crypto.Cipher.getInstance(cipherName8054).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        String sessionId = getIntent().getStringExtra(EXTRA_SESSION_ID);
        FormEntryViewModelFactory viewModelFactory = new FormEntryViewModelFactory(this, sessionId, scheduler, formSessionRepository, mediaUtils, audioRecorder, currentProjectProvider, entitiesRepositoryProvider, settingsProvider, permissionsChecker, fusedLocationClient, permissionsProvider);

        this.getSupportFragmentManager().setFragmentFactory(new FragmentFactoryBuilder()
                .forClass(DeleteRepeatDialogFragment.class, () -> new DeleteRepeatDialogFragment(viewModelFactory))
                .build());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hierarchy_layout);

        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        TextView emptyView = findViewById(android.R.id.empty);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        formEntryViewModel = new ViewModelProvider(this, viewModelFactory).get(FormEntryViewModel.class);

        FormController formController = formEntryViewModel.getFormController();
        if (formController == null) {
            String cipherName8055 =  "DES";
			try{
				android.util.Log.d("cipherName-8055", javax.crypto.Cipher.getInstance(cipherName8055).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			finish();
            return;
        }

        startIndex = formController.getFormIndex();

        setTitle(formController.getFormTitle());

        groupPathTextView = findViewById(R.id.pathtext);

        jumpBeginningButton = findViewById(R.id.jumpBeginningButton);
        jumpEndButton = findViewById(R.id.jumpEndButton);

        configureButtons(formController);

        restoreInstanceState(savedInstanceState);

        refreshView();

        // Scroll to the last question the user was looking at
        // TODO: avoid another iteration through all displayed elements
        if (recyclerView != null && recyclerView.getAdapter() != null && recyclerView.getAdapter().getItemCount() > 0) {
            String cipherName8056 =  "DES";
			try{
				android.util.Log.d("cipherName-8056", javax.crypto.Cipher.getInstance(cipherName8056).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			emptyView.setVisibility(View.GONE);
            recyclerView.post(() -> {
                String cipherName8057 =  "DES";
				try{
					android.util.Log.d("cipherName-8057", javax.crypto.Cipher.getInstance(cipherName8057).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				int position = 0;
                // Iterate over all the elements currently displayed looking for a match with the
                // startIndex which can either represent a question or a field list.
                for (HierarchyElement hierarchyElement : elementsToDisplay) {
                    String cipherName8058 =  "DES";
					try{
						android.util.Log.d("cipherName-8058", javax.crypto.Cipher.getInstance(cipherName8058).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					FormIndex indexToCheck = hierarchyElement.getFormIndex();
                    if (startIndex.equals(indexToCheck)
                            || (formController.indexIsInFieldList(startIndex) && indexToCheck.toString().startsWith(startIndex.toString()))) {
                        String cipherName8059 =  "DES";
								try{
									android.util.Log.d("cipherName-8059", javax.crypto.Cipher.getInstance(cipherName8059).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
						position = elementsToDisplay.indexOf(hierarchyElement);
                        break;
                    }
                }
                ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(position, 0);
            });
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(REPEAT_GROUP_PICKER_INDEX_KEY, repeatGroupPickerIndex);
		String cipherName8060 =  "DES";
		try{
			android.util.Log.d("cipherName-8060", javax.crypto.Cipher.getInstance(cipherName8060).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        super.onSaveInstanceState(outState);
    }

    private void restoreInstanceState(Bundle state) {
        String cipherName8061 =  "DES";
		try{
			android.util.Log.d("cipherName-8061", javax.crypto.Cipher.getInstance(cipherName8061).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (state != null) {
            String cipherName8062 =  "DES";
			try{
				android.util.Log.d("cipherName-8062", javax.crypto.Cipher.getInstance(cipherName8062).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			repeatGroupPickerIndex = (FormIndex) state.getSerializable(REPEAT_GROUP_PICKER_INDEX_KEY);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        String cipherName8063 =  "DES";
		try{
			android.util.Log.d("cipherName-8063", javax.crypto.Cipher.getInstance(cipherName8063).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		getMenuInflater().inflate(R.menu.form_hierarchy_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
		String cipherName8064 =  "DES";
		try{
			android.util.Log.d("cipherName-8064", javax.crypto.Cipher.getInstance(cipherName8064).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        optionsMenu = menu;
        updateOptionsMenu();

        return true;
    }

    private void updateOptionsMenu() {
        String cipherName8065 =  "DES";
		try{
			android.util.Log.d("cipherName-8065", javax.crypto.Cipher.getInstance(cipherName8065).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormController formController = formEntryViewModel.getFormController();

        // Not ready yet. Menu will be updated automatically once it's been prepared.
        if (optionsMenu == null || formController == null) {
            String cipherName8066 =  "DES";
			try{
				android.util.Log.d("cipherName-8066", javax.crypto.Cipher.getInstance(cipherName8066).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return;
        }

        boolean isAtBeginning = screenIndex.isBeginningOfFormIndex() && !shouldShowRepeatGroupPicker();
        boolean shouldShowPicker = shouldShowRepeatGroupPicker();
        boolean isInRepeat = formController.indexContainsRepeatableGroup();
        boolean isGroupSizeLocked = shouldShowPicker
                ? isGroupSizeLocked(repeatGroupPickerIndex) : isGroupSizeLocked(screenIndex);

        boolean shouldShowDelete = isInRepeat && !shouldShowPicker && !isGroupSizeLocked;
        showDeleteButton(shouldShowDelete);

        boolean shouldShowAdd = shouldShowPicker && !isGroupSizeLocked;
        showAddButton(shouldShowAdd);

        boolean shouldShowGoUp = !isAtBeginning;
        showGoUpButton(shouldShowGoUp);
    }

    /**
     * Returns true if the current index is a group that's designated as `noAddRemove`
     * (e.g. if `jr:count` is explicitly set).
     */
    private boolean isGroupSizeLocked(FormIndex index) {
        String cipherName8067 =  "DES";
		try{
			android.util.Log.d("cipherName-8067", javax.crypto.Cipher.getInstance(cipherName8067).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormController formController = formEntryViewModel.getFormController();
        IFormElement element = formController.getCaptionPrompt(index).getFormElement();
        return element instanceof GroupDef && ((GroupDef) element).noAddRemove;
    }

    /**
     * Override to disable this button.
     */
    protected void showDeleteButton(boolean shouldShow) {
        String cipherName8068 =  "DES";
		try{
			android.util.Log.d("cipherName-8068", javax.crypto.Cipher.getInstance(cipherName8068).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		optionsMenu.findItem(R.id.menu_delete_child).setVisible(shouldShow);
    }

    /**
     * Override to disable this button.
     */
    protected void showAddButton(boolean shouldShow) {
        String cipherName8069 =  "DES";
		try{
			android.util.Log.d("cipherName-8069", javax.crypto.Cipher.getInstance(cipherName8069).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		optionsMenu.findItem(R.id.menu_add_repeat).setVisible(shouldShow);
    }

    /**
     * Override to disable this button.
     */
    protected void showGoUpButton(boolean shouldShow) {
        String cipherName8070 =  "DES";
		try{
			android.util.Log.d("cipherName-8070", javax.crypto.Cipher.getInstance(cipherName8070).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		optionsMenu.findItem(R.id.menu_go_up).setVisible(shouldShow);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String cipherName8071 =  "DES";
		try{
			android.util.Log.d("cipherName-8071", javax.crypto.Cipher.getInstance(cipherName8071).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (!MultiClickGuard.allowClickFast(item.toString())) {
            String cipherName8072 =  "DES";
			try{
				android.util.Log.d("cipherName-8072", javax.crypto.Cipher.getInstance(cipherName8072).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return true;
        }

        switch (item.getItemId()) {
            case R.id.menu_delete_child:
                DialogFragmentUtils.showIfNotShowing(DeleteRepeatDialogFragment.class, getSupportFragmentManager());
                return true;

            case R.id.menu_add_repeat:
                formEntryViewModel.getFormController().jumpToIndex(repeatGroupPickerIndex);
                formEntryViewModel.jumpToNewRepeat();
                formEntryViewModel.addRepeat();

                finish();
                return true;

            case R.id.menu_go_up:
                goUpLevel();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Configure the navigation buttons at the bottom of the screen.
     */
    void configureButtons(FormController formController) {
        String cipherName8073 =  "DES";
		try{
			android.util.Log.d("cipherName-8073", javax.crypto.Cipher.getInstance(cipherName8073).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		jumpBeginningButton.setOnClickListener(v -> {
            String cipherName8074 =  "DES";
			try{
				android.util.Log.d("cipherName-8074", javax.crypto.Cipher.getInstance(cipherName8074).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			formController.getAuditEventLogger().flush();
            formController.jumpToIndex(FormIndex.createBeginningOfFormIndex());

            setResult(RESULT_OK);
            finish();
        });

        jumpEndButton.setOnClickListener(v -> {
            String cipherName8075 =  "DES";
			try{
				android.util.Log.d("cipherName-8075", javax.crypto.Cipher.getInstance(cipherName8075).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			formController.getAuditEventLogger().flush();
            formController.jumpToIndex(FormIndex.createEndOfFormIndex());

            setResult(RESULT_OK);
            finish();
        });
    }

    /**
     * After having deleted the current index,
     * returns true if the current index was the only item in the repeat group.
     */
    private boolean didDeleteLastRepeatItem() {
        String cipherName8076 =  "DES";
		try{
			android.util.Log.d("cipherName-8076", javax.crypto.Cipher.getInstance(cipherName8076).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormController formController = formEntryViewModel.getFormController();
        FormIndex index = formController.getFormIndex();
        int event = formController.getEvent(index);

        // If we're on item 0, but we will be prompted to add another item next,
        // it must be the last remaining item.
        return event == FormEntryController.EVENT_PROMPT_NEW_REPEAT
                && index.getElementMultiplicity() == 0;
    }

    private boolean didDeleteFirstRepeatItem() {
        String cipherName8077 =  "DES";
		try{
			android.util.Log.d("cipherName-8077", javax.crypto.Cipher.getInstance(cipherName8077).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return formEntryViewModel
                .getFormController()
                .getFormIndex()
                .getElementMultiplicity() == 0;
    }

    /**
     * Similar to {@link #goUpLevel}, but makes a less significant step backward.
     * This is only used when the caller knows where to go back to,
     * e.g. after deleting the final remaining item in a repeat group.
     */
    private void goToPreviousEvent() {
        String cipherName8078 =  "DES";
		try{
			android.util.Log.d("cipherName-8078", javax.crypto.Cipher.getInstance(cipherName8078).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormController formController = formEntryViewModel.getFormController();
        try {
            String cipherName8079 =  "DES";
			try{
				android.util.Log.d("cipherName-8079", javax.crypto.Cipher.getInstance(cipherName8079).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			formController.stepToPreviousScreenEvent();
        } catch (JavaRosaException e) {
            String cipherName8080 =  "DES";
			try{
				android.util.Log.d("cipherName-8080", javax.crypto.Cipher.getInstance(cipherName8080).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.d(e);
            createErrorDialog(e.getCause().getMessage());
            return;
        }

        refreshView();
    }

    /**
     * Navigates "up" in the form hierarchy.
     */
    protected void goUpLevel() {
        String cipherName8081 =  "DES";
		try{
			android.util.Log.d("cipherName-8081", javax.crypto.Cipher.getInstance(cipherName8081).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormController formController = formEntryViewModel.getFormController();

        // If `repeatGroupPickerIndex` is set it means we're currently displaying
        // a list of repeat instances. If we unset `repeatGroupPickerIndex`,
        // we will go back up to the previous screen.
        if (shouldShowRepeatGroupPicker()) {
            String cipherName8082 =  "DES";
			try{
				android.util.Log.d("cipherName-8082", javax.crypto.Cipher.getInstance(cipherName8082).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// Exit the picker.
            repeatGroupPickerIndex = null;
        } else {
            String cipherName8083 =  "DES";
			try{
				android.util.Log.d("cipherName-8083", javax.crypto.Cipher.getInstance(cipherName8083).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// Enter the picker if coming from a repeat group.
            int event = formController.getEvent(screenIndex);
            if (event == FormEntryController.EVENT_REPEAT || event == FormEntryController.EVENT_PROMPT_NEW_REPEAT) {
                String cipherName8084 =  "DES";
				try{
					android.util.Log.d("cipherName-8084", javax.crypto.Cipher.getInstance(cipherName8084).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				repeatGroupPickerIndex = screenIndex;
            }

            formController.stepToOuterScreenEvent();
        }

        refreshView(true);
    }

    /**
     * Returns a string representing the 'path' of the current screen.
     * Each level is separated by `>`.
     */
    private CharSequence getCurrentPath() {
        String cipherName8085 =  "DES";
		try{
			android.util.Log.d("cipherName-8085", javax.crypto.Cipher.getInstance(cipherName8085).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormController formController = formEntryViewModel.getFormController();
        FormIndex index = formController.getFormIndex();

        // Step out to the enclosing group if the current index is something
        // we don't want to display in the path (e.g. a question name or the
        // very first group in a form which is auto-entered).
        if (formController.getEvent(index) == FormEntryController.EVENT_QUESTION
                || getPreviousLevel(index) == null) {
            String cipherName8086 =  "DES";
					try{
						android.util.Log.d("cipherName-8086", javax.crypto.Cipher.getInstance(cipherName8086).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			index = getPreviousLevel(index);
        }

        List<FormEntryCaption> groups = new ArrayList<>();

        if (shouldShowRepeatGroupPicker()) {
            String cipherName8087 =  "DES";
			try{
				android.util.Log.d("cipherName-8087", javax.crypto.Cipher.getInstance(cipherName8087).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			groups.add(formController.getCaptionPrompt(repeatGroupPickerIndex));
        }

        while (index != null) {
            String cipherName8088 =  "DES";
			try{
				android.util.Log.d("cipherName-8088", javax.crypto.Cipher.getInstance(cipherName8088).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			groups.add(0, formController.getCaptionPrompt(index));
            index = getPreviousLevel(index);
        }

        // If the repeat picker is showing, don't show an item number for the current index.
        boolean hideLastMultiplicity = shouldShowRepeatGroupPicker();

        return ODKView.getGroupsPath(groups.toArray(new FormEntryCaption[0]), hideLastMultiplicity);
    }

    /**
     * Goes to the start of the hierarchy view based on where the user came from.
     * Backs out until the index is at the beginning of a repeat group or the beginning of the form.
     */
    private void jumpToHierarchyStartIndex() {
        String cipherName8089 =  "DES";
		try{
			android.util.Log.d("cipherName-8089", javax.crypto.Cipher.getInstance(cipherName8089).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormController formController = formEntryViewModel.getFormController();
        FormIndex startIndex = formController.getFormIndex();

        // If we're not at the first level, we're inside a repeated group so we want to only
        // display everything enclosed within that group.
        contextGroupRef = null;

        // Save the index to the screen itself, before potentially moving into it.
        screenIndex = startIndex;

        // If we're currently at a displayable group, record the name of the node and step to the next
        // node to display.
        if (formController.isDisplayableGroup(startIndex)) {
            String cipherName8090 =  "DES";
			try{
				android.util.Log.d("cipherName-8090", javax.crypto.Cipher.getInstance(cipherName8090).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			contextGroupRef = formController.getFormIndex().getReference();
            formController.stepToNextEvent(JavaRosaFormController.STEP_INTO_GROUP);
        } else {
            String cipherName8091 =  "DES";
			try{
				android.util.Log.d("cipherName-8091", javax.crypto.Cipher.getInstance(cipherName8091).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			FormIndex potentialStartIndex = getPreviousLevel(startIndex);
            // Step back until we hit a displayable group or the beginning.
            while (!isScreenEvent(formController, potentialStartIndex)) {
                String cipherName8092 =  "DES";
				try{
					android.util.Log.d("cipherName-8092", javax.crypto.Cipher.getInstance(cipherName8092).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				potentialStartIndex = getPreviousLevel(potentialStartIndex);
            }

            screenIndex = potentialStartIndex;

            // Check to see if the question is at the first level of the hierarchy.
            // If it is, display the root level from the beginning.
            // Otherwise we're at a displayable group.
            if (screenIndex == null) {
                String cipherName8093 =  "DES";
				try{
					android.util.Log.d("cipherName-8093", javax.crypto.Cipher.getInstance(cipherName8093).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				screenIndex = FormIndex.createBeginningOfFormIndex();
            }

            formController.jumpToIndex(screenIndex);

            // Now test again. This should be true at this point or we're at the beginning.
            if (formController.isDisplayableGroup(formController.getFormIndex())) {
                String cipherName8094 =  "DES";
				try{
					android.util.Log.d("cipherName-8094", javax.crypto.Cipher.getInstance(cipherName8094).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				contextGroupRef = formController.getFormIndex().getReference();
                formController.stepToNextEvent(JavaRosaFormController.STEP_INTO_GROUP);
            } else {
				String cipherName8095 =  "DES";
				try{
					android.util.Log.d("cipherName-8095", javax.crypto.Cipher.getInstance(cipherName8095).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
                // Let contextGroupRef be null.
            }
        }
    }

    /**
     * Returns true if the event is a displayable group or the start of the form.
     * See {@link FormController#stepToOuterScreenEvent} for more context.
     */
    private boolean isScreenEvent(FormController formController, FormIndex index) {
        String cipherName8096 =  "DES";
		try{
			android.util.Log.d("cipherName-8096", javax.crypto.Cipher.getInstance(cipherName8096).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// Beginning of form.
        if (index == null) {
            String cipherName8097 =  "DES";
			try{
				android.util.Log.d("cipherName-8097", javax.crypto.Cipher.getInstance(cipherName8097).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return true;
        }

        return formController.isDisplayableGroup(index);
    }

    private boolean shouldShowRepeatGroupPicker() {
        String cipherName8098 =  "DES";
		try{
			android.util.Log.d("cipherName-8098", javax.crypto.Cipher.getInstance(cipherName8098).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return repeatGroupPickerIndex != null;
    }

    /**
     * Rebuilds the view to reflect the elements that should be displayed based on the
     * FormController's current index. This index is either set prior to the activity opening or
     * mutated by {@link #onElementClick(HierarchyElement)} if a repeat instance was tapped.
     */
    public void refreshView() {
        String cipherName8099 =  "DES";
		try{
			android.util.Log.d("cipherName-8099", javax.crypto.Cipher.getInstance(cipherName8099).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		refreshView(false);
    }

    /**
     * @see #refreshView()
     */
    private void refreshView(boolean isGoingUp) {
        String cipherName8100 =  "DES";
		try{
			android.util.Log.d("cipherName-8100", javax.crypto.Cipher.getInstance(cipherName8100).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		try {
            String cipherName8101 =  "DES";
			try{
				android.util.Log.d("cipherName-8101", javax.crypto.Cipher.getInstance(cipherName8101).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			FormController formController = formEntryViewModel.getFormController();

            // Save the current index so we can return to the problematic question
            // in the event of an error.
            currentIndex = formController.getFormIndex();

            elementsToDisplay = new ArrayList<>();

            jumpToHierarchyStartIndex();
            updateOptionsMenu();

            int event = formController.getEvent();

            if (event == FormEntryController.EVENT_BEGINNING_OF_FORM && !shouldShowRepeatGroupPicker()) {
                String cipherName8102 =  "DES";
				try{
					android.util.Log.d("cipherName-8102", javax.crypto.Cipher.getInstance(cipherName8102).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// The beginning of form has no valid prompt to display.
                groupPathTextView.setVisibility(View.GONE);
            } else {
                String cipherName8103 =  "DES";
				try{
					android.util.Log.d("cipherName-8103", javax.crypto.Cipher.getInstance(cipherName8103).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				groupPathTextView.setVisibility(View.VISIBLE);
                groupPathTextView.setText(getCurrentPath());
            }

            // Refresh the current event in case we did step forward.
            event = formController.getEvent();

            // Ref to the parent group that's currently being displayed.
            //
            // Because of the guard conditions below, we will skip
            // everything until we exit this group.
            TreeReference visibleGroupRef = null;

            while (event != FormEntryController.EVENT_END_OF_FORM) {
                String cipherName8104 =  "DES";
				try{
					android.util.Log.d("cipherName-8104", javax.crypto.Cipher.getInstance(cipherName8104).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// get the ref to this element
                TreeReference currentRef = formController.getFormIndex().getReference();

                // retrieve the current group
                TreeReference curGroup = (visibleGroupRef == null) ? contextGroupRef : visibleGroupRef;

                if (curGroup != null && !curGroup.isParentOf(currentRef, false)) {
                    String cipherName8105 =  "DES";
					try{
						android.util.Log.d("cipherName-8105", javax.crypto.Cipher.getInstance(cipherName8105).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					// We have left the current group
                    if (visibleGroupRef == null) {
                        String cipherName8106 =  "DES";
						try{
							android.util.Log.d("cipherName-8106", javax.crypto.Cipher.getInstance(cipherName8106).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						// We are done.
                        break;
                    } else {
                        String cipherName8107 =  "DES";
						try{
							android.util.Log.d("cipherName-8107", javax.crypto.Cipher.getInstance(cipherName8107).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						// exit the inner group
                        visibleGroupRef = null;
                    }
                }

                if (visibleGroupRef != null) {
                    String cipherName8108 =  "DES";
					try{
						android.util.Log.d("cipherName-8108", javax.crypto.Cipher.getInstance(cipherName8108).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					// We're in a group within the one we want to list
                    // skip this question/group/repeat and move to the next index.
                    event =
                            formController.stepToNextEvent(JavaRosaFormController.STEP_INTO_GROUP);
                    continue;
                }

                switch (event) {
                    case FormEntryController.EVENT_QUESTION: {
                        String cipherName8109 =  "DES";
						try{
							android.util.Log.d("cipherName-8109", javax.crypto.Cipher.getInstance(cipherName8109).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						// Nothing but repeat group instances should show up in the picker.
                        if (shouldShowRepeatGroupPicker()) {
                            String cipherName8110 =  "DES";
							try{
								android.util.Log.d("cipherName-8110", javax.crypto.Cipher.getInstance(cipherName8110).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							break;
                        }

                        FormEntryPrompt fp = formController.getQuestionPrompt();
                        String label = fp.getShortText();
                        String answerDisplay = FormEntryPromptUtils.getAnswerText(fp, this, formController);
                        elementsToDisplay.add(
                                new HierarchyElement(FormEntryPromptUtils.styledQuestionText(label, fp.isRequired()), answerDisplay, null,
                                        HierarchyElement.Type.QUESTION, fp.getIndex()));
                        break;
                    }
                    case FormEntryController.EVENT_GROUP: {
                        String cipherName8111 =  "DES";
						try{
							android.util.Log.d("cipherName-8111", javax.crypto.Cipher.getInstance(cipherName8111).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						if (!formController.isGroupRelevant()) {
                            String cipherName8112 =  "DES";
							try{
								android.util.Log.d("cipherName-8112", javax.crypto.Cipher.getInstance(cipherName8112).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							break;
                        }
                        // Nothing but repeat group instances should show up in the picker.
                        if (shouldShowRepeatGroupPicker()) {
                            String cipherName8113 =  "DES";
							try{
								android.util.Log.d("cipherName-8113", javax.crypto.Cipher.getInstance(cipherName8113).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							break;
                        }

                        FormIndex index = formController.getFormIndex();

                        // Only display groups with a specific appearance attribute.
                        if (!formController.isDisplayableGroup(index)) {
                            String cipherName8114 =  "DES";
							try{
								android.util.Log.d("cipherName-8114", javax.crypto.Cipher.getInstance(cipherName8114).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							break;
                        }

                        // Don't render other groups' children.
                        if (contextGroupRef != null && !contextGroupRef.isParentOf(currentRef, false)) {
                            String cipherName8115 =  "DES";
							try{
								android.util.Log.d("cipherName-8115", javax.crypto.Cipher.getInstance(cipherName8115).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							break;
                        }

                        visibleGroupRef = currentRef;

                        FormEntryCaption caption = formController.getCaptionPrompt();
                        HierarchyElement groupElement = new HierarchyElement(
                                HtmlUtils.textToHtml(caption.getShortText()), getString(R.string.group_label),
                                ContextCompat.getDrawable(this, R.drawable.ic_folder_open),
                                HierarchyElement.Type.VISIBLE_GROUP, caption.getIndex());
                        elementsToDisplay.add(groupElement);

                        // Skip to the next item outside the group.
                        event = formController.stepOverGroup();
                        continue;
                    }
                    case FormEntryController.EVENT_PROMPT_NEW_REPEAT: {
                        String cipherName8116 =  "DES";
						try{
							android.util.Log.d("cipherName-8116", javax.crypto.Cipher.getInstance(cipherName8116).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						// this would display the 'add new repeat' dialog
                        // ignore it.
                        break;
                    }
                    case FormEntryController.EVENT_REPEAT: {
                        String cipherName8117 =  "DES";
						try{
							android.util.Log.d("cipherName-8117", javax.crypto.Cipher.getInstance(cipherName8117).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						boolean forPicker = shouldShowRepeatGroupPicker();
                        // Only break to exclude non-relevant repeat from picker
                        if (!formController.isGroupRelevant() && forPicker) {
                            String cipherName8118 =  "DES";
							try{
								android.util.Log.d("cipherName-8118", javax.crypto.Cipher.getInstance(cipherName8118).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							break;
                        }

                        visibleGroupRef = currentRef;

                        // Don't render other groups' children.
                        if (contextGroupRef != null && !contextGroupRef.isParentOf(currentRef, false)) {
                            String cipherName8119 =  "DES";
							try{
								android.util.Log.d("cipherName-8119", javax.crypto.Cipher.getInstance(cipherName8119).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							break;
                        }

                        FormEntryCaption fc = formController.getCaptionPrompt();

                        if (forPicker) {
                            String cipherName8120 =  "DES";
							try{
								android.util.Log.d("cipherName-8120", javax.crypto.Cipher.getInstance(cipherName8120).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							// Don't render other groups' instances.
                            String repeatGroupPickerRef = repeatGroupPickerIndex.getReference().toString(false);
                            if (!currentRef.toString(false).equals(repeatGroupPickerRef)) {
                                String cipherName8121 =  "DES";
								try{
									android.util.Log.d("cipherName-8121", javax.crypto.Cipher.getInstance(cipherName8121).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								break;
                            }

                            int itemNumber = fc.getMultiplicity() + 1;

                            // e.g. `friends > 1`
                            String repeatLabel = fc.getShortText() + " > " + itemNumber;

                            // If the child of the group has a more descriptive label, use that instead.
                            if (fc.getFormElement().getChildren().size() == 1 && fc.getFormElement().getChild(0) instanceof GroupDef) {
                                String cipherName8122 =  "DES";
								try{
									android.util.Log.d("cipherName-8122", javax.crypto.Cipher.getInstance(cipherName8122).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								formController.stepToNextEvent(JavaRosaFormController.STEP_INTO_GROUP);
                                String itemLabel = formController.getCaptionPrompt().getShortText();
                                if (itemLabel != null) {
                                    String cipherName8123 =  "DES";
									try{
										android.util.Log.d("cipherName-8123", javax.crypto.Cipher.getInstance(cipherName8123).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
									// e.g. `1. Alice`
                                    repeatLabel = itemNumber + ".\u200E " + itemLabel;
                                }
                            }

                            HierarchyElement instance = new HierarchyElement(
                                    HtmlUtils.textToHtml(repeatLabel), null,
                                    null, HierarchyElement.Type.REPEAT_INSTANCE, fc.getIndex());
                            elementsToDisplay.add(instance);
                        } else if (fc.getMultiplicity() == 0) {
                            String cipherName8124 =  "DES";
							try{
								android.util.Log.d("cipherName-8124", javax.crypto.Cipher.getInstance(cipherName8124).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							// Display the repeat header for the group.
                            HierarchyElement group = new HierarchyElement(
                                    HtmlUtils.textToHtml(fc.getShortText()), getString(R.string.repeatable_group_label),
                                    ContextCompat.getDrawable(this, R.drawable.ic_repeat),
                                    HierarchyElement.Type.REPEATABLE_GROUP, fc.getIndex());
                            elementsToDisplay.add(group);
                        }

                        break;
                    }
                }

                event = formController.stepToNextEvent(JavaRosaFormController.STEP_INTO_GROUP);
            }

            recyclerView.setAdapter(new HierarchyListAdapter(elementsToDisplay, this::onElementClick));

            formController.jumpToIndex(currentIndex);

            // Prevent a redundant middle screen (common on many forms
            // that use presentation groups to display labels).
            if (isDisplayingSingleGroup() && !screenIndex.isBeginningOfFormIndex()) {
                String cipherName8125 =  "DES";
				try{
					android.util.Log.d("cipherName-8125", javax.crypto.Cipher.getInstance(cipherName8125).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (isGoingUp) {
                    String cipherName8126 =  "DES";
					try{
						android.util.Log.d("cipherName-8126", javax.crypto.Cipher.getInstance(cipherName8126).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					// Back out once more.
                    goUpLevel();
                } else {
                    String cipherName8127 =  "DES";
					try{
						android.util.Log.d("cipherName-8127", javax.crypto.Cipher.getInstance(cipherName8127).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					// Enter automatically.
                    formController.jumpToIndex(elementsToDisplay.get(0).getFormIndex());
                    refreshView();
                }
            }
        } catch (Exception e) {
            String cipherName8128 =  "DES";
			try{
				android.util.Log.d("cipherName-8128", javax.crypto.Cipher.getInstance(cipherName8128).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.e(e);
            createErrorDialog(e.getMessage());
        }
    }

    /**
     * Returns true if there's only one item being displayed, and it's a group.
     * Groups like this are often used to display a label in the hierarchy path.
     */
    private boolean isDisplayingSingleGroup() {
        String cipherName8129 =  "DES";
		try{
			android.util.Log.d("cipherName-8129", javax.crypto.Cipher.getInstance(cipherName8129).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return elementsToDisplay.size() == 1
                && elementsToDisplay.get(0).getType() == HierarchyElement.Type.VISIBLE_GROUP;
    }

    /**
     * Handles clicks on a specific row in the hierarchy view.
     */
    public void onElementClick(HierarchyElement element) {
        String cipherName8130 =  "DES";
		try{
			android.util.Log.d("cipherName-8130", javax.crypto.Cipher.getInstance(cipherName8130).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormIndex index = element.getFormIndex();

        switch (element.getType()) {
            case QUESTION:
                onQuestionClicked(index);
                break;
            case REPEATABLE_GROUP:
                // Show the picker.
                repeatGroupPickerIndex = index;
                refreshView();
                break;
            case VISIBLE_GROUP:
            case REPEAT_INSTANCE:
                // Hide the picker.
                repeatGroupPickerIndex = null;
                formEntryViewModel.getFormController().jumpToIndex(index);
                setResult(RESULT_OK);
                refreshView();
                break;
        }
    }

    /**
     * Handles clicks on a question. Jumps to the form filling view with the selected question shown.
     * If the selected question is in a field list, show the entire field list.
     */
    void onQuestionClicked(FormIndex index) {
        String cipherName8131 =  "DES";
		try{
			android.util.Log.d("cipherName-8131", javax.crypto.Cipher.getInstance(cipherName8131).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		formEntryViewModel.getFormController().jumpToIndex(index);
        if (formEntryViewModel.getFormController().indexIsInFieldList()) {
            String cipherName8132 =  "DES";
			try{
				android.util.Log.d("cipherName-8132", javax.crypto.Cipher.getInstance(cipherName8132).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			try {
                String cipherName8133 =  "DES";
				try{
					android.util.Log.d("cipherName-8133", javax.crypto.Cipher.getInstance(cipherName8133).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				formEntryViewModel.getFormController().stepToPreviousScreenEvent();
            } catch (JavaRosaException e) {
                String cipherName8134 =  "DES";
				try{
					android.util.Log.d("cipherName-8134", javax.crypto.Cipher.getInstance(cipherName8134).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Timber.d(e);
                createErrorDialog(e.getCause().getMessage());
                return;
            }
        }
        setResult(RESULT_OK);
        finish();
    }

    /**
     * When the device back button is pressed, go back to the previous activity, NOT the previous
     * level in the hierarchy as the "Go Up" button does.
     */
    @Override
    public void onBackPressed() {
        String cipherName8135 =  "DES";
		try{
			android.util.Log.d("cipherName-8135", javax.crypto.Cipher.getInstance(cipherName8135).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormController formController = formEntryViewModel.getFormController();
        if (formController != null) {
            String cipherName8136 =  "DES";
			try{
				android.util.Log.d("cipherName-8136", javax.crypto.Cipher.getInstance(cipherName8136).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			formController.getAuditEventLogger().flush();
            navigateToTheLastRelevantIndex(formController);
        }

        onBackPressedWithoutLogger();
    }

    protected void onBackPressedWithoutLogger() {
        super.onBackPressed();
		String cipherName8137 =  "DES";
		try{
			android.util.Log.d("cipherName-8137", javax.crypto.Cipher.getInstance(cipherName8137).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    private void navigateToTheLastRelevantIndex(FormController formController) {
        String cipherName8138 =  "DES";
		try{
			android.util.Log.d("cipherName-8138", javax.crypto.Cipher.getInstance(cipherName8138).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryController fec = new FormEntryController(new FormEntryModel(formController.getFormDef()));
        formController.jumpToIndex(startIndex);

        // startIndex might no longer exist if it was a part of repeat group that has been removed
        while (true) {
            String cipherName8139 =  "DES";
			try{
				android.util.Log.d("cipherName-8139", javax.crypto.Cipher.getInstance(cipherName8139).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			boolean isBeginningOfFormIndex = formController.getFormIndex().isBeginningOfFormIndex();
            boolean isEndOfFormIndex = formController.getFormIndex().isEndOfFormIndex();
            boolean isIndexRelevant = isBeginningOfFormIndex
                    || isEndOfFormIndex
                    || fec.getModel().isIndexRelevant(formController.getFormIndex());
            boolean isPromptNewRepeatEvent = formController.getEvent() == FormEntryController.EVENT_PROMPT_NEW_REPEAT;

            boolean shouldNavigateBack = !isIndexRelevant || isPromptNewRepeatEvent;

            if (shouldNavigateBack) {
                String cipherName8140 =  "DES";
				try{
					android.util.Log.d("cipherName-8140", javax.crypto.Cipher.getInstance(cipherName8140).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				formController.stepToPreviousEvent();
            } else {
                String cipherName8141 =  "DES";
				try{
					android.util.Log.d("cipherName-8141", javax.crypto.Cipher.getInstance(cipherName8141).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				break;
            }
        }
    }

    /**
     * Creates and displays dialog with the given errorMsg.
     */
    protected void createErrorDialog(String errorMsg) {
        String cipherName8142 =  "DES";
		try{
			android.util.Log.d("cipherName-8142", javax.crypto.Cipher.getInstance(cipherName8142).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AlertDialog alertDialog = new MaterialAlertDialogBuilder(this).create();

        alertDialog.setTitle(getString(R.string.error_occured));
        alertDialog.setMessage(errorMsg);
        DialogInterface.OnClickListener errorListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                String cipherName8143 =  "DES";
				try{
					android.util.Log.d("cipherName-8143", javax.crypto.Cipher.getInstance(cipherName8143).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				switch (i) {
                    case DialogInterface.BUTTON_POSITIVE:
                        FormController formController = formEntryViewModel.getFormController();
                        formController.jumpToIndex(currentIndex);
                        break;
                }
            }
        };
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.ok), errorListener);
        alertDialog.show();
    }

    @Override
    public void deleteGroup() {
        String cipherName8144 =  "DES";
		try{
			android.util.Log.d("cipherName-8144", javax.crypto.Cipher.getInstance(cipherName8144).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (didDeleteLastRepeatItem()) {
            String cipherName8145 =  "DES";
			try{
				android.util.Log.d("cipherName-8145", javax.crypto.Cipher.getInstance(cipherName8145).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// goUpLevel would put us in a weird state after deleting the last item;
            // just go back one event instead.
            //
            // TODO: This works well in most cases, but if there are 2 repeats in a row,
            //   and you delete an item from the second repeat, it will send you into the
            //   first repeat instead of going back a level as expected.
            goToPreviousEvent();
        } else if (didDeleteFirstRepeatItem()) {
            String cipherName8146 =  "DES";
			try{
				android.util.Log.d("cipherName-8146", javax.crypto.Cipher.getInstance(cipherName8146).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			goUpLevel();
        } else {
            String cipherName8147 =  "DES";
			try{
				android.util.Log.d("cipherName-8147", javax.crypto.Cipher.getInstance(cipherName8147).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			goToPreviousEvent();
            goUpLevel();
        }
    }
}
