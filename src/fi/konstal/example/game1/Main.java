package fi.konstal.example.game1;

/**
 * The main class where the game is launched
 *
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments, not used
     */
    public static void main(String... args) {
        MenuWindow mw = new MenuWindow();
        mw.launchThis();
    }
}
