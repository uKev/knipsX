package org.knipsX.controller.projectview;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;

import javax.swing.TransferHandler;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by dropping pictureset into the content on the ok button when you want to
 * delete a report.
 * 
 * Acts in harmony with a JProjectView.
 */
public class PictureSetContentListDropController<M extends ProjectModel, V extends JProjectView<M>> extends
        AbstractController<M, V> {

    public PictureSetContentListDropController(M model, V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public ToTransferHandler getToTransferHandler(int action) {
        return new ToTransferHandler(action);
    }

    private class ToTransferHandler extends TransferHandler {

        private static final long serialVersionUID = 3914191965096802498L;

        int action;

        public ToTransferHandler(int action) {
            this.action = action;
        }

        public boolean canImport(TransferHandler.TransferSupport support) {

            /* we'll only support drops */
            if (!support.isDrop()) {
                return false;
            }

            /* we only import Strings */
            if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return false;
            }

            boolean actionSupported = (action & support.getSourceDropActions()) == action;
            if (actionSupported) {
                support.setDropAction(action);
                return true;
            }
            return false;
        }

        public boolean importData(TransferHandler.TransferSupport support) {

            /* if we can't handle the import, say so */
            if (!canImport(support)) {
                return false;
            }

            /* fetch the data and bail if this fails */
            PictureSet data = null;
            try {
                int hashCode = Integer.parseInt((String) support.getTransferable().getTransferData(
                        DataFlavor.stringFlavor));
                for (PictureSet set : PictureSetContentListDropController.this.model.getPictureSets()) {
                    if (set.hashCode() == hashCode) {
                        data = set;
                        break;
                    }
                }
            } catch (UnsupportedFlavorException e) {
                return false;
            } catch (java.io.IOException e) {
                return false;
            }

            PictureSet set = PictureSetContentListDropController.this.model.getSelectedPictureSet();
            PictureSetContentListDropController.this.model.addContentToPictureSet(set, data);
            return true;
        }
    }
}
