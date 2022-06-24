package online.airkhavdev.yarrmusicplayerbackend.utils.initializer;

import java.util.Arrays;

public class Initializer {
    public static void initialize(Initializable... initializables) {
        Arrays.stream(initializables).forEach(Initializable::initialize);
    }
}
