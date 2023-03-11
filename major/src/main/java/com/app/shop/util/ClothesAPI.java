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
import java.util.stream.IntStream;
import javax.net.ssl.HttpsURLConnection;

@Component
public class ClothesAPI implements CommandLineRunner {
    public static JsonObject allItems = new JsonObject();


    public static JsonArray getAllArrays(String category) {
        String [] productType = new String[] {"Hat", "Pants", "Shirt", "Socks", "Shoes"};

        JsonArray resultJSONArray = new JsonArray();
        for (int i = 0; i < 5; i ++){
            resultJSONArray.addAll(allItems.get(category).getAsJsonObject().get(productType[i]).getAsJsonArray());
        }

        return resultJSONArray;
    }

    public static JsonObject prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(json_text).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return json;
    }




    public void getHMCloth() throws IOException {

        String [] categories = new String[]{"Ladies", "men_all"};
        String [] productType = new String[] {"Hat", "Pants", "Shirt", "Socks", "Shoes"};

        for (int j = 0; j < 2; j++) {
            JsonObject itemsCategories = new JsonObject();
            for (int s = 0; s < 5; s++) {
                URL url = new URL("https://apidojo-hm-hennes-mauritz-v1.p.rapidapi.com/products/list?country=us&lang=en&currentpage=0&pagesize=10&categories=" + categories[j] + "&productTypes=" + productType[s]);

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

                JsonArray itemsForProductType = new JsonArray();


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
                    newProduct.add("categoryID", current.getAsJsonObject().get(""));
                    newProduct.add("categoryName", current.getAsJsonObject().get("categoryName"));
                    newProduct.add("mainCategoryCode", current.getAsJsonObject().get("mainCategoryCode"));

                    itemsForProductType.add(newProduct);
                }
                itemsCategories.add(productType[s], itemsForProductType);

            }

            allItems.add(categories[j], itemsCategories);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(allItems));
        System.out.println(allItems.size());

    }

    @Override
    public void run(String... args) throws Exception {
        getHMCloth();
    }
}
