package output.Game;

import java.util.ArrayList;
import java.util.List;


//Composite Design Pattern
public class ChipContainerInfo {
    private String name;
    private int chip_amount;
    private int five_amount;
    private int ten_amount;
    private int twenty_amount;
    private int twentyfive_amount;
    private int fifty_amount;
    private int hundred_amount;
    private int fivehundred_amount;
    private int thousand_amount;
    private List<ChipContainerInfo> subContainers;

    // constructor
    public ChipContainerInfo(String name,int chip_amount, int five_amount, int ten_amount, int twenty_amount, int twentyfive_amount, int fifty_amount, int hundred_amount, int fivehundred_amount, int thousand_amount) {
        this.name = name;
        this.chip_amount = chip_amount;
        this.five_amount = five_amount;
        this.ten_amount = ten_amount;
        this.twenty_amount = twenty_amount;
        this.twentyfive_amount = twentyfive_amount;
        this.fifty_amount = fifty_amount;
        this.hundred_amount = hundred_amount;
        this.fivehundred_amount = fivehundred_amount;
        this.thousand_amount = thousand_amount;
        subContainers = new ArrayList<ChipContainerInfo>();
    }

    public void add(ChipContainerInfo e) {
        subContainers.add(e);
    }

    public void remove(ChipContainerInfo e) {
        subContainers.remove(e);
    }

    public List<ChipContainerInfo> getSubContainers(){
        return subContainers;
    }

    public String toString(){
        return ("ChipContainersInfo :[ Container : " + name + ", Chips Amount : " + chip_amount + ", fives :" + five_amount+", tens : " + ten_amount + ", twenties :" + twenty_amount+", twenty-fives :" + twentyfive_amount+", fifties : " + fifty_amount + ", hundreds :" + hundred_amount+", five-hundreds :" + fivehundred_amount+", thousands : " + thousand_amount+" ]");
    }
}
