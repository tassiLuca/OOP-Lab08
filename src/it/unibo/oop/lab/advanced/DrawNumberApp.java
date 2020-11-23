package it.unibo.oop.lab.advanced;

import java.io.IOException;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private final DrawNumber model;
    private final DrawNumberView view;

    /**
     * 
     */
    public DrawNumberApp() {
        this.view = new DrawNumberViewImpl();
        try {
            Config.readGameSettings();
        } catch (NumberFormatException | IOException e) {
            this.view.displayError(e.getMessage() + "\nUses the default settings");
            Config.setDefaultSettings();
        }
        this.model = new DrawNumberImpl(Config.getMin(), 
                Config.getMax(), Config.getAttempts());
        this.view.setObserver(this);
        this.view.start();
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.view.result(result);
        } catch (IllegalArgumentException e) {
            this.view.numberIncorrect();
        } catch (AttemptsLimitReachedException e) {
            view.limitsReached();
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp();
    }

}
