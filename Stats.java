/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpserver;

import java.util.Arrays;

/**
 *
 * @author green
 */
public class Stats {

    double sd;
    double mean;
    double max;
    double min;

    public double calculateMax(double numArray[]) {
        double max = Arrays.stream(numArray).max().getAsDouble();
        return max;
    }

    public static double calculateMin(double numArray[]) {
        double min = Arrays.stream(numArray).min().getAsDouble();
        return min;
    }

    public double calculateMean(double numArray[]) {
        double mean = Arrays.stream(numArray).average().getAsDouble();
        return mean;
    }

    public double calculateSD(double numArray[]) {
        double standardDeviation = 0.0;
        int length = numArray.length;
        for (double num : numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation / length);
    }
}
