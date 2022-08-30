package com.kh.pdata.svc;/* Java 1.8 샘플 코드 */

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class ApiExplorerImpl implements ApiExplorer {

    @Override
    public Response apiCall() {
        String xmlStr = "";
        Response response = null;
        try {
            StringBuilder urlBuilder = new StringBuilder("http://www.kspo.or.kr/openapi/service/sportsNewFacilInfoService/getNewFacilInfoList"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=gLqQXaEl7S%2F1SKAHgfQg6i%2FhHE0PHZOxx66vvYlVs0EAXuisC6z57aBdJSdkX2BwZ03megcKkrzvP%2Bd5cx4lNg%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("20", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("faciRoadAddr1", "UTF-8") + "=" + URLEncoder.encode("울산", "UTF-8")); /*시설유형명*/
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "application/xml");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
            xmlStr = sb.toString();

            //xml to java Object
            XmlMapper xmlMapper = new XmlMapper();
            response = xmlMapper.readValue(xmlStr, Response.class);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}