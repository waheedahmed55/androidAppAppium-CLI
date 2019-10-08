package io.appium.config;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

import static org.testng.Assert.fail;

public class DevicesConfig {

    public static List<Device> devices;

    static {
        DevicesDto devicesDto;

        try {
            final File classpathRoot = new File(System.getProperty("user.dir"));
            File globalConfigDevicesFile = new File("src/main/resources/devices.xml");
            File localConfigDevicesFile = new File("src/main/resources/local.devices.xml");

            File configDevicesFile = localConfigDevicesFile.exists() ? localConfigDevicesFile : globalConfigDevicesFile;

            JAXBContext jaxbContext = JAXBContext.newInstance(DevicesDto.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            devicesDto = (DevicesDto) jaxbUnmarshaller.unmarshal(configDevicesFile);

            devices = devicesDto.devicesList;
        } catch (Exception e) {
            fail("Can not init list Devices!\n" + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }
}