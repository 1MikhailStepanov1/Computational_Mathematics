package exceptions;

public class IncorrectInputException extends Exception{
    public IncorrectInputException(String message){
        super("Введенные данные некорректны. Попробуйте еще раз. (" + message + ")");
    }
}
