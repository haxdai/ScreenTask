package com.hasdaipacheco.screentask;

import java.io.IOException;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.hasdaipacheco.screentask.ui.MainForm;

public class App {
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) throws SocketException {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm f = new MainForm();
					f.setLocationRelativeTo(null);
					f.setVisible(true);
				} catch (IOException ex) {
					Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
				} catch (URISyntaxException ex) {
					Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}
}
