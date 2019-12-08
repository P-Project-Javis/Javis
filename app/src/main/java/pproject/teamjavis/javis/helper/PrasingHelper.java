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
           URL url = new URL(           ); //검색 URL부분

           XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
           XmlPullParser parser = parserCreator.newPullParser();

           parser.setInput(url.openStream(), null);
           int parserEvent = parser.getEventType();


       } catch (IOException e) {

       }
   }


}
