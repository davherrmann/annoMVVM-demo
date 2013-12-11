package de.davherrmann.mvvm.test;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Property.ValueChangeNotifier;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;

import de.davherrmann.mvvm.ActionHandler;
import de.davherrmann.mvvm.ActionWrapper;
import de.davherrmann.mvvm.State;
import de.davherrmann.mvvm.StateChangeListener;
import de.davherrmann.mvvm.StateChangeWrapper;
import de.davherrmann.mvvm.ViewModelComposer;
import de.davherrmann.mvvm.test.view.TestView;
import de.davherrmann.mvvm.test.viewmodel.TestViewModel;

public class MyTouchKitUI extends UI {
	private static final long serialVersionUID = 799973362289793182L;

	@Override
	protected void init(VaadinRequest request) {
		ViewModelComposer viewModelComposer = new ViewModelComposer();

		viewModelComposer.addStateChangeWrapper(AbstractField.class,
				new StateChangeWrapper() {
					@Override
					public StateChangeListener getStateChangeListener(
							final Object notified) {
						return new StateChangeListener() {
							@SuppressWarnings("unchecked")
							@Override
							public void stateChange(Object value) {
								((AbstractField<Object>) notified)
										.setValue(value);
							}
						};
					}
				});

		viewModelComposer.addActionWrapper(ValueChangeNotifier.class,
				new ActionWrapper() {
					@Override
					public void addActionHandler(Object notifier,
							final ActionHandler actionHandler) {
						((ValueChangeNotifier) notifier)
								.addValueChangeListener(new ValueChangeListener() {
									private static final long serialVersionUID = -854400079672018869L;

									@Override
									public void valueChange(
											ValueChangeEvent event) {
										try {
											actionHandler.handle(event
													.getProperty().getValue());
										} catch (UnsupportedOperationException e) {
											System.out.println(e.getMessage());
										}
									}
								});
					}
				});
		
		viewModelComposer.addActionWrapper(Button.class, new ActionWrapper() {
			@Override
			public void addActionHandler(Object notifier, final ActionHandler actionHandler) {
				((Button) notifier).addClickListener(new ClickListener() {
					private static final long serialVersionUID = 3154305342571215268L;

					@Override
					public void buttonClick(ClickEvent event) {
						actionHandler.handle(event.getButton().getData());
					}
				});
			}
		});
		
		viewModelComposer.addStateChangeWrapper(State.class, new StateChangeWrapper() {
			@Override
			public StateChangeListener getStateChangeListener(final Object notified) {
				return new StateChangeListener() {
					@SuppressWarnings("unchecked")
					@Override
					public void stateChange(Object value) {
						((State<Object>) notified).set(value);
					}
				};
			}
		});

		TestViewModel testViewModel = new TestViewModel();
		TestView testView = new TestView(viewModelComposer, testViewModel);

		setContent(testView);
	}

}