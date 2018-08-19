package youth;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginController  implements Initializable {
    public LoginModel loginmodel = new LoginModel();

    public Button loginBtn;
    public TextField username;
    public PasswordField userpass;

    Connection conn;

    public Stage stage = new Stage();


    public void handleLogin(ActionEvent event) throws SQLException {


        if (loginmodel.isLogin(username.getText(), userpass.getText())) {

        try {



            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("primary.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Login");
            stage.setScene(new Scene(root1));
            stage.show();


            createTable();


        } catch (Exception e) {
            System.out.print(e);

        } finally {

            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }




    }    else{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText("Information Alert");
        String s = "USERNAME OR PASSWORD IS INCORRECT  ";
        alert.setContentText(s);
        alert.show();
    }


}

    private void createTable() {

        try {

            conn = MysqlConnection.connect();


            PreparedStatement preparedStatement,preparedStatement1,preparedStatement2;
            ResultSet resultSet = null;

            preparedStatement = conn.prepareStatement("CREATE TABLE  IF NOT EXISTS youth (surname char(20),other_names char (20),id_number int  PRIMARY KEY UNIQUE, contact int,status char(10), occupation char(10),gender char (10), nationality char(10),email char(20),Dob date,photo longblob)");

            preparedStatement1= conn.prepareStatement("CREATE TABLE IF NOT EXISTS guardian (id_number int (20) PRIMARY KEY UNIQUE,names char(20),relationship char(20),contact int(20), FOREIGN KEY (id_number) REFERENCES youth(id_number))");

            preparedStatement2= conn.prepareStatement("CREATE TABLE IF NOT EXISTS memberdetails (id_no int (20)PRIMARY KEY UNIQUE,district char(20),elder char(30),family char(20), full_member char (5),admission char (5),commissioned char (5), FOREIGN KEY (id_number) REFERENCES youth(id_number))");



            preparedStatement.executeUpdate();
            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }
}
