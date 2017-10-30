package yotube;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import yotube.json.ActivityResponse;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws UnirestException, ParseException {
        String[] listVideo = {"HA6H6X77yZo", "aCx0qPN_zO0", "wMQu45BuFZU"};
        initUnirest();
//
//        HttpResponse<String> response = Unirest.get("https://www.googleapis.com/youtube/v3/commentThreads")
//                .queryString("part", "snippet,replies")
//                .queryString("videoId", "HA6H6X77yZo")
//                .queryString("maxResults", 1)
//                .queryString("key", "AIzaSyAsR-0YjQ1cD1HRe4wY9AsH-YMLTTMj_34")
//                .asString();
//            System.out.println(response.getBody());
////
//        HttpResponse<ActivityResponse> response = Unirest.get("https://www.googleapis.com/youtube/v3/commentThreads")
//                .queryString("part", "snippet,replies")
//                .queryString("videoId", "HA6H6X77yZo")
//                .queryString("maxResults", 5)
//                .queryString("key", "AIzaSyAsR-0YjQ1cD1HRe4wY9AsH-YMLTTMj_34")
//                .asObject(ActivityResponse.class);
//
//        ActivityResponse activity = response.getBody();
//        for (int i = 0; i < activity.items.size(); i++) {
//            Date update = MyDateUtils.convertStringToDate(activity.items.get(i).snippet.topLevelComment.snippet.updatedAt);
//            Date published = MyDateUtils.convertStringToDate(activity.items.get(i).snippet.topLevelComment.snippet.publishedAt);
//            System.out.println("name: " + "\"" + activity.items.get(i).snippet.topLevelComment.snippet.authorDisplayName + "\""
//                    + " like: " + activity.items.get(i).snippet.topLevelComment.snippet.likeCount
//                    + " date update: " + update
//                    + " date published: " + published
//                    + " edit " + MyDateUtils.datesComparing(published, update));
//            System.out.println("comment: " + activity.items.get(i).snippet.topLevelComment.snippet.textDisplay);
//            System.out.println();
//        }

        for (int i = 0; i < listVideo.length; i++) {
            showComment(listVideo[i]);
            System.out.println();
        }

    }

    private static void showComment(String linkVideo) throws UnirestException, ParseException {
        HttpResponse<ActivityResponse> response = Unirest.get("https://www.googleapis.com/youtube/v3/commentThreads")
                .queryString("part", "snippet,replies")
                .queryString("videoId", linkVideo)
                .queryString("maxResults", 5)
                .queryString("key", "AIzaSyAsR-0YjQ1cD1HRe4wY9AsH-YMLTTMj_34")
                .asObject(ActivityResponse.class);

        ActivityResponse activity = response.getBody();
        for (int i = 0; i < activity.items.size(); i++) {
            Date update = MyDateUtils.convertStringToDate(activity.items.get(i).snippet.topLevelComment.snippet.updatedAt);
            Date published = MyDateUtils.convertStringToDate(activity.items.get(i).snippet.topLevelComment.snippet.publishedAt);
            System.out.println("name: " + "\"" + activity.items.get(i).snippet.topLevelComment.snippet.authorDisplayName + "\""
                    + " like: " + activity.items.get(i).snippet.topLevelComment.snippet.likeCount
                    + " date update: " + update
                    + " date published: " + published
                    + " edit " + MyDateUtils.datesComparing(published, update));
            System.out.println("comment: " + activity.items.get(i).snippet.topLevelComment.snippet.textDisplay);
        }
    }

    private static void initUnirest() {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
