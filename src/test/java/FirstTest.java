import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static com.codeborne.selenide.Selenide.*;
import static io.netty.handler.codec.rtsp.RtspHeaders.Values.URL;
import org.openqa.selenium.edge.EdgeDriver;
public class FirstTest {
    WebDriver driver;
    private static String baseUrl = "https://idemo.bspb.ru/";
    private SelenideElement loginInput = $(By.xpath("//input[@name='username']"));
    private SelenideElement passwordInput = $(By.xpath("//input[@name='password']"));
    private SelenideElement loginBtn = $(By.xpath("//button[@id='login-button']"));
    private SelenideElement codeInput = $(By.xpath("//input[@id='otp-code']"));
    private SelenideElement codeBtn = $(By.xpath("//button[@id='login-otp-button']"));
    private SelenideElement bankOverviewBut = $(By.xpath("//a[@id='bank-overview']"));
    private SelenideElement accountsIndexBut = $(By.xpath("//a[@id='accounts-index']"));
    private SelenideElement paymentsFormBut = $(By.xpath("//a[@id='payments-form']"));
    private SelenideElement cardsOverviewIndexBut = $(By.xpath("//a[@id='cards-overview-index']"));
    private SelenideElement depositsIndexBut = $(By.xpath("//a[@id='deposits-index']"));
    private SelenideElement loansIndexBut = $(By.xpath("//a[@id='loans-index']"));
    private SelenideElement externaltraderoomIndexBut = $(By.xpath("//a[@id='externaltraderoom-index']"));
    private SelenideElement insuranceTraveBut = $(By.xpath("//a[@id='insurance-travel']"));
    private SelenideElement btnOpenContribution = $(By.xpath("//a[@id='btn-show-rates']"));
    private SelenideElement btnDemoDeposit2 = $(By.xpath("//a[@data-ga-dimension13='Демо депозит №2']"));
    private SelenideElement amountInput = $(By.xpath("//input[@name='amount']"));
    private SelenideElement submitBtn = $(By.xpath("//button[@id='submit-button']"));
    private SelenideElement newDepositConditionCheckBoxInput = $(By.xpath("//input[@name='condition.newDepositConditions']"));
    private SelenideElement minDaysTwoYearInput = $(By.xpath("//input[@value='733']"));
    private SelenideElement prolongationCheckBoxInput = $(By.xpath("//input[@name='prolongation']"));
    private SelenideElement reopenDepositsBtn = $(By.xpath("//a[@href='/deposits/10056/reopen/form']"));
    private SelenideElement conditionDepositReopenCheckBoxInput = $(By.xpath("//input[@name='condition.deposit.reopen.conditions']"));
    private SelenideElement messageSpan = $(By.xpath("//span[@class='icon-email']"));
    private SelenideElement newMessagetBtn = $(By.xpath("//a[@id='new-message-btn']"));
    private SelenideElement topicNameSelect = $(By.xpath("//select[@name='message.topicName']"));
    private SelenideElement BUGtopicNameOption = $(By.xpath("//option[@value='BUG']"));
    private SelenideElement messageTextarea = $(By.xpath("//Textarea[@name='message.text']"));
    private SelenideElement sendBtn = $(By.xpath("//button[@id='send-button']"));
    @BeforeAll
    static void beforeConfig() {
        WebDriverManager.edgedriver().setup();
        Configuration.browser = "edge";
        Configuration.browserSize = "1920x1080";
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
        step4();  // Открытие вклада (неполное, потому что возникла проблема со скроллингом)
        step5();  // Переоформление вклада
        step6();  // Отправка сообщения об ошибке
    }
    @Step("1. Authorization in the bank")
    public void step1(){
        loginInput.should(Condition.visible).val("demo");
        passwordInput.should(Condition.visible).val("demo");
        loginBtn.should(Condition.visible).click();
    }
    @Step("2. Confirmation code")
    public void step2(){
        codeInput.should(Condition.visible).val("0000");
        codeBtn.should(Condition.visible).click();
    }
    @Step("3. Checking the values of the main menu")
    public void step3(){
        bankOverviewBut.should(Condition.text("Обзор")).click();
        accountsIndexBut.should(Condition.text("Счета")).click();
        paymentsFormBut.should(Condition.text("Платежи и Переводы")).click();
        cardsOverviewIndexBut.should(Condition.text("Карты")).click();
        depositsIndexBut.should(Condition.text("Вклады")).click();
        loansIndexBut.should(Condition.text("Кредиты")).click();
        externaltraderoomIndexBut.should(Condition.text("Валюта")).click();
        insuranceTraveBut.should(Condition.text("Страхование")).click();
    }
    @Step("4. Opening the deposit")
    public void step4(){
        depositsIndexBut.should(Condition.visible).click();
        btnOpenContribution.should(Condition.visible).click();
        minDaysTwoYearInput.should(Condition.visible).click();
        btnDemoDeposit2.should(Condition.visible).click();
        amountInput.should(Condition.visible).val("110000");
        // Сорян за Sleep, ты говорил, что так не надо, но так надо)
        sleep(1000);
        prolongationCheckBoxInput.should(Condition.visible).click();
        submitBtn.should(Condition.visible).click();
        newDepositConditionCheckBoxInput.should(Condition.visible).click();
        // Брат, тут я сталкнулся с проблемой со скроллингом, о которой я тебе говорил, мне ни Илья Климов,
        // ни Костя Пономарёв помочь не смогли, и я решил забить, просто сделаю ещё что-нибудь
    }
    @Step("5. Reissuing the deposit")
    public void step5() {
        depositsIndexBut.should(Condition.visible).click();
        reopenDepositsBtn.should(Condition.visible).click();
        conditionDepositReopenCheckBoxInput.should(Condition.visible).click();
        sleep(1000);
        submitBtn.should(Condition.visible).click();
    }
    @Step("6. Sending an error message")
    public void step6() {
        messageSpan.should(Condition.visible).click();
        newMessagetBtn.should(Condition.visible).click();
        topicNameSelect.should(Condition.visible).click();
        BUGtopicNameOption.should(Condition.visible).click();
        messageTextarea.should(Condition.visible).val("Your site is in a very raw state");
        sendBtn.should(Condition.visible).click();
    }
    @AfterEach
    public void after() {}
}