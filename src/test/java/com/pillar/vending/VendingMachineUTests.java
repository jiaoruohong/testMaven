package com.pillar.vending;

import com.pillar.coin.*;
import com.pillar.globals.Consts;
import com.pillar.product.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class VendingMachineUTests {

    private static Coin quarter=mock(Coin.class);
    private static Coin dime=mock(Coin.class);
    private static Coin nickel=mock(Coin.class);
    private static Coin penny=mock(Coin.class);

    private static Product cola=mock(Product.class);
    private static Product chips=mock(Product.class);
    private static Product candy= mock(Product.class);
    private static Product pretzels=mock(Product.class);

    private VendingMachine vm;

    @BeforeClass
    public static void beforeClass() throws Exception {
        when(quarter.getWeight()).thenReturn(5.67);
        when(quarter.getSize()).thenReturn(0.955);

        when(dime.getWeight()).thenReturn(2.28);
        when(dime.getSize()).thenReturn(0.705);

        when(nickel.getWeight()).thenReturn(5.01);
        when(nickel.getSize()).thenReturn(0.835);

        when(penny.getWeight()).thenReturn(2.5);
        when(penny.getSize()).thenReturn(0.75);

        when(cola.getPrice()).thenReturn(1.0);
        when(cola.getQuantity()).thenReturn(10);

        when(chips.getPrice()).thenReturn(0.50);
        when(chips.getQuantity()).thenReturn(10);

        when(candy.getPrice()).thenReturn(0.65);
        when(candy.getQuantity()).thenReturn(10);

//        when(pretzels.getPrice()).thenReturn(1.0);
//        when(pretzels.getQuantity()).thenReturn(0);
    }

    @Before
    public void setUp() {
        vm = new VendingMachine();
    }

    @Test
    public void displayShowsInsertCoinWhenEmpty() {
        Assert.assertTrue("should display INSERT COIN when no coins have been inserted", vm.display().equals("INSERT COIN"));
    }

    @Test
    public void acceptsQuarter() {
//        test with mockito
        vm.accept(quarter);
        Assert.assertTrue("should display amount of quarter inserted.", vm.display().equals("0.25"));
    }

    @Test
    public void acceptsDime() {
        vm.accept(Consts.DIME);
        Assert.assertTrue("should display amount of dime inserted.", vm.display().equals("0.10"));
    }

    @Test
    public void acceptsNickel() {
        vm.accept(Consts.NICKEL);
        Assert.assertTrue("should display amount of nickel inserted.", vm.display().equals("0.05"));
    }

    @Test
    public void acceptsMultipleValidCoins() {
//        test with mockito
        vm.accept(quarter);
        vm.accept(dime);
        vm.accept(nickel);
        Assert.assertTrue("should display amount of cents inserted.", vm.display().equals("0.40"));
    }

    @Test
    public void doesNotAcceptInvalidCoins() {
//        test with mockito
        vm.accept(penny);
        Assert.assertTrue("should display INSERT COIN because there are none.", vm.display().equals("INSERT COIN"));
    }

    @Test
    public void acceptsValidAndRejectsInvalidCoins() {
        vm.accept(Consts.PENNY);
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.PENNY);
        vm.accept(Consts.DIME);
        vm.accept(Consts.NICKEL);
        Assert.assertTrue("should display amount of cents inserted.", vm.display().equals("0.40"));
    }

    @Test
    public void colaIsDispensedWithCorrectAmount() {
//        test with mockito
        vm.accept(quarter);
        vm.accept(quarter);
        vm.accept(quarter);
        vm.accept(quarter);
        vm.selectProduct(cola);
        Assert.assertTrue("should display THANK YOU after vending", vm.display().equals("THANK YOU"));
        Assert.assertTrue("should display INSERT COIN after display of THANK YOU", vm.display().equals("INSERT COIN"));
    }

    @Test
    public void chipsIsDispensedWithCorrectAmount() {
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.QUARTER);
        vm.selectProduct(Consts.CHIPS);
        Assert.assertTrue("should display THANK YOU after vending", vm.display().equals("THANK YOU"));
        Assert.assertTrue("should display INSERT COIN after display of THANK YOU", vm.display().equals("INSERT COIN"));
    }

    @Test
    public void candyIsDispensedWithCorrectAmount() {
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.DIME);
        vm.accept(Consts.NICKEL);
        vm.selectProduct(Consts.CANDY);
        Assert.assertTrue("should display THANK YOU after vending", vm.display().equals("THANK YOU"));
        Assert.assertTrue("should display INSERT COIN after display of THANK YOU", vm.display().equals("INSERT COIN"));
    }

    @Test
    public void displayIsCorrectWhenProductIsSelectedButLacksFunds() {
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.QUARTER);
        vm.selectProduct(Consts.CANDY);
        Assert.assertTrue("should display PRICE after vending", vm.display().equals("PRICE 0.65"));
    }

    @Test
    public void displayIsCorrectWhenProductIsSelectedButLacksFundsAndMoreCoinsAreInserted() {
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.QUARTER);
        vm.selectProduct(Consts.CANDY);
        Assert.assertTrue("should display PRICE after vending", vm.display().equals("PRICE 0.65"));
        vm.accept(Consts.DIME);
        vm.accept(Consts.NICKEL);
        Assert.assertTrue("should display THANK YOU after vending", vm.display().equals("THANK YOU"));
        Assert.assertTrue("should display INSERT COIN after display of PRICE", vm.display().equals("INSERT COIN"));
    }

    @Test
    public void retrieveChangeAfterColaPurchase() {
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.DIME);
        vm.selectProduct(Consts.COLA);
        Assert.assertTrue("should display THANK YOU after vending", vm.display().equals("THANK YOU"));
        Assert.assertTrue("should display INSERT COIN after display of THANK YOU", vm.display().equals("INSERT COIN"));
        Assert.assertTrue("should return proper change", vm.retrieveChange().equals("0.10"));
    }

    @Test
    public void retrieveChangeAfterChipsPurchase() {
//        test with mockito
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.DIME);
//        vm.selectProduct(Consts.CHIPS);
        vm.selectProduct(chips);
        Assert.assertTrue("should display THANK YOU after vending", vm.display().equals("THANK YOU"));
        Assert.assertTrue("should display INSERT COIN after display of THANK YOU", vm.display().equals("INSERT COIN"));
        Assert.assertTrue("should return proper change", vm.retrieveChange().equals("0.35"));
    }

    @Test
    public void retrieveChangeAfterCandyPurchase() {
//        test with mockito
        vm.accept(nickel);
        vm.accept(quarter);
        vm.accept(nickel);
        vm.accept(quarter);
        vm.accept(dime);
        vm.accept(dime);
        vm.selectProduct(candy);
        Assert.assertTrue("should display THANK YOU after vending", vm.display().equals("THANK YOU"));
        Assert.assertTrue("should display INSERT COIN after display of THANK YOU", vm.display().equals("INSERT COIN"));
        Assert.assertTrue("should return proper change", vm.retrieveChange().equals("0.15"));
    }

    @Test
    public void coinsAreReturned() {
//        test with mockito
        vm.accept(nickel);
        vm.accept(quarter);
        vm.accept(nickel);
        vm.accept(quarter);
        vm.accept(dime);
        vm.accept(dime);
        Assert.assertTrue("should display amount of change inserted.", vm.display().equals("0.80"));
        vm.returnCoins();
        Assert.assertTrue("should display INSERT COIN after coins are returned", vm.display().equals("INSERT COIN"));
    }

    @Test
    public void coinsAreReturnedAfterProductIsSelected() {
        vm.accept(Consts.NICKEL);
        vm.selectProduct(Consts.CANDY);
        Assert.assertTrue("should display PRICE after vending", vm.display().equals("PRICE 0.65"));
        vm.returnCoins();
        Assert.assertTrue("should display INSERT COIN after coins are returned", vm.display().equals("INSERT COIN"));
    }

    @Test
    public void machineDisplaysSoldOut() {
//        test with mockito
        vm.selectProduct(pretzels);
        Assert.assertTrue("should display SOLD OUT when product is sold out", vm.display().equals("SOLD OUT"));
        Assert.assertTrue("should display INSERT COIN since machine has no inserted coins", vm.display().equals("INSERT COIN"));
    }

    @Test
    public void machineDisplaysSoldOutWithCoins() {
        vm.accept(Consts.NICKEL);
        vm.selectProduct(Consts.PRETZELS);
        Assert.assertTrue("should display SOLD OUT when product is sold out", vm.display().equals("SOLD OUT"));
        Assert.assertTrue("should display amount of change inserted.", vm.display().equals("0.05"));
    }

    @Test
    public void machineDisplaysExactChangeOnly() {
        VendingMachine vm = new VendingMachine(true);
        Assert.assertTrue("should display EXACT CHANGE ONLY since machine has no change to return", vm.display().equals("EXACT CHANGE ONLY"));
    }

}
