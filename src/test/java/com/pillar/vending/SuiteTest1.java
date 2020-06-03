package com.pillar.vending;

import com.pillar.coin.Coin;
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
public class SuiteTest1 {

    private VendingMachine vm;

    @Before
    public void setUp() {
        vm = new VendingMachine();
    }

    @Test
    public void displayShowsInsertCoinWhenEmpty() {
        Assert.assertTrue("should display INSERT COIN when no coins have been inserted", vm.display().equals("INSERT COIN"));
    }

}
