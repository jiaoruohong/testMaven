package com.pillar.vending;

import com.pillar.globals.Consts;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineUTests {

    private VendingMachine vm;

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
        vm.accept(Consts.QUARTER);
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
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.DIME);
        vm.accept(Consts.NICKEL);
        Assert.assertTrue("should display amount of cents inserted.", vm.display().equals("0.40"));
    }

    @Test
    public void doesNotAcceptInvalidCoins() {
        vm.accept(Consts.PENNY);
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
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.QUARTER);
        vm.selectProduct(Consts.COLA);
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
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.DIME);
        vm.selectProduct(Consts.CHIPS);
        Assert.assertTrue("should display THANK YOU after vending", vm.display().equals("THANK YOU"));
        Assert.assertTrue("should display INSERT COIN after display of THANK YOU", vm.display().equals("INSERT COIN"));
        Assert.assertTrue("should return proper change", vm.retrieveChange().equals("0.35"));
    }

    @Test
    public void retrieveChangeAfterCandyPurchase() {
        vm.accept(Consts.NICKEL);
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.NICKEL);
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.DIME);
        vm.accept(Consts.DIME);
        vm.selectProduct(Consts.CANDY);
        Assert.assertTrue("should display THANK YOU after vending", vm.display().equals("THANK YOU"));
        Assert.assertTrue("should display INSERT COIN after display of THANK YOU", vm.display().equals("INSERT COIN"));
        Assert.assertTrue("should return proper change", vm.retrieveChange().equals("0.15"));
    }

    @Test
    public void coinsAreReturned() {
        vm.accept(Consts.NICKEL);
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.NICKEL);
        vm.accept(Consts.QUARTER);
        vm.accept(Consts.DIME);
        vm.accept(Consts.DIME);
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
        vm.selectProduct(Consts.PRETZELS);
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
