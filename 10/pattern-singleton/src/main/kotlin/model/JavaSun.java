package model;

public class JavaSun {

    private static JavaSun instance;

    private JavaSun() {}

    public static JavaSun getInstance() {
        if (instance == null) {
            instance = new JavaSun();
        }

        return instance;
    }
}
