package de.fiducia.mvvm.test;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
public class MyFallbackUI extends UI {
	private static final long serialVersionUID = 675064289279159925L;

	@Override
    protected void init(VaadinRequest request) {
        setContent(new Label(
                "This app is only designed for mobile webkit based devices"));

    }

}
