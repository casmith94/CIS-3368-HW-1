package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ListView<Employee> employeeListView;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private CheckBox isActiveCheckBox;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
    @FXML
    private Button clearButton;

    private Employee currentEmp;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        employeeListView.getSelectionModel().selectedItemProperty().addListener((
                        ObservableValue<? extends Worker> ov, Worker old_val, Worker new_val)->{
            Worker selectedItem = employeeListView.getSelectionModel().getSelectedItem();
            firstNameTextField.setText(((Employee)selectedItem).firstName);
            lastNameTextField.setText(((Employee)selectedItem).lastName);
            isActiveCheckBox.setSelected(((Employee)selectedItem).isActive);
                }

                );
        clearButton.setOnAction(new EventHandler<>(){
            public void handle (ActionEvent event) {
                firstNameTextField.setText("");
                lastNameTextField.setText("");
                isActiveCheckBox.setSelected(false);
            }

                                });
        deleteButton.setOnAction(new EventHandler<>(){
            public void handle (ActionEvent event){
                int index = employeeListView.getSelectionModel().getSelectedIndex();
                if (index >= 0){
                employeeListView.getItems().remove(index);
            }
        }
                                 });
        addButton.setOnAction(new EventHandler<>(){
            public void handle (ActionEvent event){
                ObservableList<Employee> items = employeeListView.getItems();
                Employee currentEmp = new Employee();
                currentEmp.firstName = firstNameTextField.getText();
                currentEmp.lastName = lastNameTextField.getText();
                currentEmp.isActive = isActiveCheckBox.isSelected();
                items.add(currentEmp);
            }
        });

        ObservableList<Employee> items = employeeListView.getItems();
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        employee1.firstName = "Robert";
        employee1.lastName = "Smith";
        employee2.firstName = "Lisa";
        employee2.lastName = "Smith";

        items.add(employee1);
        items.add(employee2);

        for(int i = 0; i<10; i++){
            Employee employee = new Employee();
            employee.firstName = "Generic";
            employee.lastName = "Employee" + " " + i;
            employee.hire();
            items.add(employee);
        }
        Staff staff1 = new Staff();
        staff1.firstName = "StaffPerson";
        staff1.lastName = "GoodWorker";

        Faculty faculty1 = new Faculty();
        faculty1.firstName = "FacultyPerson";
        faculty1.lastName = "TerribleWorker";

        items.add(staff1);
        items.add(faculty1);

    }
}
