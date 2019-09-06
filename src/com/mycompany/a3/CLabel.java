package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Label;

public class CLabel extends Label{                  // label class with set styles

	public CLabel(String text) {
		super(text);
		this.getAllStyles().setFgColor(ColorUtil.LTGRAY);
		this.getAllStyles().setPadding(0,0,4,4);
	}

	public CLabel() {
		this.getAllStyles().setFgColor(ColorUtil.LTGRAY);
		this.getAllStyles().setPadding(0,0,4,4);
	}
}
