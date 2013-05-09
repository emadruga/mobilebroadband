package misc;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;

import javax.swing.table.TableModel;

import org.pentaho.reporting.engine.classic.core.ClassicEngineBoot;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.TableDataFactory;
import org.pentaho.reporting.engine.classic.core.modules.gui.base.PreviewDialog;
import org.pentaho.reporting.libraries.resourceloader.Resource;
import org.pentaho.reporting.libraries.resourceloader.ResourceException;
import org.pentaho.reporting.libraries.resourceloader.ResourceManager;

import tableModel.IFaceTableModel;

public class Report {
	
	public void showChart(IFaceTableModel tableModel, String pathToPrpt) {
		// Load Report and Launch the Preview Dialog
		try {
			ClassicEngineBoot.getInstance().start();
			// load report definition
			ResourceManager manager = new ResourceManager();
			manager.registerDefaults();
			Resource res = manager.createDirectly(new URL("file:"+ pathToPrpt), MasterReport.class);
			MasterReport report = (MasterReport) res.getResource();
			
			//load data
			TableDataFactory factory = new TableDataFactory();
			factory.addTable("default", (TableModel) tableModel);
			report.setDataFactory(factory);
			
			//showing
			final PreviewDialog preview = new PreviewDialog(report);
			preview.addWindowListener(new WindowAdapter() {
				
				public void windowClosing(final WindowEvent event) {
					preview.setVisible(false);
				}
			});
			preview.pack();
			preview.setVisible(true);
			
		} catch (ResourceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
