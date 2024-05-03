
package com.mycompany.userapp;

import connection.database;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PrimaryController {

    @FXML
    private Button btn;

    @FXML
    private Button close;

    @FXML
    private Button close1;

    @FXML
    private Button close11;

    @FXML
    private Button close111;

    @FXML
    private AnchorPane login_form;
    
    @FXML
    private AnchorPane mainForm;

    
    @FXML
    private TextField login_email;

    @FXML
    private Hyperlink login_forgotpassword;

    @FXML
    private Button login_guimail;

    @FXML
    private TextField login_maxn;

    @FXML
    private TextField login_mkmoi;

    @FXML
    private Button login_quaylaiemail;

    @FXML
    private Button login_quaylaimaxn;

    @FXML
    private CheckBox login_showpassword;

    @FXML
    private Button login_xacnhanma;

    @FXML
    private TextField login_xacnhanmkmoi;

    @FXML
    private AnchorPane loginemailform;

    @FXML
    private AnchorPane loginmaxnform;

    @FXML
    private Button loginquyalaimkmoi;

    @FXML
    private AnchorPane loginxacnhanmkform;

    @FXML
    private Button loginxacnhanmkmoibtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField showpassword;

    @FXML
    private TextField username;
    
    private double x = 0;
    private double y = 0;
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    

    
    public void login(ActionEvent ev){
        String sql = "SELECT * FROM TAIKHOAN WHERE username = ? and password = ?";
        connect = database.getConn();
        
        try{
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());
            
            result = prepare.executeQuery();
            
            Alert alert;
            
            if(username.getText().isEmpty() || password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Các ô không được để trống");
                alert.showAndWait();
            }else{
                if(result.next()){
                    // IF CORRECT USERNAME AND PASSWORD THEN PROCEED TO DASHBOARD 
                    
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Đăng nhập thành công!");
                    alert.showAndWait();
                    
                    LoginData.taikhoan = username.getText();
                    LoginData.matkhau = password.getText();
                    //getData.username = username.getText();
                    
                    // TO HIDE LOGIN FORM
                    btn.getScene().getWindow().hide();
                    
                    // LINK YOUR DASHBOARD FORM
                    Parent root = FXMLLoader.load(getClass().getResource("secondary.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("BCODE APP");
                    Scene scene = new Scene(root);
                    
                    root.setOnMousePressed((MouseEvent event) ->{
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });

                    root.setOnMouseDragged((MouseEvent event) ->{
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });
                    
                    stage.initStyle(StageStyle.TRANSPARENT);
                    
                    stage.setScene(scene);
                    stage.show();
                    
                }else{
                    // IF NOT THEN ERROR MESSAGE WILL APPEAR
                    
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Tài khoản hoặc mật khẩu không đúng");
                    alert.showAndWait();
                    
                }
            }
            
        }catch(Exception e){e.printStackTrace();}
        
    }
    public void showpassword(ActionEvent event){
        //image.setVisible(true);
        if(login_showpassword.isSelected()){
            showpassword.setText(password.getText());
            showpassword.setVisible(true);
            password.setVisible(false);
        }
        else{
            password.setText(showpassword.getText());
            showpassword.setVisible(false);
            password.setVisible(true);
        }
    }
    public void forgotpassword(ActionEvent event){
        
                loginemailform.setVisible(true);
                login_form.setVisible(false);
                loginmaxnform.setVisible(false);
                loginxacnhanmkform.setVisible(false);
            
    }
    public void sendmail(ActionEvent event){
        Alert alert;
        try{
            if (login_email.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Các ô không được để trống");
                alert.showAndWait();
            }
            else{
            loginemailform.setVisible(false);
            login_form.setVisible(false);
            loginmaxnform.setVisible(true);
            loginxacnhanmkform.setVisible(false);
            }    
            
        }catch(Exception e){e.printStackTrace();}
    }
    public void backofmail(ActionEvent event){
            loginemailform.setVisible(false);
            login_form.setVisible(true);
            loginmaxnform.setVisible(false);
            loginxacnhanmkform.setVisible(false);
    }
    public void verifycode(ActionEvent event){
        //viết thêm
        Alert alert;
        try{
            if (login_maxn.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Các ô không được để trống");
                alert.showAndWait();
            }
            else{
            loginemailform.setVisible(false);
            login_form.setVisible(false);
            loginmaxnform.setVisible(false);
            loginxacnhanmkform.setVisible(true);
            }    
            
        }catch(Exception e){e.printStackTrace();}
    }
    public void backofverifycode(ActionEvent event){
            loginemailform.setVisible(true);
            login_form.setVisible(false);
            loginmaxnform.setVisible(false);
            loginxacnhanmkform.setVisible(false);
    }
    public void verifypasword(ActionEvent event){
        Alert alert;
        try{
            if (login_mkmoi.getText().isEmpty() || login_xacnhanmkmoi.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Các ô không được để trống");
                alert.showAndWait();
            }
            else if(!login_mkmoi.getText().equals(login_xacnhanmkmoi.getText())){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Mật khẩu xác nhận chưa trùng khớp");
                alert.showAndWait();
            }
            else{
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Đổi mật khẩu thành công!");
                alert.showAndWait();
                
                loginemailform.setVisible(false);
                login_form.setVisible(true);
                loginmaxnform.setVisible(false);
                loginxacnhanmkform.setVisible(false);
            }    
            
        }catch(Exception e){e.printStackTrace();}
    }
    public void backofverifypasword(ActionEvent event){ //không cần thiết??
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Thông báo xác nhận");
                alert.setHeaderText(null);
                alert.setContentText("Bạn có chắc muốn quay lại?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
            backofmail(event);}

    }
    public void close(ActionEvent event){
        System.exit(0);
    }

}
