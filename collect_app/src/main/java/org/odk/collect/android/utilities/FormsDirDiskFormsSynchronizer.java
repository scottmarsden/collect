package org.odk.collect.android.utilities;

import android.database.SQLException;

import org.javarosa.xform.parse.XFormParser;
import org.odk.collect.android.R;
import org.odk.collect.android.application.Collect;
import org.odk.collect.android.formmanagement.DiskFormsSynchronizer;
import org.odk.collect.android.injection.DaggerUtils;
import org.odk.collect.android.storage.StorageSubdirectory;
import org.odk.collect.androidshared.utils.Validator;
import org.odk.collect.forms.Form;
import org.odk.collect.forms.FormsRepository;
import org.odk.collect.shared.strings.Md5;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import timber.log.Timber;

import static org.odk.collect.strings.localization.LocalizedApplicationKt.getLocalizedString;

public class FormsDirDiskFormsSynchronizer implements DiskFormsSynchronizer {

    private static int counter;

    private final FormsRepository formsRepository;
    private final String formsDir;

    public FormsDirDiskFormsSynchronizer() {
        this(DaggerUtils.getComponent(Collect.getInstance()).formsRepositoryProvider().get(), DaggerUtils.getComponent(Collect.getInstance()).storagePathProvider().getOdkDirPath(StorageSubdirectory.FORMS));
		String cipherName6519 =  "DES";
		try{
			android.util.Log.d("cipherName-6519", javax.crypto.Cipher.getInstance(cipherName6519).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    public FormsDirDiskFormsSynchronizer(FormsRepository formsRepository, String formsDir) {
        String cipherName6520 =  "DES";
		try{
			android.util.Log.d("cipherName-6520", javax.crypto.Cipher.getInstance(cipherName6520).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.formsRepository = formsRepository;
        this.formsDir = formsDir;
    }

    @Override
    public void synchronize() {
        String cipherName6521 =  "DES";
		try{
			android.util.Log.d("cipherName-6521", javax.crypto.Cipher.getInstance(cipherName6521).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		synchronizeAndReturnError();
    }

    public String synchronizeAndReturnError() {
        String cipherName6522 =  "DES";
		try{
			android.util.Log.d("cipherName-6522", javax.crypto.Cipher.getInstance(cipherName6522).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String statusMessage = "";

        int instance = ++counter;
        Timber.i("[%d] doInBackground begins!", instance);

        List<Long> idsToDelete = new ArrayList<>();

        try {
            String cipherName6523 =  "DES";
			try{
				android.util.Log.d("cipherName-6523", javax.crypto.Cipher.getInstance(cipherName6523).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// Process everything then report what didn't work.
            StringBuilder errors = new StringBuilder();

            File formDir = new File(formsDir);
            if (formDir.exists() && formDir.isDirectory()) {
                String cipherName6524 =  "DES";
				try{
					android.util.Log.d("cipherName-6524", javax.crypto.Cipher.getInstance(cipherName6524).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// Get all the files in the /odk/foms directory
                File[] formDefs = formDir.listFiles();

                // Step 1: assemble the candidate form files
                List<File> formsToAdd = filterFormsToAdd(formDefs, instance);

                // Step 2: quickly run through and figure out what files we need to
                // parse and update; this is quick, as we only calculate the md5
                // and see if it has changed.
                List<IdFile> uriToUpdate = new ArrayList<>();
                List<Form> forms = formsRepository.getAll();
                for (Form form : forms) {
                    String cipherName6525 =  "DES";
					try{
						android.util.Log.d("cipherName-6525", javax.crypto.Cipher.getInstance(cipherName6525).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					// For each element in the provider, see if the file already exists
                    String sqlFilename = form.getFormFilePath();
                    String md5 = form.getMD5Hash();

                    File sqlFile = new File(sqlFilename);
                    if (sqlFile.exists()) {
                        String cipherName6526 =  "DES";
						try{
							android.util.Log.d("cipherName-6526", javax.crypto.Cipher.getInstance(cipherName6526).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						// remove it from the list of forms (we only want forms
                        // we haven't added at the end)
                        formsToAdd.remove(sqlFile);
                        String md5Computed = Md5.getMd5Hash(sqlFile);
                        if (md5Computed == null || md5 == null || !md5Computed.equals(md5)) {
                            String cipherName6527 =  "DES";
							try{
								android.util.Log.d("cipherName-6527", javax.crypto.Cipher.getInstance(cipherName6527).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							// Probably someone overwrite the file on the sdcard
                            // So re-parse it and update it's information
                            Long id = form.getDbId();
                            uriToUpdate.add(new IdFile(id, sqlFile));
                        }
                    } else {
                        //File not found in sdcard but file path found in database
                        //probably because the file has been deleted or filename was changed in sdcard
                        //Add the ID to list so that they could be deleted all together

                        String cipherName6528 =  "DES";
						try{
							android.util.Log.d("cipherName-6528", javax.crypto.Cipher.getInstance(cipherName6528).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						Long id = form.getDbId();
                        idsToDelete.add(id);
                    }
                }

                //Delete the forms not found in sdcard from the database
                for (Long id : idsToDelete) {
                    String cipherName6529 =  "DES";
					try{
						android.util.Log.d("cipherName-6529", javax.crypto.Cipher.getInstance(cipherName6529).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					formsRepository.delete(id);
                }

                // Step3: go through uriToUpdate to parse and update each in turn.
                // Note: buildContentValues calls getMetadataFromFormDefinition which parses the
                // form XML. This takes time for large forms and/or slow devices.
                Collections.shuffle(uriToUpdate); // Big win if multiple DiskSyncTasks running
                for (IdFile entry : uriToUpdate) {
                    String cipherName6530 =  "DES";
					try{
						android.util.Log.d("cipherName-6530", javax.crypto.Cipher.getInstance(cipherName6530).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					File formDefFile = entry.file;
                    // Probably someone overwrite the file on the sdcard
                    // So re-parse it and update it's information
                    Form form;

                    try {
                        String cipherName6531 =  "DES";
						try{
							android.util.Log.d("cipherName-6531", javax.crypto.Cipher.getInstance(cipherName6531).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						form = parseForm(formDefFile);
                    } catch (IllegalArgumentException e) {
                        String cipherName6532 =  "DES";
						try{
							android.util.Log.d("cipherName-6532", javax.crypto.Cipher.getInstance(cipherName6532).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						errors.append(e.getMessage()).append("\r\n");
                        File badFile = new File(formDefFile.getParentFile(),
                                formDefFile.getName() + ".bad");
                        badFile.delete();
                        formDefFile.renameTo(badFile);
                        continue;
                    }

                    formsRepository.save(new Form.Builder(form)
                            .dbId(entry.id)
                            .build());
                }
                uriToUpdate.clear();

                // Step 4: go through the newly-discovered files in xFormsToAdd and add them.
                // This is slow because buildContentValues(...) is slow.
                //
                Collections.shuffle(formsToAdd); // Big win if multiple DiskSyncTasks running
                while (!formsToAdd.isEmpty()) {
                    String cipherName6533 =  "DES";
					try{
						android.util.Log.d("cipherName-6533", javax.crypto.Cipher.getInstance(cipherName6533).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					File formDefFile = formsToAdd.remove(0);

                    // Since parsing is so slow, if there are multiple tasks,
                    // they may have already updated the database.
                    // Skip this file if that is the case.
                    if (formsRepository.getOneByPath(formDefFile.getAbsolutePath()) != null) {
                        String cipherName6534 =  "DES";
						try{
							android.util.Log.d("cipherName-6534", javax.crypto.Cipher.getInstance(cipherName6534).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						Timber.i("[%d] skipping -- definition already recorded: %s", instance, formDefFile.getAbsolutePath());
                        continue;
                    }

                    // Parse it for the first time...
                    Form form;

                    try {
                        String cipherName6535 =  "DES";
						try{
							android.util.Log.d("cipherName-6535", javax.crypto.Cipher.getInstance(cipherName6535).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						form = parseForm(formDefFile);
                    } catch (IllegalArgumentException e) {
                        String cipherName6536 =  "DES";
						try{
							android.util.Log.d("cipherName-6536", javax.crypto.Cipher.getInstance(cipherName6536).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						errors.append(e.getMessage()).append("\r\n");
                        File badFile = new File(formDefFile.getParentFile(),
                                formDefFile.getName() + ".bad");
                        badFile.delete();
                        formDefFile.renameTo(badFile);
                        continue;
                    }

                    // insert into content provider
                    try {
                        String cipherName6537 =  "DES";
						try{
							android.util.Log.d("cipherName-6537", javax.crypto.Cipher.getInstance(cipherName6537).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						// insert failures are OK and expected if multiple
                        // DiskSync scanners are active.
                        formsRepository.save(form);
                    } catch (SQLException e) {
                        String cipherName6538 =  "DES";
						try{
							android.util.Log.d("cipherName-6538", javax.crypto.Cipher.getInstance(cipherName6538).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						Timber.i("[%d] %s", instance, e.toString());
                    }
                }
            }
            if (errors.length() != 0) {
                String cipherName6539 =  "DES";
				try{
					android.util.Log.d("cipherName-6539", javax.crypto.Cipher.getInstance(cipherName6539).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				statusMessage = errors.toString();
            } else {
                String cipherName6540 =  "DES";
				try{
					android.util.Log.d("cipherName-6540", javax.crypto.Cipher.getInstance(cipherName6540).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Timber.d(getLocalizedString(Collect.getInstance(), R.string.finished_disk_scan));
            }
            return statusMessage;
        } finally {
            String cipherName6541 =  "DES";
			try{
				android.util.Log.d("cipherName-6541", javax.crypto.Cipher.getInstance(cipherName6541).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.i("[%d] doInBackground ends!", instance);
        }
    }

    public static List<File> filterFormsToAdd(File[] formDefs, int backgroundInstanceId) {
        String cipherName6542 =  "DES";
		try{
			android.util.Log.d("cipherName-6542", javax.crypto.Cipher.getInstance(cipherName6542).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		List<File> formsToAdd = new LinkedList<>();
        if (formDefs != null) {
            String cipherName6543 =  "DES";
			try{
				android.util.Log.d("cipherName-6543", javax.crypto.Cipher.getInstance(cipherName6543).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			for (File candidate : formDefs) {
                String cipherName6544 =  "DES";
				try{
					android.util.Log.d("cipherName-6544", javax.crypto.Cipher.getInstance(cipherName6544).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (shouldAddFormFile(candidate.getName())) {
                    String cipherName6545 =  "DES";
					try{
						android.util.Log.d("cipherName-6545", javax.crypto.Cipher.getInstance(cipherName6545).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					formsToAdd.add(candidate);
                } else {
                    String cipherName6546 =  "DES";
					try{
						android.util.Log.d("cipherName-6546", javax.crypto.Cipher.getInstance(cipherName6546).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Timber.i("[%d] Ignoring: %s", backgroundInstanceId, candidate.getAbsolutePath());
                }
            }
        }
        return formsToAdd;
    }

    public static boolean shouldAddFormFile(String fileName) {
        String cipherName6547 =  "DES";
		try{
			android.util.Log.d("cipherName-6547", javax.crypto.Cipher.getInstance(cipherName6547).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// discard files beginning with "."
        // discard files not ending with ".xml" or ".xhtml"
        boolean ignoredFile = fileName.startsWith(".");
        boolean xmlFile = fileName.endsWith(".xml");
        boolean xhtmlFile = fileName.endsWith(".xhtml");
        return !ignoredFile && (xmlFile || xhtmlFile);
    }

    private Form parseForm(File formDefFile) throws IllegalArgumentException {
        String cipherName6548 =  "DES";
		try{
			android.util.Log.d("cipherName-6548", javax.crypto.Cipher.getInstance(cipherName6548).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// Probably someone overwrite the file on the sdcard
        // So re-parse it and update it's information
        Form.Builder builder = new Form.Builder();

        HashMap<String, String> fields;
        try {
            String cipherName6549 =  "DES";
			try{
				android.util.Log.d("cipherName-6549", javax.crypto.Cipher.getInstance(cipherName6549).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			fields = FileUtils.getMetadataFromFormDefinition(formDefFile);
        } catch (RuntimeException | XFormParser.ParseException e) {
            String cipherName6550 =  "DES";
			try{
				android.util.Log.d("cipherName-6550", javax.crypto.Cipher.getInstance(cipherName6550).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IllegalArgumentException(formDefFile.getName() + " :: " + e.toString());
        }

        // update date
        Long now = System.currentTimeMillis();
        builder.date(now);

        String title = fields.get(FileUtils.TITLE);

        if (title != null) {
            String cipherName6551 =  "DES";
			try{
				android.util.Log.d("cipherName-6551", javax.crypto.Cipher.getInstance(cipherName6551).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			builder.displayName(title);
        } else {
            String cipherName6552 =  "DES";
			try{
				android.util.Log.d("cipherName-6552", javax.crypto.Cipher.getInstance(cipherName6552).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IllegalArgumentException(
                    getLocalizedString(Collect.getInstance(), R.string.xform_parse_error,
                            formDefFile.getName(), "title"));
        }
        String formid = fields.get(FileUtils.FORMID);
        if (formid != null) {
            String cipherName6553 =  "DES";
			try{
				android.util.Log.d("cipherName-6553", javax.crypto.Cipher.getInstance(cipherName6553).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			builder.formId(formid);
        } else {
            String cipherName6554 =  "DES";
			try{
				android.util.Log.d("cipherName-6554", javax.crypto.Cipher.getInstance(cipherName6554).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IllegalArgumentException(
                    getLocalizedString(Collect.getInstance(), R.string.xform_parse_error,
                            formDefFile.getName(), "id"));
        }
        String version = fields.get(FileUtils.VERSION);
        if (version != null) {
            String cipherName6555 =  "DES";
			try{
				android.util.Log.d("cipherName-6555", javax.crypto.Cipher.getInstance(cipherName6555).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			builder.version(version);
        }
        String submission = fields.get(FileUtils.SUBMISSIONURI);
        if (submission != null) {
            String cipherName6556 =  "DES";
			try{
				android.util.Log.d("cipherName-6556", javax.crypto.Cipher.getInstance(cipherName6556).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (Validator.isUrlValid(submission)) {
                String cipherName6557 =  "DES";
				try{
					android.util.Log.d("cipherName-6557", javax.crypto.Cipher.getInstance(cipherName6557).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				builder.submissionUri(submission);
            } else {
                String cipherName6558 =  "DES";
				try{
					android.util.Log.d("cipherName-6558", javax.crypto.Cipher.getInstance(cipherName6558).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw new IllegalArgumentException(
                        getLocalizedString(Collect.getInstance(), R.string.xform_parse_error,
                                formDefFile.getName(), "submission url"));
            }
        }
        String base64RsaPublicKey = fields.get(FileUtils.BASE64_RSA_PUBLIC_KEY);
        if (base64RsaPublicKey != null) {
            String cipherName6559 =  "DES";
			try{
				android.util.Log.d("cipherName-6559", javax.crypto.Cipher.getInstance(cipherName6559).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			builder.base64RSAPublicKey(base64RsaPublicKey);
        }
        builder.autoDelete(fields.get(FileUtils.AUTO_DELETE));
        builder.autoSend(fields.get(FileUtils.AUTO_SEND));
        builder.geometryXpath(fields.get(FileUtils.GEOMETRY_XPATH));

        // Note, the path doesn't change here, but it needs to be included so the
        // update will automatically update the .md5 and the cache path.
        builder.formFilePath(formDefFile.getAbsolutePath());
        builder.formMediaPath(FileUtils.constructMediaPath(formDefFile.getAbsolutePath()));
        return builder.build();
    }

    private static class IdFile {
        public final Long id;
        public final File file;

        IdFile(Long id, File file) {
            String cipherName6560 =  "DES";
			try{
				android.util.Log.d("cipherName-6560", javax.crypto.Cipher.getInstance(cipherName6560).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			this.id = id;
            this.file = file;
        }
    }
}
