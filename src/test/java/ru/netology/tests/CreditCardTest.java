package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.Card;
import ru.netology.data.CreditRequestEntity;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlHelper;
import ru.netology.pages.CreditPage;
import ru.netology.pages.DescriptionPage;

import static com.codeborne.selenide.Selenide.open;

public class CreditCardTest {
    DescriptionPage tour;
    CreditPage card;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        SqlHelper.prepareDb();
        open("http://localhost:8080");
        tour = new DescriptionPage();
        card = tour.chooseCreditPayment();
    }

    @Test
    void shouldBeSuccessfulPurchaseTourCredit() {
        Card cardInfo = DataHelper.getValidCardInfo();
        card.pay(cardInfo);
        card.approved();
        CreditRequestEntity entity = SqlHelper.creditRequestEntity();
        Assertions.assertEquals("APPROVED", entity.getStatus());
    }
    @Test
    void shouldBeDeclinedPurchaseTourCredit() {
        card.pay(DataHelper.getValidCardInfo().withNumber(DataHelper.cardNumberDeclined()));
        card.declined();
        CreditRequestEntity entity = SqlHelper.creditRequestEntity();
        Assertions.assertEquals("DECLINED", entity.getStatus());
    }
    @Test
    void shouldEmptyForm(){
        card.pay(DataHelper.getEmptyCard());
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldWithEmptyFieldNumber() {
        card.pay(DataHelper.getValidCardInfo().withNumber(DataHelper.cardNumberEmpty()));
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldNotEnoughCharInNumber() {
        card.pay(DataHelper.getValidCardInfo().withNumber(DataHelper.cardNumberLowerBound()));
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldLettersCharInNumber() {
        card.pay(DataHelper.getValidCardInfo().withNumber("qwerty"));
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldSpecSymbolCharInNumber() {
        card.pay(DataHelper.getValidCardInfo().withNumber("*-)=+&%"));
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldInvalidPurchaseTourCredit(){
        card.pay(DataHelper.getValidCardInfo().withNumber(DataHelper.invalidCardNumber()));
        card.declined();
        CreditRequestEntity entity = SqlHelper.creditRequestEntity();
        Assertions.assertNotNull(entity);
        Assertions.assertEquals("DECLINED", entity.getStatus());
    }
    @Test
    void shouldWithEmptyFieldMonth(){
        card.pay(DataHelper.getValidCardInfo().withMonth(""));
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldUpperBoundInMonth(){
        card.pay(DataHelper.getValidCardInfo().withMonth("18"));
        card.wrongValidityNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldLettersCharInMonth() {
        card.pay(DataHelper.getValidCardInfo().withMonth("qwerty"));
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldSpecSymbolCharInMonth() {
        card.pay(DataHelper.getValidCardInfo().withMonth("_--=+"));
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldWithEmptyFieldYear(){
        card.pay(DataHelper.getValidCardInfo().withYear(""));
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldLowerBoundInYear(){
        card.pay(DataHelper.getValidCardInfo().withYear("22"));
        card.expiredNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldUpperBoundInYear(){
        card.pay(DataHelper.getValidCardInfo().withYear("29"));
        card.wrongValidityNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldLettersCharInYear() {
        card.pay(DataHelper.getValidCardInfo().withYear("qwerty"));
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldSpecSymbolCharInYear() {
        card.pay(DataHelper.getValidCardInfo().withYear("_--=+"));
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldWithEmptyFieldOwner(){
        card.pay(DataHelper.getValidCardInfo().withName(""));
        card.requiredFieldNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldLettersNumberInOwner() {
        card.pay(DataHelper.getValidCardInfo().withName("123345"));
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldSpecSymbolCharInOwner() {
        card.pay(DataHelper.getValidCardInfo().withName("_--=+"));
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldLowerBoundInOwner(){
        card.pay(DataHelper.getValidCardInfo().withName("E"));
        card.expiredNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldUpperBoundInOwner(){
        card.pay(DataHelper.getValidCardInfo().withName("UsaevaElinaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        card.wrongValidityNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldIncorrectLayoutInOwner(){
        card.pay(DataHelper.getValidCardInfo().withName("Элина Усаева"));
        card.wrongValidityNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldWithEmptyCvv(){
        card.pay(DataHelper.getValidCardInfo().withCVV(""));
        card.requiredFieldNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldLettersLettersInCvv() {
        card.pay(DataHelper.getValidCardInfo().withCVV("qwerrt"));
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldSpecSymbolCharInCvv() {
        card.pay(DataHelper.getValidCardInfo().withCVV("_--=+"));
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldLowerBoundInCvv(){
        card.pay(DataHelper.getValidCardInfo().withCVV("12"));
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }
    @Test
    void shouldUpperBoundInCvv(){
        card.pay(DataHelper.getValidCardInfo().withCVV("1234"));
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }
}
