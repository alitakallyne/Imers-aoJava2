import java.security.MessageDigest;
 //Lançando exceção
public class ClienteHttpException extends RuntimeException{

    public ClienteHttpException(String message) {
        super(message);
    }

}
