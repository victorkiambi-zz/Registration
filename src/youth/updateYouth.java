package youth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class updateYouth implements Initializable {

    @FXML
    public TextField txtsurname,txtnames,txtcontact,txtstatus,txtoccupation,txtemail,txtid;

    @FXML
    public Button searchbtn;

    Connection conn;


    public void handleSearch(ActionEvent event){

        conn = MysqlConnection.connect();
        try {

            String id = txtid.getText();

            PreparedStatement preparedStatement;
            ResultSet resultSet;

            resultSet = conn.createStatement().executeQuery(" Select * from youth where id_number ="+id+"");

            while(resultSet.next()){
                txtsurname.setText(resultSet.getString("surname"));
                txtnames.setText(resultSet.getString("other_names"));

                txtemail.setText(resultSet.getString("email"));
                txtstatus.setText(resultSet.getString("status"));
                txtoccupation.setText(resultSet.getString("occupation"));
                txtcontact.setText(resultSet.getString("contact"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleUpdate(ActionEvent event) {
        conn= MysqlConnection.connect();


    }
}
