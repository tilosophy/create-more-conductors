import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import javax.imageio.ImageIO;

/** Static UV/model inspection, not an in-game screenshot. No hats, lighting engine or animation. */
public class PreviewConductors {
    static Graphics2D g;
    static int cx,cy;
    static final int SCALE=8;
    static Point project(double x,double y,double z) {
        return new Point(cx+(int)Math.round(SCALE*(.866*x+.5*z)),cy+(int)Math.round(SCALE*(y-.25*z+.144*x)));
    }
    static void pixel(BufferedImage tex,int u,int v,double[][] p,double shade) {
        Color c=new Color(tex.getRGB(u,v),true);
        g.setColor(new Color((int)(c.getRed()*shade),(int)(c.getGreen()*shade),(int)(c.getBlue()*shade)));
        Polygon polygon=new Polygon();
        for(double[] a:p) {Point b=project(a[0],a[1],a[2]);polygon.addPoint(b.x,b.y);}
        g.fillPolygon(polygon);
    }
    static void box(BufferedImage t,int u,int v,int w,int h,int d,double x,double y,double z) {
        for(int j=0;j<h;j++)for(int i=0;i<d;i++)pixel(t,u+d+w+i,v+d+j,
                new double[][]{{x+w,y+j,z+i},{x+w,y+j+1,z+i},{x+w,y+j+1,z+i+1},{x+w,y+j,z+i+1}},.8);
        for(int j=0;j<h;j++)for(int i=0;i<w;i++)pixel(t,u+d+i,v+d+j,
                new double[][]{{x+i,y+j,z},{x+i+1,y+j,z},{x+i+1,y+j+1,z},{x+i,y+j+1,z}},1);
        for(int j=0;j<d;j++)for(int i=0;i<w;i++)pixel(t,u+d+i,v+j,
                new double[][]{{x+i,y,z+j},{x+i+1,y,z+j},{x+i+1,y,z+j+1},{x+i,y,z+j+1}},.93);
    }
    public static void main(String[] args)throws Exception {
        String[] names={"brass","copper","train","shadow","radiant","zinc","blaze","sturdy","industrial","overburden","blackstone","platinum"};
        BufferedImage out=new BufferedImage(1000,960,BufferedImage.TYPE_INT_RGB);
        g=out.createGraphics();g.setColor(new Color(22,30,38));g.fillRect(0,0,1000,960);
        g.setFont(new Font("SansSerif",Font.BOLD,23));g.setColor(new Color(232,226,209));g.drawString("Create: More Conductors — first body designs",28,35);
        g.setFont(new Font("SansSerif",Font.PLAIN,14));g.drawString("UV/model preview • Hats and animations supplied by Steam ’n’ Rails in game",28,61);
        for(int i=0;i<names.length;i++) {
            cx=125+i%4*250;cy=93+i/4*285;
            g.setColor(new Color(29,39,48));g.fillRoundRect(i%4*250+14,cy-12,222,272,16,16);
            BufferedImage t=ImageIO.read(Path.of("src/main/resources/assets/moreconductors/textures/entity/conductor/"+names[i]+".png").toFile());
            box(t,34,15,3,5,4,0,19,-2);box(t,50,15,3,5,4,-3,19,-2);
            box(t,0,29,6,4,4,-3,15,-2);
            box(t,34,0,3,9,4,-7,10,-2);box(t,50,0,3,9,4,4,10,-2);
            box(t,0,17,8,5,6,-4,10,-3);box(t,0,0,8,8,8,-4,2,-4);
            g.setFont(new Font("SansSerif",Font.BOLD,18));g.setColor(new Color(232,226,209));
            String label=names[i].substring(0,1).toUpperCase()+names[i].substring(1);
            g.drawString(label,cx-g.getFontMetrics().stringWidth(label)/2,cy+240);
        }
        g.dispose();ImageIO.write(out,"PNG",Path.of("art/conductor-lineup.png").toFile());
    }
}
