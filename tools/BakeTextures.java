import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;

/** Deterministic UV bake from our original atlas. Run from the repository root with Java 21. */
public class BakeTextures {
    static final String[] NAMES = {"brass", "copper", "train", "shadow", "radiant", "zinc",
            "blaze", "sturdy", "industrial", "overburden", "blackstone", "platinum"};
    // Texture offsets and dimensions of Steam 'n' Rails' existing conductor cuboids.
    static final int[][] BOXES = {{0,0,8,8,8}, {0,17,8,5,6}, {0,29,6,4,4},
            {34,0,3,9,4}, {50,0,3,9,4}, {34,15,3,5,4}, {50,15,3,5,4}};
    static BufferedImage atlas;
    static int sample(int tile, double x, double y) {
        int sx = Math.min(atlas.getWidth()-1, (int)((tile%4+x)*atlas.getWidth()/4.0));
        int sy = Math.min(atlas.getHeight()-1, (int)((tile/4+y)*atlas.getHeight()/3.0));
        return atlas.getRGB(sx,sy) | 0xff000000;
    }
    static void face(BufferedImage out, int tile, int u, int v, int w, int h) {
        for(int y=0;y<h;y++) for(int x=0;x<w;x++) {
            // One texel structural frame; retain the material pattern in every inset panel.
            double sx = x==0 ? .045 : x==w-1 ? .955 : .18+(x-1)*.64/Math.max(1,w-3);
            double sy = y==0 ? .035 : y==h-1 ? .965 : .18+(y-1)*.64/Math.max(1,h-3);
            out.setRGB(u+x,v+y,sample(tile,sx,sy));
        }
    }
    public static void main(String[] args) throws Exception {
        atlas = ImageIO.read(Path.of("art/material-atlas.png").toFile());
        Path dest=Path.of("src/main/resources/assets/moreconductors/textures/entity/conductor");
        Files.createDirectories(dest);
        for(int tile=0;tile<NAMES.length;tile++) {
            BufferedImage out=new BufferedImage(64,64,BufferedImage.TYPE_INT_ARGB);
            for(int[] b:BOXES) {
                int u=b[0],v=b[1],w=b[2],h=b[3],d=b[4];
                face(out,tile,u,v+d,d,h); face(out,tile,u+d,v+d,w,h);
                face(out,tile,u+d+w,v+d,d,h); face(out,tile,u+2*d+w,v+d,w,h);
                face(out,tile,u+d,v,w,d); face(out,tile,u+d+w,v,w,d);
            }
            // Familiar friendly conductor eyes. Light rims keep them legible on dark casings.
            for(int eye:new int[]{10,13}) for(int y=10;y<=12;y++) {
                out.setRGB(eye,y,y==10 ? sample(tile,.5,.035) : 0xff11191b);
                out.setRGB(eye+1,y,y==10 ? sample(tile,.5,.035) : 0xff11191b);
            }
            ImageIO.write(out,"PNG",dest.resolve(NAMES[tile]+".png").toFile());
            System.out.println(NAMES[tile]+" 64x64");
        }
    }
}
