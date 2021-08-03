package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField address;
    @FXML
    private ChoiceBox<String> choiceSpeed;
    @FXML
    private ChoiceBox<String> choicebBandwidth;
    @FXML
    private ChoiceBox<String> choicebContract;


    ObservableList<User> persons = FXCollections.<User>observableArrayList();
    User person;

    @FXML
    TableView<User> table = new TableView<>();


    @FXML
    private void initialize() {
        person = new User();

        firstName.textProperty().bindBidirectional(person.firstNameProperty());
        lastName.textProperty().bindBidirectional(person.lastNameProperty());
        address.textProperty().bindBidirectional(person.addressProperty());
        
        choiceSpeed.valueProperty().bindBidirectional(person.speedProperty());
        choiceSpeed.getItems().addAll("2 Mbit", "5 Mbit", "10 Mbit", "20 Mbit", "50 Mbit", "100 Mbit");
        choiceSpeed.setValue("20 Mbit");
        
        choicebBandwidth.valueProperty().bindBidirectional(person.bandwidthProperty());
        choicebBandwidth.getItems().addAll("1 GB", "5 GB", "10 GB", "100 GB", "Flat");
        choicebBandwidth.setValue("10 GB");
        
        choicebContract.valueProperty().bindBidirectional(person.contractProperty());
        choicebContract.getItems().addAll("1 year", "2 years");
        choicebContract.setValue("2 years");
    }

    @FXML
    private void addPerson() {
        persons = table.getItems();
        if ((person.getFirstName().trim().equals(""))
            || (person.getLastName().trim().equals(""))
            || (person.getAddress().trim().equals(""))
            || (person.getSpeed() == null)
            || (person.getBandwidth() == null)
            || (person.getContract() == null)) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("A field was left empty");
            alert.setHeaderText("You must provide all of the required info!");
            alert.showAndWait();
            
        } else {
            persons.add(new User(firstName.getText(), lastName.getText(),
            address.getText(),
            choiceSpeed.getSelectionModel().getSelectedItem(),
            choicebBandwidth.getSelectionModel().getSelectedItem(),
            choicebContract.getSelectionModel().getSelectedItem()));
            table.setItems(persons);
            resetPerson();
        }
    }

    @FXML
    private void resetPerson() {        
        person.firstNameProperty().set("");
        person.lastNameProperty().set("");
        person.addressProperty().set("");
//        person.speedProperty().set("20 Mbit");
//        person.bandwidthProperty().set("10 GB");
//        person.contractProperty().set("2 years");
    }

    @FXML
    private void deletePerson() {
        persons = table.getItems();
        int index = table.selectionModelProperty().getValue().getSelectedIndex();
        if (index != -1) {
            persons.remove(index);
            table.setItems(persons);
            
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nothing was selected");
            alert.setHeaderText("Plese select a user to delete first.");
            alert.showAndWait();
        }

    }

}