package Agenecy;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class AgencySystemDemo extends Application {
    private static List<Freelancer> freelancers = new ArrayList<>();
    private static List<Company> companies = new ArrayList<>();
    private static User admin = new Admin();
    private List<Freelancer> acceptedFreelancers = new ArrayList<>();
    private List<Freelancer> rejectedFreelancers = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Agency System");

        // Create buttons
        Button freelancerButton = new Button("Register as Freelancer");
        Button companyButton = new Button("Register as Company");
        Button adminButton = new Button("Admin - Manage Connections");
        Button exitButton = new Button("Exit");

        // Set action handlers for the buttons
        freelancerButton.setOnAction(e -> registerFreelancer());
        companyButton.setOnAction(e -> registerCompany());
        adminButton.setOnAction(e -> manageConnections());
        exitButton.setOnAction(e -> System.exit(0));

        // Set button colors using inline CSS
        freelancerButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        companyButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        adminButton.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
        exitButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");

        // Create a horizontal box for the buttons
        HBox buttonLayout = new HBox(10);
        buttonLayout.getChildren().addAll(freelancerButton, companyButton, adminButton, exitButton);
        buttonLayout.setStyle("-fx-padding: 10; -fx-alignment: center;");

        // Create a BorderPane to position the button layout at the top
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(buttonLayout);

        Scene scene = new Scene(mainLayout, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void registerFreelancer() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Register Freelancer");
        window.setMinWidth(300);
        window.setMinHeight(400);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setStyle("-fx-background-color: #F0F4F8; -fx-padding: 20;");

        // Create text fields
        TextField nameField = new TextField();
        TextField workTypeField = new TextField();
        TextField ageField = new TextField();
        TextField gpaField = new TextField();
        TextField departmentField = new TextField();
        TextField emailField = new TextField();
        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll("Male", "Female", "Other");

        Button registerButton = new Button("Register");
        grid.add(new Label("Freelancer Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Work Type:"), 0, 1);
        grid.add(workTypeField, 1, 1);
        grid.add(new Label("Age:"), 0, 2);
        grid.add(ageField, 1, 2);
        grid.add(new Label("GPA:"), 0, 3);
        grid.add(gpaField, 1, 3);
        grid.add(new Label("Department:"), 0, 4);
        grid.add(departmentField, 1, 4);
        grid.add(new Label("Email:"), 0, 5);
        grid.add(emailField, 1, 5);
        grid.add(new Label("Gender:"), 0, 6);
        grid.add(genderComboBox, 1, 6);
        grid.add(registerButton, 1, 7);

        registerButton.setOnAction(e -> {
            try {
                Freelancer worker = createFreelancer(nameField, workTypeField, ageField, gpaField, departmentField, emailField, genderComboBox);
                freelancers.add(worker);
                admin.manageWorkers(worker);
                showAlert("Freelancer registered: " + worker.getName());
                window.close();
            } catch (NumberFormatException ex) {
                showAlert("Please enter valid age and GPA values.");
            }
        });

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();
    }

    private Freelancer createFreelancer(TextField nameField, TextField workTypeField, TextField ageField,
                                         TextField gpaField, TextField departmentField, TextField emailField,
                                        ComboBox<String> genderComboBox) {
        String name = nameField.getText();
        String workType = workTypeField.getText();
        int age = Integer.parseInt(ageField.getText());
        double gpa = Double.parseDouble(gpaField.getText());
        String department = departmentField.getText();
        String email = emailField.getText();
        String gender = genderComboBox.getValue();

        Freelancer worker = new Freelancer(name);
        worker.setWorkType(workType);
        worker.setAge(age);
        worker.setGpa(gpa);
        worker.setDepartment(department);
        worker.setEmail(email);
        worker.setSex(gender);

        return worker;
    }

    private void registerCompany() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Register Company");
        window.setMinWidth(300);
        window.setMinHeight(300);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField nameField = new TextField();
        TextField jobTitleField = new TextField();
        TextField gpaRequirementField = new TextField();
        TextField departmentField = new TextField();
        TextField numberOfPositionsField = new TextField();
        Button registerButton = new Button("Register");

        grid.add(new Label("Company Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Job Title:"), 0, 1);
        grid.add(jobTitleField, 1, 1);
        grid.add(new Label("GPA Requirement:"), 0, 2);
        grid.add(gpaRequirementField, 1, 2);
        grid.add(new Label("Department:"), 0, 3);
        grid.add(departmentField, 1, 3);
        grid.add(new Label("Number of Positions:"), 0, 4);
        grid.add(numberOfPositionsField, 1, 4);
        grid.add(registerButton, 1, 5);

        registerButton.setOnAction(e -> {
            try {
                Company employer = createCompany(nameField, jobTitleField, gpaRequirementField, departmentField, numberOfPositionsField);
                companies.add(employer);
                admin.manageEmployers(employer);
                showAlert("Company registered: " + employer.getName());
                window.close();
            } catch (NumberFormatException ex) {
                showAlert("Please enter valid GPA and number of positions.");
            }
        });

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();
    }

    private Company createCompany(TextField nameField, TextField jobTitleField, TextField gpaRequirementField,
                                   TextField departmentField, TextField numberOfPositionsField) {
        String name = nameField.getText();
        String jobTitle = jobTitleField.getText();
        double gpaRequirement = Double.parseDouble(gpaRequirementField.getText());
        String department = departmentField.getText();
        int numberOfPositions = Integer.parseInt(numberOfPositionsField.getText());

        Company employer = new Company(name);
        employer.postJob(jobTitle, gpaRequirement, department, numberOfPositions);

        return employer;
    }

    private void manageConnections() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Admin Connection Management");
        window.setMinWidth(400);
        window.setMinHeight(300);

        HBox buttonLayout = new HBox(10);

        Button viewFreelancersButton = new Button("View Freelancers");
        viewFreelancersButton.setStyle("-fx-background-color: #A8E6CE; -fx-text-fill: black;");
        
        Button viewCompaniesButton = new Button("View Companies");
        viewCompaniesButton.setStyle("-fx-background-color: #64B5F6; -fx-text-fill: black;");
        
        Button connectButton = new Button("Connect Freelancer to Company");
        connectButton.setStyle("-fx-background-color: #FFAB40; -fx-text-fill: black;");
        
        Button showResultsButton = new Button("Show Accepted/Rejected");
        showResultsButton.setStyle("-fx-background-color: #FFD54F; -fx-text-fill: black;");
        
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #FF6F61; -fx-text-fill: white;");

        viewFreelancersButton.setOnAction(e -> showFreelancers());
        viewCompaniesButton.setOnAction(e -> showCompanies());
        connectButton.setOnAction(e -> connectFreelancerToCompany());
        showResultsButton.setOnAction(e -> showConnectionResults());
        backButton.setOnAction(e -> window.close());

        buttonLayout.getChildren().addAll(viewFreelancersButton, viewCompaniesButton, connectButton, showResultsButton, backButton);
        buttonLayout.setStyle("-fx-padding: 10; -fx-alignment: center;");

        VBox layout = new VBox(10);
        layout.getChildren().addAll(buttonLayout);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: #F0F4F8;");

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    private void showConnectionResults() {
        StringBuilder message = new StringBuilder("Accepted Freelancers:\n");
        for (Freelancer freelancer : acceptedFreelancers) {
            message.append(freelancer.getName()).append("\n");
        }
        message.append("\nRejected Freelancers:\n");
        for (Freelancer freelancer : rejectedFreelancers) {
            message.append(freelancer.getName()).append("\n");
        }
        showAlert(message.toString());
    }

    private void showFreelancers() {
        Stage window = new Stage();
        window.setTitle("Freelancers");

        TableView<Freelancer> tableView = new TableView<>();

        // Create columns
        TableColumn<Freelancer, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        TableColumn<Freelancer, String> workTypeCol = new TableColumn<>("Work Type");
        workTypeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWorkType()));

        TableColumn<Freelancer, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAge()).asObject());

        TableColumn<Freelancer, Double> gpaCol = new TableColumn<>("GPA");
        gpaCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getGpa()).asObject());

        TableColumn<Freelancer, String> departmentCol = new TableColumn<>("Department");
        departmentCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartment()));

        TableColumn<Freelancer, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));

        TableColumn<Freelancer, String> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSex()));

        // Add columns to table
        tableView.getColumns().addAll(nameCol, workTypeCol, ageCol, gpaCol, departmentCol, emailCol, genderCol);
        tableView.getItems().addAll(freelancers);

        VBox layout = new VBox();
        layout.getChildren().addAll(tableView);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    private void showCompanies() {
        Stage window = new Stage();
        window.setTitle("Companies");

        TableView<Company> tableView = new TableView<>();

        // Create columns
        TableColumn<Company, String> nameCol = new TableColumn<>("Company Name");
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        TableColumn<Company, String> jobTitleCol = new TableColumn<>("Job Title");
        jobTitleCol.setCellValueFactory(cellData -> {
            if (cellData.getValue().getJobs().isEmpty()) {
                return new SimpleStringProperty("N/A");
            }
            return new SimpleStringProperty(cellData.getValue().getJobs().get(0).getTitle());
        });

        TableColumn<Company, Double> gpaCol = new TableColumn<>("GPA Requirement");
        gpaCol.setCellValueFactory(cellData -> {
            if (cellData.getValue().getJobs().isEmpty()) {
                return new SimpleDoubleProperty(0.0).asObject();
            }
            return new SimpleDoubleProperty(cellData.getValue().getJobs().get(0).getGpaRequirement()).asObject();
        });

        TableColumn<Company, String> departmentCol = new TableColumn<>("Department");
        departmentCol.setCellValueFactory(cellData -> {
            if (cellData.getValue().getJobs().isEmpty()) {
                return new SimpleStringProperty("N/A");
            }
            return new SimpleStringProperty(cellData.getValue().getJobs().get(0).getDepartment());
        });

        TableColumn<Company, Integer> numberOfPositionsCol = new TableColumn<>("Number of Positions");
        numberOfPositionsCol.setCellValueFactory(cellData -> {
            if (cellData.getValue().getJobs().isEmpty()) {
                return new SimpleIntegerProperty(0).asObject();
            }
            return new SimpleIntegerProperty(cellData.getValue().getJobs().get(0).getNumberOfPositions()).asObject();
        });

        // Add columns to table
        tableView.getColumns().addAll(nameCol, jobTitleCol, gpaCol, departmentCol, numberOfPositionsCol);
        tableView.getItems().addAll(companies); // Add all companies

        VBox layout = new VBox();
        layout.getChildren().addAll(tableView);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    private void connectFreelancerToCompany() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Connect Freelancer to Company");
        window.setMinWidth(400);
        window.setMinHeight(300);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // ListView for freelancers
        ListView<Freelancer> freelancerListView = new ListView<>();
        freelancerListView.getItems().addAll(freelancers);
        
        // ListView for companies
        ListView<Company> companyListView = new ListView<>();
        companyListView.getItems().addAll(companies);

        Button connectButton = new Button("Connect");

        // Add ListView and button to the grid
        grid.add(new Label("Select Company:"), 0, 0);
        grid.add(companyListView, 0, 1);
        grid.add(new Label("Freelancer(s) will be connected automatically."), 0, 2);
        grid.add(connectButton, 0, 3);

        connectButton.setOnAction(e -> {
            // Automatically select the company
            Company selectedCompany = companyListView.getItems().isEmpty() ? null : companyListView.getItems().get(0);

            if (selectedCompany != null) {
                List<Freelancer> suitableFreelancers = new ArrayList<>();
                for (Freelancer freelancer : freelancers) {
                    if (freelancer.getGpa() >= selectedCompany.getJobs().get(0).getGpaRequirement() &&
                        freelancer.getDepartment().equalsIgnoreCase(selectedCompany.getJobs().get(0).getDepartment())) {
                        suitableFreelancers.add(freelancer);
                    }
                }

                // Sort freelancers by GPA in descending order
                suitableFreelancers.sort(Comparator.comparingDouble(Freelancer::getGpa).reversed());

                // Connect freelancers until positions are filled
                Job job = selectedCompany.getJobs().get(0); // Get the first job
                for (Freelancer freelancer : suitableFreelancers) {
                    if (job.getNumberOfPositions() > 0) {
                        job.setNumberOfPositions(job.getNumberOfPositions() - 1);
                        if (!acceptedFreelancers.contains(freelancer)) { // Avoid duplicates
                            acceptedFreelancers.add(freelancer);
                        }
                        showAlert(freelancer.getName() + " has been accepted by " + selectedCompany.getName() + ".");
                    } else {
                        rejectedFreelancers.add(freelancer); // Add to rejected list if no positions are left
                    }
                }

                if (job.getNumberOfPositions() == 0) {
                    showAlert("All available positions filled for " + selectedCompany.getName() + ".");
                }
            } else {
                showAlert("No company selected.");
            }
        });

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();
    }

    private Freelancer findFreelancer(String name) {
        for (Freelancer freelancer : freelancers) {
            if (freelancer.getName().equalsIgnoreCase(name)) {
                return freelancer;
            }
        }
        return null;
    }

    private Company findCompany(String name) {
        for (Company company : companies) {
            if (company.getName().equalsIgnoreCase(name)) {
                return company;
            }
        }
        return null;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}