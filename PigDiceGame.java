import javax.swing.JOptionPane;
public class PigDiceGame
{
    public static void main(String[] args)
    {
        final int WIN = 60;
        double comDiff;
        String diff = "";
        String opponent = "";
        int userScore = 0;
        int comScore = 0;
        double comTurn = 0.9;
        int userTurn = 1;
        int userValue1 = 2;
        int userValue2 = 2;
        int comValue1 = 2;
        int comValue2 = 2;

        JOptionPane.showMessageDialog(null, "Welcome to the Pig Dice Game!" +
                                            "\nFirst to " + WIN + " wins! Roll a 1 and you get no points!" +
                                            "\nRoll two 1s, your score resets to zero!");

        diff = JOptionPane.showInputDialog(null, "Choose computer difficulty level" +
                                                 "\nEasy - Least aggressive opponent" +
                                                 "\nMedium - Somewhat aggressive opponent" +
                                                 "\nHard - Very aggressive opponent" +
                                                 "\nLegendary - Just give up");
        diff.toLowerCase();
        if (diff.startsWith("ea"))
        {
            comDiff = 0.8;
            opponent = "Jerry";
            JOptionPane.showMessageDialog(null, "Meet your easy level opponent, " + opponent);
        }
        else
        {
            if (diff.startsWith("me"))
            {
                comDiff = 0.5;
                opponent = "Morty";
                JOptionPane.showMessageDialog(null, "Meet your medium level opponent, " + opponent);
            }
            else
            {
                if (diff.startsWith("ha"))
                {
                    comDiff = 0.3;
                    opponent = "Summer";
                    JOptionPane.showMessageDialog(null, "Meet your hard level opponent, " + opponent);
                }
                else
                {
                    if (diff.startsWith("le"))
                    {
                        comDiff = 0.1;
                        opponent = "Rick";
                        JOptionPane.showMessageDialog(null, "Meet your legendary opponent, " + opponent + ". Good Luck!");
                    }
                    else
                    {
                        comDiff = 0.8;
                        opponent = "Jerry";
                        JOptionPane.showMessageDialog(null, "Invalid difficulty entered. We'll let you play with " + opponent + " for now.");
                    }
                }
            }
        }

        while (userScore < WIN && comScore < WIN)
        {
            do 
            {
                JOptionPane.showMessageDialog(null, "You roll!");
                Die userDie1 = new Die();
                Die userDie2 = new Die();
                userValue1 = userDie1.getValue();
                userValue2 = userDie2.getValue();
                JOptionPane.showMessageDialog(null, "You roll a " + userValue1 + " and a " + userValue2);
                if (userValue1 != 1 && userValue2 != 1 && userScore < WIN && comScore < WIN)
                {
                    userScore += userValue1 + userValue2;
                    if (userScore < WIN && comScore < WIN)
                    {
                        userTurn = Integer.parseInt(JOptionPane.showInputDialog(null, "Score: You - " + userScore + " " + opponent + " - " + comScore +
                                                                "\nWould you like to roll again?" + 
                                                                "\n(Enter 1 to roll again, any other number to skip your turn"));
                    }
                }
                else
                {
                    if (userValue1 == 1 && userValue2 == 1)
                    {
                        userScore = 0;
                        JOptionPane.showMessageDialog(null, "You rolled two 1s!" + 
                                                            "\nYour score resets to zero!" +
                                                            "\nScore: You - " + userScore + " " + opponent + " - " + comScore);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "You rolled a 1!" +
                                                            "\nYou get no points for this roll!" +
                                                            "\nScore: You - " + userScore + " " + opponent + " - " + comScore);
                    }
                } 
            } while (userValue1 != 1 && userValue2 != 1 && userTurn == 1 && userScore < WIN && comScore < WIN);
            
            if (userScore < WIN)
            {
                do
                {
                    if (comTurn < comDiff)
                    {
                        JOptionPane.showMessageDialog(null, opponent + " rolls!");
                    }
                    Die comDie1 = new Die();
                    Die comDie2 = new Die();
                    comValue1 = comDie1.getValue();
                    comValue2 = comDie2.getValue();
                    if (comValue1 != 1 && comValue2 != 1 && userScore < WIN && comScore < WIN)
                    {
                        comScore += comValue1 + comValue2;
                        if (userScore < WIN && comScore < WIN) 
                        {
                            JOptionPane.showMessageDialog(null, opponent + " rolled a " + comValue1 + " and a " + comValue2 +
                                                                "\nScore: You - " + userScore + " " + opponent + " - " + comScore);
                        }
                        comTurn = Math.random();
                        if (comTurn >= comDiff)
                        {
                            JOptionPane.showMessageDialog(null, opponent + " rolls again!");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, opponent + " skips their turn!");
                        }
                    }
                    else
                    {
                        if (comValue1 == 1 && comValue2 == 1)
                        {
                            comScore = 0;
                            JOptionPane.showMessageDialog(null, opponent + " rolled two 1s!" + 
                                                                "\nTheir score resets to zero!" +
                                                                "\nScore: You - " + userScore + " " + opponent + " - " + comScore);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, opponent + " rolled a 1!" + 
                                                                "\nThey get no points for this roll!" +
                                                                "\nScore: You - " + userScore + " " + opponent + " - " + comScore);
                        }
                    }
                } while (comValue1 != 1 && comValue2 != 1 && comTurn >= comDiff && userScore < WIN && comScore < WIN);
            }
        }
        if (comScore > userScore)
        {
            JOptionPane.showMessageDialog(null, opponent + " wins! By a score of " + comScore + " to " + userScore);
        }
        
        if (userScore > comScore)
        {
            JOptionPane.showMessageDialog(null, "You beat " + opponent + "! By a score of " + userScore + " to " + comScore);
        }
    }
}