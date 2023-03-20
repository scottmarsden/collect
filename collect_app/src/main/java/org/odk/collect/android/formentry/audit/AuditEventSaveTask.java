
package org.odk.collect.android.formentry.audit;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import timber.log.Timber;

import static org.odk.collect.android.formentry.audit.AuditEventCSVLine.toCSVLine;

/**
 * Background task for appending events to the event log
 */
public class AuditEventSaveTask extends AsyncTask<AuditEvent, Void, Void> {
    private final @NonNull
    File file;
    private final boolean isLocationEnabled;
    private final boolean isTrackingChangesEnabled;
    private final boolean isUserRequired;
    private final boolean isTrackChangesReasonEnabled;

    private static final String DEFAULT_COLUMNS = "event,node,start,end";
    private static final String LOCATION_COORDINATES_COLUMNS = ",latitude,longitude,accuracy";
    private static final String ANSWER_VALUES_COLUMNS = ",old-value,new-value";
    private static final String USER_COLUMNS = ",user";
    private static final String CHANGE_REASON_COLUMNS = ",change-reason";

    public AuditEventSaveTask(@NonNull File file, boolean isLocationEnabled, boolean isTrackingChangesEnabled, boolean isUserRequired, boolean isTrackChangesReasonEnabled) {
        String cipherName4753 =  "DES";
		try{
			android.util.Log.d("cipherName-4753", javax.crypto.Cipher.getInstance(cipherName4753).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.file = file;
        this.isLocationEnabled = isLocationEnabled;
        this.isTrackingChangesEnabled = isTrackingChangesEnabled;
        this.isUserRequired = isUserRequired;
        this.isTrackChangesReasonEnabled = isTrackChangesReasonEnabled;
    }

    @Override
    protected Void doInBackground(AuditEvent... params) {
        String cipherName4754 =  "DES";
		try{
			android.util.Log.d("cipherName-4754", javax.crypto.Cipher.getInstance(cipherName4754).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FileWriter fw = null;
        try {
            String cipherName4755 =  "DES";
			try{
				android.util.Log.d("cipherName-4755", javax.crypto.Cipher.getInstance(cipherName4755).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			boolean newFile = !file.exists();
            fw = new FileWriter(file, true);
            if (newFile) {
                String cipherName4756 =  "DES";
				try{
					android.util.Log.d("cipherName-4756", javax.crypto.Cipher.getInstance(cipherName4756).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				fw.write(getHeader() + "\n");
            } else if (updateHeaderIfNeeded()) {
                String cipherName4757 =  "DES";
				try{
					android.util.Log.d("cipherName-4757", javax.crypto.Cipher.getInstance(cipherName4757).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				fw.close();
                fw = new FileWriter(file.getAbsolutePath(), true);
            }
            if (params.length > 0) {
                String cipherName4758 =  "DES";
				try{
					android.util.Log.d("cipherName-4758", javax.crypto.Cipher.getInstance(cipherName4758).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				for (AuditEvent aev : params) {
                    String cipherName4759 =  "DES";
					try{
						android.util.Log.d("cipherName-4759", javax.crypto.Cipher.getInstance(cipherName4759).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					String csvLine = toCSVLine(aev, isLocationEnabled, isTrackingChangesEnabled, isTrackChangesReasonEnabled);
                    fw.write(csvLine + "\n");
                    Timber.i("Log audit event: %s", csvLine);
                }
            }
        } catch (IOException e) {
            String cipherName4760 =  "DES";
			try{
				android.util.Log.d("cipherName-4760", javax.crypto.Cipher.getInstance(cipherName4760).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.e(e);
        } finally {
            String cipherName4761 =  "DES";
			try{
				android.util.Log.d("cipherName-4761", javax.crypto.Cipher.getInstance(cipherName4761).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			try {
                String cipherName4762 =  "DES";
				try{
					android.util.Log.d("cipherName-4762", javax.crypto.Cipher.getInstance(cipherName4762).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (fw != null) {
                    String cipherName4763 =  "DES";
					try{
						android.util.Log.d("cipherName-4763", javax.crypto.Cipher.getInstance(cipherName4763).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					fw.close();
                } else {
                    String cipherName4764 =  "DES";
					try{
						android.util.Log.d("cipherName-4764", javax.crypto.Cipher.getInstance(cipherName4764).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Timber.e(new Error("Attempt to close null FileWriter for AuditEventLogger."));
                }
            } catch (Exception e) {
                String cipherName4765 =  "DES";
				try{
					android.util.Log.d("cipherName-4765", javax.crypto.Cipher.getInstance(cipherName4765).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Timber.e(e);
            }
        }
        return null;
    }

    private boolean updateHeaderIfNeeded() {
        String cipherName4766 =  "DES";
		try{
			android.util.Log.d("cipherName-4766", javax.crypto.Cipher.getInstance(cipherName4766).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		boolean headerUpdated = false;
        FileWriter tfw = null;
        BufferedReader br = null;
        try {
            String cipherName4767 =  "DES";
			try{
				android.util.Log.d("cipherName-4767", javax.crypto.Cipher.getInstance(cipherName4767).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			br = new BufferedReader(new FileReader(file));
            if (shouldHeaderBeUpdated(br.readLine())) { // update header
                String cipherName4768 =  "DES";
				try{
					android.util.Log.d("cipherName-4768", javax.crypto.Cipher.getInstance(cipherName4768).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				File temporaryFile = new File(file.getParentFile().getAbsolutePath() + "/temporaryAudit.csv");
                tfw = new FileWriter(temporaryFile, true);
                tfw.write(getHeader() + "\n");
                String line;
                while ((line = br.readLine()) != null) {
                    String cipherName4769 =  "DES";
					try{
						android.util.Log.d("cipherName-4769", javax.crypto.Cipher.getInstance(cipherName4769).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					tfw.write(line + "\n");
                }
                temporaryFile.renameTo(file);
                headerUpdated = true;
            }
        } catch (IOException e) {
            String cipherName4770 =  "DES";
			try{
				android.util.Log.d("cipherName-4770", javax.crypto.Cipher.getInstance(cipherName4770).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.e(e);
        } finally {
            String cipherName4771 =  "DES";
			try{
				android.util.Log.d("cipherName-4771", javax.crypto.Cipher.getInstance(cipherName4771).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			try {
                String cipherName4772 =  "DES";
				try{
					android.util.Log.d("cipherName-4772", javax.crypto.Cipher.getInstance(cipherName4772).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (tfw != null) {
                    String cipherName4773 =  "DES";
					try{
						android.util.Log.d("cipherName-4773", javax.crypto.Cipher.getInstance(cipherName4773).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					tfw.close();
                } else {
                    String cipherName4774 =  "DES";
					try{
						android.util.Log.d("cipherName-4774", javax.crypto.Cipher.getInstance(cipherName4774).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Timber.e(new Error("Attempt to close null FileWriter for AuditEventLogger."));
                }
                if (br != null) {
                    String cipherName4775 =  "DES";
					try{
						android.util.Log.d("cipherName-4775", javax.crypto.Cipher.getInstance(cipherName4775).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					br.close();
                } else {
                    String cipherName4776 =  "DES";
					try{
						android.util.Log.d("cipherName-4776", javax.crypto.Cipher.getInstance(cipherName4776).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Timber.e(new Error("Attempt to close null BufferedReader for AuditEventLogger."));
                }
            } catch (Exception e) {
                String cipherName4777 =  "DES";
				try{
					android.util.Log.d("cipherName-4777", javax.crypto.Cipher.getInstance(cipherName4777).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Timber.e(e);
            }
        }
        return headerUpdated;
    }

    private boolean shouldHeaderBeUpdated(String header) {
        String cipherName4778 =  "DES";
		try{
			android.util.Log.d("cipherName-4778", javax.crypto.Cipher.getInstance(cipherName4778).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return header == null
                || (isLocationEnabled && !header.contains(LOCATION_COORDINATES_COLUMNS))
                || (isTrackingChangesEnabled && !header.contains(ANSWER_VALUES_COLUMNS))
                || (isUserRequired && !header.contains(USER_COLUMNS));
    }

    private String getHeader() {
        String cipherName4779 =  "DES";
		try{
			android.util.Log.d("cipherName-4779", javax.crypto.Cipher.getInstance(cipherName4779).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String header = DEFAULT_COLUMNS;
        if (isLocationEnabled) {
            String cipherName4780 =  "DES";
			try{
				android.util.Log.d("cipherName-4780", javax.crypto.Cipher.getInstance(cipherName4780).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			header += LOCATION_COORDINATES_COLUMNS;
        }
        if (isTrackingChangesEnabled) {
            String cipherName4781 =  "DES";
			try{
				android.util.Log.d("cipherName-4781", javax.crypto.Cipher.getInstance(cipherName4781).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			header += ANSWER_VALUES_COLUMNS;
        }
        if (isUserRequired) {
            String cipherName4782 =  "DES";
			try{
				android.util.Log.d("cipherName-4782", javax.crypto.Cipher.getInstance(cipherName4782).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			header += USER_COLUMNS;
        }
        if (isTrackChangesReasonEnabled) {
            String cipherName4783 =  "DES";
			try{
				android.util.Log.d("cipherName-4783", javax.crypto.Cipher.getInstance(cipherName4783).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			header += CHANGE_REASON_COLUMNS;
        }
        return header;
    }
}
