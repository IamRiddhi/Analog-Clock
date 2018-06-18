package analog.clock;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.Timer;

/**
 *
 * @author riddhi
 */
public class ClockPanel extends JPanel
{
    private final int margin=5;
    private int hour,minute,seconds;
    private void setTime()
    {
        Calendar time = Calendar.getInstance();
        hour = time.get(Calendar.HOUR);
        minute = time.get(Calendar.MINUTE);
        seconds = time.get(Calendar.SECOND);
    }
    public ClockPanel()
    {
        setTime();
        ActionListener l = new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                setTime();
                repaint();
            }
        
         };
        Timer t= new Timer(1000, l);
        t.start();
    }
    
     
    public void drawDot(Graphics g, int x , int y, int diameter)
    {
        x-=(diameter)/2;
        y-=(diameter)/2;
        g.drawOval(x, y, diameter, diameter);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); 
        int side,w,h,x,y;
        h= getHeight();
        w= getWidth();
        if(w<h)
        {
            side=w; x=0; y=(h-side)/2;
        }
        else
        {
            side=h; x= (w-side)/2; y=0;
        }
        
        x+=margin; y+=margin;
        side= Math.abs(side-2*margin);
        
        //drawing outer circle
        g.fillOval(x, y, side, side);
        
        //drawing inner circle
        int radius= side/2;
        int innerRadius= (int)(9*radius)/10;
        Color c= g.getColor();
        g.setColor(this.getBackground());
        int xouter= x + radius - innerRadius;
        int youter= y + radius - innerRadius;
        g.fillOval(xouter, youter, innerRadius*2,innerRadius*2);
        g.setColor(c);
        
        int midCircleRadius = radius/20;
        xouter= x + radius - midCircleRadius;
        youter= y + radius-  midCircleRadius;
        g.drawOval(xouter,youter,midCircleRadius*2,midCircleRadius*2);
        
        // Minute circles.
        int angle=90;
        int cx= x + radius;
        int cy= y + radius;
        int dotsRadius = (85*radius)/100;
        for(int i=0;i<60;i++)
        {
            xouter= cx + (int) (dotsRadius * Math.cos(Math.toRadians(angle)));
            youter= cy - (int) (dotsRadius * Math.sin(Math.toRadians(angle)));
            if(i%5==0)
               drawDot(g,xouter,youter,10);
           else
               drawDot(g,xouter,youter,5);
            angle+= 6;
        }
        
        // Drawing Hands
        int secHand= (87*radius)/100;
        int minutesHand= (70*radius)/100;
        int hoursHand= (60*radius)/100;
        
        //hardCode
//        hour=8;minute=50;seconds=43;
        
        // drawing hour hand
        angle= 30*hour + (minute /2);
        xouter= cx + (int) (hoursHand * Math.cos(Math.toRadians(angle-90)));
        youter= cy + (int) (hoursHand * Math.sin(Math.toRadians(angle-90)));
        g.setColor(Color.blue);
        g.drawLine(cx,cy,xouter, youter);
        
        //drawing hour tail
        int htail= (int)(hoursHand/5.0);
        int xtail= cx - (int) ( htail * Math.cos(Math.toRadians(angle-90)));
        int ytail= cy - (int) ( htail * Math.sin(Math.toRadians(angle-90)));
        int hbellyx1= cx - (int) ( htail/2 * Math.cos(Math.toRadians(angle-180)));
        int hbellyx2= cx - (int) ( htail/2 * Math.cos(Math.toRadians(angle)));
        int hbellyy1= cy - (int) ( htail/2 * Math.sin(Math.toRadians(angle-180)));
        int hbellyy2= cy - (int) ( htail/2 * Math.sin(Math.toRadians(angle)));
        g.fillPolygon(new int[]{xouter,hbellyx1, xtail,hbellyx2}, 
                new int[]{youter,hbellyy1,ytail,hbellyy2},4);
        g.setColor(Color.WHITE);
        g.drawPolygon(new int[]{xouter,hbellyx1, xtail,hbellyx2}, 
                new int[]{youter,hbellyy1,ytail,hbellyy2},4);
        
        //drawing minute hand
        angle= 6*minute + (6*seconds)/60;
        xouter= cx + (int) ( minutesHand * Math.cos(Math.toRadians(angle-90)));
        youter= cy + (int) ( minutesHand * Math.sin(Math.toRadians(angle-90)));
        g.setColor(Color.green);
        g.drawLine(cx,cy,xouter, youter);
        
        //drawing minute tail
        int mtail= (int)(hoursHand/5.0);
        xtail= cx - (int) ( mtail * Math.cos(Math.toRadians(angle-90)));
        ytail= cy - (int) ( mtail * Math.sin(Math.toRadians(angle-90)));
        int mbellyx1= cx - (int) ( mtail/2 * Math.cos(Math.toRadians(angle-180)));
        int mbellyx2= cx - (int) ( mtail/2 * Math.cos(Math.toRadians(angle)));
        int mbellyy1= cy - (int) ( mtail/2 * Math.sin(Math.toRadians(angle-180)));
        int mbellyy2= cy - (int) ( mtail/2 * Math.sin(Math.toRadians(angle)));
        
        g.fillPolygon(new int[]{xouter,mbellyx1, xtail,mbellyx2}, 
                new int[]{youter,mbellyy1,ytail,mbellyy2},4);
       
        g.setColor(Color.WHITE);
        
        g.drawPolygon(new int[]{xouter,mbellyx1, xtail,mbellyx2}, 
                new int[]{youter,mbellyy1,ytail,mbellyy2},4);
        
        
        //drawing seconds hand
        angle= 6*seconds; // each second increases by 60 degree. 
        xouter= cx + (int) ( secHand * Math.cos(Math.toRadians(angle-90)));
        youter= cy + (int) ( secHand * Math.sin(Math.toRadians(angle-90)));
        g.setColor(Color.red);
        g.drawLine(cx,cy,xouter, youter); 
        
         htail= (int)(hoursHand/5.0);
        xtail= cx - (int) ( htail * Math.cos(Math.toRadians(angle-90)));
        ytail= cy - (int) ( htail * Math.sin(Math.toRadians(angle-90)));
        hbellyx1= cx - (int) ( htail/2 * Math.cos(Math.toRadians(angle-180)));
        hbellyx2= cx - (int) ( htail/2 * Math.cos(Math.toRadians(angle)));
        hbellyy1= cy - (int) ( htail/2 * Math.sin(Math.toRadians(angle-180)));
        hbellyy2= cy - (int) ( htail/2 * Math.sin(Math.toRadians(angle)));
        
        g.fillPolygon(new int[]{xouter,hbellyx1, xtail,hbellyx2}, 
                new int[]{youter,hbellyy1,ytail,hbellyy2},4);
        g.setColor(c);
    }
}