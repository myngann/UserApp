package com.mycompany.userapp;

import java.time.format.DateTimeFormatter;
import connection.database;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SecondaryController implements Initializable {

    @FXML
    private Label page_label;
    
    @FXML
    private Button minimize;
    
    @FXML
    private Button close;
    
    @FXML
    private Button Minimize;

    @FXML
    private Button dangxuat_btn;

    @FXML
    private AnchorPane yeucaudv_form;

    
    @FXML
    private TableView<?> dichvu_tableview;

    @FXML
    private Button dv_btn;

    @FXML
    private TableColumn<?, ?> dv_col_ngaydien;

    @FXML
    private TableColumn<?, ?> dv_col_tendv;

    @FXML
    private TableColumn<?, ?> dv_col_tinhtrang;

    @FXML
    private CheckBox dv_cua_checkbox;

    @FXML
    private Button dv_guiyeucauBtn;

    @FXML
    private CheckBox dv_ongnuoc_checkbox;

    @FXML
    private CheckBox dv_toilet_checkbox;

    @FXML
    private CheckBox dv_voisen_checkbox;

    @FXML
    private Button hoadon_btn;

    @FXML
    private Label hoadon_cthd_dongia;

    @FXML
    private AnchorPane hoadon_form;

    @FXML
    private Label hoadon_hd_conno;

    @FXML
    private ComboBox<?> hoadon_hd_kidong;

    @FXML
    private ComboBox<?> hoadon_hd_tenphong;

    @FXML
    private Label hoadon_hd_tongtien;

    @FXML
    private Label hoadon_hd_trangthai;

    @FXML
    private Button khachnganhan_btn;

    @FXML
    private TextField khachnganhan_cccd;

    @FXML
    private TableColumn<KhachData, String> khachnganhan_col_cccd;

    @FXML
    private TableColumn<KhachData, String> khachnganhan_col_email;

    @FXML
    private TableColumn<KhachData, String> khachnganhan_col_gioitinh;

    @FXML
    private TableColumn<KhachData, String> khachnganhan_col_hoten;

    @FXML
    private TableColumn<KhachData, String> khachnganhan_col_hotenDD;

    @FXML
    private TableColumn<KhachData, String> khachnganhan_col_maDD;

    @FXML
    private TableColumn<KhachData, String> khachnganhan_col_maKT;

    @FXML
    private TableColumn<KhachData, String> khachnganhan_col_ngaysinh;

    @FXML
    private TableColumn<KhachData, String> khachnganhan_col_sdt;
    
    @FXML
    private TableColumn<KhachData, String> khachnganhan_col_ngaybatdau;
    
    @FXML
    private TableColumn<KhachData, String> khachnganhan_col_ngayketthuc;
    
    @FXML
    private TableColumn<KhachData, String> khachnganhan_col_trangthai;
        
    @FXML
    private DatePicker khachnganhan_ngaybatdau;

    @FXML
    private DatePicker khachnganhan_ngayketthuc;

    @FXML
    private TextField khachnganhan_email;

    @FXML
    private AnchorPane khachnganhan_form;

    @FXML
    private ComboBox<?> khachnganhan_gioitinh;

    @FXML
    private Button khachnganhan_guiyeucauBtn;

    @FXML
    private TextField khachnganhan_hoten;

    @FXML
    private TextField khachnganhan_hotenDD;

    @FXML
    private TextField khachnganhan_maDD;

    @FXML
    private TextField khachnganhan_maKT;

    @FXML
    private DatePicker khachnganhan_ngaysinh;

    @FXML
    private TextField khachnganhan_sdt;

    @FXML
    private TableView<KhachData> khachnganhan_tableview;

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane trangchu_form;

    
    @FXML
    private Button trangchu_btn;

    @FXML
    private Label trangchu_cccd;

    @FXML
    private Label trangchu_diachi;

    @FXML
    private Label trangchu_email;

    @FXML
    private Label trangchu_gioitinh;

    @FXML
    private Label trangchu_hoten;

    @FXML
    private Label trangchu_maKT;
    
    @FXML
    private Label trangchu_hotenDD;

    @FXML
    private Label trangchu_ngaybd;

    @FXML
    private Label trangchu_ngaykt;

    @FXML
    private Label trangchu_ngaysinh;

    @FXML
    private Label trangchu_sdt;

    @FXML
    private Label trangchu_sldkkhachnganhan;

    @FXML
    private Label trangchu_slhoadonthanhtoan;

    @FXML
    private Label trangchu_slyeucaudv;
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    
    
    public void close(ActionEvent event){
        System.exit(0);
    }
    public void minimize(ActionEvent event) {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    public void swithForm(ActionEvent event){
        if(event.getSource()==trangchu_btn){
            trangchu_form.setVisible(true);
            yeucaudv_form.setVisible(false);
            khachnganhan_form.setVisible(false);
            hoadon_form.setVisible(false);
            
            trangchu_hoten();
            
            page_label.setText("TRANG CHỦ");
        } else if (event.getSource()==dv_btn){
            trangchu_form.setVisible(false);
            yeucaudv_form.setVisible(true);
            khachnganhan_form.setVisible(false);
            hoadon_form.setVisible(false);
            
            page_label.setText("YÊU CẦU DỊCH VỤ");
        } else if (event.getSource()==khachnganhan_btn){
            trangchu_form.setVisible(false);
            yeucaudv_form.setVisible(false);
            khachnganhan_form.setVisible(true);
            hoadon_form.setVisible(false);
            
            khachnganhan_hotenDD.setDisable(true);
            KhachNganHanShowListData();
            KhachNganHanGioiTinh();
            khachnganhan_hotenDD();
            khachnganhan_maDD();
            
            page_label.setText("ĐĂNG KÍ KHÁCH NGẮN HẠN");
        }  else if (event.getSource()==hoadon_btn){
            trangchu_form.setVisible(false);
            yeucaudv_form.setVisible(false);
            khachnganhan_form.setVisible(false);
            hoadon_form.setVisible(true);
            
            page_label.setText("HÓA ĐƠN");
        }
        
    }

    public void trangchu_hotenDD() {
        String hotenDD = "SELECT HOTEN FROM KHACHTHUE KT1, TAIKHOAN WHERE KT1.MADD=TAIKHOAN.MAKT "
                + "AND KT1.MADD=(SELECT KT2.MAKT FROM KHACHTHUE KT2 "
                + "AND USERNAME = ?";

        connect = database.getConn();

        try {
            prepare = connect.prepareStatement(hotenDD);
            prepare.setString(1, LoginData.taikhoan); // Sử dụng giá trị từ LoginData.taikhoan
            result = prepare.executeQuery();

            if (result.next()) {
                trangchu_hotenDD.setText(result.getString("HOTEN"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void trangchu_hoten() {
        String hoten = "SELECT HOTEN FROM KHACHTHUE, TAIKHOAN WHERE KHACHTHUE.MAKT=TAIKHOAN.MAKT AND USERNAME = ?";

        connect = database.getConn();

        try {
            prepare = connect.prepareStatement(hoten);
            prepare.setString(1, LoginData.taikhoan); // Sử dụng giá trị từ LoginData.taikhoan
            result = prepare.executeQuery();

            if (result.next()) {
                trangchu_hoten.setText(result.getString("HOTEN"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void trangchu_cccd() {
        String cccd = "SELECT CCCD FROM KHACHTHUE, TAIKHOAN WHERE KHACHTHUE.MAKT=TAIKHOAN.MAKT AND USERNAME = ?";

        connect = database.getConn();

        try {
            prepare = connect.prepareStatement(cccd);
            prepare.setString(1, LoginData.taikhoan); // Sử dụng giá trị từ LoginData.taikhoan
            result = prepare.executeQuery();

            if (result.next()) {
                trangchu_cccd.setText(result.getString("CCCD"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void trangchu_sdt() {
        String sdt = "SELECT SDT FROM KHACHTHUE, TAIKHOAN WHERE KHACHTHUE.MAKT=TAIKHOAN.MAKT AND USERNAME = ?";

        connect = database.getConn();

        try {
            prepare = connect.prepareStatement(sdt);
            prepare.setString(1, LoginData.taikhoan); // Sử dụng giá trị từ LoginData.taikhoan
            result = prepare.executeQuery();

            if (result.next()) {
                trangchu_sdt.setText(result.getString("SDT"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    public void trangchu_email() {
        String email = "SELECT EMAIL FROM KHACHTHUE, TAIKHOAN WHERE KHACHTHUE.MAKT=TAIKHOAN.MAKT AND USERNAME = ?";

        connect = database.getConn();

        try {
            prepare = connect.prepareStatement(email);
            prepare.setString(1, LoginData.taikhoan); // Sử dụng giá trị từ LoginData.taikhoan
            result = prepare.executeQuery();

            if (result.next()) {
                trangchu_email.setText(result.getString("EMAIL"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }     
    
    public void trangchu_ngaysinh() {
        String ngaysinh = "SELECT NGAYSINH FROM KHACHTHUE, TAIKHOAN WHERE KHACHTHUE.MAKT=TAIKHOAN.MAKT AND USERNAME = ?";
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        
        connect = database.getConn();

        try {
            prepare = connect.prepareStatement(ngaysinh);
            prepare.setString(1, LoginData.taikhoan); // Sử dụng giá trị từ LoginData.taikhoan
            result = prepare.executeQuery();

            if (result.next()) {
                LocalDate ngaySinh = result.getDate("NGAYSINH").toLocalDate();
                String formattedNgaySinh = ngaySinh.format(formatter);
                trangchu_ngaysinh.setText(formattedNgaySinh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void trangchu_ngaybatdau() {
        String ngaybatdau = "SELECT NGAYBATDAU FROM KHACHTHUE, TAIKHOAN WHERE KHACHTHUE.MAKT=TAIKHOAN.MAKT AND USERNAME = ?";
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        
        connect = database.getConn();

        try {
            prepare = connect.prepareStatement(ngaybatdau);
            prepare.setString(1, LoginData.taikhoan); // Sử dụng giá trị từ LoginData.taikhoan
            result = prepare.executeQuery();

            if (result.next()) {
                LocalDate ngayBatDau = result.getDate("NGAYBATDAU").toLocalDate();
                String formattedNgayBatDau = ngayBatDau.format(formatter);
                trangchu_ngaybd.setText(formattedNgayBatDau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void trangchu_ngayketthuc() {
        String ngayketthuc = "SELECT NGAYKETTHUC FROM KHACHTHUE, TAIKHOAN WHERE KHACHTHUE.MAKT=TAIKHOAN.MAKT AND USERNAME = ?";
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        
        connect = database.getConn();

        try {
            prepare = connect.prepareStatement(ngayketthuc);
            prepare.setString(1, LoginData.taikhoan); // Sử dụng giá trị từ LoginData.taikhoan
            result = prepare.executeQuery();

            if (result.next()) {
                LocalDate ngayKetThuc = result.getDate("NGAYKETTHUC").toLocalDate();
                String formattedNgayKetThuc = ngayKetThuc.format(formatter);
                trangchu_ngaykt.setText(formattedNgayKetThuc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    public void trangchu_gioitinh() {
        String gioitinh = "SELECT GIOITINH FROM KHACHTHUE, TAIKHOAN WHERE KHACHTHUE.MAKT=TAIKHOAN.MAKT AND USERNAME = ?";

        connect = database.getConn();

        try {
            prepare = connect.prepareStatement(gioitinh);
            prepare.setString(1, LoginData.taikhoan); // Sử dụng giá trị từ LoginData.taikhoan
            result = prepare.executeQuery();

            if (result.next()) {
                trangchu_gioitinh.setText(result.getString("GIOITINH"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
        public void khachnganhan_hotenDD() {
        String hotenDD = "SELECT HOTEN FROM KHACHTHUE, TAIKHOAN WHERE KHACHTHUE.MAKT=TAIKHOAN.MAKT AND USERNAME = ?";

        connect = database.getConn();

        try {
            prepare = connect.prepareStatement(hotenDD);
            prepare.setString(1, LoginData.taikhoan); // Sử dụng giá trị từ LoginData.taikhoan
            result = prepare.executeQuery();

            if (result.next()) {
                khachnganhan_hotenDD.setText(result.getString("HOTEN"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public void khachnganhan_maDD() {
        String maDD = "SELECT KHACHTHUE.MAKT " +
                  "FROM KHACHTHUE, TAIKHOAN " +
                  "WHERE KHACHTHUE.MAKT = TAIKHOAN.MAKT AND USERNAME = ?";

        connect = database.getConn();

        try {
            prepare = connect.prepareStatement(maDD);
            prepare.setString(1, LoginData.taikhoan); // Sử dụng giá trị từ LoginData.taikhoan
            result = prepare.executeQuery();

            if (result.next()) {
                khachnganhan_maDD.setText(result.getString("MAKT"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
        public void KhachNganHan_Guiyeucau(ActionEvent event) {

        String sql = "INSERT INTO KHACHNGANHAN (MAKT,HOTEN,GIOITINH,NGAYSINH,SDT,CCCD,EMAIL,NGAYBATDAU,NGAYKETTHUC,MADD,TRANGTHAI) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        connect = database.getConn();

        try {
            Alert alert;

            if (
                    khachnganhan_hoten.getText().isEmpty()
                    || khachnganhan_gioitinh.getSelectionModel().getSelectedItem() == null
                    || khachnganhan_ngaysinh.getValue() == null
                    || khachnganhan_ngaybatdau.getValue() == null
                    || khachnganhan_ngayketthuc.getValue() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng điền vào các ô có *");
                alert.showAndWait();

            } else {
                // Lấy mã khách hàng mới
                String newKhachID = generateKhachID();

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, newKhachID);
                prepare.setString(2, khachnganhan_hoten.getText());
                prepare.setString(3, (String) khachnganhan_gioitinh.getSelectionModel().getSelectedItem());

                LocalDate ngaySinh = khachnganhan_ngaysinh.getValue();
                java.sql.Date sqlNgaySinh = java.sql.Date.valueOf(ngaySinh);
                prepare.setDate(4, sqlNgaySinh);

                prepare.setString(5, khachnganhan_sdt.getText());
                prepare.setString(6, khachnganhan_cccd.getText());
                prepare.setString(7, khachnganhan_email.getText());

                LocalDate ngayBatdau = khachnganhan_ngaybatdau.getValue();
                java.sql.Date sqlNgayBatdau = java.sql.Date.valueOf(ngayBatdau);
                prepare.setDate(8, sqlNgayBatdau);

                LocalDate ngayKetthuc = khachnganhan_ngayketthuc.getValue();
                java.sql.Date sqlNgayKetthuc = java.sql.Date.valueOf(ngayKetthuc);
                prepare.setDate(9, sqlNgayKetthuc);
                
                
                prepare.setString(10, khachnganhan_maDD.getText());
                prepare.setString(11, String.valueOf("Chờ duyệt"));

                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Thêm thành công!");
                alert.showAndWait();

                // SHOW UPDATED TABLEVIEW
                KhachNganHanShowListData();

                // CLEAR ALL FIELDS
                KhachNganHanClear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String generateKhachID() {
        // Tạo biến đếm tạm thời để tăng dần mã khách hàng
        int khachCounter = KhachNganHanList.size() + 1;
        return "K" + String.format("%03d", khachCounter);
    }

    public void KhachNganHanClear() {

        khachnganhan_hoten.setText("");
        khachnganhan_gioitinh.getSelectionModel().clearSelection();
        khachnganhan_ngaysinh.setValue(null);
        khachnganhan_sdt.setText("");
        khachnganhan_cccd.setText("");
        khachnganhan_email.setText("");
        khachnganhan_ngaybatdau.setValue(null);
        khachnganhan_ngayketthuc.setValue(null);
    }
    public void KhachNganHanGioiTinh() {
        ObservableList listData = FXCollections.observableArrayList("Nam", "Nữ","Khác");
        khachnganhan_gioitinh.setItems(listData);
    }
    public ObservableList<KhachData> KhachNganHanListData() {

        ObservableList<KhachData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM KHACHNGANHAN";

        connect = database.getConn();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            KhachData khachnh;
            
            // Định dạng cho ngày/tháng/năm
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            while (result.next()) {
                LocalDate ngaySinh = result.getDate("NGAYSINH").toLocalDate();
                LocalDate ngayBatDau = result.getDate("NGAYBATDAU").toLocalDate();
                LocalDate ngayKetThuc = result.getDate("NGAYKETTHUC").toLocalDate();

                // Format ngày/tháng/năm
                String formattedNgaySinh = ngaySinh.format(formatter);
                String formattedNgayBatDau = ngayBatDau.format(formatter);
                String formattedNgayKetThuc = ngayKetThuc.format(formatter);

                khachnh = new KhachData(result.getString("MAKT"),
                        result.getString("HOTEN"), result.getString("GIOITINH"),
                        formattedNgaySinh, result.getString("SDT"),
                        result.getString("CCCD"), result.getString("EMAIL"),
                        result.getString("MADD"), result.getString("TRANGTHAI"),
                        formattedNgayBatDau, formattedNgayKetThuc);
                listData.add(khachnh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<KhachData> KhachNganHanList;

    public void KhachNganHanShowListData() {
        KhachNganHanList = KhachNganHanListData();
        
        khachnganhan_col_maKT.setCellValueFactory(cellData -> cellData.getValue().getKhachidProperty());
        khachnganhan_col_hoten.setCellValueFactory(cellData -> cellData.getValue().getHotenProperty());
        khachnganhan_col_gioitinh.setCellValueFactory(cellData -> cellData.getValue().getGioitinhProperty());
        khachnganhan_col_ngaysinh.setCellValueFactory(cellData -> cellData.getValue().getNgaysinhProperty());
        khachnganhan_col_sdt.setCellValueFactory(cellData -> cellData.getValue().getSdtProperty());
        khachnganhan_col_cccd.setCellValueFactory(cellData -> cellData.getValue().getCccdProperty());
        khachnganhan_col_email.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
        khachnganhan_col_ngaybatdau.setCellValueFactory(cellData -> cellData.getValue().getNgaybatdauProperty());
        khachnganhan_col_ngayketthuc.setCellValueFactory(cellData -> cellData.getValue().getNgayketthucProperty());
        khachnganhan_col_maDD.setCellValueFactory(cellData -> cellData.getValue().getDaidienidProperty());
        khachnganhan_col_trangthai.setCellValueFactory(cellData -> cellData.getValue().getTrangthaiProperty());

        
        khachnganhan_tableview.setItems(KhachNganHanList);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            KhachNganHanShowListData();
            KhachNganHanGioiTinh();
            
            khachnganhan_hotenDD.setDisable(true);
            khachnganhan_maDD.setDisable(true);
            
            trangchu_hoten();
            trangchu_ngaysinh();
            trangchu_gioitinh();
            trangchu_cccd();
            trangchu_sdt();
            trangchu_email();
            trangchu_ngaybatdau();
            trangchu_ngayketthuc();
            trangchu_hotenDD();
    }
}