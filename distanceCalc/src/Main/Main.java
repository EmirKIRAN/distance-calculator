package Main;

import View.ControlPanelView;

public class Main {

	public static void main(String[] args) {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		ControlPanelView panel = new ControlPanelView("Distance App", "./images/icons/dialog.png");
		panel.showWindow();
	}

}
