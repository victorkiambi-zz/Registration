package youth;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class printController implements Initializable {


    @FXML
    public Button printbtn;

    @FXML
    public TextField txtprint;

    Connection conn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void handlePrint(ActionEvent event) throws SQLException {

        conn = MysqlConnection.connect();

        String printname = txtprint.getText();

        try {

            com.itextpdf.text.Document report = new com.itextpdf.text.Document();


            PdfWriter.getInstance(report, new FileOutputStream("C:/Users/hp/Documents/"+printname+".pdf"));
            report.open();



            //we have four columns in our table


            String idno,names,surname,contact,gender,family,fullmember,distr,admission,commission;
            String sql = " Select id_number, other_names, surname,contact,full_member,family,district,admission,commissioned FROM youth INNER JOIN " +
                    "memberdetails ON youth.id_number = memberdetails.id_no";


            Font font = FontFactory.getFont(FontFactory.HELVETICA, 6);


            PdfPTable table = new PdfPTable(10);



            PdfPTable nameTable = new PdfPTable(2);
            PdfPCell cell = new PdfPCell(new Paragraph("Youth Details"));
            cell.setColspan(3);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.CYAN);
            nameTable.addCell(cell);
            nameTable.setWidths(new int[]{2, 1});

            report.add(nameTable);

            PdfPTable vendTable = new PdfPTable(8);
            PdfPCell cell1 = new PdfPCell(new Paragraph("Names",font));
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            vendTable.addCell(cell1);
            PdfPCell cell2 = new PdfPCell(new Paragraph("Surname",font));
            cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            vendTable.addCell(cell2);
            PdfPCell cell3 = new PdfPCell(new Paragraph("Contact",font));
            cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            vendTable.addCell(cell3);
            PdfPCell cell4 = new PdfPCell(new Paragraph("FullMember",font));
            cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            vendTable.addCell(cell4);
            PdfPCell cell5 = new PdfPCell(new Paragraph("Family",font));
            cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
            vendTable.addCell(cell5);
            PdfPCell cell6 = new PdfPCell(new Paragraph("District",font));
            cell6.setBackgroundColor(BaseColor.LIGHT_GRAY);
            vendTable.addCell(cell6);
            PdfPCell cell7 = new PdfPCell(new Paragraph("Admission",font));
            cell7.setBackgroundColor(BaseColor.LIGHT_GRAY);
            vendTable.addCell(cell7);
            PdfPCell cell8 = new PdfPCell(new Paragraph("Commissioned",font));
            cell8.setBackgroundColor(BaseColor.LIGHT_GRAY);
            vendTable.addCell(cell8);



            PreparedStatement preparedStatement;
            ResultSet resultSet;

            preparedStatement = conn.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                idno = resultSet.getString(1);
                names = resultSet.getString(2);
                surname = resultSet.getString(3);
                contact = resultSet.getString(4);

                family = resultSet.getString(5);
                fullmember = resultSet.getString(6);
                distr = resultSet.getString(7);
                admission = resultSet.getString(8);
                commission = resultSet.getString(9);





                vendTable.addCell(new Phrase(names,FontFactory.getFont(FontFactory.HELVETICA, 6)));
                vendTable.addCell(new Phrase(surname,FontFactory.getFont(FontFactory.HELVETICA, 6)));
                vendTable.addCell(new Phrase(contact,FontFactory.getFont(FontFactory.HELVETICA, 6)));
                vendTable.addCell(new Phrase(family,FontFactory.getFont(FontFactory.HELVETICA, 6)));
                vendTable.addCell(new Phrase(fullmember,FontFactory.getFont(FontFactory.HELVETICA, 6)));
                vendTable.addCell(new Phrase(distr,FontFactory.getFont(FontFactory.HELVETICA, 6)));
                vendTable.addCell(new Phrase(admission,FontFactory.getFont(FontFactory.HELVETICA, 6)));
                vendTable.addCell(new Phrase(commission,FontFactory.getFont(FontFactory.HELVETICA, 6)));

            }
            report.add(vendTable);




            report.close();

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);

            alert1.setHeaderText("Printing Complete");


            alert1.show();


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }



    }



    }

