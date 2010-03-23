package org.knipsX.controller.projectview;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;

import javax.swing.TransferHandler;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by dropping picture set into the content on the ok button when you want to
 * delete a report.
 * 
 * Acts in harmony with a JProjectView.
 * 
 * @param <M>
 *            a model.
 * 
 * @param <V>
 *            a view.
 */
public class PictureSetContentListDropController<M extends ProjectModel, V extends JProjectView<M>> extends
        AbstractController<M, V> {

    /**
     * Creates a new controller which is connected to a view and a model.
     * 
     * @param model
     *            the model.
     * @param view
     *            the view.
     */
    public PictureSetContentListDropController(final M model, final V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
    }

    /**
     * Get the transfer handler.
     * 
     * @param action
     *            the allowed actions.
     * 
     * @return the transfer handler.
     */
    public ToTransferHandler getToTransferHandler(final int action) {
        return new ToTransferHandler(action);
    }

    private class ToTransferHandler extends TransferHandler {

        private static final long serialVersionUID = 3914191965096802498L;

        int action;

        public ToTransferHandler(final int action) {
            this.action = action;
        }

        @Override
        public boolean canImport(final TransferHandler.TransferSupport support) {

            /* we'll only support drops */
            if (!support.isDrop()) {
                return false;
            }

            /* we only import Strings */
            if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return false;
            }

            final boolean actionSupported = (this.action & support.getSourceDropActions()) == this.action;
            if (actionSupported) {
                support.setDropAction(this.action);
                return true;
            }
            return false;
        }

        @Override
        public boolean importData(final TransferHandler.TransferSupport support) {

            /* if we can't handle the import, say so */
            if (!this.canImport(support)) {
                return false;
            }

            /* fetch the data and bail if this fails */
            PictureSet data = null;
            try {
                final int hashCode = Integer.parseInt((String) support.getTransferable().getTransferData(
                        DataFlavor.stringFlavor));
                for (final PictureSet set : PictureSetContentListDropController.this.model.getPictureSets()) {
                    if (set.hashCode() == hashCode) {
                        data = set;
                        break;
                    }
                }
            } catch (final UnsupportedFlavorException e) {
                return false;
            } catch (final java.io.IOException e) {
                return false;
            }

            final PictureSet set = PictureSetContentListDropController.this.model.getSelectedPictureSet();
            PictureSetContentListDropController.this.model.addContentToPictureSet(set, new PictureContainer[] { data });
            return true;
        }
    }
}
