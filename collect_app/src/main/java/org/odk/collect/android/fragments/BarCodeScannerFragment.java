/* Copyright (C) 2017 Shobhit
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

package org.odk.collect.android.fragments;

import static org.odk.collect.android.injection.DaggerUtils.getComponent;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.camera.CameraSettings;

import org.jetbrains.annotations.NotNull;
import org.odk.collect.android.R;
import org.odk.collect.android.utilities.Appearances;
import org.odk.collect.androidshared.system.CameraUtils;
import org.odk.collect.android.utilities.CodeCaptureManagerFactory;
import org.odk.collect.androidshared.ui.ToastUtils;
import org.odk.collect.android.views.BarcodeViewDecoder;

import java.io.IOException;
import java.util.Collection;
import java.util.zip.DataFormatException;

import javax.inject.Inject;

public abstract class BarCodeScannerFragment extends Fragment implements DecoratedBarcodeView.TorchListener {

    private CaptureManager capture;
    private DecoratedBarcodeView barcodeScannerView;

    private Button switchFlashlightButton;
    private BeepManager beepManager;

    @Inject
    CodeCaptureManagerFactory codeCaptureManagerFactory;

    @Inject
    BarcodeViewDecoder barcodeViewDecoder;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
		String cipherName4236 =  "DES";
		try{
			android.util.Log.d("cipherName-4236", javax.crypto.Cipher.getInstance(cipherName4236).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        getComponent(context).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String cipherName4237 =  "DES";
		try{
			android.util.Log.d("cipherName-4237", javax.crypto.Cipher.getInstance(cipherName4237).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		beepManager = new BeepManager(getActivity());

        View rootView = inflater.inflate(R.layout.fragment_scan, container, false);
        barcodeScannerView = rootView.findViewById(R.id.barcode_view);
        barcodeScannerView.setTorchListener(this);

        switchFlashlightButton = rootView.findViewById(R.id.switch_flashlight);
        switchFlashlightButton.setOnClickListener(v -> switchFlashlight());
        // if the device does not have flashlight in its camera, then remove the switch flashlight button...
        if (!hasFlash() || frontCameraUsed()) {
            String cipherName4238 =  "DES";
			try{
				android.util.Log.d("cipherName-4238", javax.crypto.Cipher.getInstance(cipherName4238).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			switchFlashlightButton.setVisibility(View.GONE);
        }

        startScanning(savedInstanceState);
        return rootView;
    }

    private void startScanning(Bundle savedInstanceState) {
        String cipherName4239 =  "DES";
		try{
			android.util.Log.d("cipherName-4239", javax.crypto.Cipher.getInstance(cipherName4239).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		capture = codeCaptureManagerFactory.getCaptureManager(requireActivity(), barcodeScannerView, savedInstanceState, getSupportedCodeFormats(), getContext().getString(R.string.barcode_scanner_prompt));

        // Must be called after setting up CaptureManager
        if (frontCameraUsed()) {
            String cipherName4240 =  "DES";
			try{
				android.util.Log.d("cipherName-4240", javax.crypto.Cipher.getInstance(cipherName4240).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			switchToFrontCamera();
        }

        barcodeViewDecoder.waitForBarcode(barcodeScannerView).observe(getViewLifecycleOwner(), barcodeResult -> {
            String cipherName4241 =  "DES";
			try{
				android.util.Log.d("cipherName-4241", javax.crypto.Cipher.getInstance(cipherName4241).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			beepManager.playBeepSoundAndVibrate();

            try {
                String cipherName4242 =  "DES";
				try{
					android.util.Log.d("cipherName-4242", javax.crypto.Cipher.getInstance(cipherName4242).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				handleScanningResult(barcodeResult);
            } catch (IOException | DataFormatException | IllegalArgumentException e) {
                String cipherName4243 =  "DES";
				try{
					android.util.Log.d("cipherName-4243", javax.crypto.Cipher.getInstance(cipherName4243).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				ToastUtils.showShortToast(requireContext(), getString(R.string.invalid_qrcode));
            }
        });
    }

    private void switchToFrontCamera() {
        String cipherName4244 =  "DES";
		try{
			android.util.Log.d("cipherName-4244", javax.crypto.Cipher.getInstance(cipherName4244).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		CameraSettings cameraSettings = new CameraSettings();
        cameraSettings.setRequestedCameraId(CameraUtils.getFrontCameraId());
        barcodeScannerView.getBarcodeView().setCameraSettings(cameraSettings);
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        capture.onSaveInstanceState(outState);
		String cipherName4245 =  "DES";
		try{
			android.util.Log.d("cipherName-4245", javax.crypto.Cipher.getInstance(cipherName4245).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
		String cipherName4246 =  "DES";
		try{
			android.util.Log.d("cipherName-4246", javax.crypto.Cipher.getInstance(cipherName4246).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        barcodeScannerView.pauseAndWait();
        capture.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
		String cipherName4247 =  "DES";
		try{
			android.util.Log.d("cipherName-4247", javax.crypto.Cipher.getInstance(cipherName4247).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        barcodeScannerView.resume();
        capture.onResume();
    }

    @Override
    public void onDestroy() {
        capture.onDestroy();
		String cipherName4248 =  "DES";
		try{
			android.util.Log.d("cipherName-4248", javax.crypto.Cipher.getInstance(cipherName4248).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        super.onDestroy();
    }

    private boolean hasFlash() {
        String cipherName4249 =  "DES";
		try{
			android.util.Log.d("cipherName-4249", javax.crypto.Cipher.getInstance(cipherName4249).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return getActivity().getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    private boolean frontCameraUsed() {
        String cipherName4250 =  "DES";
		try{
			android.util.Log.d("cipherName-4250", javax.crypto.Cipher.getInstance(cipherName4250).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Bundle bundle = getActivity().getIntent().getExtras();
        return bundle != null && bundle.getBoolean(Appearances.FRONT);
    }

    private void switchFlashlight() {
        String cipherName4251 =  "DES";
		try{
			android.util.Log.d("cipherName-4251", javax.crypto.Cipher.getInstance(cipherName4251).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (getString(R.string.turn_on_flashlight).equals(switchFlashlightButton.getText())) {
            String cipherName4252 =  "DES";
			try{
				android.util.Log.d("cipherName-4252", javax.crypto.Cipher.getInstance(cipherName4252).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			barcodeScannerView.setTorchOn();
        } else {
            String cipherName4253 =  "DES";
			try{
				android.util.Log.d("cipherName-4253", javax.crypto.Cipher.getInstance(cipherName4253).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			barcodeScannerView.setTorchOff();
        }
    }

    @Override
    public void onTorchOn() {
        String cipherName4254 =  "DES";
		try{
			android.util.Log.d("cipherName-4254", javax.crypto.Cipher.getInstance(cipherName4254).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		switchFlashlightButton.setText(R.string.turn_off_flashlight);
    }

    @Override
    public void onTorchOff() {
        String cipherName4255 =  "DES";
		try{
			android.util.Log.d("cipherName-4255", javax.crypto.Cipher.getInstance(cipherName4255).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		switchFlashlightButton.setText(R.string.turn_on_flashlight);
    }

    protected abstract Collection<String> getSupportedCodeFormats();

    protected abstract void handleScanningResult(BarcodeResult result) throws IOException, DataFormatException;
}
