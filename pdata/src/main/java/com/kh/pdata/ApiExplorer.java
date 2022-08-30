package com.kh.pdata;/* Java 1.8 샘플 코드 */


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://www.kspo.or.kr/openapi/service/sportsNewFacilInfoService/getNewFacilInfoList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=gLqQXaEl7S%2F1SKAHgfQg6i%2FhHE0PHZOxx66vvYlVs0EAXuisC6z57aBdJSdkX2BwZ03megcKkrzvP%2Bd5cx4lNg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
//        urlBuilder.append("&" + URLEncoder.encode("faciNm","UTF-8") + "=" + URLEncoder.encode("365당구클럽", "UTF-8")); /*체육시설명*/
//        urlBuilder.append("&" + URLEncoder.encode("fcobNm","UTF-8") + "=" + URLEncoder.encode("당구장업", "UTF-8")); /*업종명*/
//        urlBuilder.append("&" + URLEncoder.encode("ftypeNm","UTF-8") + "=" + URLEncoder.encode("당구장", "UTF-8")); /*시설유형명*/
        urlBuilder.append("&" + URLEncoder.encode("faciRoadAddr1","UTF-8") + "=" + URLEncoder.encode("울산", "UTF-8")); /*시설유형명*/
//        urlBuilder.append("&" + URLEncoder.encode("faciAddr1","UTF-8") + "=" + URLEncoder.encode("울산", "UTF-8")); /*지번주소*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("accept", "application/xml");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
//        System.out.println(sb.toString());

        String xmlStr = sb.toString();

        XmlMapper xmlMapper = new XmlMapper();

        //xml to json
        JsonNode node = xmlMapper.readTree(xmlStr);
        ObjectMapper jsonMapper = new ObjectMapper();
        String json = jsonMapper.writeValueAsString(node);

        //xml to java Object
        Response response = xmlMapper.readValue(xmlStr, Response.class);

        for (Response.Body.Item item : response.getBody().getItems()) {
            System.out.println("item = " + item.getFaciAddr1());
        }

        //json to java Object
        System.out.println("json = " + json);
        ResponseJson response2 = jsonMapper.readValue(json, ResponseJson.class);
        System.out.println(response2.getBody());

    }
}