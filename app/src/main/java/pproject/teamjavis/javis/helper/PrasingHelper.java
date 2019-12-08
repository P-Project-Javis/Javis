package pproject.teamjavis.javis.helper;

import android.os.Bundle;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.URL;

public class PrasingHelper {

   @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

       try{
           URL url = new URL("http://openapi.kepco.co.kr/service/evInfoService/getEvSearchList?"
                   + "&pageNo=1&numOfRows=10&ServiceKey="
                   + "iOsw4MlgRU0JZpvuR5AkLUfkX%2FAOl0Q03HF78VRzR2g0dz6iD0esiw6HmLHKly6PVvGVP2PPgRpqtpULJBWSHg%3D%3D"
           ); //검색 URL부분

           XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
           XmlPullParser parser = parserCreator.newPullParser();

           parser.setInput(url.openStream(), null);
           int parserEvent = parser.getEventType();


       } catch (IOException e) {

       }
   }


}
