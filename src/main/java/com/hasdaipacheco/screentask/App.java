package com.hasdaipacheco.screentask;

import java.io.IOException;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;

import com.hasdaipacheco.screentask.ui.MainForm;

public class App {
	private static final Logger LOG = Logger.getLogger(App.class.getName());
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) throws SocketException, ParseException {
		/* Set the Nimbus look and feel */
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		//Options options = new Options();
		//options.addOption("headless", false, "run server in headless mode");
		//options.addOption("p", "port", true, "server port");

		//CommandLineParser parser = new DefaultParser();
		//CommandLine cmd = parser.parse(options, args);

		//if (cmd.hasOption("headless")) {
		//	System.out.println("Headless mode");
		//} else {
			try {
				for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
					if ("Nimbus".equals(info.getName())) {
						javax.swing.UIManager.setLookAndFeel(info.getClassName());
						break;
					}
				}
			} catch (ClassNotFoundException ex) {
				LOG.log(Level.SEVERE, null, ex);
			} catch (InstantiationException ex) {
				LOG.log(Level.SEVERE, null, ex);
			} catch (IllegalAccessException ex) {
				LOG.log(Level.SEVERE, null, ex);
			} catch (javax.swing.UnsupportedLookAndFeelException ex) {
				LOG.log(Level.SEVERE, null, ex);
			}

			/* Create and display the form */
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainForm f = new MainForm();
						f.setLocationRelativeTo(null);
						f.setVisible(true);
					} catch (IOException ex) {
						LOG.log(Level.SEVERE, null, ex);
					} catch (URISyntaxException ex) {
						LOG.log(Level.SEVERE, null, ex);
					}
				}
			});
		//}
	}
}
