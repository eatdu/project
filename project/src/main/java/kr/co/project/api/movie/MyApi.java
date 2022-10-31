package kr.co.project.api.movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.web.util.UriComponentsBuilder;

public class MyApi {

	public static String getResponse(String url, Map map) throws Exception {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("");
		Iterator iter =  map.keySet().iterator(); //
		while (iter.hasNext()) {
			String key = (String)iter.next();
			builder.queryParam(key, map.get(key));
		}
			
		String returnUrl = url+builder.toUriString();
		
		URL apiUrl = new URL(returnUrl);
		
		HttpURLConnection con = (HttpURLConnection)apiUrl.openConnection();
		con.setRequestMethod("GET");
		
		InputStreamReader streamReader = new InputStreamReader(con.getInputStream());

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }
            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
	}
}
