package de.fiducia.mvvm.test.view;

import java.util.NoSuchElementException;

import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.davherrmann.mvvm.ViewModelComposer;
import de.davherrmann.mvvm.annotations.BindAction;
import de.davherrmann.mvvm.annotations.BindState;
import de.fiducia.mvvm.test.viewmodel.TestViewModel.ChangeCaption;
import de.fiducia.mvvm.test.viewmodel.TestViewModel.LoggedInUser;

public class TestView extends VerticalLayout {
	private static final long serialVersionUID = 2542706496578027047L;
	
	@BindState(LoggedInUser.class)
	private TextField tfLoggedInUser = new TextField("LoggedIn");
	
	private TextField tfChange = new TextField("Change...");
	
	@BindAction(value = ChangeCaption.class, source = {"tfLoggedInUser", "tfChange"})
	private Button button = new Button("Test2");

	public TestView(ViewModelComposer viewModelComposer, Object... viewModels) {
		addComponent(tfLoggedInUser);
		tfChange.setImmediate(true);
		addComponent(tfChange);
		addComponent(button);
		try {
			viewModelComposer.bind(this, viewModels);
		} catch (IllegalAccessException | NoSuchElementException
				| UnsupportedOperationException e) {
			e.printStackTrace();
		}
	}
}
