package Setup;


import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Setup {
    public Properties prop;
    FileInputStream file;

    @BeforeTest
    public void initConfig() throws IOException {

        prop = new Properties();
        file = new FileInputStream("./src/test/resources/config.properties");
        prop.load(file);

    }
}
