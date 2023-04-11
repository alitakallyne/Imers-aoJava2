import java.io.InputStream;
import java.net.URL;
import java.util.List;


public class App { 
 
    public static void main(String[] args) throws Exception {

        //Fazer uma conexão http e  buscar os tops 250 filmes.
       
        API api = API.NASA_APOD;

        String url = api.getUrl();
        ExtratorDeConteudo extrator = api.getExtrator();
                
        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        //exibir e manipular dados
       
        List<Conteudo> conteudos = extrator.ExtraiConteudo(json);

        var geradora = new GeradoraDeFigurinhas();

        for(int i = 0; i < 3 ; i++){
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "./saida/" +conteudo.titulo() +".png";

            geradora.criarFigura(inputStream, nomeArquivo);

            System.out.println(conteudo.titulo());
            System.out.println();

        }
        



        //System.out.println(listaDeFilmes.size()); 
        //exibir e manipular os dados Aula02
        /*  for (Map<String,String> filme : listaDeFilmes) {
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
        } */
       
    }
}
