package io.appium.config;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class AllureHelper {

    private static Document environment;
    private static Element environmentRoot;

    public static void addEnvironmentParamsInReport() {

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = documentFactory.newDocumentBuilder();

            environment = docBuilder.newDocument();
            environmentRoot = environment.createElement("environment");
            environment.appendChild(environmentRoot);

            List<Device> devices = DevicesConfig.devices;

            List<String> devicesNames = devices.stream()
                    .map(device -> device.deviceName)
                    .collect(Collectors.toList());

            List<String> platformsList = devices.stream()
                    .map(device -> device.platformName + " " + device.platformVersion)
                    .collect(Collectors.toList());

            createParameter("Application", "");
            createParameter("Platform", String.join(", ", platformsList));
            createParameter("Simulator", String.join(", ", devicesNames));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(environment);
            StreamResult result = new StreamResult(new File("target/allure-results/environment.xml"));

            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createParameter(String key, String value) {
        Element parameterRoot = environment.createElement("parameter");
        environmentRoot.appendChild(parameterRoot);

        Element parameterKey = environment.createElement("key");
        parameterKey.appendChild(environment.createTextNode(key));
        parameterRoot.appendChild(parameterKey);

        Element parameterValue = environment.createElement("value");
        parameterValue.appendChild(environment.createTextNode(value));
        parameterRoot.appendChild(parameterValue);
    }
}
