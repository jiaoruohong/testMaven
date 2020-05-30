import com.pillar.coin.Penny;
import com.pillar.coin.Quarter;
import com.pillar.globals.Consts;
import com.pillar.vending.VendingMachine;

import java.io.*;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Hi! Here is a Vending Machine.");

        System.out.println("Cola        1.00");
        System.out.println("Chips       0.50");
        System.out.println("Candy       0.65");
        System.out.println("Pretzels    0.65");

        System.out.println("Input Coins in Weights and Size.");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        VendingMachine vm = new VendingMachine();
        String line;
        double coinData[] = new double[2];
        int coinDataIndex = 1;
        boolean quit = false;
        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
            int cur;
            StreamTokenizer st = new StreamTokenizer(new StringReader(line));
            while ((cur = st.nextToken()) != st.TT_EOF) {
                if (cur == StreamTokenizer.TT_NUMBER) {
                    coinDataIndex ^= 1;
                    coinData[coinDataIndex] = st.nval;

                    if (coinDataIndex == 1) {
                        vm.accept(new Penny(coinData[0], coinData[1]));
//                        System.out.println("Money:" + vm.totalMoney() + " " + coinData[0] + ", " + coinData[1]);
                        System.out.println(vm.display());
                    }

                } else if (cur == StreamTokenizer.TT_WORD) {
                    String opt = st.sval;
                    if (opt.toUpperCase().equals("COLA")) {
                        vm.selectProduct(Consts.COLA);
                        System.out.println(vm.display());
                    } else if (opt.toUpperCase().equals("CHIPS")) {
                        vm.selectProduct(Consts.CHIPS);
                        System.out.println(vm.display());
                    } else if (opt.toUpperCase().equals("CANDY")) {
                        vm.selectProduct(Consts.CANDY);
                        System.out.println(vm.display());
                    } else if (opt.toUpperCase().equals("PRETZELS")) {
                        vm.selectProduct(Consts.PRETZELS);
                        System.out.println(vm.display());
                    } else if (opt.toUpperCase().equals("RETURN")) {
                        vm.returnCoins();
                    } else if (opt.toUpperCase().equals("QUIT")) {
                        quit = true;
                    } else {
                        System.out.println("Unrecognized option: " + opt);
                    }

                    break;
                } else {
//                  pass
                }
            }

            if (quit) {
                System.out.println("Goodbye!");
                break;
            }

            if(vm.totalMoney()==0){
                System.out.println(vm.display());
            }

        }
    }
}
