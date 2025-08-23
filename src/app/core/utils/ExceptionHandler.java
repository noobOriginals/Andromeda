package app.core.utils;

public class ExceptionHandler {

    public static void handleException(Exception e) {
        throw new RuntimeException(e.getMessage(), e.getCause());
    }

    public static void tryCatch(TryCatch t) {
        try {
            t.run();
        } catch (Exception e) {
            handleException(e);
        }
    }

    @FunctionalInterface
    public interface TryCatch {
        public void run() throws Exception;
    }
}
