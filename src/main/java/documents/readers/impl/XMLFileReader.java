package documents.readers.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import documents.exceptions.FileReaderException;
import documents.readers.IFileReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLFileReader implements IFileReader {

    /**
     * Method reading XML file
     * @param filePath file path of the XML file to be converted
     * @return converted XML file as List of Maps
     * @throws FileReaderException if reading XML file is failed
     */
    @Override
    public List<Map<String, String>> read(String filePath) throws FileReaderException {
        List<Map<String, String>> result = new ArrayList<>();
        File file = new File(filePath);
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            //normalization - it compresses the content of the object
            // and speeds up file processing (it is important for large files)
            document.getDocumentElement().normalize();
            Element rootElement = document.getDocumentElement();

            NodeList elements = rootElement.getChildNodes();

            for (int i = 0; i < elements.getLength(); i++) {
                Node node = elements.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element nodeElement = (Element) node;
                    NodeList dataRawXml = nodeElement.getChildNodes();

                    Map<String, String> data = new HashMap<>();
                    for (int j = 0; j < dataRawXml.getLength(); j++) {
                        Node subNode = dataRawXml.item(j);
                        if (subNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element dataElement = (Element) subNode;
                            data.put(dataElement.getTagName(), dataElement.getFirstChild().getTextContent());
                        }
                    }
                    result.add(data);
                }
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new FileReaderException(e.getMessage(), e);
        }
        return result;
    }
}
