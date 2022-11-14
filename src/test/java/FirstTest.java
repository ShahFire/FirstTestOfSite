import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import java.util.Scanner;
import org.openqa.selenium.WebElement;
import static com.codeborne.selenide.Selenide.*;
import static io.netty.handler.codec.rtsp.RtspHeaders.Values.URL;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;


public class FirstTest {
    WebDriver driver;
    private static String baseUrl = "https://idemo.bspb.ru/";
    private SelenideElement loginInput = $(By.xpath("//input[@name='username']"));
    private SelenideElement passwordInput = $(By.xpath("//input[@name='password']"));
    private SelenideElement loginBtn = $(By.xpath("//button[@id='login-button']"));
    private SelenideElement codeInput = $(By.xpath("//input[@id='otp-code']"));
    private SelenideElement codeBtn = $(By.xpath("//button[@id='login-otp-button']"));
    private SelenideElement btn1 = $(By.xpath("//a[@id='bank-overview']"));
    private SelenideElement btn2 = $(By.xpath("//a[@id='accounts-index']"));
    private SelenideElement btn3 = $(By.xpath("//a[@id='payments-form']"));
    private SelenideElement btn4 = $(By.xpath("//a[@id='cards-overview-index']"));
    private SelenideElement btn5 = $(By.xpath("//a[@id='deposits-index']"));
    private SelenideElement btn6 = $(By.xpath("//a[@id='loans-index']"));
    private SelenideElement btn7 = $(By.xpath("//a[@id='externaltraderoom-index']"));
    private SelenideElement btn8 = $(By.xpath("//a[@id='insurance-travel']"));
    private SelenideElement btnOpenContribution = $(By.xpath("//a[@id='btn-show-rates']"));
    private SelenideElement btnDemoDeposit2 = $(By.xpath("//a[@data-ga-dimension13='Демо депозит №2']"));
    private SelenideElement endDateInput = $(By.xpath("//input[@name='endDate']"));
    private SelenideElement amountInput = $(By.xpath("//input[@name='amount']"));
    private SelenideElement submitBtn = $(By.xpath("//button[@id='submit-button']"));
    private SelenideElement newDepositConditionCheckBoxInput = $(By.xpath("//input[@name='condition.newDepositConditions']"));
    private SelenideElement instantDepositAgreementCheckBoxInput = $(By.xpath("//input[@name='condition.instantDepositAgreement']"));
    private SelenideElement minDaysTwoYearInput = $(By.xpath("//input[@value='733']"));
    private SelenideElement prolongationCheckBoxInput = $(By.xpath("//input[@name='prolongation']"));
//    private SelenideElement btnAcceptInstantDepositAgreement = $(By.xpath("//a[@id='accept-instant-deposit-agreement-button']"));
//    private SelenideElement instantDepositAgreementContentDiv = $(By.xpath("//div[@id='instant-deposit-agreement-content']"));

    @BeforeAll
    static void beforeConfig() {
        WebDriverManager.edgedriver().setup();
        Configuration.browser = "edge";
        Configuration.browserSize = "1920x1080";
    }
    public void setUp() {
        driver = new EdgeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void before() {
        open(baseUrl);
    }

    @Test
    public void test() {
        step1();  // Авторизация в банке
        step2();  // Подтверждающий код
        step3();  // Проверка значений главного меню
        step4();

/*        Scanner in = new Scanner(System.in);
        System.out.print("Input a number: ");
        int num = in.nextInt();
        System.out.printf("Your number: %d \n", num);
        in.close();

 */
    }

    @Step("111")
    public void step1(){
        loginInput.should(Condition.visible).val("demo");
        passwordInput.should(Condition.visible).val("demo");
        loginBtn.should(Condition.visible).click();
    }
    @Step("222")
    public void step2(){
        codeInput.should(Condition.visible).val("0000");
        codeBtn.should(Condition.visible).click();
    }
    @Step("333")
    public void step3(){
        btn1.should(Condition.text("Обзор"));
        btn2.should(Condition.text("Счета"));
        btn3.should(Condition.text("Платежи и Переводы"));
        btn4.should(Condition.text("Карты"));
        btn5.should(Condition.text("Вклады"));
        btn6.should(Condition.text("Кредиты"));
        btn7.should(Condition.text("Валюта"));
        btn8.should(Condition.text("Страхование"));
    }
    @Step("444")
    public void step4(){
        btn5.should(Condition.visible).click();
        btnOpenContribution.should(Condition.visible).click();
        minDaysTwoYearInput.should(Condition.visible).click();
        btnDemoDeposit2.should(Condition.visible).click();
        amountInput.should(Condition.visible).val("110000");

        for (int i = 0; i < 10; i++){
            prolongationCheckBoxInput.should(Condition.visible).click();
        }

        submitBtn.should(Condition.visible).click();
        newDepositConditionCheckBoxInput.should(Condition.visible).click();
        instantDepositAgreementCheckBoxInput.should(Condition.visible).click();

//        scrollingToElementofAPage();

//        prolongationCheckBoxInput.should(Condition.visible).click();

    }
/*        public void scrollingToElementofAPage() {
        WebElement element = driver.findElement(By.className("btn btn-text btn-toggle active"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

 */

    @AfterEach
    public void after() {
//        driver.quit();
    }

}