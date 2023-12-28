package domain.algorithms;

import java.util.Random;

import domain.logic.Strategy;

public class CoopForCoop extends Strategy{

    private static double expo(double rate) {
        Random random = new Random();
        double exponentialValue = Math.log(1 - random.nextDouble()) / (-rate);

        // Convert the exponential value to a binary value
        return (exponentialValue < 1.0) ? 1 : 0;
    }

    private int normalDist(double mean, double stdv) {
        Random random = new Random();

        double normalValue = random.nextGaussian() * stdv + mean;

        return (normalValue < mean) ? 0 : 1;
    }

    @Override
    public byte Action(byte previousMove) {
        switch (previousMove) {
            case 1:
                return (byte) expo(1.5);

            case 2:
                return 0;
        
            default:
                return 0;
        }
    }

    @Override
    public void Reset() {
        
    }

    @Override
    public String Name() {
        return "CoopForCoop";
    }
    
}
