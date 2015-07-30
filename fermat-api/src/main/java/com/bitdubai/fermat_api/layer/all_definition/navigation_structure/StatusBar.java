package com.bitdubai.fermat_api.layer.all_definition.navigation_structure;

import ae.javax.xml.bind.annotation.XmlElement;
import ae.javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Matias
 */
@XmlRootElement(name = "statusBar")
public class StatusBar implements com.bitdubai.fermat_api.layer.all_definition.navigation_structure.interfaces.FermatStatusBar {

    /**
     * StatusBar class member variables
     */
    String color;

    boolean isVisible;

    /**
     * StatusBar class constructors
     */
    public StatusBar() {
    }

    public StatusBar(String color, boolean isVisible) {
        this.color = color;
        this.isVisible = isVisible;
    }

    /**
     * StatusBar class methods
     */
    @Override
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * StatusBar class getters
     */

    @XmlElement
    @Override
    public String getColor() {
        return color;
    }

    /**
     * StatusBar class setters
     */
    @Override
    public void setColor(String color) {
        this.color=color;
    }

    @Override
    public void setVisible(boolean isVisible) {
        this.isVisible=isVisible;
    }
}
