package framework;

import java.io.IOException;

/**
 * This class holds all data that is shared over the life of the test run (i.e. broader scope even than features)
 * As of now, this is just reading the configuration files that will hold data relevant to the whole test run
 */
public class ContextOfTest {
    // these fields are static so that the test steps (which are static) can gain access to them
    public static ConfigReader sutConfiguration;
    public static ConfigReader testConfiguration;

    public static String sutUrl;

    // these are here so as to be highly visible to the code editor
    // update them if you place the properties files somewhere else
    private final String LOCATION_SUT_CONFIG_FILE = "configuration/systemUnderTest.properties";
    private final String LOCATION_TEST_CONFIG_FILE = "configuration/testFramework.properties";
    private final String LOCATION_REPORT_CONFIG_FILE = "configuration/reporting.properties";

    /**
     * The following private declaration of one of my kind and the subsequent private constructor ensure that I am a Singleton
     * Initialisation of me does not work if it is in the constructor, it has to be here
     */
    private static ContextOfTest theContext;

    static {
        try {
            theContext = new ContextOfTest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private ContextOfTest() throws IOException, NoSuchFieldException {

        sutConfiguration = new ConfigReader(LOCATION_SUT_CONFIG_FILE);
        testConfiguration = new ConfigReader(LOCATION_TEST_CONFIG_FILE);

        sutUrl = sutConfiguration.getProperty("protocol") + "://" + sutConfiguration.getProperty("domainName");

        // this system property is required by the fancy HTML reporter from https://gitlab.com/monochromata-de/cucumber-reporting-plugin
        System.setProperty("cucumber.reporting.config.file", LOCATION_REPORT_CONFIG_FILE);
    }
}
