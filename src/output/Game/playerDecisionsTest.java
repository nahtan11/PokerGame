package output.Game;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class playerDecisionsTest {
    static int yourDebt;
    static int yourBetInteger;
    static Button betButton = variables.getBetButton();
    static Button callButton = variables.getCallButton();
    static Button checkButton = variables.getCheckButton();
    static Button raiseButton = variables.getRaiseButton();
    static Button foldButton = variables.getFoldButton();
    static Label yourMoney;
    static Label potLabel;
    static int potMoney;
    static int money;
    static String lastBet;

    @Test
    void betEvent() {
        int yourBetInt = 100;
        String yourDecision = "\nYou bet $";

        yourDecision = "\nYou raise $";
        yourDebt = variables.getYourDebt();
        int yourRaise = yourBetInt + yourDebt;
        yourDebt = 0;
        variables.setYourDebt(yourDebt);
        potMoney = 1000;
        potMoney = potMoney + yourRaise;
        variables.setPotMoney(potMoney);
        money = 1000;
        money = money - yourRaise;
        variables.setMoney(money);
        if (yourRaise > 0 && yourRaise < 50)
            lastBet = "low";
        else if (yourRaise > 49 && yourRaise < 100)
            lastBet = "medium";
        else if (yourRaise > 99 && yourRaise < 200)
            lastBet = "high";
        else
            lastBet = "very high";

        variables.setLastBet(lastBet);

        potLabel = variables.getPotLabel();
        yourMoney = variables.getYourMoney();
        assertEquals(1100,potMoney);
        assertEquals(900,money);

    }

    @Test
    void callEvent() {
        yourDebt = 50;
        money = 900;
        money = money - yourDebt;
        variables.setMoney(money);
        potMoney = 1100;
        potMoney = potMoney + yourDebt;
        variables.setPotMoney(potMoney);
        potLabel = variables.getPotLabel();
        yourMoney = variables.getYourMoney();
        yourDebt = 0;
        variables.setYourDebt(yourDebt);
        assertEquals(1150,potMoney);
        assertEquals(850,money);
    }
}