package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public final class Config {

    private static final int DEFAULT_MIN = 0;
    private static final int DEFAULT_MAX = 100;
    private static final int DEFAULT_ATTEMPTS = 10;

    private static final String CONFIG_FILE_NAME = "config.yml";
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String CONFIG_FILE_PATH = System.getProperty("user.dir")
            + SEPARATOR + "res" + SEPARATOR + CONFIG_FILE_NAME;

    private static int min = DEFAULT_MIN;
    private static int max = DEFAULT_MAX;
    private static int attempts = DEFAULT_ATTEMPTS;

    private Config() { };

    /**
     * Reads from the config file the game settings.
     * @throws FileNotFoundException 
     *          if the settings string does not contain a parsable integer
     * @throws IOException 
     *          if I/O occurs
     */
    public static void readGameSettings() throws FileNotFoundException, IOException  {
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
            Config.areConsistent();
        }
    }

    /**
     * @return true if the settings are consistent. 
     */
    private static void areConsistent() {
        if (min > max || attempts <= 0) {
            min = DEFAULT_MIN;
            max = DEFAULT_MAX;
            attempts = DEFAULT_ATTEMPTS;
            throw new IllegalStateException("Settings are inconsinstent.");
        }
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
