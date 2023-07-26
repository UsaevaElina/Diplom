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
        Card cardInfo = DataHelper.cardNumberApproved();
        card.pay(cardInfo);
        card.approved();
        CreditRequestEntity entity = SqlHelper.creditRequestEntity();
        Assertions.assertEquals("APPROVED", entity.getStatus());
    }

    @Test
    void shouldBeDeclinedPurchaseTourCredit() {
        card.pay(DataHelper.cardNumberDeclined());
        card.declined();
        CreditRequestEntity entity = SqlHelper.creditRequestEntity();
        Assertions.assertEquals("DECLINED", entity.getStatus());
    }

    @Test
    void shouldEmptyForm() {
        card.pay(DataHelper.cardEmpty());
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldWithEmptyFieldNumber() {
        card.pay(DataHelper.cardNumberEmpty());
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldNotEnoughCharInNumber() {
        card.pay(DataHelper.cardNumberLowerBound());
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldLettersCharInNumber() {
        card.pay(DataHelper.cardLettersCharInNumber());
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldSpecSymbolCharInNumber() {
        card.pay(DataHelper.cardSymbolsInNumber());
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldInvalidPurchaseTourCredit() {
        card.pay(DataHelper.invalidCardNumber());
        card.declined();
        CreditRequestEntity entity = SqlHelper.creditRequestEntity();
        Assertions.assertNotNull(entity);
        Assertions.assertEquals("DECLINED", entity.getStatus());
    }

    @Test
    void shouldWithEmptyFieldMonth() {
        card.pay(DataHelper.emptyMonth());
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldUpperBoundInMonth() {
        card.pay(DataHelper.upperBoundMonth());
        card.wrongValidityNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldLettersCharInMonth() {
        card.pay(DataHelper.lettersInMonth());
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldSpecSymbolCharInMonth() {
        card.pay(DataHelper.symbolsInMonth());
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldWithEmptyFieldYear() {
        card.pay(DataHelper.emptyYear());
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldLowerBoundInYear() {
        card.pay(DataHelper.lowerBoundYear());
        card.expiredNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldUpperBoundInYear() {
        card.pay(DataHelper.upperBoundYear());
        card.wrongValidityNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldLettersCharInYear() {
        card.pay(DataHelper.lettersInYear());
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldSpecSymbolCharInYear() {
        card.pay(DataHelper.symbolsInYear());
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldWithEmptyFieldOwner() {
        card.pay(DataHelper.emptyOwner());
        card.requiredFieldNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldLettersNumberInOwner() {
        card.pay(DataHelper.numbersInOwner());
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldSpecSymbolCharInOwner() {
        card.pay(DataHelper.specSymbolCharInOwner());
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldLowerBoundInOwner() {
        card.pay(DataHelper.lowerBoundInOwnerOwner());
        card.expiredNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldUpperBoundInOwner() {
        card.pay(DataHelper.upperBoundInOwnerOwner());
        card.wrongValidityNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldIncorrectLayoutInOwner() {
        card.pay(DataHelper.incorrectLayoutInOwner());
        card.wrongValidityNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldWithEmptyCvv() {
        card.pay(DataHelper.emptyCvv());
        card.requiredFieldNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldLettersLettersInCvv() {
        card.pay(DataHelper.lettersInCvv());
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldSpecSymbolCharInCvv() {
        card.pay(DataHelper.specSymbolCharInCvv());
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldLowerBoundInCvv() {
        card.pay(DataHelper.lowerBoundInCvv());
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }

    @Test
    void shouldUpperBoundInCvv() {
        card.pay(DataHelper.upperBoundInCvv());
        card.wrongFormatNotification();
        SqlHelper.assertDbEmpty();
    }
}
