package org.knipsX.view.projectview;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolTip;

import org.knipsX.model.picturemanagement.Picture;

public class MyToolTipLabel extends JLabel {

    private static final long serialVersionUID = 8488536518262154168L;
    
    private Picture picture;
    
    @Override
    public JToolTip createToolTip() {
        JToolTipWithIcon tip = null;
        final Image bigThumbnail = picture.getBigThumbnail();
        ImageIcon imageIconbigTooltip = new ImageIcon(bigThumbnail);
        tip = new JToolTipWithIcon(imageIconbigTooltip);
        tip.setComponent(tip);
        return tip;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
