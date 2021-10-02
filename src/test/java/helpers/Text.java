package helpers;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

@SuppressWarnings("unused")
public class Text {


    /**
     * @param input - A string that (hopefully) contains good XML
     * @return - a String that has line-breaks ind indentations, OR just an error message
     */
    public static String indentXML(String input) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (Exception e) {
            // Could be one of a couple. Don't send back the XML as output, it may not be nice enough to print out
            e.printStackTrace();
            return "Not XML or could not be parsed";
        }
    }

    /**
     * XML may not show nicely, even if you have indented it, so this makes it work nicely when displayed on a web page (report)
     *
     * @param xmlToConvert - - A string that (hopefully) contains good XML, possibly from indentXML
     * @return - a string that should show well in an HTML report
     */
    public static String convertXMLtoHTMLFriendly(String xmlToConvert) {
        xmlToConvert = indentXML(xmlToConvert);
        xmlToConvert = xmlToConvert.replace("<", "&lt;");
        xmlToConvert = xmlToConvert.replace(">", "&gt;");
        return xmlToConvert;
    }
}
