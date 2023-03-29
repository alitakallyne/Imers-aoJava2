import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
 
    public static void main(String[] args) throws Exception {
       // System.out.println("Hello, World!");

        //Fazer uma conexão http e  buscar os tops 250 filmes.
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        

        //Extrair só os dados que interessam(titulo,poster,classifição)
        var parser = new JsonParser();
        List<Map<String,String>> listaDeFilmes = parser.parse(body);
        //System.out.println(listaDeFilmes.size()); 
        //exibir e manipular os dados
        
        for (Map<String,String> filme : listaDeFilmes) {
            String urlImage = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            String nomeArquivo = titulo +".png";

            var GeradoraDeFigurinhas = new GeradoraDeFigurinhas();
            GeradoraDeFigurinhas.criarFigura(inputStream, nomeArquivo);

            System.out.println("\u001b[31m\u001b[1mTitulo:\u001b[m" + "\u001b[32m"+filme.get("title")+"\u001b[m");
            System.out.println("\u001b[1m\u001b[3mLinkImage:\u001b[m" + "\u001b[36m"+filme.get("image")+"\u001b[m");
            // System.out.println(filme.get("imDbRating"));
            System.out.print("\u001b[35m\u001b[1m"+"Classification: "+"\u001b[m");
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrela = (int)classificacao;
            for (int n = 1 ; n <= numeroEstrela; n++) {
                System.out.print("⭐️");
               //// System.out.print("\uD83D\uDC99 💙️");
            }
            System.out.println();
        }
    }
}
