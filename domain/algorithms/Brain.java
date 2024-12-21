public final class Brain extends Strategy
{
    byte[] brain;
    byte complexity;
    byte pM;
    String name;
    public Brain(byte[] Brained, String Name)
    {
        name=Name;
        brain = Brained;
        byte size = (byte) Brained.length;
        byte comp=0;
        while(size > 0)
        {
            size >>= 1;
            comp+=1;
        }
        complexity=comp;
    }
    public Brain(byte[] Brained, byte comp, String Name)
    {
        brain = Brained;
        complexity = comp;
        name = Name;
    }
    public byte Action(byte previousMove)
    {
        if(previousMove==2)
        {
            return brain[0];
        }
        else
        {
            pM <<=1;
            pM += previousMove;
            pM %= brain.length;
            return brain[pM];
        }
    }
    public String Name()
    {
        return name;
    }
    public Strategy Duplicate()
    {
        return new Brain(brain, complexity, name);
    }
}
