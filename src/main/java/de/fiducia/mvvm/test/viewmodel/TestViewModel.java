package de.fiducia.mvvm.test.viewmodel;

import java.util.Collection;

import de.davherrmann.mvvm.ActionHandler;
import de.davherrmann.mvvm.BasicState;
import de.davherrmann.mvvm.State;
import de.davherrmann.mvvm.annotations.AfterVMBinding;
import de.davherrmann.mvvm.annotations.HandlesAction;
import de.davherrmann.mvvm.annotations.ProvidesState;

public class TestViewModel {
	public interface LoggedInUser extends State<String>{}
	public interface ActivePupils extends State<Collection<String>>{}
	
	public interface ChangeCaption extends ActionHandler{}

	@ProvidesState(LoggedInUser.class)
	public BasicState<String> loggedInUser = new BasicState<String>(
			String.class);
	
	public TestViewModel() {
		loggedInUser.set("TestConstructor..");
	}

	@HandlesAction(ChangeCaption.class)
	public void doChangeCaption(String[] caption) {
		System.out.println("c " + caption);
		for (String string : caption) {
			System.out.println(string);
		}
	}

	@AfterVMBinding
	public void afterVMBinding() {
		loggedInUser.set("Test");
	}
}
