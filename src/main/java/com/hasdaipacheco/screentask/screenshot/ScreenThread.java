/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hasdaipacheco.screentask.screenshot;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.hasdaipacheco.screentask.ui.MainForm;

/**
 *
 * @author Ahmad
 */
public class ScreenThread implements Runnable {//extends Thread {
    
        private volatile boolean isRunning = true;
        private int _every;
        private boolean _mousePointer = false;
        
        public ScreenThread(int every,boolean mousePointer){
            _every=every;
            _mousePointer = mousePointer;
        }
        public void run() {
            try {
                while (isRunning) {
                    ScreenShot.takeScreenshot(_every,_mousePointer);
                }
            } catch (IOException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void kill() {
            isRunning = false;
        }
        
        public void mousePointer(boolean mousePointer){
            _mousePointer = mousePointer;
        }
}
