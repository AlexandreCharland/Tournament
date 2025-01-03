package logic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import algorithms.*;

import java.util.List;
public class Tournament
{
    public static void main(String[] args)
    {
        /*Random rand = new Random();
        int numberOfRounds = 195 + rand.nextInt(11);
        ArrayList<Strategy> competitor = new ArrayList<Strategy>();
        competitor.add(new RandomMove(rand));
        competitor.add(new GenerousTFT());
        competitor.add(new exp_algo());
        competitor.add(new gauss_algo());
        competitor.add(new GoodToBad());
        competitor.add(new NastyThreeMove());
        competitor.add(new NiceThreeMove());
        */
        Random rand = new Random();
        int numberOfRounds = 195;// + rand.nextInt(11);
        ArrayList<Strategy> competitor = new ArrayList<Strategy>();
        competitor.add(new Brain(new byte[] {0}, "Coop"));
        competitor.add(new Brain(new byte[] {1}, "Jerk"));
        competitor.add(new Brain(new byte[] {0,1}, "TitForTat"));
        competitor.add(new Brain(new byte[] {1,0}, "Negation"));
        competitor.add(new Brain(new byte[] {0,0,0,1}, "And"));
        competitor.add(new Brain(new byte[] {0,1,1,0}, "Xor"));
        competitor.add(new Brain(new byte[] {0,1,1,1}, "Or"));
        competitor.add(new Brain(new byte[] {1,0,0,1}, "sSi"));
        competitor.add(new Brain(new byte[] {1,0,1,1}, "Impl"));
        competitor.add(new Brain(new byte[] {1,1,0,1}, "Impr"));
        competitor.add(new SmarterThreeMove());
        
        
        // Initializing result map
        Map<String, Integer> resultTable = new HashMap<>();
        for (Strategy strategy : competitor) {
            resultTable.put(strategy.Name(), 0);
        }

        for (int i = 0; i < competitor.size(); ++i)
        {
            for (int j = 0; j <= i; ++j)
            {
                Map<String, Integer> result = fight(numberOfRounds, (Strategy)competitor.get(i), (Strategy)competitor.get(j));
                String algo1Name = competitor.get(i).Name();
                String algo2Name = competitor.get(j).Name();

                int actualValue1 = resultTable.get(algo1Name);
                int newValue1 = actualValue1 + result.get(algo1Name);
                resultTable.put(algo1Name, newValue1);
                // To prevent double count
                if(algo1Name != algo2Name)
                {
                    int actualValue2 = resultTable.get(algo2Name);
                    int newValue2 = actualValue2 + result.get(algo2Name);
                    resultTable.put(algo2Name, newValue2);
                }
            }
        }

        List<String> names = new ArrayList<>(resultTable.keySet());
        List<Integer> points = new ArrayList<>(resultTable.values());

        List<String> orderedNames = new ArrayList<>();
        List<Integer> orderedPoints = new ArrayList<>();

        while (!points.isEmpty()) {
            int max = Collections.max(points);
            int maxIndex = points.indexOf(max);
            orderedNames.add(names.remove(maxIndex));
            orderedPoints.add(points.remove(maxIndex));
        }

        Map<Integer, String> positions = new HashMap<>();
        positions.put(0, "1st");
        positions.put(1, "2nd");
        positions.put(2, "3rd");
        positions.put(3, "4th");
        positions.put(4, "5th");

        for (int i = 0; i < orderedNames.size(); i++) {
            if (i == 0)
                System.out.println("1st ---> " + orderedNames.get(i) + ": " + orderedPoints.get(i));
            else if (i > 0 && i < 5)
                if (orderedPoints.get(i) == orderedPoints.get(i - 1))
                    System.out.println(positions.get(i-1) + " ---> " + orderedNames.get(i) + ": " + orderedPoints.get(i));
                else
                    System.out.println(positions.get(i) + " ---> " + orderedNames.get(i) + ": " + orderedPoints.get(i));
            else
                System.out.println(orderedNames.get(i) + ": " + orderedPoints.get(i));
        }
    }
    
    public static Map<String, Integer> fight(int numberOfRounds, Strategy a, Strategy b)
    {
        Strategy playerA = a.Duplicate();
        Strategy playerB = b.Duplicate();
        Map<String, Integer> result = new HashMap<>();
        int totalA = 0; int totalB = 0;
        byte previousActionA = 2; byte previousActionB = 2;
        byte actionA; byte actionB;
        for (int i = 0; i < numberOfRounds; ++i)
        {
            actionA = playerA.Action(previousActionB);
            actionB = playerB.Action(previousActionA);
            if(actionA == 0)
            {
                if(actionB == 0)
                {
                    totalA += 3;
                    totalB += 3;
                }
                else
                {
                    totalB += 5;
                }
            }
            else
            {
                if (actionB == 0)
                {
                    totalA += 5;
                }
                else
                {
                    totalA += 1;
                    totalB += 1;
                }
            }
            previousActionA = actionA;
            previousActionB = actionB;
        }
        result.put(playerA.Name(), totalA);
        result.put(playerB.Name(), totalB);
        return result;
    }
}
