package youth;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;



import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class YouthController implements Initializable {


    public FileInputStream fileInputStream;
    public FileChooser fileChooser;

    public File file;

    public Stage stage = new Stage();



    public Label label;

    Connection conn;
    public TextField txtsurname, txtnames,txtemail,txtoccupation,txtcontact, txtnationality,txtid;

    public Button btnsave,imagebtn,viewbtn,searchbtn,searchall,printbtn;

    public Image image;
    public ImageView imageView;

    @FXML
    public DatePicker datePicker;

    @FXML
            public ComboBox<String> comboBox, comboBox1;

    @FXML
    public ComboBox<String> combodistrict,comboelder,combofamily,combomember,comboadm,combocomm;

    public Button nextbtn, savebtn;

    public TextField txtname,txtrelation ,txtsearch,txtcontact1;

    @FXML
    public TableView<Members> tableView;

    @FXML
    private TableColumn<Members, Integer> id_no;

    @FXML
    private TableColumn<Members, String> surname;
    @FXML
    private TableColumn<Members, String> names;


    @FXML
    private TableColumn<Members, Integer> contact;
    @FXML
    private TableColumn<Members, String> fam;
    @FXML
    private TableColumn<Members, String> gender;
    @FXML
    private TableColumn<Members, String> full_member;
    @FXML
    private TableColumn<Members, String> adm;
    @FXML
    private TableColumn<Members, String> commissioned;
    @FXML
    private TableColumn<Members, String> dist;


    ObservableList<String> stat = FXCollections.observableArrayList("Single","Married");
    ObservableList<String> gend = FXCollections.observableArrayList("Male","Female");
    ObservableList<String> district = FXCollections.observableArrayList("I don't Know","Amani","New Mwanzo","Emmanuel","Gateway","Hope","Agape","Milimani","Tumaini","Ebenezer","Unity","Upperhill","New Valley","Baraka","Upendo","Mwangaza","Central","New Plaza","Bethel");
    ObservableList<String> elders = FXCollections.observableArrayList("I don't Know","Njung'e Nyaga","David Kamau","Geoffrey Kimani","Patrick Wang'ondu","George King'ori","Kapai Lerionka","Winnie Githae","Kinyanjui Waweru","Lawerence Kiambi","Beatrice Gachunga","Peter Mutuku","Samuel Maina","Jeff Njoroge","Francis Njoroge","Job Gichimu","Peter Mwangi","Wilson Macharia","Bernard Maina");
    ObservableList<String> family = FXCollections.observableArrayList("J walkers","Favoured","Unstoppable","Undisputed","Alphas");
    ObservableList<String> full = FXCollections.observableArrayList("Yes","No");


    @FXML
    public TabPane tabPane;

    @FXML
    public Tab tab1,tab2,tab3;

    public SelectionModel<Tab> selectionModel;



    @FXML
    public ListView<String> listView;



    private ObservableList<Members> data;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comboBox.setItems(stat);
        comboBox1.setItems(gend);
        combodistrict.setItems(district);
        comboelder.setItems(elders);
        combofamily.setItems(family);
        combomember.setItems(full);
        comboadm.setItems(full);
        combocomm.setItems(full);



    }





    public void saveImage(ActionEvent event) {

        fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            openFile(file);

        } else {

        }


        }

        public void openFile (File file){

            image = new Image(file.toURI().toString(), 100, 150, true, true);
            imageView = new ImageView(image);
            imageView.setFitHeight(150);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);
            label.setText(file.getName());

        }

        public void handleSave (ActionEvent event){

            if (event.getSource() == btnsave) {

                conn = MysqlConnection.connect();

                try {
                    String surname = txtsurname.getText();
                    String othernames = txtnames.getText();
                    String occupation = txtoccupation.getText();
                    String nationality = txtnationality.getText();
                    String email = txtemail.getText();
                    String status = comboBox.getValue();
                    String gender = comboBox1.getValue();
                    String youthcontact = txtcontact.getText();
                    int contact = Integer.parseInt(youthcontact);
                    java.sql.Date getDate = java.sql.Date.valueOf(datePicker.getValue());


                    String id_no = txtid.getText();
                    int id = Integer.parseInt(id_no);


                    PreparedStatement preparedStatement;
                    ResultSet resultSet = null;
                    preparedStatement = conn.prepareStatement(" INSERT INTO youth (surname ,other_names,id_number, contact, status,occupation,gender,nationality,email,Dob,photo)" + "values (?,?,?,?,?,?,?,?,?,?,?)");

                    preparedStatement.setString(1, surname);
                    preparedStatement.setString(2, othernames);
                    preparedStatement.setInt(3, id);
                    preparedStatement.setInt(4, contact);
                    preparedStatement.setString(5, status);
                    preparedStatement.setString(6, occupation);
                    preparedStatement.setString(7, gender);
                    preparedStatement.setString(8, nationality);
                    preparedStatement.setString(9, email);
                    preparedStatement.setDate(10, getDate);

                    fileInputStream = new FileInputStream(file);
                    preparedStatement.setBinaryStream(11, fileInputStream, (int) file.length());

                    preparedStatement.execute();


                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);


                    String s = "Added successfully  ";
                    alert.setContentText(s);
                    alert.show();

                    selectionModel = tabPane.getSelectionModel();

                    selectionModel.select(tab2);

                } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setHeaderText("Information Alert");
                    String s = "error  ";
                    alert.setContentText(s);
                    alert.show();
                    System.out.println(e);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                txtemail.clear();
                txtoccupation.clear();
                txtnames.clear();
                txtcontact.clear();
                txtsurname.clear();
                txtnationality.clear();
                label.setText("");



            }

        }





    public void handleUpdate(ActionEvent event) {



        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateyouth.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Login");
            stage.setScene(new Scene(root1));
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public void searchYouth(ActionEvent event) throws SQLException {

            conn = MysqlConnection.connect();

            try {

                String id = txtsearch.getText();

                data = FXCollections.observableArrayList();
                PreparedStatement preparedStatement ;
                ResultSet resultSet;

                resultSet = conn.createStatement().executeQuery(" Select id_number, other_names, surname,contact,gender,full_member,family,district,admission,commissioned FROM youth INNER JOIN " +
                        "memberdetails ON youth.id_number = memberdetails.id_no WHERE id_number ="+id+"");




                while (resultSet.next()) {
                    data.add(new Members(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5),
                            resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9),resultSet.getString(10)));
                }

                id_no.setCellValueFactory(new PropertyValueFactory<>("id_no"));
                names.setCellValueFactory(new PropertyValueFactory<>("names"));
                surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
                contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
                gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
                fam.setCellValueFactory(new PropertyValueFactory<>("family"));
                full_member.setCellValueFactory(new PropertyValueFactory<>("full_member"));

                dist.setCellValueFactory(new PropertyValueFactory<>("district"));
                adm.setCellValueFactory(new PropertyValueFactory<>("admission"));
                commissioned.setCellValueFactory(new PropertyValueFactory<>("commissioned"));





                tableView.setItems(data);
                tableView.setOnMouseClicked((MouseEvent e)-> {
                    if (e.getClickCount() > 1) {

                        Members members = tableView.getSelectionModel().getSelectedItem();

                        String idno = Integer.toString(members.getId_no());

                        conn = MysqlConnection.connect();

                        try {



                            data = FXCollections.observableArrayList();
                            PreparedStatement preparedStatement1 ;
                            ResultSet resultSet1;

                            resultSet1 = conn.createStatement().executeQuery(" Select id_number, other_names, surname,contact,gender,full_member,family,district,admission,commissioned FROM youth INNER JOIN " +
                                    "memberdetails ON youth.id_number = memberdetails.id_no WHERE id_number ="+id+"");




                            while (resultSet1.next()) {
                                data.add(new Members(resultSet1.getInt(1), resultSet1.getString(2), resultSet1.getString(3), resultSet.getInt(4), resultSet.getString(5),
                                        resultSet1.getString(6), resultSet1.getString(7), resultSet1.getString(8), resultSet.getString(9),resultSet.getString(10)));
                            }






                    } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }






        });
              } catch (SQLException e1) {
        e1.printStackTrace();
    }
    }


        public void handleSaveGuardian(ActionEvent event){
            conn = MysqlConnection.connect();
            try {


                String names = txtname.getText();
                String relation = txtrelation.getText();
                String contactinfo = txtcontact1.getText();
                int contact = Integer.parseInt(contactinfo);

                String id_no = txtid.getText();
                int id = Integer.parseInt(id_no);

                PreparedStatement preparedStatement;
                ResultSet resultSet;

                preparedStatement = conn.prepareStatement(" INSERT INTO guardian (id_number,names, relationship, contact)" + "values (?,?,?,?)");

                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, names);
                preparedStatement.setString(3, relation);
                preparedStatement.setInt(4, contact);


                preparedStatement.execute();


                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);


                String s = "Added successfully  ";
                alert.setContentText(s);
                alert.show();


                selectionModel = tabPane.getSelectionModel();
                selectionModel.select(tab3);

            } catch (SQLException e) {
                e.printStackTrace();
            }


            txtcontact1.clear();
            txtname.clear();
            txtrelation.clear();


        }

        public void handleSaveMember(ActionEvent event){


            conn = MysqlConnection.connect();
            try {


                String district = combodistrict.getValue();
                String elders = comboelder.getValue();
                String family = combofamily.getValue();
                String fullmember = combomember.getValue();
                String admission = comboadm.getValue();
                String commision = combocomm.getValue();


                String id_no = txtid.getText();
                int id = Integer.parseInt(id_no);

                PreparedStatement preparedStatement;
                ResultSet resultSet;

                preparedStatement = conn.prepareStatement(" INSERT INTO memberdetails (id_no,district, elder, family,full_member,admission,commissioned)" + "values (?,?,?,?,?,?,?)");

                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, district);
                preparedStatement.setString(3, elders);
                preparedStatement.setString(4, family);
                preparedStatement.setString(5, fullmember);
                preparedStatement.setString(6, admission);
                preparedStatement.setString(7, commision);



                preparedStatement.execute();


                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);


                String s = "Added successfully  ";
                alert.setContentText(s);
                alert.show();

                selectionModel = tabPane.getSelectionModel();
                selectionModel.select(tab1);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            txtid.clear();







        }
        public void handleView() throws FileNotFoundException {

            com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4);

            document.addAuthor("victor");
            document.addHeader("lawsavic","investments");
            document.addTitle("demo");

            try {
                PdfWriter.getInstance(document,new FileOutputStream("HELLO WOLD.pdf"));
                document.open();

                Paragraph paragraph = new Paragraph("HELLO");
                document.add(paragraph);




            } catch (DocumentException e) {
                e.printStackTrace();
            }



        }
        public void handleSearchAll(){

            conn = MysqlConnection.connect();

            try {



                data = FXCollections.observableArrayList();
                PreparedStatement preparedStatement ;
                ResultSet resultSet;

                resultSet = conn.createStatement().executeQuery(" Select id_number, other_names, surname,contact,gender,full_member,family,district,admission,commissioned FROM youth INNER JOIN " +
                        "memberdetails ON youth.id_number = memberdetails.id_no");




                while (resultSet.next()) {
                    data.add(new Members(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5),
                            resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9),resultSet.getString(10)));
                }

                id_no.setCellValueFactory(new PropertyValueFactory<>("id_no"));
                names.setCellValueFactory(new PropertyValueFactory<>("names"));
                surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
                contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
                gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
                fam.setCellValueFactory(new PropertyValueFactory<>("family"));
                full_member.setCellValueFactory(new PropertyValueFactory<>("full_member"));

                dist.setCellValueFactory(new PropertyValueFactory<>("district"));
                adm.setCellValueFactory(new PropertyValueFactory<>("admission"));
                commissioned.setCellValueFactory(new PropertyValueFactory<>("commissioned"));





                tableView.setItems(data);




            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void handlePrint(ActionEvent event) throws SQLException, FileNotFoundException, DocumentException {

            try {



                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("print.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();

                stage.initStyle(StageStyle.DECORATED);
                stage.setTitle("Print");
                stage.setScene(new Scene(root1));
                stage.show();





            } catch (Exception e) {
                System.out.print(e);

            }

        }



        }




