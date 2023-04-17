package com.app.shop.util;

import com.app.shop.model.Product;
import com.app.shop.repository.CategoryRepository;
import com.app.shop.repository.ProductRepository;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.util.stream.IntStream;
import javax.net.ssl.HttpsURLConnection;

@Component
public class ClothesAPI implements CommandLineRunner {
    public static JsonObject allItems = new JsonObject();

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;


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
            for (int s = 0; s < 5; s++) {
                URL url = new URL("https://apidojo-hm-hennes-mauritz-v1.p.rapidapi.com/products/list?country=us&lang=en&currentpage=0&pagesize=10&categories=" + categories[j] + "&productTypes=" + productType[s]);

                // Open the connection.
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestProperty("X-RapidAPI-Key", "f038a88304msh80e246d7a73f86dp14b10bjsnffb88e99baca");
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
                    Product newProduct = new Product();


                    newProduct.setCode(current.getAsJsonObject().get("code").getAsString());
                    newProduct.setName(current.getAsJsonObject().get("name").getAsString());
                    newProduct.setPrice(current.getAsJsonObject().get("whitePrice").getAsJsonObject().get("value").getAsString());
                    newProduct.setNormalPicture(current.getAsJsonObject().get("defaultArticle").getAsJsonObject().get("normalPicture").getAsJsonArray().get(0).getAsJsonObject().get("baseUrl").getAsString());
                    newProduct.setLogoPicture(current.getAsJsonObject().get("defaultArticle").getAsJsonObject().get("logoPicture").getAsJsonArray().get(0).getAsJsonObject().get("baseUrl").getAsString());
                    newProduct.setLinkPdp("https://www2.hm.com" + current.getAsJsonObject().get("linkPdp").toString().replace("\"", ""));
                    newProduct.setColors( current.getAsJsonObject().get("articleColorNames").getAsJsonArray().toString());
                    newProduct.setBrand( current.getAsJsonObject().get("brandName").getAsString());
                    newProduct.setCategoryName(current.getAsJsonObject().get("categoryName").getAsString());
                    newProduct.setMainCategoryCode(current.getAsJsonObject().get("mainCategoryCode").getAsString());
                    newProduct.setSex(categories[j]);
                    newProduct.setCategory(categoryRepository.findCategoryByName(productType[s]));

                    productRepository.save(newProduct);

                }

            }

        }

        System.out.println("Ready");


    }


    @Override
    public void run(String... args) throws Exception {
        // getHMCloth();
    }
}
