package org.knipsX.controller.projectview;

import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by dragging a picture set.
 * 
 * Acts in harmony with a JProjectView.
 * 
 * @param <M>
 *            a model.
 * 
 * @param <V>
 *            a view.
 */
public class PictureSetListDragController<M extends ProjectModel, V extends JProjectView<M>> extends
        AbstractController<M, V> {

    private FromTransferHandler handler = new FromTransferHandler();

    /**
     * Creates a new controller which is connected to a view and a model.
     * 
     * @param model
     *            the model.
     * @param view
     *            the view.
     */
    public PictureSetListDragController(M model, V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    /**
     * Get the TransferHandler.
     * 
     * @return the handler.
     */
    public FromTransferHandler getFromTransferHandler() {
        return this.handler;
    }

    private class FromTransferHandler extends TransferHandler {

        private static final long serialVersionUID = -8735266873682575818L;

        private int index = 0;

        public int getSourceActions(JComponent comp) {
            return FromTransferHandler.COPY;
        }

        /* copies the hashcode so we can find a picture set */
        public Transferable createTransferable(JComponent comp) {
            index = PictureSetListDragController.this.view.getSelectedPictureSetIndex();
            if (index < 0) {
                return null;
            }
            int hashCode = ((PictureSet) PictureSetListDragController.this.view.getSelectedPictureSetValue())
                    .hashCode();
            return new StringSelection("" + hashCode);
        }
    }
}
