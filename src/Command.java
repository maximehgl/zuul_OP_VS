package src;

/**
 * Classe Command - une commande du jeu d'aventure Zuul.
 *
 * @author votre nom
 */
public class Command {
    private String aCommandWord;
    private String aSecondWord;

    public Command(
            final String pCommandWord, final String pSecondWord) {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    }

    public String getCommandWord() {
        return this.aCommandWord;
    }

    public String getSecondWord() {
        return this.aSecondWord;
    }

    public boolean hasSecondWord() {
        if (this.aSecondWord != null) {
            return true;

        } else {
            return false;
        }
    }

    public boolean isUnknown() {
        if (this.aCommandWord == null) {
            return true;

        } else {
            return false;
        }
    }
    // Command
}
