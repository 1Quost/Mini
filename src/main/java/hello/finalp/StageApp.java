package hello.finalp;

//All the imports
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.util.Duration;

import java.time.LocalDate;


public class StageApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    //Part1:
    @Override
    public void start(Stage primaryStage) {
        //____________________________________

        //STAGE1

        //stage title
        primaryStage.setTitle("Sign in");
        primaryStage.setResizable(false);           //make the stage fix unresizable
        GridPane g = new GridPane();//create a gride pane
        g.setAlignment(Pos.CENTER);//aligning all the gridpane to the center
        //set the vertical and horizontal gap
        g.setVgap(10); //space between each line
        g.setHgap(5); //space ex between username and the textfield
        g.setPadding(new Insets(25, 0, 25, 0));
        //gridPane.setPadding(new Insets(top, right, bottom, left));

        //Image
        ImageView headerIcon = new ImageView(new Image("https://cdn-icons-png.flaticon.com/128/149/149071.png"));
        headerIcon.setFitWidth(50);//adjustWidth of the icon
        headerIcon.setFitHeight(50);//adjust height of the icon
        g.setHalignment(headerIcon, javafx.geometry.HPos.CENTER);//position the image in the center
        g.add(headerIcon, 0, 0, 3, 1); //add the image to the grid

        //-------------------------------------------------------------------------------------------------------
        //Label title
        Label title = new Label("🔐 MEMBER LOGIN");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setTextFill(Color.DARKRED);//title text-color
        //title.setTextAlignment(TextAlignment.CENTER);//positioning the text in the center
        g.setHalignment(title, javafx.geometry.HPos.CENTER);//positioning the text in the center
        g.add(title, 0, 1, 3, 1);
        //g.add(Label,Column index,Row index,Column span,Row span)

        //------------------------------------------------------------------------------------------------

        //The username:
        Label userName = new Label("UserName:");
        g.add(userName, 1, 2);

        //userName box:
        TextField textField = new TextField();
        textField.setPromptText("👤 Enter Username");
        textField.setStyle("-fx-background-radius: 300px; -fx-padding: 2px;");
        userName.setAlignment(Pos.CENTER_RIGHT);// remove it will not change anything
        //ading the box to the grid
        g.add(textField, 2, 2);

        //-------------------------------------------------------------------------------------------------------
        //The pass:
        Label pw = new Label("Password:");
        g.add(pw, 1, 3);
        //adding the text (Label) Password to the grid

        PasswordField pwField = new PasswordField();
        pwField.setPromptText("🔑 Enter Password");
        pwField.setStyle("-fx-background-radius: 150px; -fx-padding: 2px;");
        pw.setAlignment(Pos.CENTER_RIGHT);
        //node.setStyle("-fx-background-radius: Xpx; -fx-padding: Ypx;");
        //Xpx: Controls the corner rounding of the background.
        //Ypx:  Sets the padding inside the node.
        //------------------------------------
        //adding the box to the grid

        g.add(pwField, 2, 3);
        //-------------------------------------------------------------------------------------------------------

        //STAGE1 BUTTON

        //login Button
        Button loginButton = new Button("Login 🚀");
        loginButton.setStyle("-fx-background-color: #eb8bb1; -fx-text-fill: purple; -fx-font-size: 14px; -fx-background-radius: 12px; -fx-padding: 7px;");
        loginButton.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> loginButton.setStyle("-fx-background-color: purple; -fx-text-fill: white;"));
        loginButton.addEventHandler(MouseEvent.MOUSE_EXITED, e -> loginButton.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 100%, rgba(250,100,202,1) 0%, rgba(150,200,233,1) 100%);" +
                " -fx-text-fill: purple; -fx-background-radius: 15px;"));
        g.setStyle("-fx-background-color: linear-gradient(to bottom right, #f95050, #ed337a);");

        g.add(loginButton, 1, 4);

        //-------------------------------------------------------------------------------------------------------

        //_____________________________

        //STAGE2 SCENE1


        // Loading screen elements
        VBox loadingScreen = new VBox(20);
        loadingScreen.setAlignment(Pos.CENTER); //position center
        ProgressIndicator progressIndicator = new ProgressIndicator(); //use a control ui progress indicator
        Text loadingText = new Text("Loading...");
        loadingText.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        loadingScreen.getChildren().addAll(progressIndicator, loadingText);
        loadingScreen.setStyle("-fx-background-color: linear-gradient(to bottom right, #833ab4, #c81111);");

        //-------------------------------------------------------------------------------------------------------


        //Stage1 Scene1
        Scene scene = new Scene(g, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        //Stage2 scene1
        Scene loadingScene = new Scene(loadingScreen, 400, 400);

        //2nd Stage
        Stage s=new Stage();
        s.setTitle("Employee Table:");
        s.setX(primaryStage.getX()+50);
        s.setY(primaryStage.getY()+50);

        //-------------------------------------------------------------------------------------------------------


        //STAGE2 SCENE2
        //Making a tabPane():

        TabPane tabPane =new TabPane();
        //TAB1 SCENE1

        //tab1 informations
        GridPane gtab1=new GridPane();
        gtab1.setHgap(10);
        gtab1.setVgap(10);
        gtab1.setPadding(new Insets(10));
        gtab1.setMinWidth(400);
        gtab1.setMinHeight(300);
        gtab1.setStyle("-fx-background-color: linear-gradient(to bottom right,  #833ab4, #c81111);");


        //-------------------------------------------------------------------------------------------------------


        Tab tab1 =new Tab("Employee Data",gtab1);
        //first control ui
        //USERNAME:k

        Text titletab1 =new Text("Employee");
        titletab1.setFont(Font.font("Tahoma",FontWeight.NORMAL,32));
        gtab1.add(titletab1,0,0,2,1);


        Label subtitle=new Label("Personal informations:");
        subtitle.setFont(Font.font("Tahona",FontWeight.NORMAL,20));
        gtab1.add(subtitle,0,1,2,2);

        Label name =new Label("FullName: ");
        gtab1.add(name,0,3);

        TextField textField1=new TextField();
        textField1.setPromptText("Enter your full name");
        gtab1.add(textField1,1,3);

        //-------------------------------------------------------------------------------------------------------

        //Age
        Label age=new Label("Age:");
        Spinner<Integer> stab = new Spinner<Integer>();
        int intialValue=1;
        SpinnerValueFactory<Integer> valueFactory=//
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,intialValue);
        stab.setValueFactory(valueFactory);

        gtab1.add(age,0,4);
        gtab1.add(stab,1,4);

        //Nationality
        Label nat=new Label("🌎Nationality: ");
        gtab1.add(nat,0,6);
        String [] array={"Lebanese","American","British","Canadian", "Australian", "Chinese", "Indian", "German","French", "Italian",  "Spanish",
                "Japanese", "Russian", "Brazilian", "Mexican", "Saudi Arabian","South African", "Egyptian","Turkish", "South Korean"
        };
        ComboBox comb=new ComboBox<>(FXCollections.observableArrayList(array));
        gtab1.add(comb,1,6);

        //Date of birth
        Label date=new Label("BirthDate: ");
        gtab1.add(date,0,7);
        DatePicker dtab1=new DatePicker();
        dtab1.setValue(LocalDate.now());
        gtab1.add(dtab1,1,7);

        //Gender:
        Label gendtab1 =new Label("Gender");
        gtab1.add(gendtab1,0,5);

        RadioButton ratb1=new RadioButton("Male");
        ratb1.setSelected(true);
        RadioButton ratb2= new RadioButton("Female");
        ToggleGroup radioGroup= new ToggleGroup();
        ratb1.setToggleGroup(radioGroup);
        ratb2.setToggleGroup(radioGroup);
        HBox ratioBox = new HBox(ratb1,ratb2);
        ratioBox.setSpacing(25);
        gtab1.add(ratioBox,1,5);


        Button Next1= new Button("Next!");
        gtab1.add(Next1,4,8,2,3);


        //TAB1 SCENE2

        GridPane g2tab1=new GridPane();
        g2tab1.setHgap(10);
        g2tab1.setVgap(10);
        g2tab1.setPadding(new Insets(10));
        g2tab1.setMinWidth(200);
        g2tab1.setMinHeight(100);
        g2tab1.setStyle("-fx-background-color: linear-gradient(to bottom right,  #833ab4, #c81111);");

        Scene s2tab1 =new Scene(g2tab1,400,300);
        Next1.setOnAction(evt-> {
            Next1.setStyle("-fx-background-color: #eb8bb1; -fx-text-fill: purple; -fx-font-size: 14px; -fx-background-radius: 12px; -fx-padding: 7px;");
            Next1.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> Next1.setStyle("-fx-background-color: purple; -fx-text-fill: white;"));
            Next1.addEventHandler(MouseEvent.MOUSE_EXITED, e -> Next1.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 100%, rgba(250,100,202,1) 0%, rgba(150,200,233,1) 100%);" +
                    " -fx-text-fill: purple; -fx-background-radius: 15px;"));

            tab1.setContent(g2tab1);


        });


        //TAB1 SCENE3 GRID
        GridPane g3tab1=new GridPane();
        g3tab1.setHgap(10);
        g3tab1.setVgap(10);
        g3tab1.setPadding(new Insets(10));
        g3tab1.setMinWidth(200);
        g3tab1.setMinHeight(100);
        g3tab1.setStyle("-fx-background-color: linear-gradient(to bottom right,  #833ab4, #c81111);");




        Button Next2= new Button("Next!");
        g2tab1.add(Next2,4,8,2,3);
        Next2.setOnAction(evt-> {
            Next2.setStyle("-fx-background-color: #eb8bb1; -fx-text-fill: purple; -fx-font-size: 14px; -fx-background-radius: 12px; -fx-padding: 7px;");
            Next2.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> Next2.setStyle("-fx-background-color: purple; -fx-text-fill: white;"));
            Next2.addEventHandler(MouseEvent.MOUSE_EXITED, e -> Next2.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 100%, rgba(250,100,202,1) 0%, rgba(150,200,233,1) 100%);" +
                    " -fx-text-fill: purple; -fx-background-radius: 15px;"));

            tab1.setContent(g3tab1);


        });


        Button Back1=new Button("Previous!");
        g2tab1.add(Back1,0,8,2,3);

        Back1.setOnAction(evt-> {
            tab1.setContent(gtab1);
        });


        //TAB1 SCENE2
        Text title2tab1 =new Text("Employee");
        title2tab1.setFont(Font.font("Arial",FontWeight.NORMAL,32));
        g2tab1.add(title2tab1,0,0,2,1);

        Label subtitle2=new Label("Contact informations:");
        subtitle2.setFont(Font.font("Arial",FontWeight.NORMAL,22));
        g2tab1.add(subtitle2,0,1,2,2);


        Label telLabel = new Label("Phone number:");
        TextField areaCodeField = new TextField();
        areaCodeField.setPromptText("Area");
        areaCodeField.setMaxWidth(50);
        g2tab1.add(telLabel, 0, 3);

        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("📞Phone number");
        phoneNumberField.setMaxWidth(150);

        HBox phoneBox = new HBox(5, areaCodeField, phoneNumberField); // 5px spacing
        phoneBox.setAlignment(Pos.CENTER_LEFT);
        g2tab1.add(phoneBox, 1, 3);


        Label add=new Label("Address:");
        g2tab1.add(add,0,5);

        TextField addfi=new TextField();
        addfi.setPromptText("Enter your address:");
        g2tab1.add(addfi,1,5);

        Label email=new Label("Email:");
        g2tab1.add(email,0,7);

        TextField emfi =new TextField();
        emfi.setPromptText("✉Enter your email:");
        g2tab1.add(emfi,1,7);



        //TAB1 SCENE3:

        Text title3tab1 =new Text("Employee");
        title3tab1.setFont(Font.font("Arial",FontWeight.NORMAL,32));
        g3tab1.add(title3tab1,0,0,2,1);


        Label sub3title =new Label("Work informations:");
        sub3title.setFont(Font.font("Arial",FontWeight.NORMAL,20));
        g3tab1.add(sub3title,0,1,2,2);

        Label positionLabel = new Label("Position:");
        ChoiceBox<String> positionChoice = new ChoiceBox<>();
        positionChoice.getItems().addAll("Manager", "Developer", "Designer", "Tester");
        g3tab1.add(positionLabel, 0, 3);
        g3tab1.add(positionChoice, 1, 3);

        Label time =new Label("Full/Part ?");
        g3tab1.add(time,0,4);

        ToggleGroup t=new ToggleGroup();
        CheckBox part=new CheckBox("Part-timer");

        CheckBox full=new CheckBox("Full-Timer");
        HBox h= new HBox(10,part,full);
        g3tab1.add(h,1,4);

        Label sal=new Label("Salary per-month");
        g3tab1.add(sal,0,5);

        Slider sss=new Slider(50,1000,50);
        sss.setBlockIncrement(100);
        sss.getValue();
        g3tab1.add(sss,1,5);

        Label startd=new Label("Start Date: ");
        g3tab1.add(startd,0,7);

        DatePicker sdtab1=new DatePicker();
        sdtab1.setValue(LocalDate.now());
        g3tab1.add(sdtab1,1,7);



        Button Back2=new Button("Previous!");
        g3tab1.add(Back2,0,8,2,3);

        Back2.setOnAction(evt-> {
            tab1.setContent(g2tab1);
        });



        Button addemp = new Button("➕ Employee.");
        g3tab1.add(addemp, 2, 8, 3, 4);


        //tab2 informations:

        GridPane gtab2=new GridPane();
        gtab2.setHgap(10);
        gtab2.setVgap(10);
        gtab2.setPadding(new Insets(10));
        gtab2.setMinWidth(200);
        gtab2.setMinHeight(300);
        gtab2.setStyle("-fx-background-color: linear-gradient(to bottom right,  #833ab4, #c81111);");


        //_______________________
        //Tab2 THE TABLE

        Tab tab2 =new Tab("The Table",gtab2);

