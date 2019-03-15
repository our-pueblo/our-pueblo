package com.codeup.ourpueblo.Controllers;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;


import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


import static com.codeup.ourpueblo.Controllers.constants.googleKey;


public class GetSpanish {

    public static String getSpanish(String text) throws Exception {
        // Instantiates a client
        Translate translate = TranslateOptions.newBuilder().setApiKey(googleKey).build().getService();
        // Enter sample text to be translated


        // Translates some text into Spanish
        Translation translation =
                translate.translate(
                        text,
                        TranslateOption.sourceLanguage("en"),
                        TranslateOption.targetLanguage("es"));

        // Print for comparison
        System.out.printf("Text: %s%n", text);
        System.out.printf("Translation: %s%n", translation.getTranslatedText());


        return translation.getTranslatedText();
    }
}
// [END translate_quickstart]