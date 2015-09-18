package com.epam.service.restservice;

import com.epam.datamodel.CommonConstants;
import com.epam.exception.SendingException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Vadym_Vlasenko on 9/8/2015.
 */
@Service
public class HttpSender {

    public String sendPost(String URL, List<NameValuePair> valuePairList) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(URL);
        HttpEntity postParams = new UrlEncodedFormEntity(valuePairList);
        httpPost.setEntity(postParams);
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println("POST Response Status: "
                + statusCode);
        if (statusCode != 200) {
            throw new SendingException();
        }
        String response = readResponse(httpResponse);
        httpClient.close();
        return response;
    }

    public String sendGet(String URL) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(URL);
        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("GET Response Status: "
                + statusCode);
        if (statusCode != 200) {
            throw new SendingException();
        }
        return readResponse(response);
    }

    private String readResponse(HttpResponse httpResponse) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        return response.toString();
    }

}
