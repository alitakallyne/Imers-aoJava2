//import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
  public void criarFigura(InputStream inputStream,String nomeArquivo) throws Exception {

    //leitura da imagem
    // Arquivo :InputStream inputStream = new FileInputStream(new File("./entrada/filme.jpg"));
    // URL : InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
    BufferedImage imageOriginal = ImageIO.read(inputStream);
   
    // faz direto arquivo: BufferedImage imageOriginal = ImageIO.read(new File("./entrada/filme.jpg"));

    //cria nova imagem em memoria com transparencia e com tamanho novo
    int largura = imageOriginal.getWidth();
    int altura = imageOriginal.getHeight();
    int novaAltura = altura+200;
    BufferedImage novaImage = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

    //copiar a imagem original pra novo imagem (em memoria)
    Graphics2D graphics = (Graphics2D)novaImage.getGraphics();
    graphics.drawImage(imageOriginal,0,0,null);
    
    //Configurar fonte
    var fonte = new Font(Font.SANS_SERIF,Font.BOLD,64);
    graphics.setColor(Color.MAGENTA);
    graphics.setFont(fonte);
    //escrever uma frase na nova imagem
    graphics.drawString("TOPZERA", 100, novaAltura-100);
    //escrever a nova imagem em um arquivo
    ImageIO.write(novaImage, "png", new File(nomeArquivo));
    
  }

}
