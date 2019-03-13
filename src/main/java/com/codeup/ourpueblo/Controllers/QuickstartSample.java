package com.codeup.ourpueblo.Controllers;
// [START translate_quickstart]
// Imports the Google Cloud client library
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class QuickstartSample {
    public static void main(String... args) throws Exception {
        // Instantiates a client
        Translate translate = TranslateOptions.getDefaultInstance().getService();

        // The text to translate
        String text = "<div> <h1> This is the greatest day of my life. Your mother looks like she works out. </h1></div>";

        // Translates some text into Russian
        Translation translation =
                translate.translate(
                        text,
                        TranslateOption.sourceLanguage("en"),
                        TranslateOption.targetLanguage("es"));


        System.out.printf("Text: %s%n", text);
        System.out.printf("Translation: %s%n", translation.getTranslatedText());
    }
}
// [END translate_quickstart]