/*
 * Copyright (C) 2021 Gabriel Venberg
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 *
 * @author Gabriel Venberg
 */
public class Client {

    private static boolean isPrime(int n) {
        for (int i = 2; i < n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        LuckyNumberList luckyNumbers = new LuckyNumberList();

        luckyNumbers.addLuckyNumber(new LuckyNumber("alice"));
        luckyNumbers.addLuckyNumber(new LuckyNumber("bob"));
        luckyNumbers.addLuckyNumber(new LuckyNumber("bill"));
        luckyNumbers.addLuckyNumber(new LuckyNumber("jill"));
        luckyNumbers.addLuckyNumber(new LuckyNumber("gabe"));
        luckyNumbers.addLuckyNumber(new LuckyNumber("erica"));
        luckyNumbers.addLuckyNumber(new LuckyNumber("albrich"));
        luckyNumbers.addLuckyNumber(new LuckyNumber("orianna"));
        luckyNumbers.addLuckyNumber(new LuckyNumber("maritt"));
        luckyNumbers.addLuckyNumber(new LuckyNumber("joe"));

        Iterator<Position<LuckyNumber>> iterator;
        String format = "%1$10s, %2$10d %3$10s %4$10s %n";
        
        iterator = luckyNumbers.positions().iterator();
        System.out.println("full iterator:");
        while (iterator.hasNext()) {
            LuckyNumber n = iterator.next().getElement();
            String even = "odd";
            String prime = "not prime";
            if (n.getLuckyNumber() % 2 == 0) {
                even = "even";
            } else if (isPrime(n.getLuckyNumber())) {
                prime = "prime";
            }
            System.out.printf(format, n.getName(), n.getLuckyNumber(), even, prime);
        }
        
        System.out.println("even iterator:");
        iterator = luckyNumbers.EvenPositions().iterator();
        while (iterator.hasNext()) {
            LuckyNumber n = iterator.next().getElement();
            String even = "odd";
            String prime = "not prime";
            if (n.getLuckyNumber() % 2 == 0) {
                even = "even";
            } else if (isPrime(n.getLuckyNumber())) {
                prime = "prime";
            }
            System.out.printf(format, n.getName(), n.getLuckyNumber(), even, prime);
        }
        
        System.out.println("prime iterator");
        iterator = luckyNumbers.PrimePositions().iterator();
        while (iterator.hasNext()) {
            LuckyNumber n = iterator.next().getElement();
            String even = "odd";
            String prime = "not prime";
            if (n.getLuckyNumber() % 2 == 0) {
                even = "even";
            } else if (isPrime(n.getLuckyNumber())) {
                prime = "prime";
            }
            System.out.printf(format, n.getName(), n.getLuckyNumber(), even, prime);
        }
    }
}
