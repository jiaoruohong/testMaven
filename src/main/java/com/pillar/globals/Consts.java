package com.pillar.globals;

import com.pillar.coin.Dime;
import com.pillar.coin.Nickel;
import com.pillar.coin.Penny;
import com.pillar.coin.Quarter;
import com.pillar.product.Candy;
import com.pillar.product.Chips;
import com.pillar.product.Cola;
import com.pillar.product.Pretzels;

public final class Consts {
    // Could have just passed these objects into the accept() method,
    // but constants mean less repeated code overall, plus you can just change it once

    public static final Quarter QUARTER = new Quarter(5.67, 0.955);

    public static final Dime DIME = new Dime(2.28, 0.705);

    public static final Nickel NICKEL = new Nickel(5.01, 0.835);

    public static final Penny PENNY = new Penny(2.5, 0.75);

    // Could have just passed these objects into the selectProduct() method,
    // but constants mean less repeated code overall, plus you can just change it once

    public static final Cola COLA = new Cola(1.0, 10);

    public static final Chips CHIPS = new Chips(0.50, 10);

    public static final Candy CANDY = new Candy(0.65, 10);

    public static final Pretzels PRETZELS = new Pretzels(0.65, 0);
}
