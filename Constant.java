public final class Constant extends Strategy
{
    byte action;
    String name;
    public Constant(byte Action, String Name)
    {
        action = Action;
        name = Name;
    }
    public byte Action(byte previousMove)
    {
        return action;
    }
    public void Reset()
    {

    }
    public String Name()
    {
        return name;
    }
}