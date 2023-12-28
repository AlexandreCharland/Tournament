package domain.algorithms;
import domain.logic.Strategy;
import java.util.Random;

public class Algo2 extends Strategy {


    //Quantile d'une expo
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

    private static int generateBinaryValue(double probabilityZero, double probabilityOne) {
        Random random = new Random();
        double randomValue = random.nextDouble(); // Generate a random value between 0 (inclusive) and 1 (exclusive)

        // Check the generated value against the probabilities
        if (randomValue < probabilityZero) {
            return 0; // Return 0 with probability probabilityZero
        } else {
            return 1; // Return 1 with probability probabilityOne
        }
    }

    @Override
    public byte Action(byte previousMove) {
        double rate = 1.5;
        double mean = 0.9;
        double stdv = 0.05;

        return (byte) expo(rate);
    }

    @Override
    public void Reset() {

    }

    @Override
    public String Name() {
        return "Algo2";
    }
    
}
