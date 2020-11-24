package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public final class Config {

    private static final String CONFIG_FILE_NAME = "config.yml";
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String CONFIG_FILE_PATH = System.getProperty("user.dir")
            + SEPARATOR + "res" + SEPARATOR + CONFIG_FILE_NAME;

    private static int min;
    private static int max;
    private static int attempts;

    private Config() { };

    /**
     * Reads from the config file the game settings.
     * @throws IOException 
     */
    public static void readGameSettings() throws IOException {
        try (
            BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                        new FileInputStream(new File(CONFIG_FILE_PATH))));
        ) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                final StringTokenizer st = new StringTokenizer(line, ":");
                final String setting = st.nextToken();
                switch (setting) {
                case "minimum":
                    min = Integer.parseInt(st.nextToken().replace(" ", ""));
                    break;
                case "maximum":
                    max = Integer.parseInt(st.nextToken().replace(" ", ""));
                    break;
                case "attempts":
                    attempts = Integer.parseInt(st.nextToken().replace(" ", ""));
                    break;
                default:
                    System.out.println("NO SETTING FOUND");
                }
            }
        }
    }

    public static void setDefaultSettings() {
        Config.min = 0;
        Config.max = 100;
        Config.attempts = 10;
    }

    /**
     * @return the minimum value
     */
    public static int getMin() {
        return Config.min;
    }

    /**
     * @return the maximum value
     */
    public static int getMax() {
        return Config.max;
    }

    /**
     * @return the attempts count
     */
    public static int getAttempts() {
        return Config.attempts;
    }
}
