package com.app.shop.util;

import com.google.gson.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.*;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;

@Component
public class ClothesAPI implements CommandLineRunner {

    public static JsonArray items = new JsonArray();

    public static JsonObject prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(json_text).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return json;
    }

    public void getHMCloth() throws IOException {
        String [] categories = new String[]{"Ladies", "men_all"};
        for (int j = 0; j < 2; j++) {
            URL url = new URL("https://apidojo-hm-hennes-mauritz-v1.p.rapidapi.com/products/list?country=us&lang=en&currentpage=0&pagesize=30&categories=" + categories[j]);

            // Open the connection.
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("X-RapidAPI-Key", "23fdd19920msh46f82a646bd243bp1d1bcejsnd3b322329af3");
            connection.setRequestProperty("X-Rapidapi-Host", "apidojo-hm-hennes-mauritz-v1.p.rapidapi.com");

            // Receive the JSON response body.
            InputStream stream = connection.getInputStream();
            String response = new Scanner(stream).useDelimiter("\\A").next();

            // Construct the result object.
            SearchResults results = new SearchResults(new HashMap<String, String>(), response);

            // Extract Bing-related HTTP headers.
            Map<String, List<String>> headers = connection.getHeaderFields();
            for (String header : headers.keySet()) {
                if (header == null) continue;      // may have null key
                if (header.startsWith("BingAPIs-") || header.startsWith("X-MSEdge-")) {
                    results.relevantHeaders.put(header, headers.get(header).get(0));
                }
            }
            stream.close();


            JsonObject allResults = prettify(results.jsonResponse);

            JsonArray elem = allResults.get("results").getAsJsonArray();


            for (int i = 0; i < elem.size(); i++) {
                JsonElement current = elem.get(i);
                JsonObject newProduct = new JsonObject();
                newProduct.add("code", current.getAsJsonObject().get("code"));
                newProduct.add("name", current.getAsJsonObject().get("name"));
                newProduct.add("price", current.getAsJsonObject().get("whitePrice").getAsJsonObject().get("value"));

                JsonObject help = new JsonObject();
                help.add("normalPicture", current.getAsJsonObject().get("defaultArticle").getAsJsonObject().get("normalPicture").getAsJsonArray().get(0).getAsJsonObject().get("baseUrl"));
                help.add("logoPicture", current.getAsJsonObject().get("defaultArticle").getAsJsonObject().get("logoPicture").getAsJsonArray().get(0).getAsJsonObject().get("baseUrl"));
                newProduct.add("pictures", help);


                newProduct.add("linkPdp", new JsonPrimitive("https://www2.hm.com" + current.getAsJsonObject().get("linkPdp").toString().replace("\"", "")));
                newProduct.add("colors", current.getAsJsonObject().get("articleColorNames"));
                newProduct.add("brand", current.getAsJsonObject().get("brandName"));
                newProduct.add("categoryName", current.getAsJsonObject().get("categoryName"));
                newProduct.add("mainCategoryCode", current.getAsJsonObject().get("mainCategoryCode"));

                items.add(newProduct);
            }




        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(items));
        System.out.println(items.size());



    }

    @Override
    public void run(String... args) throws Exception {
        getHMCloth();
    }
}
