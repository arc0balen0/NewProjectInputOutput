package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ConfigParser {
    public static Config parseConfig(File configFile) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(configFile);
            document.getDocumentElement().normalize();

            Element rootElement = document.getDocumentElement();

            // Чтение блока load
            NodeList loadNodeList = rootElement.getElementsByTagName("load");
            Element loadElement = (Element) loadNodeList.item(0);
            boolean loadEnabled = Boolean.parseBoolean(loadElement.getElementsByTagName("enabled").item(0).getTextContent());
            String loadFileName = loadElement.getElementsByTagName("fileName").item(0).getTextContent();
            String loadFormat = loadElement.getElementsByTagName("format").item(0).getTextContent();

            // Чтение блока save
            NodeList saveNodeList = rootElement.getElementsByTagName("save");
            Element saveElement = (Element) saveNodeList.item(0);
            boolean saveEnabled = Boolean.parseBoolean(saveElement.getElementsByTagName("enabled").item(0).getTextContent());
            String saveFileName = saveElement.getElementsByTagName("fileName").item(0).getTextContent();
            String saveFormat = saveElement.getElementsByTagName("format").item(0).getTextContent();

            // Чтение блока log
            NodeList logNodeList = rootElement.getElementsByTagName("log");
            Element logElement = (Element) logNodeList.item(0);
            boolean logEnabled = Boolean.parseBoolean(logElement.getElementsByTagName("enabled").item(0).getTextContent());
            String logFileName = logElement.getElementsByTagName("fileName").item(0).getTextContent();

            return new Config(loadEnabled, loadFileName, loadFormat, saveEnabled, saveFileName, saveFormat, logEnabled, logFileName);
        } catch (Exception e) {
            System.out.println("Ошибка чтения файла конфигурации.");
            e.printStackTrace();
            return null;
        }
    }
}
