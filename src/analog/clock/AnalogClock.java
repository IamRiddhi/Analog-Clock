package analog.clock;
import javax.swing.JFrame;

/**
 *
 * @author riddhi
 */
public class AnalogClock 
{
    public static void main(String[] args)
    {
        JFrame f= new CenteredFrame("Analog Clock",1,1);
        f.add(new ClockPanel());  
        f.setVisible(true);
    }
    
    
}
