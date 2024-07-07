package View.Menu.StorePanel;

import Models.Constant;
import Models.Epsilon.Epsilon;

public class StorePanelLogic {
    Epsilon epsilon;
    Constant constant;
    private long lastPhonoiSlaughter = 0;

    public StorePanelLogic(Constant constant, Epsilon epsilon){
        this.epsilon = epsilon;
        this.constant = constant;
    }

    public long getLastPhonoiSlaughter() {
        return lastPhonoiSlaughter;
    }

    public void setLastPhonoiSlaughter(long lastPhonoiSlaughter) {
        this.lastPhonoiSlaughter = lastPhonoiSlaughter;
    }

}


