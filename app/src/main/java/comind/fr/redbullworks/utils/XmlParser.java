package comind.fr.redbullworks.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import comind.fr.redbullworks.model.News;

/**
 * Created by Pascal on 21/03/2017.
 *
 */

public class XmlParser extends AsyncTask<Void, Void, List<News>> {

    private String getXmlFromUrl() {
        String xml = null;
        try {
            URL url=new URL("http://red-bull-x.webnode.fr/rss/all.xml");
            HttpURLConnection http=(HttpURLConnection) url.openConnection();
            http.setDoInput(true);
            http.connect();
            InputStream is = http.getInputStream();

            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }

            xml = total.toString();


        } catch (IOException e) {
            e.printStackTrace();
        }
        // return XML
        return xml;
    }

    private Document getDomElement(String xml){
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            doc = db.parse(is);

        } catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        }
        // return DOM
        return doc;
    }

    private String getElementValue( Node elem ) {
        Node child;
        if( elem != null){
            if (elem.hasChildNodes()){
                for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
                    if( child.getNodeType() == Node.TEXT_NODE  ){
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }

    @Override
    protected List<News> doInBackground(Void... params) {

        List<News> result = new LinkedList<>();

        /*String xml = getXmlFromUrl(); // getting XML

        Document doc = getDomElement(xml); // getting DOM element

        NodeList nl = doc.getElementsByTagName("item");

        // looping through all item nodes <item>
        for (int i = 0; i < nl.getLength(); i++) {

            Element e = (Element) nl.item(i);

            NodeList titleNode = e.getElementsByTagName("title");
            Element titleElement = (Element) titleNode.item(0);

            NodeList descriptionNode = e.getElementsByTagName("description");
            Element descriptionElement = (Element) descriptionNode.item(0);

            NodeList linkNode = e.getElementsByTagName("link");
            Element linkElement = (Element) linkNode.item(0);

            String title = getCharacterDataFromElement(titleElement);
            String description = getCharacterDataFromElement(descriptionElement);
            String link = getCharacterDataFromElement(linkElement);

            result.add(new News(title, description, link, ""));
        }*/

        return result;
    }

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }
}
