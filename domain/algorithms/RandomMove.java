
import java.util.Random;
public final class RandomMove extends Strategy
{
    Random rand;
    public RandomMove(Random Rand)
    {
        rand = Rand;
    }
    public byte Action(byte previousMove)
    {
        return (byte)rand.nextInt(2);
    }
    public Strategy Duplicate()
    {
        return new RandomMove(rand);
    }
    public String Name()
    {
        return "Random";
    }
}
