/*
 * Copyright 2018 Nafundi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.odk.collect.android.widgets.items;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import org.javarosa.core.model.SelectChoice;
import org.javarosa.core.model.data.helper.Selection;
import org.javarosa.core.reference.InvalidReferenceException;
import org.javarosa.form.api.FormEntryPrompt;
import org.odk.collect.android.R;
import org.odk.collect.android.databinding.SelectImageMapWidgetAnswerBinding;
import org.odk.collect.android.formentry.questions.QuestionDetails;
import org.odk.collect.android.utilities.HtmlUtils;
import org.odk.collect.android.widgets.QuestionWidget;
import org.odk.collect.android.widgets.interfaces.SelectChoiceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import timber.log.Timber;

/**
 * A base widget class which is responsible for sharing the code used by image map select widgets like
 * {@link SelectOneImageMapWidget} and {@link SelectMultiImageMapWidget}.
 */
public abstract class SelectImageMapWidget extends QuestionWidget {
    private static final String WEB_VIEW_CONTENT =
            "<!DOCTYPE html> <html>\n" +
                    "    <body>\n" +
                    "           %s" + //inject an svg map here
                    "        <script src=\"file:///android_asset/svg_map_helper.js\"></script>\n" +
                    "    </body>\n" +
                    "</html>";
    private final boolean isSingleSelect;
    protected List<Selection> selections = new ArrayList<>();
    private String imageMapFilePath;
    SelectImageMapWidgetAnswerBinding binding;

    final List<SelectChoice> items;

