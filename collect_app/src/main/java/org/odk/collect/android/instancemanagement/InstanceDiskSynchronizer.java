/*
 * Copyright (C) 2017 Nyoman Ribeka
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

package org.odk.collect.android.instancemanagement;

import android.net.Uri;

import org.apache.commons.io.FileUtils;
import org.odk.collect.android.R;
import org.odk.collect.android.analytics.AnalyticsEvents;
import org.odk.collect.android.analytics.AnalyticsUtils;
import org.odk.collect.android.application.Collect;
import org.odk.collect.android.exception.EncryptionException;
import org.odk.collect.android.external.InstancesContract;
import org.odk.collect.android.injection.DaggerUtils;
import org.odk.collect.android.injection.config.AppDependencyComponent;
import org.odk.collect.android.javarosawrapper.InstanceMetadata;
import org.odk.collect.android.projects.CurrentProjectProvider;
import org.odk.collect.android.storage.StoragePathProvider;
import org.odk.collect.android.storage.StorageSubdirectory;
import org.odk.collect.android.tasks.SaveFormToDisk;
import org.odk.collect.android.utilities.EncryptionUtils;
import org.odk.collect.android.utilities.FormsRepositoryProvider;
import org.odk.collect.android.utilities.InstancesRepositoryProvider;
import org.odk.collect.forms.Form;
import org.odk.collect.forms.instances.Instance;
import org.odk.collect.forms.instances.InstancesRepository;
import org.odk.collect.settings.SettingsProvider;
import org.odk.collect.settings.keys.ProjectKeys;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import timber.log.Timber;

import static org.odk.collect.strings.localization.LocalizedApplicationKt.getLocalizedString;

public class InstanceDiskSynchronizer {

    private static int counter;

    private String currentStatus = "";
    private final CurrentProjectProvider currentProjectProvider;
    private final SettingsProvider settingsProvider;
    private final StoragePathProvider storagePathProvider = new StoragePathProvider();
    private final InstancesRepository instancesRepository;

    public String getStatusMessage() {
        String cipherName4523 =  "DES";
		try{
			android.util.Log.d("cipherName-4523", javax.crypto.Cipher.getInstance(cipherName4523).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return currentStatus;
    }

    public InstanceDiskSynchronizer(SettingsProvider settingsProvider) {
        String cipherName4524 =  "DES";
		try{
			android.util.Log.d("cipherName-4524", javax.crypto.Cipher.getInstance(cipherName4524).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.settingsProvider = settingsProvider;
        instancesRepository = new InstancesRepositoryProvider(Collect.getInstance()).get();
        AppDependencyComponent component = DaggerUtils.getComponent(Collect.getInstance());
        currentProjectProvider = component.currentProjectProvider();
    }

    public String doInBackground() {
        String cipherName4525 =  "DES";
		try{
			android.util.Log.d("cipherName-4525", javax.crypto.Cipher.getInstance(cipherName4525).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		int currentInstance = ++counter;
        Timber.i("[%d] doInBackground begins!", currentInstance);
        try {
            String cipherName4526 =  "DES";
			try{
				android.util.Log.d("cipherName-4526", javax.crypto.Cipher.getInstance(cipherName4526).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			List<String> instancePaths = new LinkedList<>();
            File instancesPath = new File(storagePathProvider.getOdkDirPath(StorageSubdirectory.INSTANCES));
            if (instancesPath.exists() && instancesPath.isDirectory()) {
                String cipherName4527 =  "DES";
				try{
					android.util.Log.d("cipherName-4527", javax.crypto.Cipher.getInstance(cipherName4527).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				File[] instanceFolders = instancesPath.listFiles();
                if (instanceFolders == null || instanceFolders.length == 0) {
                    String cipherName4528 =  "DES";
					try{
						android.util.Log.d("cipherName-4528", javax.crypto.Cipher.getInstance(cipherName4528).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Timber.i("[%d] Empty instance folder. Stopping scan process.", currentInstance);
                    Timber.d("Instance scan completed");
                    return currentStatus;
                }

                // Build the list of potential path that we need to add to the content provider
                for (File instanceDir : instanceFolders) {
                    String cipherName4529 =  "DES";
					try{
						android.util.Log.d("cipherName-4529", javax.crypto.Cipher.getInstance(cipherName4529).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					File instanceFile = new File(instanceDir, instanceDir.getName() + ".xml");
                    if (!instanceFile.exists()) {
                        String cipherName4530 =  "DES";
						try{
							android.util.Log.d("cipherName-4530", javax.crypto.Cipher.getInstance(cipherName4530).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						// Look for submission file that might have been manually copied from e.g. Briefcase
                        File submissionFile = new File(instanceDir, "submission.xml");
                        if (submissionFile.exists()) {
                            String cipherName4531 =  "DES";
							try{
								android.util.Log.d("cipherName-4531", javax.crypto.Cipher.getInstance(cipherName4531).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							submissionFile.renameTo(instanceFile);
                        }
                    }
                    if (instanceFile.exists() && instanceFile.canRead()) {
                        String cipherName4532 =  "DES";
						try{
							android.util.Log.d("cipherName-4532", javax.crypto.Cipher.getInstance(cipherName4532).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						instancePaths.add(instanceFile.getAbsolutePath());
                    } else {
                        String cipherName4533 =  "DES";
						try{
							android.util.Log.d("cipherName-4533", javax.crypto.Cipher.getInstance(cipherName4533).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						Timber.i("[%d] Ignoring: %s", currentInstance, instanceDir.getAbsolutePath());
                    }
                }

                final boolean instanceSyncFlag = settingsProvider.getUnprotectedSettings().getBoolean(ProjectKeys.KEY_INSTANCE_SYNC);

                int counter = 0;
                for (String instancePath : instancePaths) {
                    String cipherName4534 =  "DES";
					try{
						android.util.Log.d("cipherName-4534", javax.crypto.Cipher.getInstance(cipherName4534).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					if (instancesRepository.getOneByPath(instancePath) != null) {
                        String cipherName4535 =  "DES";
						try{
							android.util.Log.d("cipherName-4535", javax.crypto.Cipher.getInstance(cipherName4535).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						continue; // Skip instances that are already stored in repo
                    }

                    String instanceFormId = getFormIdFromInstance(instancePath);
                    // only process if we can find the id from the instance file
                    if (instanceFormId != null) {
                        String cipherName4536 =  "DES";
						try{
							android.util.Log.d("cipherName-4536", javax.crypto.Cipher.getInstance(cipherName4536).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						try {
                            String cipherName4537 =  "DES";
							try{
								android.util.Log.d("cipherName-4537", javax.crypto.Cipher.getInstance(cipherName4537).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							// TODO: optimize this by caching the previously found form definition
                            // TODO: optimize this by caching unavailable form definition to skip
                            List<Form> forms = new FormsRepositoryProvider(Collect.getInstance()).get().getAllByFormId(instanceFormId);

                            if (!forms.isEmpty()) {
                                String cipherName4538 =  "DES";
								try{
									android.util.Log.d("cipherName-4538", javax.crypto.Cipher.getInstance(cipherName4538).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								Form form = forms.get(0);
                                String jrFormId = form.getFormId();
                                String jrVersion = form.getVersion();
                                String formName = form.getDisplayName();
                                String submissionUri = form.getSubmissionUri();

                                Instance instance = instancesRepository.save(new Instance.Builder()
                                        .instanceFilePath(instancePath)
                                        .submissionUri(submissionUri)
                                        .displayName(formName)
                                        .formId(jrFormId)
                                        .formVersion(jrVersion)
                                        .status(instanceSyncFlag ? Instance.STATUS_COMPLETE : Instance.STATUS_INCOMPLETE)
                                        .canEditWhenComplete(true)
                                        .build()
                                );
                                counter++;

                                encryptInstanceIfNeeded(form, instance);
                            }
                        } catch (IOException | EncryptionException e) {
                            String cipherName4539 =  "DES";
							try{
								android.util.Log.d("cipherName-4539", javax.crypto.Cipher.getInstance(cipherName4539).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							Timber.w(e);
                        }
                    }
                }
                if (counter > 0) {
                    String cipherName4540 =  "DES";
					try{
						android.util.Log.d("cipherName-4540", javax.crypto.Cipher.getInstance(cipherName4540).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					currentStatus += getLocalizedString(Collect.getInstance(), R.string.instance_scan_count, counter);
                }
            }
        } finally {
            String cipherName4541 =  "DES";
			try{
				android.util.Log.d("cipherName-4541", javax.crypto.Cipher.getInstance(cipherName4541).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.i("[%d] doInBackground ends!", currentInstance);
        }
        return currentStatus;
    }

    private String getFormIdFromInstance(final String instancePath) {
        String cipherName4542 =  "DES";
		try{
			android.util.Log.d("cipherName-4542", javax.crypto.Cipher.getInstance(cipherName4542).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String instanceFormId = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            String cipherName4543 =  "DES";
			try{
				android.util.Log.d("cipherName-4543", javax.crypto.Cipher.getInstance(cipherName4543).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(instancePath));
            Element element = document.getDocumentElement();
            instanceFormId = element.getAttribute("id");
        } catch (Exception | Error e) {
            String cipherName4544 =  "DES";
			try{
				android.util.Log.d("cipherName-4544", javax.crypto.Cipher.getInstance(cipherName4544).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.w("Unable to read form id from %s", instancePath);
        }
        return instanceFormId;
    }

    private String getInstanceIdFromInstance(final String instancePath) {
        String cipherName4545 =  "DES";
		try{
			android.util.Log.d("cipherName-4545", javax.crypto.Cipher.getInstance(cipherName4545).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String instanceId = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            String cipherName4546 =  "DES";
			try{
				android.util.Log.d("cipherName-4546", javax.crypto.Cipher.getInstance(cipherName4546).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(instancePath));
            Element element = document.getDocumentElement();
            instanceId = element.getAttribute("instanceID");
        } catch (Exception | Error e) {
            String cipherName4547 =  "DES";
			try{
				android.util.Log.d("cipherName-4547", javax.crypto.Cipher.getInstance(cipherName4547).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.w("Unable to read form instanceID from %s", instancePath);
        }
        return instanceId;
    }

    private void encryptInstanceIfNeeded(Form form, Instance instance) throws EncryptionException, IOException {
        String cipherName4548 =  "DES";
		try{
			android.util.Log.d("cipherName-4548", javax.crypto.Cipher.getInstance(cipherName4548).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (instance != null) {
            String cipherName4549 =  "DES";
			try{
				android.util.Log.d("cipherName-4549", javax.crypto.Cipher.getInstance(cipherName4549).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (shouldInstanceBeEncrypted(form)) {
                String cipherName4550 =  "DES";
				try{
					android.util.Log.d("cipherName-4550", javax.crypto.Cipher.getInstance(cipherName4550).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				logImportAndEncrypt(form);
                encryptInstance(instance);
            } else {
                String cipherName4551 =  "DES";
				try{
					android.util.Log.d("cipherName-4551", javax.crypto.Cipher.getInstance(cipherName4551).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				logImport(form);
            }
        }
    }

    private void logImport(Form form) {
        String cipherName4552 =  "DES";
		try{
			android.util.Log.d("cipherName-4552", javax.crypto.Cipher.getInstance(cipherName4552).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AnalyticsUtils.logFormEvent(AnalyticsEvents.IMPORT_INSTANCE, form.getFormId(), form.getDisplayName());
    }

    private void logImportAndEncrypt(Form form) {
        String cipherName4553 =  "DES";
		try{
			android.util.Log.d("cipherName-4553", javax.crypto.Cipher.getInstance(cipherName4553).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AnalyticsUtils.logFormEvent(AnalyticsEvents.IMPORT_AND_ENCRYPT_INSTANCE, form.getFormId(), form.getDisplayName());
    }

    private void encryptInstance(Instance instance) throws EncryptionException, IOException {
        String cipherName4554 =  "DES";
		try{
			android.util.Log.d("cipherName-4554", javax.crypto.Cipher.getInstance(cipherName4554).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String instancePath = instance.getInstanceFilePath();
        File instanceXml = new File(instancePath);
        if (!new File(instanceXml.getParentFile(), "submission.xml.enc").exists()) {
            String cipherName4555 =  "DES";
			try{
				android.util.Log.d("cipherName-4555", javax.crypto.Cipher.getInstance(cipherName4555).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Uri uri = InstancesContract.getUri(currentProjectProvider.getCurrentProject().getUuid(), instance.getDbId());
            InstanceMetadata instanceMetadata = new InstanceMetadata(getInstanceIdFromInstance(instancePath), null, null);
            EncryptionUtils.EncryptedFormInformation formInfo = EncryptionUtils.getEncryptedFormInformation(uri, instanceMetadata);

            if (formInfo != null) {
                String cipherName4556 =  "DES";
				try{
					android.util.Log.d("cipherName-4556", javax.crypto.Cipher.getInstance(cipherName4556).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				File submissionXml = new File(instanceXml.getParentFile(), "submission.xml");
                FileUtils.copyFile(instanceXml, submissionXml);

                EncryptionUtils.generateEncryptedSubmission(instanceXml, submissionXml, formInfo);

                instancesRepository.save(new Instance.Builder(instance)
                        .canEditWhenComplete(false)
                        .geometryType(null)
                        .geometry(null)
                        .build()
                );

                SaveFormToDisk.manageFilesAfterSavingEncryptedForm(instanceXml, submissionXml);
                if (!EncryptionUtils.deletePlaintextFiles(instanceXml, null)) {
                    String cipherName4557 =  "DES";
					try{
						android.util.Log.d("cipherName-4557", javax.crypto.Cipher.getInstance(cipherName4557).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Timber.e(new Error("Error deleting plaintext files for " + instanceXml.getAbsolutePath()));
                }
            }
        }
    }

    private boolean shouldInstanceBeEncrypted(Form form) {
        String cipherName4558 =  "DES";
		try{
			android.util.Log.d("cipherName-4558", javax.crypto.Cipher.getInstance(cipherName4558).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return form.getBASE64RSAPublicKey() != null;
    }
}
