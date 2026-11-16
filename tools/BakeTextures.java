import java.awt.image.BufferedImage;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.Properties;
import javax.imageio.ImageIO;

/** Exact-coordinate palette substitution. Never draws, moves, resizes or adds a pixel. */
public class BakeTextures {
    static final String[] NAMES = {"brass", "copper", "train", "shadow", "radiant", "zinc",
            "blaze", "sturdy", "industrial", "overburden", "blackstone", "platinum"};
    static final String SOURCE_SHA256 = "12345094dbfdd7de89dfb323544b51772e05020bfc4f4b1b117b23b1a9ae56e7";
    static final int[] FRAME_REFERENCE = {110, 114, 111};
    static final int[] PANEL_REFERENCE = {106, 79, 45};

    static int[] rgb(String hex) {
        int c = Integer.parseInt(hex,16);
        return new int[]{c >> 16 & 255, c >> 8 & 255, c & 255};
    }
    static int channel(int n) { return Math.max(0,Math.min(255,n)); }
    static int colour(int alpha,int[] channels) {
        return alpha << 24 | channel(channels[0]) << 16 | channel(channels[1]) << 8 | channel(channels[2]);
    }
    static boolean eye(int x,int y) {
        return (y == 11 || y == 12) && (x == 9 || x == 10 || x == 13 || x == 14);
    }
    static int recolour(int pixel,int x,int y,int[] frame,int[] panel,int[] eyes) {
        int a=pixel >>> 24;
        if(a==0) return pixel;
        int[] src={pixel >> 16 & 255,pixel >> 8 & 255,pixel & 255};
        if(eye(x,y)) {
            if(eyes==null) return pixel;
            // Preserve three-tone relief, shifted to a subdued light palette on dark materials.
            return colour(a,new int[]{eyes[0]+src[0]-25,eyes[1]+src[1]-25,eyes[2]+src[2]-25});
        }
        // Neutral sockets/joints and unused reference checkerboard remain byte-for-byte intact.
        if(src[0]==src[1] && src[1]==src[2]) return pixel;
        boolean isPanel=src[0]>src[1] && src[1]>src[2];
        int[] target=isPanel?panel:frame, reference=isPanel?PANEL_REFERENCE:FRAME_REFERENCE;
        int[] dst=new int[3];
        for(int c=0;c<3;c++) dst[c]=(int)Math.round(src[c]*(double)target[c]/reference[c]);
        return colour(a,dst);
    }
    public static void main(String[] args) throws Exception {
        boolean verify=args.length==1 && args[0].equals("--verify");
        Path source=Path.of("art/upstream/conductor.png");
        String hash=HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(Files.readAllBytes(source)));
        if(!hash.equals(SOURCE_SHA256)) throw new IllegalStateException("Upstream source changed; review masks before baking");
        BufferedImage original=ImageIO.read(source.toFile());
        if(original.getWidth()!=64 || original.getHeight()!=64) throw new IllegalStateException("Expected 64x64 source");
        Properties palettes=new Properties();
        try(Reader reader=Files.newBufferedReader(Path.of("art/palettes.properties"))) { palettes.load(reader); }
        Path dest=Path.of("src/main/resources/assets/moreconductors/textures/entity/conductor");
        if(!verify) Files.createDirectories(dest);
        for(String name:NAMES) {
            String[] palette=palettes.getProperty(name).split(",");
            int[] frame=rgb(palette[0]),panel=rgb(palette[1]),eyes=palette[2].equals("original")?null:rgb(palette[2]);
            BufferedImage out=new BufferedImage(64,64,BufferedImage.TYPE_INT_ARGB);
            BufferedImage existing=verify?ImageIO.read(dest.resolve(name+".png").toFile()):null;
            if(verify && (existing.getWidth()!=64 || existing.getHeight()!=64)) throw new IllegalStateException(name+": dimensions");
            for(int y=0;y<64;y++) for(int x=0;x<64;x++) {
                int src=original.getRGB(x,y),dst=recolour(src,x,y,frame,panel,eyes);
                out.setRGB(x,y,dst);
                if(verify && existing.getRGB(x,y)!=dst) throw new IllegalStateException(name+": unexpected pixel at "+x+","+y);
                if((dst>>>24)!=(src>>>24)) throw new IllegalStateException(name+": transparency changed");
            }
            if(!verify) ImageIO.write(out,"PNG",dest.resolve(name+".png").toFile());
            System.out.println((verify?"Verified ":"Baked ")+name+": 4096 original coordinates; eyes "+palette[2]);
        }
    }
}