// TableView for Employees
        TableView<Employee> employeeTable = new TableView<>();
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();



// Define columns
        TableColumn<Employee, String> fullNameColumn = new TableColumn<>("Full Name");
        fullNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFullName()));
        fullNameColumn.setMinWidth(100);


        TableColumn<Employee, String> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAge()));

        TableColumn<Employee, String> nationalityColumn = new TableColumn<>("Nationality");
        nationalityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNationality()));

        TableColumn<Employee, String> birthDateColumn = new TableColumn<>("Birth Date");
        birthDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBirthDate()));

        TableColumn<Employee, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGender()));

        TableColumn<Employee, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));

        TableColumn<Employee, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));

        TableColumn<Employee, String> positionColumn = new TableColumn<>("Position");
        positionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPosition()));

        TableColumn<Employee, String> workTypeColumn = new TableColumn<>("Work Type");
        workTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWorkType()));

        TableColumn<Employee, String> startDateColumn = new TableColumn<>("Start Date");
        startDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStartDate()));

        TableColumn<Employee, String> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSalary()));

// Add columns to table
        employeeTable.getColumns().addAll(fullNameColumn, ageColumn, nationalityColumn, birthDateColumn, genderColumn, phoneColumn,
                emailColumn, positionColumn, workTypeColumn,
                startDateColumn, salaryColumn);

        //  employeeTable.setItems(employeeList);
        System.out.println(employeeList);

