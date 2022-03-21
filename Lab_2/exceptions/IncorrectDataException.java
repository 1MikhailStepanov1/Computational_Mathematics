package exceptions;

public class IncorrectDataException extends Exception{
    public IncorrectDataException(String message){
        super("Введенные данные некорректны. Попробуйте еще раз. (" + message + ")");
    }
}
