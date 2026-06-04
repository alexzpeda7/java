package software.pdi.modelo;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Imagen {
    private BufferedImage bufferedImage;
    private File archivo;

    public Imagen(File archivo) throws IOException {
        this.archivo = archivo;
        this.bufferedImage = ImageIO.read(archivo);
    }

    public Imagen(BufferedImage imagen) {
        this.bufferedImage = imagen;
        this.archivo = null;
    }

    public BufferedImage getBufferedImage() { return bufferedImage; }
    public void setBufferedImage(BufferedImage imagen) { this.bufferedImage = imagen; }
    public File getArchivo() { return archivo; }
    public void setArchivo(File archivo) { this.archivo = archivo; }
    
    public int getAncho() { return bufferedImage.getWidth(); }
    public int getAlto() { return bufferedImage.getHeight(); }
    public int getTipo() { return bufferedImage.getType(); }

    public Imagen clonar() {
        BufferedImage copia = new BufferedImage(getAncho(), getAlto(), getTipo());
        Graphics2D g = copia.createGraphics();
        g.drawImage(bufferedImage, 0, 0, null);
        g.dispose();
        return new Imagen(copia);
    }

    public void redimensionar(int nuevoAncho, int nuevoAlto) {
        Image tmp = bufferedImage.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
        BufferedImage nueva = new BufferedImage(nuevoAncho, nuevoAlto, getTipo());
        Graphics2D g = nueva.createGraphics();
        g.drawImage(tmp, 0, 0, null);
        g.dispose();
        this.bufferedImage = nueva;
    }

    public void rotar() {
        int w = getAncho();
        int h = getAlto();
        BufferedImage rotada = new BufferedImage(h, w, getTipo());

        AffineTransform at = new AffineTransform();
        at.translate(h / 2.0, w / 2.0);
        at.rotate(Math.toRadians(90));
        at.translate(-w / 2.0, -h / 2.0);

        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        op.filter(bufferedImage, rotada);
        this.bufferedImage = rotada;
    }

    public boolean esBinaria() {
        for (int y = 0; y < getAlto(); y++) {
            for (int x = 0; x < getAncho(); x++) {
                Color p = new Color(bufferedImage.getRGB(x, y));
                if (p.getRed() != p.getGreen() || p.getRed() != p.getBlue()) return false;
                if (p.getRed() != 0 && p.getRed() != 255) return false;
            }
        }
        return true;
    }
}