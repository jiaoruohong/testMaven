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
public class SuiteTest2 {

    private static Coin quarter=mock(Coin.class);

    private VendingMachine vm;

    @BeforeClass
    public static void beforeClass() throws Exception {
        when(quarter.getWeight()).thenReturn(5.67);
        when(quarter.getSize()).thenReturn(0.955);
    }

    @Before
    public void setUp() {
        vm = new VendingMachine();
    }

    @Test
    public void acceptsQuarter() {
//        test with mockito
        vm.accept(quarter);
        Assert.assertTrue("should display amount of quarter inserted.", vm.display().equals("0.25"));
    }
}
