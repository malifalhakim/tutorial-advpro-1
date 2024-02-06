package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.List;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class ActionFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest(){
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    void createProduct(ChromeDriver driver, String name, String quantity){
        WebElement createProductButton = driver.findElement(By.linkText("Create Product"));
        createProductButton.click();

        WebElement nameProductInput = driver.findElement(By.id("nameInput"));
        nameProductInput.clear();
        nameProductInput.sendKeys(name);

        WebElement productQuantityInput = driver.findElement(By.id("quantityInput"));
        productQuantityInput.clear();
        productQuantityInput.sendKeys(quantity);

        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
        submitButton.click();
    }

    @Test
    void editProduct_isCorrect(ChromeDriver driver) throws Exception{
        driver.get(baseUrl + "/product/list");
        createProduct(driver,"Product 1","2");

        WebElement editButton = driver.findElement(By.xpath("//a[text()='Edit']"));
        editButton.click();

        WebElement nameProductEdit = driver.findElement(By.id("nameInput"));
        nameProductEdit.clear();
        nameProductEdit.sendKeys("Product 2");

        WebElement productQuantityEdit = driver.findElement(By.id("quantityInput"));
        productQuantityEdit.clear();
        productQuantityEdit.sendKeys("3");

        WebElement updateButton = driver.findElement(By.xpath("//button[text()='Update']"));
        updateButton.click();

        WebElement productNameElem = driver.findElement(By.xpath("//tr[last()]/td[1]"));
        String productName =  productNameElem.getText();
        assertEquals("Product 2",productName);

        WebElement productQuantityElem = driver.findElement(By.xpath("//tr[last()]/td[2]"));
        String productQuantity =  productQuantityElem.getText();
        assertEquals("3",productQuantity);
    }

    @Test
    void deleteProduct_isCorrect(ChromeDriver driver){
        driver.get(baseUrl + "/product/list");
        createProduct(driver, "Product 1","2");

        WebElement deleteButton = driver.findElement(By.xpath("//button[text()='Delete']"));
        deleteButton.click();

        List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
        assertEquals(0,rows.size());
    }
}
