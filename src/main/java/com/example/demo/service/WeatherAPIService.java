package com.example.demo.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.example.demo.model.Forecast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherAPIService {
    public static void timelineRequestHttpClient() throws Exception {
        //set up the end point
        String apiEndPoint = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
        String location = "Minsk,BLR";
        String startDate = null;
        String endDate = null;
        String unitGroup = "metric";
        String apiKey = "J6XE7L79EF9LFZUKWNSMQY9JK";
        StringBuilder requestBuilder = new StringBuilder(apiEndPoint);
        requestBuilder.append(URLEncoder.encode(location, StandardCharsets.UTF_8.toString()));
        if (startDate != null && !startDate.isEmpty()) {
            requestBuilder.append("/").append(startDate);
            if (endDate != null && !endDate.isEmpty()) {
                requestBuilder.append("/").append(endDate);
            }
        }
        URIBuilder builder = new URIBuilder(requestBuilder.toString());
        builder.setParameter("unitGroup", unitGroup)
                .setParameter("key", apiKey);
        HttpGet get = new HttpGet(builder.build());
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(get);
        String rawResult = null;
        try {
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                System.out.printf("Bad response status code:%d%n", response.getStatusLine().getStatusCode());
                return;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                rawResult = EntityUtils.toString(entity, Charset.forName("utf-8"));
            }
        } finally {
            response.close();
        }
        parseTimelineJson(rawResult);
    }

    private static void parseTimelineJson(String rawResult) {
        if (rawResult == null || rawResult.isEmpty()) {
            System.out.printf("No raw data%n");
            return;
        }
        JSONObject timelineResponse = new JSONObject(rawResult);
        ZoneId zoneId = ZoneId.of(timelineResponse.getString("timezone"));
        System.out.printf("Weather data for: %s%n", timelineResponse.getString("resolvedAddress"));
        JSONArray values = timelineResponse.getJSONArray("days");
        System.out.printf("Date\tMaxTemp\tMinTemp\tPrecip\tSource%n");
        for (int i = 0; i < values.length(); i++) {
            JSONObject dayValue = values.getJSONObject(i);
            ZonedDateTime datetime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(dayValue.getLong("datetimeEpoch")), zoneId);
            double maxtemp = dayValue.getDouble("tempmax");
            double mintemp = dayValue.getDouble("tempmin");
            double pop = dayValue.getDouble("precip");
            String source = dayValue.getString("source");
            System.out.printf("%s\t%.1f\t%.1f\t%.1f\t%s%n", datetime.format(DateTimeFormatter.ISO_LOCAL_DATE), maxtemp, mintemp, pop, source);
        }
    }

    public static Forecast getForecast(int days, String location) throws Exception {
        Forecast forecast = new Forecast();
        forecast.setLocation(location);
        forecast.setTime(new Timestamp(System.currentTimeMillis() + (86400000L * days)));
        String apiEndPoint = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
        String startDate = null;
        String endDate = null;
        String unitGroup = "metric";
        String apiKey = "J6XE7L79EF9LFZUKWNSMQY9JK";
        StringBuilder requestBuilder = new StringBuilder(apiEndPoint);
        requestBuilder.append(URLEncoder.encode(location, StandardCharsets.UTF_8.toString()));
        if (startDate != null && !startDate.isEmpty()) {
            requestBuilder.append("/").append(startDate);
            if (endDate != null && !endDate.isEmpty()) {
                requestBuilder.append("/").append(endDate);
            }
        }
        URIBuilder builder = new URIBuilder(requestBuilder.toString());
        builder.setParameter("unitGroup", unitGroup)
                .setParameter("key", apiKey);
        HttpGet get = new HttpGet(builder.build());
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(get);
        String rawResult = null;
        try {
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                System.out.printf("Bad response status code:%d%n", response.getStatusLine().getStatusCode());
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                rawResult = EntityUtils.toString(entity, Charset.forName("utf-8"));
            }
        } finally {
            response.close();
        }
        parseTimelineJson(rawResult);
        if (rawResult == null || rawResult.isEmpty()) {
            System.out.printf("No raw data%n");
            return null;
        }
        JSONObject timelineResponse = new JSONObject(rawResult);
        ZoneId zoneId = ZoneId.of(timelineResponse.getString("timezone"));
        JSONArray values = timelineResponse.getJSONArray("days");
        JSONObject dayValue = values.getJSONObject(days);
        forecast.setMaxtemp(dayValue.getDouble("tempmax"));
        forecast.setMintemp(dayValue.getDouble("tempmin"));
        forecast.setPrecip(dayValue.getDouble("precip"));
        return forecast;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(getForecast(1, "Minsk, BLR"));
    }
}
