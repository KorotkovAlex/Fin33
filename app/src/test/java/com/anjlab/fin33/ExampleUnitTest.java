package com.anjlab.fin33;

import com.anjlab.fin33.model.Bank;
import com.anjlab.fin33.model.BanksUpdatedListener;
import com.anjlab.fin33.model.Fin33Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.util.List;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    String url  = "http://www.fin33.ru/";
    Document doc;
    @Test
    public void addition_isCorrect() throws Exception {

        doc = Jsoup.connect(url).get();

        new Fin33Parser().parseMainInfo(doc, new BanksUpdatedListener() {
            @Override
            public void onParseDone(List<Bank> banks) {}

            @Override
            public void onParseError(Throwable error) {}
        });

    }

}