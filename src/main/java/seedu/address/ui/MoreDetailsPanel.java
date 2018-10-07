package seedu.address.ui;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.address.model.person.AssignmentStub;
import seedu.address.model.person.Person;

/**
 * The More Details Panel of the App.
 */
public class MoreDetailsPanel extends UiPart<Region> {

    /*
    public static final String DEFAULT_PAGE = "default.html";
    public static final String SEARCH_PAGE_URL =
            "https://se-edu.github.io/addressbook-level4/DummySearchPage.html?name=";
    */

    private static final String FXML = "MoreDetailsPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    // Initializing test data
    private AssignmentStub[] assignments = {new AssignmentStub("Finals Exam", 73), new AssignmentStub("Mid-terms", 39),
            new AssignmentStub("Participation", 7), new AssignmentStub("Assignment 1", 22)};

    @FXML
    private TextArea notesText;

    // Left side holds component name, eg. Participation, right side holds mark attained.
    @FXML
    private GridPane components;

    public MoreDetailsPanel() {
        super(FXML);

        // if student has no recorded assignments
        if (false) {// student no assignments){
            // default label
            Label noComponents = new Label("<No assignments entered>");
            noComponents.setFont(new Font("System", (double)25));
            components.add(noComponents, 0, 0);
        } else {
            // add 2 columns, default has 1
            ColumnConstraints newColumn = new ColumnConstraints();
            components.getColumnConstraints().add(newColumn);

            RowConstraints newRow = new RowConstraints();
            components.getRowConstraints().add(newRow);

            // add no. of rows equal to no. of assignments keyed in
            for (int i = 0; i < Array.getLength(assignments); i++){
                Label toAdd = new Label(assignments[i].getName());
                toAdd.setStyle("-fx-font-size: 11pt;\n" + "-fx-font-family: \"Segoe UI Semibold\";\n" +
                        "-fx-text-fill: white;\n" + "-fx-opacity: 1;");
                components.add(toAdd, 0, i);
                Label marksLabel = new Label(Float.toString(assignments[i].getMarks()));
                marksLabel.setStyle("-fx-font-size: 11pt;\n" + "-fx-font-family: \"Segoe UI Semibold\";\n" +
                        "-fx-text-fill: white;\n" + "-fx-opacity: 1;");
                components.add(marksLabel, 1, i);
            }
        }

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);

        /*
        loadDefaultPage();
        registerAsAnEventHandler(this);
        */
    }

    /*
    private void loadPersonPage(Person person) {
        loadPage(SEARCH_PAGE_URL + person.getName().fullName);
    }

    public void loadPage(String url) {
        Platform.runLater(() -> browser.getEngine().load(url));
    }

    /**
     * Loads a default HTML file with a background that matches the general theme.
     */
    /*
    private void loadDefaultPage() {
        URL defaultPage = MainApp.class.getResource(FXML_FILE_FOLDER + DEFAULT_PAGE);
        loadPage(defaultPage.toExternalForm());
    }
    */

    /**
     * Frees resources allocated to the browser.
     */
    /*
    public void freeResources() {
        browser = null;
    }*/
    /*
    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadPersonPage(event.getNewSelection());
    }
    */
}