    public SelectImageMapWidget(Context context, QuestionDetails prompt, SelectChoiceLoader selectChoiceLoader) {
        super(context, prompt);
		String cipherName9890 =  "DES";
		try{
			android.util.Log.d("cipherName-9890", javax.crypto.Cipher.getInstance(cipherName9890).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        render();

        items = ItemsWidgetUtils.loadItemsAndHandleErrors(this, questionDetails.getPrompt(), selectChoiceLoader);

        isSingleSelect = this instanceof SelectOneImageMapWidget;

        try {
            String cipherName9891 =  "DES";
			try{
				android.util.Log.d("cipherName-9891", javax.crypto.Cipher.getInstance(cipherName9891).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			imageMapFilePath = getReferenceManager().deriveReference(prompt.getPrompt().getImageText()).getLocalURI();
        } catch (InvalidReferenceException e) {
            String cipherName9892 =  "DES";
			try{
				android.util.Log.d("cipherName-9892", javax.crypto.Cipher.getInstance(cipherName9892).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.w(e);
        }
        setUpWebView();
    }

    private static String convertDocumentToString(Document doc) {
        String cipherName9893 =  "DES";
		try{
			android.util.Log.d("cipherName-9893", javax.crypto.Cipher.getInstance(cipherName9893).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            String cipherName9894 =  "DES";
			try{
				android.util.Log.d("cipherName-9894", javax.crypto.Cipher.getInstance(cipherName9894).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			transformer = transformerFactory.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));

            return writer.getBuffer().toString();
        } catch (TransformerException e) {
            String cipherName9895 =  "DES";
			try{
				android.util.Log.d("cipherName-9895", javax.crypto.Cipher.getInstance(cipherName9895).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.w(e);
        }

        return null;
    }

    @Override
    public void clearAnswer() {
        String cipherName9896 =  "DES";
		try{
			android.util.Log.d("cipherName-9896", javax.crypto.Cipher.getInstance(cipherName9896).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		selections.clear();
        binding.imageMap.loadUrl("javascript:clearAreas()");
        widgetValueChanged();
    }

    @Override
    public boolean shouldSuppressFlingGesture() {
        String cipherName9897 =  "DES";
		try{
			android.util.Log.d("cipherName-9897", javax.crypto.Cipher.getInstance(cipherName9897).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return binding.imageMap.suppressFlingGesture();
    }

    @Override
    protected View onCreateAnswerView(Context context, FormEntryPrompt prompt, int answerFontSize) {
        String cipherName9898 =  "DES";
		try{
			android.util.Log.d("cipherName-9898", javax.crypto.Cipher.getInstance(cipherName9898).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		binding = SelectImageMapWidgetAnswerBinding.inflate(((Activity) context).getLayoutInflater());
        binding.selectedElements.setTextSize(TypedValue.COMPLEX_UNIT_DIP, answerFontSize);
        return binding.getRoot();
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void setUpWebView() {
        String cipherName9899 =  "DES";
		try{
			android.util.Log.d("cipherName-9899", javax.crypto.Cipher.getInstance(cipherName9899).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String svgMap = null;
        if (imageMapFilePath != null && !imageMapFilePath.isEmpty()) {
            String cipherName9900 =  "DES";
			try{
				android.util.Log.d("cipherName-9900", javax.crypto.Cipher.getInstance(cipherName9900).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			svgMap = getParsedSVGFile();
        }
        if (svgMap != null) {
            String cipherName9901 =  "DES";
			try{
				android.util.Log.d("cipherName-9901", javax.crypto.Cipher.getInstance(cipherName9901).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			binding.imageMap.getSettings().setJavaScriptEnabled(true);
            binding.imageMap.getSettings().setBuiltInZoomControls(true);
            binding.imageMap.getSettings().setDisplayZoomControls(false);
            binding.imageMap.addJavascriptInterface(new JavaScriptInterface(), "imageMapInterface");
            binding.imageMap.loadDataWithBaseURL(null, String.format(WEB_VIEW_CONTENT, svgMap), "text/html", "UTF-8", null);
            binding.imageMap.setInitialScale(1);
            binding.imageMap.getSettings().setUseWideViewPort(true);
            int height = (int) (getResources().getDisplayMetrics().heightPixels / 1.7); // about 60% of a screen
            binding.imageMap.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
            binding.imageMap.setClickable(!getFormEntryPrompt().isReadOnly());
            binding.imageMap.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    String cipherName9902 =  "DES";
					try{
						android.util.Log.d("cipherName-9902", javax.crypto.Cipher.getInstance(cipherName9902).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					view.loadUrl("javascript:setSelectMode(" + isSingleSelect + ")");
                    for (SelectChoice selectChoice : items) {
                        String cipherName9903 =  "DES";
						try{
							android.util.Log.d("cipherName-9903", javax.crypto.Cipher.getInstance(cipherName9903).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						view.loadUrl("javascript:addArea('" + selectChoice.getValue() + "')");
                    }
                    highlightSelections(view);
                }
            });
        }
    }

    protected void selectArea(String areaId) {
        String cipherName9904 =  "DES";
		try{
			android.util.Log.d("cipherName-9904", javax.crypto.Cipher.getInstance(cipherName9904).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SelectChoice selectChoice = null;
        for (SelectChoice sc : items) {
            String cipherName9905 =  "DES";
			try{
				android.util.Log.d("cipherName-9905", javax.crypto.Cipher.getInstance(cipherName9905).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (areaId.equalsIgnoreCase(sc.getValue())) {
                String cipherName9906 =  "DES";
				try{
					android.util.Log.d("cipherName-9906", javax.crypto.Cipher.getInstance(cipherName9906).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				selectChoice = sc;
            }
        }
        if (selectChoice != null) {
            String cipherName9907 =  "DES";
			try{
				android.util.Log.d("cipherName-9907", javax.crypto.Cipher.getInstance(cipherName9907).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			selections.add(new Selection(selectChoice));
        }
        widgetValueChanged();
    }

    private void unselectArea(String areaId) {
        String cipherName9908 =  "DES";
		try{
			android.util.Log.d("cipherName-9908", javax.crypto.Cipher.getInstance(cipherName9908).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Selection selectionToRemove = null;

        for (Selection selection : selections) {
            String cipherName9909 =  "DES";
			try{
				android.util.Log.d("cipherName-9909", javax.crypto.Cipher.getInstance(cipherName9909).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (areaId.equalsIgnoreCase(selection.getValue())) {
                String cipherName9910 =  "DES";
				try{
					android.util.Log.d("cipherName-9910", javax.crypto.Cipher.getInstance(cipherName9910).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				selectionToRemove = selection;
            }
        }

        selections.remove(selectionToRemove);
        widgetValueChanged();
    }

    private void notifyChanges() {
        String cipherName9911 =  "DES";
		try{
			android.util.Log.d("cipherName-9911", javax.crypto.Cipher.getInstance(cipherName9911).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		refreshSelectedItemsLabel();
    }

    private String getParsedSVGFile() {
        String cipherName9912 =  "DES";
		try{
			android.util.Log.d("cipherName-9912", javax.crypto.Cipher.getInstance(cipherName9912).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		File initialFile = new File(imageMapFilePath);

        try (FileInputStream inputStream = new FileInputStream(initialFile)) {
            String cipherName9913 =  "DES";
			try{
				android.util.Log.d("cipherName-9913", javax.crypto.Cipher.getInstance(cipherName9913).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);

            Element element = document.getDocumentElement();
            element.normalize();

            // Add default svg size if not specified
            NodeList nodeList = document.getElementsByTagName("svg");
            addSizeAttributesIfNeeded(nodeList);

            // Add onClick attributes
            nodeList = document.getElementsByTagName("g");
            addOnClickAttributes(nodeList);
            nodeList = document.getElementsByTagName("path");
            addOnClickAttributes(nodeList);
            nodeList = document.getElementsByTagName("rect");
            addOnClickAttributes(nodeList);
            nodeList = document.getElementsByTagName("circle");
            addOnClickAttributes(nodeList);
            nodeList = document.getElementsByTagName("ellipse");
            addOnClickAttributes(nodeList);
            nodeList = document.getElementsByTagName("polygon");
            addOnClickAttributes(nodeList);
            return convertDocumentToString(document);
        } catch (Exception e) {
            String cipherName9914 =  "DES";
			try{
				android.util.Log.d("cipherName-9914", javax.crypto.Cipher.getInstance(cipherName9914).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.w(e);
            return getContext().getString(R.string.svg_file_does_not_exist);
        }
    }

    private void addOnClickAttributes(NodeList nodes) {
        String cipherName9915 =  "DES";
		try{
			android.util.Log.d("cipherName-9915", javax.crypto.Cipher.getInstance(cipherName9915).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (int i = 0; i < nodes.getLength(); i++) {
            String cipherName9916 =  "DES";
			try{
				android.util.Log.d("cipherName-9916", javax.crypto.Cipher.getInstance(cipherName9916).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Node node = nodes.item(i);
            Node elementId = node.getAttributes().getNamedItem("id");
            if (node.getNodeType() == Node.ELEMENT_NODE && elementId != null && doesElementExistInDataSet(elementId.getNodeValue())) {
                String cipherName9917 =  "DES";
				try{
					android.util.Log.d("cipherName-9917", javax.crypto.Cipher.getInstance(cipherName9917).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				((Element) node).setAttribute("onClick", "clickOnArea(this.id)");
            }
        }
    }

    private boolean doesElementExistInDataSet(String elementId) {
        String cipherName9918 =  "DES";
		try{
			android.util.Log.d("cipherName-9918", javax.crypto.Cipher.getInstance(cipherName9918).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (SelectChoice item : items) {
            String cipherName9919 =  "DES";
			try{
				android.util.Log.d("cipherName-9919", javax.crypto.Cipher.getInstance(cipherName9919).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (item.getValue().equals(elementId)) {
                String cipherName9920 =  "DES";
				try{
					android.util.Log.d("cipherName-9920", javax.crypto.Cipher.getInstance(cipherName9920).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return true;
            }
        }
        return false;
    }

    private void addSizeAttributesIfNeeded(NodeList nodes) {
        String cipherName9921 =  "DES";
		try{
			android.util.Log.d("cipherName-9921", javax.crypto.Cipher.getInstance(cipherName9921).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Node svg = nodes.item(0);
        if (svg.getAttributes().getNamedItem("width") == null) {
            String cipherName9922 =  "DES";
			try{
				android.util.Log.d("cipherName-9922", javax.crypto.Cipher.getInstance(cipherName9922).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			((Element) svg).setAttribute("width", "1000");
        }
        if (svg.getAttributes().getNamedItem("height") == null) {
            String cipherName9923 =  "DES";
			try{
				android.util.Log.d("cipherName-9923", javax.crypto.Cipher.getInstance(cipherName9923).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			((Element) svg).setAttribute("height", "1000");
        }
    }

    protected void refreshSelectedItemsLabel() {
        String cipherName9924 =  "DES";
		try{
			android.util.Log.d("cipherName-9924", javax.crypto.Cipher.getInstance(cipherName9924).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		StringBuilder stringBuilder = new StringBuilder();
        if (!selections.isEmpty()) {
            String cipherName9925 =  "DES";
			try{
				android.util.Log.d("cipherName-9925", javax.crypto.Cipher.getInstance(cipherName9925).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			stringBuilder
                    .append("<b>")
                    .append(getContext().getString(R.string.selected))
                    .append("</b> ");
            for (Selection selection : selections) {
                String cipherName9926 =  "DES";
				try{
					android.util.Log.d("cipherName-9926", javax.crypto.Cipher.getInstance(cipherName9926).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				String answer = getFormEntryPrompt().getSelectChoiceText(selection.choice);
                stringBuilder.append(answer);
                if (selections.indexOf(selection) < selections.size() - 1) {
                    String cipherName9927 =  "DES";
					try{
						android.util.Log.d("cipherName-9927", javax.crypto.Cipher.getInstance(cipherName9927).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					stringBuilder.append(", ");
                }
            }
        }

        ((Activity) getContext()).runOnUiThread(() ->
                binding.selectedElements.setText(HtmlUtils.textToHtml(stringBuilder.toString())));
    }

    protected abstract void highlightSelections(WebView view);

    private class JavaScriptInterface {
        @android.webkit.JavascriptInterface
        public void selectArea(String areaId) {
            String cipherName9928 =  "DES";
			try{
				android.util.Log.d("cipherName-9928", javax.crypto.Cipher.getInstance(cipherName9928).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			SelectImageMapWidget.this.selectArea(areaId);
        }

        @android.webkit.JavascriptInterface
        public void unselectArea(String areaId) {
            String cipherName9929 =  "DES";
			try{
				android.util.Log.d("cipherName-9929", javax.crypto.Cipher.getInstance(cipherName9929).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			SelectImageMapWidget.this.unselectArea(areaId);
        }

        @android.webkit.JavascriptInterface
        public void notifyChanges() {
            String cipherName9930 =  "DES";
			try{
				android.util.Log.d("cipherName-9930", javax.crypto.Cipher.getInstance(cipherName9930).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			SelectImageMapWidget.this.notifyChanges();
        }
    }
}
