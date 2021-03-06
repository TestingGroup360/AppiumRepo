package SeleniumGenric;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverUtils {

	public static WebDriver driver = null;

	public static WebDriver launchbrowser(String Browsername, String url) {
		if (Browsername.equalsIgnoreCase("ff")) {
			System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (Browsername.equalsIgnoreCase("ch")) {
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--start-maximized");
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver = new ChromeDriver(co);
		} else if (Browsername.equalsIgnoreCase("iE")) {
			System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else if (Browsername.equalsIgnoreCase("ed")) {
			System.setProperty("webdriver.edge.driver", "drivers/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();

		}
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		driver.get(url);
		return driver;

	}
	
	public static void launchbrowserOnAndroid(boolean BrowserTypebrowserName)  {
		CommandLine command = new CommandLine("cmd");
		command.addArgument("/c");
		command.addArgument("Appium & ");		
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		try {
			executor.execute(command, resultHandler);
		} catch (ExecuteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
     	DesiredCapabilities android=DesiredCapabilities.android();
		android.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserTypebrowserName);
		android.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
		android.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		android.setCapability(MobileCapabilityType.DEVICE_NAME, "android");
		android.setCapability(MobileCapabilityType.VERSION, "7.0");
		URL StringUrl = null;		
		try {
			StringUrl=new URL("http://0.0.0.0:4723//wd/hub");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		AndroidDriver driver=new AndroidDriver(StringUrl, android);
		
		driver.get("https://m.facebook.com/");
		

	}

	public static WebDriver launchbrowserforgrid(String Gridbrowsername, Platform pl, String browsername) {

		System.setProperty("webdriver.gecko.driver",
				"C://Users//Deepak//workspace//Training_PoaDevEnv//Software//geckodriver.exe");
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		cap.setBrowserName(Gridbrowsername);
		cap.setPlatform(pl);
		driver = new FirefoxDriver(cap);
		driver.get(browsername);
		return driver;

	}

	public static WebDriver launch(String Gridbrowsername, Platform pl, String browsername) {

		System.setProperty("webdriver.gecko.driver",
				"C://Users//Deepak//workspace//Training_PoaDevEnv//Software//geckodriver.exe");
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		cap.setBrowserName(Gridbrowsername);
		cap.setPlatform(pl);
		driver = new FirefoxDriver(cap);
		driver.get(browsername);
		return driver;

	}

	public static void closeDriver() {
		driver.close();
	}

	public static void quitDriver() {
		driver.quit();
	}

	public static void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	public static void deleteCookiebyName(String cookieName) {
		driver.manage().deleteCookieNamed(cookieName);
	}

	public static void remoteWebDriver(String browserName, String ExePath, String remoteMachineUrl, String appUrl) {
		DesiredCapabilities capObj = null;
		WebDriver remoteWebDriver = null;
		if (browserName.equalsIgnoreCase("ff")) {
			try {
				capObj = DesiredCapabilities.firefox();
				capObj.setCapability(FirefoxDriver.BINARY, new File(ExePath).getAbsolutePath());
				remoteWebDriver = new RemoteWebDriver(new URL(remoteMachineUrl), capObj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (browserName.equalsIgnoreCase("ch")) {
			try {
				ChromeOptions chrmOptn = new ChromeOptions();
				chrmOptn.setBinary(new File(ExePath));
				capObj = DesiredCapabilities.chrome();
				capObj.setCapability(ChromeOptions.CAPABILITY, chrmOptn);
				remoteWebDriver = new RemoteWebDriver(new URL(remoteMachineUrl), capObj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		driver.get(appUrl);
	}
}
