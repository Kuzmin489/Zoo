package lv.autentica.exception;

/**
 * Created by german on 4/16/16 for MyTi project.
 */
public class StaffException extends Exception {
    public StaffException(Throwable e) {
        super(e);
    }
    public StaffException(String message) {
        super(message);
    }
}