// Layout for Table Scene
        VBox tableLayout = new VBox(10, new Label("Employee Records:"), employeeTable);
        tableLayout.setPadding(new Insets(10));

        tab2.setContent(tableLayout);

        //succ mess
        Text successMsg = new Text();
        successMsg.setFill(Color.AQUA);
        g3tab1.add(successMsg,1,12,2,1);


// Update "Add Employee" Button to Store Data in Table
        addemp.setOnAction(evt -> {
            String fullName = textField1.getText();
            String age2 = String.valueOf(stab.getValue());
            String nationality = (String) comb.getValue();
            String birthDate = dtab1.getValue().toString();
            String gender = ratb1.isSelected() ? "Male" : "Female";
            String phoneNumber = areaCodeField.getText() + phoneNumberField.getText();
            String email1 = emfi.getText();
            String position = positionChoice.getValue();
            String workType = part.isSelected() ? "Part-time" : "Full-time";
            String startDate = sdtab1.getValue().toString();
            String salary = String.valueOf(sss.getValue());

            if (fullName.isEmpty() || nationality==null || email1.isEmpty() || phoneNumber.isEmpty() || position==null || workType==null ) {
                successMsg.setFill(Color.RED);
                successMsg.setText("Please fill all the details.");
                return;
            } else {
                Employee newEmployee = new Employee(fullName,age2 , nationality, birthDate, gender,
                        phoneNumber,email1,position,workType,startDate,salary);
                employeeList.add(newEmployee);
                successMsg.setFill(Color.GREEN);
                successMsg.setText("Employee added successfully!");
                employeeTable.setItems(employeeList);


                // Reset input fields
                textField1.clear(); // Clear the full name field
                stab.getValueFactory().setValue(1); // Reset Spinner to initial value (1)
                comb.getSelectionModel().clearSelection(); // Clear ComboBox selection
                dtab1.setValue(LocalDate.now()); // Reset DatePicker to today
                ratb1.setSelected(true); // Reset RadioButton to Male
                areaCodeField.clear(); // Clear area code field
                phoneNumberField.clear(); // Clear phone number field
                emfi.clear(); // Clear email field

                positionChoice.getSelectionModel().clearSelection(); // Clear ChoiceBox selection
                part.setSelected(false); // Reset Part-time CheckBox
                full.setSelected(false); // Reset Full-time CheckBox
                sss.setValue(50); // Reset Slider to initial value (50)
                sdtab1.setValue(LocalDate.now()); // Reset Start Date to today

            }



        });





        //___________________________
        //tab3 informations:

        Tab tab3 =new Tab("Some Statistics",new Label("STAY TUNED COMING SOON!!! 🔜"));

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);

        VBox vBox = new VBox(tabPane);
        Scene s3 = new Scene(vBox);




        //error message
        Text errorMsg = new Text();
        errorMsg.setFill(Color.AQUA);
        g.add(errorMsg, 1, 5, 2, 1);
        //g.add(Label,Column index,Row index,Column span,Row span)

        //-------------------------------------------------------------------------------------------------------
        // Login button action
        loginButton.setOnAction(evt -> {
            String u = textField.getText();
            String p = pwField.getText();

            if (u.isEmpty() || p.isEmpty()) {
                errorMsg.setText("Username and password are required!");


            } else if (!u.equals("TheBoss") || !p.equals("162816")) {
                errorMsg.setText("Incorrect username and/or password");
            } else {
                s.setScene(loadingScene); // Show loading screen   //s is the second stage

                // Pause for 2 seconds, then switch to main scene
                PauseTransition pause = new PauseTransition(Duration.seconds(1));//pause transition to make the loading scene longer
                pause.setOnFinished(event -> s.setScene(s3));//after finishing the pause i am setting the stage scene to mainScreen that is the 2nd scene
                pause.play();
                s.show();

            }
        });




    }
}




// Employee Class
class Employee {
    private String fullName;
    private String age;
    private String nationality;
    private String birthDate;
    private String gender;
    private String phoneNumber;
    private String email;
    private String position;
    private String workType;
    private String startDate;
    private String salary;

    public Employee(String fullName, String age, String nationality, String birthDate, String gender,
                    String phoneNumber, String email, String position, String workType, String startDate, String salary) {
        this.fullName = fullName;
        this.age = age;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.position = position;
        this.workType = workType;
        this.startDate = startDate;
        this.salary = salary;
    }

    public String getFullName() { return fullName; }
    public String getAge() { return age; }
    public String getNationality() { return nationality; }
    public String getBirthDate() { return birthDate; }
    public String getGender() { return gender; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getPosition() { return position; }
    public String getWorkType() { return workType; }
    public String getStartDate() { return startDate; }
    public String getSalary() { return salary; }
}

