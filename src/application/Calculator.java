package application;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import entities.*;

public class Calculator {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("##.#####");
        List<Operations> list = new ArrayList<>();
        InputCreation ic = new InputCreation();
        Check chk = new Check();
        Double result = 0.00;

        System.out.println("|| Calculator ||");
        System.out.println();

        while (!chk.powerOffCheck(ic.getInput())) {

            ic.inputCreation(sc, result);
            Operations operation = chk.opInstanceCheck(ic.getOp(), ic.getNumber());

            list.add(operation);
            ic.setNumber("");

            if (chk.allCheck(ic.getInput()) && !chk.powerOffCheck(ic.getInput())) {
                Result res = new Result();
                res.result(list, result);

                if (chk.resultCheck(ic.getInput()))
                    list.clear();

                if (res.getResult() != 0)
                    System.out.println("Display: " + df.format(res.getResult()));

                result = res.getResult();
                ic.setNumber(df.format(result));
            }
        }
        System.out.println("Turned Off.");
        sc.close();
    }
}
