package managers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import enums.DriverType;
import enums.EnvironmentType;


public class WebDriverManager {
	private WebDriver driver;
	private static DriverType driverType;
	private static EnvironmentType environmentType;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

	public WebDriverManager() {
		driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
		environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
	}

	public WebDriver getDriver() {
		if(driver == null) driver = createDriver();
		return driver;
	}

	private WebDriver createDriver() {
		switch (environmentType) {	    
	        case LOCAL : driver = createLocalDriver();
	        	break;
	        case REMOTE : driver = createRemoteDriver();
	        	break;
		}
		if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize())
			driver.manage().window().maximize();
		    driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
	return driver;
	}

	private WebDriver createRemoteDriver() {
		throw new RuntimeException("RemoteWebDriver is not yet implemented");
	}

	private WebDriver createLocalDriver() {
        switch (driverType) {	    
        case FIREFOX : driver = new FirefoxDriver();
	    	break;
        case CHROME : 
        	System.setProperty(CHROME_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReader().getDriverPath());
    		ChromeOptions options = new ChromeOptions();
    		options.setExperimentalOption("useAutomationExtension", false);
    		options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation")); 
    		Map<String, Object> prefs = new HashMap<String, Object>();
    		prefs.put("credentials_enable_service", false);
    		prefs.put("profile.password_manager_enabled", false);
    		options.setExperimentalOption("prefs", prefs);
    		//options.addArguments("--headless");
    		options.addArguments("window-size=1400,1500"); 
        	driver = new ChromeDriver(options);
    		break;
        case INTERNETEXPLORER : driver = new InternetExplorerDriver();
    		break;
        }
		return driver;
	}	

	public void closeDriver() {
		driver.close();
		driver.quit();
	}

}