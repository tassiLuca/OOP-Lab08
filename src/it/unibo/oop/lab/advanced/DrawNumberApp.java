package it.unibo.oop.lab.advanced;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private final DrawNumber model;
    private final List<DrawNumberView> views = new ArrayList<>();

    /**
     * @param views
     *          the views to attach
     */
    public DrawNumberApp(final DrawNumberView... views) {
        this.views.addAll(Arrays.asList(views));
        for (final DrawNumberView view : views) {
            view.setObserver(this);
            view.start();
        }
        try {
            Config.readGameSettings();
        } catch (IllegalStateException | NumberFormatException | IOException e) {
            for (final DrawNumberView view : views) {
                view.displayError(e.getMessage() + "\nUses the default settings for those incorrect!");
            }
        } 
        this.model = new DrawNumberImpl(Config.getMin(), Config.getMax(), Config.getAttempts());
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view : views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view : views) {
                view.numberIncorrect();
            }
        } catch (AttemptsLimitReachedException e) {
            for (final DrawNumberView view : views) {
                view.limitsReached();
            }
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
     * @throws FileNotFoundException 
     */
    public static void main(final String... args) throws FileNotFoundException {
        new DrawNumberApp(new DrawNumberViewImpl(), 
                new MatchLogView(System.out), 
                new MatchLogView("match.log"));
    }

}
