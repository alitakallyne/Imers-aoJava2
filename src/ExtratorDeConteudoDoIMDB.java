import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoIMDB implements ExtratorDeConteudo {
    
    public List<Conteudo> ExtraiConteudo (String json){

        //Extrair só os dados que interessam(titulo,poster,classifição)
        var parser = new JsonParser();
        List<Map<String,String>> listaDeAtributos = parser.parse(json);

        // Usando Stream para add em uma lista
        return  listaDeAtributos.stream()
            .map(atributos -> new Conteudo (atributos.get("title"),atributos.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg")))
            .toList();
        /*Popular lista de conteudos
        List<Conteudo> Conteudos = new ArrayList<>();
       
        for(Map<String,String> atributos : listaDeAtributos){
            
           String titulo = atributos.get("title");
           String urlImagem = atributos.get("image")
            .replaceAll("(@+)(.*).jpg$", "$1.jpg");
           var conteudo = new Conteudo(titulo, urlImagem);

           Conteudos.add(conteudo);
        }

       return Conteudos; */
    }
}
