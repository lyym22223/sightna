package com.sweblim.entity;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.sterodium.extensions.client.SikuliExtensionClient;
import io.sterodium.extensions.node.rmi.TargetFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.robot.Mouse;
import org.springframework.stereotype.Component;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Component
public class Agent {
    public void run1() {

        RemoteWebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4445/wd/hub/"), new ChromeOptions());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().maximize();
        //RemoteWebDriver driver = new RemoteWebDriver(new ChromeOptions());
        String sessionId = driver.getSessionId().toString();
        SikuliExtensionClient client = new SikuliExtensionClient("localhost", 4445, sessionId);
        //打开网页
        open("https://github.com/");
        //xpath找到输入框
        WebElement search_text = $(By.xpath("/html/body/div[1]/header/div/div[2]/div[2]/div/div/div/form/label/input[1]"));
        search_text.sendKeys("selenide");//搜索关键字
        search_text.submit();
        ElementsCollection lis = $(".repo-list").$$(By.tagName("li"));
        String str = "projectName,projectDescription,usingLanguage,starsCount\r\n";
        for (SelenideElement element : lis) {
            WebElement title = element.$(By.className("v-align-middle"));
            String titleText = title.getText();
            String infoText = null;
            try {
                WebElement info = element.$(By.cssSelector("h3~p"));
                infoText = info.getText();
            } catch (Throwable t) {
                infoText = "";
            }
            WebElement type = element.$(By.xpath(".//span[@itemprop=\"programmingLanguage\"]"));
            String typeText = type.getText();
            WebElement num = element.$(By.className("text-right")).$(By.className("muted-link"));
            String numText = num.getText();
            str = str + "\"" + titleText + "\"" + "," + "\"" + infoText + "\"" + "," + "\"" + typeText + "\"" + "," + "\"" + numText + "\"" + "\r\n";

        }
        try {
            File fout = new File("G:/test1.csv");
            FileOutputStream fos = new FileOutputStream(fout);
            OutputStreamWriter out = new OutputStreamWriter(fos);
            //writer.write("");

            out.write(str);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        WebDriverRunner.closeWebDriver();
    }

    public void run2() {
        RemoteWebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), new ChromeOptions());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        WebDriverRunner.setWebDriver(driver);
        //RemoteWebDriver driver = new RemoteWebDriver(new ChromeOptions());
        String sessionId = driver.getSessionId().toString();
        SikuliExtensionClient client = new SikuliExtensionClient("localhost", 4444, sessionId);
        client.uploadResourceBundle("img");
        TargetFactory targetFactory = client.getTargetFactory();
        //ImageTarget imageTarget = targetFactory.createImageTarget("google.png");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DesktopScreenRegion desktop = client.getDesktop();

        Mouse mouse = client.getMouse();
        for (int i = 0; i < 5; i++) {
            try {
                client.getKeyboard().keyDown(KeyEvent.VK_WINDOWS);
                client.getKeyboard().keyDown(KeyEvent.VK_R);
                client.getKeyboard().keyUp(KeyEvent.VK_WINDOWS);
                client.getKeyboard().keyUp(KeyEvent.VK_R);
                Thread.sleep(3000);
                client.getKeyboard().keyDown(KeyEvent.VK_ESCAPE);
                client.getKeyboard().keyUp(KeyEvent.VK_ESCAPE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        WebDriverRunner.closeWebDriver();
    }
}
