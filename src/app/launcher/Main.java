package app.launcher;

import app.core.graphics.Window;
import app.core.utils.ExceptionHandler;

public class Main {
    public static void main(String[] args) {
        Window window = new Window("Andromeda", 800, 600);
        while (true) {
            if (window.shouldClose()) {
                System.exit(0);
            }
            ExceptionHandler.tryCatch(() -> {
                Thread.sleep(1000 / 60);
            });
        }
    }
}
