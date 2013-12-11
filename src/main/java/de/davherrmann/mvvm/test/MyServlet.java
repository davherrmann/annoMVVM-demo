package de.davherrmann.mvvm.test;

import javax.servlet.ServletException;

import com.vaadin.addon.touchkit.server.TouchKitServlet;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;

public class MyServlet extends TouchKitServlet {
	private static final long serialVersionUID = 3889413958476144052L;
	private MyUIProvider uiProvider = new MyUIProvider();
    
    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        getService().addSessionInitListener(new SessionInitListener() {
			private static final long serialVersionUID = 1163740613748797311L;

			@Override
            public void sessionInit(SessionInitEvent event) throws ServiceException {
                event.getSession().addUIProvider(uiProvider);
            }
        });
    }

}
