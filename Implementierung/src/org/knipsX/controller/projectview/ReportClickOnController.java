package org.knipsX.controller.projectview;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.UIManager;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.projectview.JProjectView;
import org.knipsX.view.reportmanagement.AbstractReportCompilation;
import org.knipsX.view.reportmanagement.JReportConfig;

/**
 * Represents the Actions which are done by klicking on open report.
 * Acts in harmony with a JProjectView.
 */
public class ReportClickOnController<M extends ProjectModel, V extends JProjectView<M>> extends
        AbstractController<M, V> implements MouseListener {

    private final int MOUSE_LEFT = 1;

    public ReportClickOnController(M model, V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }


    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MOUSE_LEFT) {
            if (mouseEvent.getClickCount() == 2) {
                UIManager.put("TabbedPane.contentAreaColor", new Color(238, 238, 238));

                if (this.view.getSelectedReports().length > 0) {
                    new JReportConfig<AbstractReportModel, AbstractReportCompilation>((AbstractReportModel) this.model
                            .getReports()[this.view.getSelectedReports()[0]], this.view.getSelectedReports()[0]);
                }
            }
        }
    }


    public void mouseEntered(MouseEvent arg0) {
    }


    public void mouseExited(MouseEvent arg0) {
    }


    public void mousePressed(MouseEvent arg0) {
    }


    public void mouseReleased(MouseEvent arg0) {
    }
}
