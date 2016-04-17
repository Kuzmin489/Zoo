package lv.autentica.exception;

/**
 * Created by german on 4/16/16 for MyTi project.
 */
public class AnimalException extends Exception {
    public AnimalException(Throwable e) {
        super(e);
    }
    public AnimalException(String message) {
        super(message);
    }
}
