package Presentation.GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Domain.hoaDonServiceImpl;
import Domain.Model.hoaDon;
import Domain.Model.hoaDonGio;
import Domain.Model.hoaDonNgay;
import Observer.Subscriber;
import Presentation.Controller.CommandProcessor;
import Presentation.Controller.addCommand;
import Presentation.Controller.deleteCommand;
import Presentation.Controller.findCommand;
import Presentation.Controller.loadHDCommand;
import Presentation.Controller.soLuongCommand;
import Presentation.Controller.tbThanhTienCommand;
import Presentation.Controller.updateCommand;

public class giaoDien extends JFrame implements ActionListener,Subscriber{
	//Field
	private JTable table_hoaDon,table_thongKe;
	private DefaultTableModel table_Model;
	private JLabel lb_maHoaDon,lb_maPhong,lb_ngayLapHoaDon,lb_tenKhachHang,lb_donGia,lb_soGioThue,lb_soNgayThue,lb_loaiHoaDon,lb_thang,lb_tieudeThanhTien,lb_tbThanhTien,lb_soGio,lb_soNgay,lb_soLuongHDGio,lb_soLuongHDNgay,lb_ThanhTien,txt_ThanhTien,lb_nam;
	private JTextField txt_maHoaDon,txt_maPhong,txt_ngayLapHoaDon,txt_tenKhachHang,txt_donGia,txt_soGioThue,txt_soNgayThue,txt_nam;
	private JButton addButton,deleteButton,findButton,updateButton,resetButton,soLuongButton;
	private JComboBox<String> cb_loaiHoaDon,cb_thang;

	private Date ngayLapHoaDon;
	private String tenKhachHang;
	private int maPhong,soGioThue,soNgayThue,maHoaDon;
	private double donGia,thanhTien;

	private hoaDonServiceImpl hoaDonService;
	private CommandProcessor commandProcessor;
	//Constructor
    public giaoDien()
    {
        InitializeComponents();
    }
	//Methods
    private void InitializeComponents()
    {
		commandProcessor = new CommandProcessor();
		hoaDonService = new hoaDonServiceImpl();
		hoaDonService.addSubscriber(this);

		//label
		lb_loaiHoaDon = new JLabel("Loại Hoá Đơn");
		lb_maHoaDon = new JLabel("Mã Hoá Đơn");
		lb_maPhong = new JLabel("Mã Phòng");
		lb_ngayLapHoaDon = new JLabel("Ngày Lập Hoá Đơn");
		lb_tenKhachHang = new JLabel("Tên Khách Hàng");
		lb_donGia = new JLabel("Đơn Giá");
		lb_soGioThue = new JLabel("Số Giờ Thuê");
		lb_soNgayThue = new JLabel("Số Ngày Thuê");
		lb_thang = new JLabel("Tháng Thống Kê Trung Bình");
		lb_tieudeThanhTien = new JLabel("Trung Bình Thành Tiền: ");
		lb_tbThanhTien = new JLabel();
		lb_soGio = new JLabel("Số Lượng Hoá Đơn Giờ");
		lb_soNgay = new JLabel("Số Lượng Hoá Đơn Ngày");
		lb_soLuongHDGio = new JLabel("0");
		lb_soLuongHDNgay = new JLabel("0");
		lb_ThanhTien = new JLabel("Thành Tiền: ");
		txt_ThanhTien = new JLabel("0");
		lb_nam = new JLabel("Năm Thống Kê: ");


		//textfield
		txt_maHoaDon = new JTextField(5);
		txt_maPhong = new JTextField(5);
		txt_ngayLapHoaDon = new JTextField(15);
		txt_tenKhachHang = new JTextField(15);
		txt_donGia = new JTextField(15);
		txt_soGioThue = new JTextField(5);
		txt_soNgayThue = new JTextField(5);
		txt_soNgayThue.setEnabled(false);
		txt_nam = new JTextField(5);
		txt_nam.addActionListener(this);

		//button
		addButton = new JButton("Thêm");
		addButton.setPreferredSize(new Dimension(100, 30));
		addButton.addActionListener(this);

		deleteButton = new JButton("Xoá");
		deleteButton.setPreferredSize(new Dimension(100, 30));
		deleteButton.addActionListener(this);

		updateButton = new JButton("Cập nhật");
		updateButton.setPreferredSize(new Dimension(100,30));
		updateButton.addActionListener(this);

		resetButton = new JButton("Làm mới");
		resetButton.addActionListener(this);
		resetButton.setPreferredSize(new Dimension(100, 30));

		findButton = new JButton("Tìm Kiếm");
		findButton.setPreferredSize(new Dimension(100, 30));
		findButton.addActionListener(this);
		
		soLuongButton = new JButton("Thống Kê Số Lượng Hoá Đơn");
		soLuongButton.setPreferredSize(new Dimension(220, 30));
		soLuongButton.addActionListener(this);


	
		//table setup
		String [] dulieuTable = {"Mã Hoá Đơn","Mã Phòng","Ngày Lập Hoá Đơn","Tên Khách Hàng","Đơn Gía","Số Giờ Thuê","Số Ngày Thuê","Thành Tiền"};
		table_Model = new DefaultTableModel(dulieuTable,0);
		table_hoaDon = new JTable(table_Model);
		table_thongKe = new JTable(table_Model);

		//Combobox
		cb_loaiHoaDon = new JComboBox<>(new String[]{"Hoá Đơn Giờ","Hoá Đơn Ngày"});
		cb_loaiHoaDon.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int selectedValue =  cb_loaiHoaDon.getSelectedIndex();
            if (selectedValue==0) {
                	txt_soGioThue.setEnabled(true);
                	txt_soNgayThue.setEnabled(false);
            } else if (selectedValue == 1) {
                	txt_soGioThue.setEnabled(false);
                	txt_soNgayThue.setEnabled(true);
                }
            }
        });

		cb_thang = new JComboBox<>(new String[]
		{
			"Tháng 1","Tháng 2","Tháng 3","Tháng 4","Tháng 5","Tháng 6","Tháng 7","Tháng 8","Tháng 9","Tháng 10","Tháng 11","Tháng 12"
		});
		//gridBagConstraints
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

		//inputPanel
		JPanel inputPanel = new JPanel(new GridBagLayout());

		gbc.gridx = 0;
        gbc.gridy = 0;
		inputPanel.add(lb_loaiHoaDon,gbc);
		
		gbc.gridx++;
		inputPanel.add(cb_loaiHoaDon,gbc);

		gbc.gridy++;
		gbc.gridx=0;
		inputPanel.add(lb_tenKhachHang,gbc);
		
		gbc.gridx++;
		inputPanel.add(txt_tenKhachHang,gbc);

		gbc.gridx++;
		inputPanel.add(lb_maHoaDon,gbc);

		gbc.gridx++;
		inputPanel.add(txt_maHoaDon,gbc);
		

		gbc.gridy++;
		gbc.gridx=0;
		inputPanel.add(lb_ngayLapHoaDon,gbc);
		
		gbc.gridx++;
		inputPanel.add(txt_ngayLapHoaDon,gbc);

		gbc.gridx++;
		
		inputPanel.add(lb_maPhong,gbc);

		gbc.gridx++;
		inputPanel.add(txt_maPhong,gbc);
		
		gbc.gridy++;
		gbc.gridx=0;
		inputPanel.add(lb_donGia,gbc);

		gbc.gridx++;
		inputPanel.add(txt_donGia,gbc);

		gbc.gridx++;
		inputPanel.add(lb_soGioThue,gbc);

		gbc.gridx++;
		inputPanel.add(txt_soGioThue,gbc);

		gbc.gridx++;
		inputPanel.add(lb_soNgayThue,gbc);

		gbc.gridx++;
		inputPanel.add(txt_soNgayThue,gbc);

		gbc.gridy++;
		gbc.gridx=0;
		inputPanel.add(lb_ThanhTien,gbc);

		gbc.gridx++;
		inputPanel.add(txt_ThanhTien,gbc);

		//buttonPanel
		JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
		buttonPanel.add(resetButton);
        buttonPanel.add(findButton);
		

		//table_hoaDon
		loadHoaDon();
		table_hoaDon.getSelectionModel().addListSelectionListener(e -> {//Sự kiện click bảng
            if (!e.getValueIsAdjusting()) {
				clearTextField();
                showSelectedHoaDon();
            }
        });

		//inputPanel_thongKe
		JPanel inputPanel_thongKe = new JPanel(new GridBagLayout());

		gbc.gridx=0;
		gbc.gridy=0;
		inputPanel_thongKe.add(soLuongButton,gbc);

		gbc.gridx++;
		inputPanel_thongKe.add(lb_soGio,gbc);
		
		gbc.gridx++;
		inputPanel_thongKe.add(lb_soLuongHDGio,gbc);

		gbc.gridx=1;
		gbc.gridy++;
		inputPanel_thongKe.add(lb_soNgay,gbc);

		gbc.gridx++;
		inputPanel_thongKe.add(lb_soLuongHDNgay,gbc);

		gbc.gridx=0;
		gbc.gridy++;
		inputPanel_thongKe.add(lb_thang,gbc);

		gbc.gridx++;
		inputPanel_thongKe.add(cb_thang,gbc);

		gbc.gridx++;
		inputPanel_thongKe.add(lb_nam,gbc);

		gbc.gridx++;
		inputPanel_thongKe.add(txt_nam,gbc);

		gbc.gridx=0;
		gbc.gridy++;
		inputPanel_thongKe.add(lb_tieudeThanhTien,gbc);

		gbc.gridx++;
		inputPanel_thongKe.add(lb_tbThanhTien,gbc);

		//hoaDonPanel
		JPanel hoaDonPanel = new JPanel(new BorderLayout());
        hoaDonPanel.add(new JScrollPane(table_hoaDon), BorderLayout.CENTER);
        hoaDonPanel.add(inputPanel, BorderLayout.NORTH);
       	hoaDonPanel.add(buttonPanel, BorderLayout.SOUTH);

		//thongKePanel
		JPanel thongKePanel = new JPanel();
		thongKePanel.setLayout(new BorderLayout());
		thongKePanel.add(inputPanel_thongKe,BorderLayout.NORTH);
		thongKePanel.add(new JScrollPane(table_thongKe),BorderLayout.CENTER);

		//tabbedPane
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Quản Lý Hoá Đơn", hoaDonPanel);
		tabbedPane.addTab("Thống Kê",thongKePanel);
		tabbedPane.addChangeListener(e -> {
			//Load lại danh sách hoaDon khi chuyển sang tab mới
			loadHoaDon();
			txt_nam.setText("");
        });

		//mainPanel
		JPanel mainPanel = new JPanel(new CardLayout());
		mainPanel.add(tabbedPane);

        this.setTitle("Hệ thống quản lý hoá đơn");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 500);
        this.add(mainPanel);
		this.setLocationRelativeTo(null);
	}
	//Methods
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addButton){
			addHoaDon();
		}
		else if(e.getSource() == updateButton){
			updateHoaDon();
		}
		else if(e.getSource() == deleteButton){
			deleteHoaDon();
		}
		else if(e.getSource() == findButton){
			findHoaDon();
		}
		else if(e.getSource() == soLuongButton){
			thongKeSoLuong();
		}else if(e.getSource() == txt_nam){
			tbThanhTien();
		}
		else{
			clearTextField();
			loadHoaDon();
		}
	}
	public void tbThanhTien(){
		loadHoaDon(); 
		int selectedRow = cb_thang.getSelectedIndex();
		String input = txt_nam.getText();
		if(!input.isEmpty()){
			try {
				int nam = Integer.parseInt(input);
				commandProcessor.execute(new tbThanhTienCommand(hoaDonService, null,selectedRow + 1,nam));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Đầu vào không hơp lệ. Vui lòng nhập số");
			}
		}
		else{
			JOptionPane.showMessageDialog(null,"Vui lòng nhập năm muốn thống kê");
		}
		
	}
	private void clearTextField(){
		txt_donGia.setText("");
		txt_maHoaDon.setText("");
		txt_maPhong.setText("");
		txt_ngayLapHoaDon.setText("");
		txt_soGioThue.setText("");
		txt_soNgayThue.setText("");
		txt_tenKhachHang.setText("");
		cb_loaiHoaDon.setSelectedItem("Hoá Đơn Giờ");
	}
	private void addHoaDon(){
		if(txt_donGia.getText().equals("")||txt_maPhong.getText().equals("")|
				txt_ngayLapHoaDon.getText().equals("")||
				txt_tenKhachHang.getText().equals("")
				) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ");
		}else{
			String date =txt_ngayLapHoaDon.getText();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			tenKhachHang = txt_tenKhachHang.getText();
			maPhong =Integer.parseInt(txt_maPhong.getText());
			donGia = Double.parseDouble(txt_donGia.getText());
			try {
				dateFormat.setLenient(false);
				ngayLapHoaDon = dateFormat.parse(date);

				int year = Calendar.getInstance().get(Calendar.YEAR);

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(ngayLapHoaDon);
				int nhapyear = calendar.get(Calendar.YEAR);
				if(nhapyear>year){
                       JOptionPane.showMessageDialog(null, "Năm không hợp lệ");
				}else{

					if(txt_soGioThue.getText().isEmpty()){
						soNgayThue = Integer.parseInt(txt_soNgayThue.getText());	
						hoaDonNgay hoaDonNgay = new hoaDonNgay(maHoaDon, ngayLapHoaDon, tenKhachHang, maPhong, donGia, soNgayThue,thanhTien);
						commandProcessor.execute(new addCommand(hoaDonService, hoaDonNgay));
					}
					else{
						soGioThue = Integer.parseInt(txt_soGioThue.getText());
						hoaDonGio hoaDonGio = new hoaDonGio(1, ngayLapHoaDon, tenKhachHang, maPhong, donGia, soGioThue,thanhTien);
						commandProcessor.execute(new addCommand(hoaDonService, hoaDonGio));
					}
					loadHoaDon();
					clearTextField();
				}
			} catch (ParseException e) {
				//e.printStackTrace();
				JOptionPane.showMessageDialog(null,"Sai định dạng");
			}
		}
	}
	private void updateHoaDon(){
			if(txt_maHoaDon.getText().equals("")||txt_donGia.getText().equals("")||txt_maPhong.getText().equals("")|
				txt_ngayLapHoaDon.getText().equals("")||
				txt_tenKhachHang.getText().equals("")
				) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ");
			}else{
			maHoaDon = Integer.parseInt(txt_maHoaDon.getText());
			String date =txt_ngayLapHoaDon.getText();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			tenKhachHang = txt_tenKhachHang.getText();
			maPhong =Integer.parseInt(txt_maPhong.getText());
			donGia = Double.parseDouble(txt_donGia.getText());
			try {
				dateFormat.setLenient(false);
				ngayLapHoaDon = dateFormat.parse(date);

				int year = Calendar.getInstance().get(Calendar.YEAR);

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(ngayLapHoaDon);
				int nhapyear = calendar.get(Calendar.YEAR);
				if(nhapyear>year){
                       JOptionPane.showMessageDialog(null, "Năm không hợp lệ");
				}else{

					if(txt_soGioThue.getText().isEmpty()){
						soNgayThue = Integer.parseInt(txt_soNgayThue.getText());	
						hoaDonNgay hoaDonNgay = new hoaDonNgay(maHoaDon, ngayLapHoaDon, tenKhachHang, maPhong, donGia, soNgayThue,thanhTien);
						commandProcessor.execute(new updateCommand(hoaDonService, hoaDonNgay));
					}
					else{
						soGioThue = Integer.parseInt(txt_soGioThue.getText());
						hoaDonGio hoaDonGio = new hoaDonGio(1, ngayLapHoaDon, tenKhachHang, maPhong, donGia, soGioThue,thanhTien);
						commandProcessor.execute(new updateCommand(hoaDonService, hoaDonGio));
					}
					loadHoaDon();
					clearTextField();
				}
			} catch (ParseException e) {
				//e.printStackTrace();
				JOptionPane.showMessageDialog(null,"Sai định dạng");
			}
		}
	}
	private void deleteHoaDon(){
		String input = txt_maHoaDon.getText();
		if(!input.isEmpty()){
			maHoaDon = Integer.parseInt(txt_maHoaDon.getText());
			commandProcessor.execute(new deleteCommand(hoaDonService,null,maHoaDon));
			loadHoaDon();
			clearTextField();
		}else{
			JOptionPane.showMessageDialog(null,"Chọn dòng cần xoá!");
		}
	}
	private void loadHoaDon(){
		commandProcessor.execute(new loadHDCommand(hoaDonService, null));
	}
	private void showHoaDon(hoaDon hoaDon){
		    if(hoaDon instanceof hoaDonGio){
				soGioThue = ((hoaDonGio)hoaDon).getSoGioThue();
				soNgayThue = 0;
			}
			else if(hoaDon instanceof hoaDonNgay){
				soNgayThue = ((hoaDonNgay)hoaDon).getSoNgayThue();
				soGioThue = 0;
			}
			Object[] rowData ={hoaDon.getMaHoaDon(),hoaDon.getMaPhong(),hoaDon.getNgayHoaDon(),hoaDon.getTenKhachHang(),hoaDon.getDonGia(),(soGioThue > 0) ? soGioThue : "",(soNgayThue > 0) ? soNgayThue : "",hoaDon.getThanhTien()};
			table_Model.addRow(rowData);
	}
	private void findHoaDon(){
		clearTextField();
		String idStr = JOptionPane.showInputDialog(this,"Nhập hoá đơn cần tìm kiếm");
		if(idStr!=null && !idStr.isEmpty()){
			try {
				int id = Integer.parseInt(idStr);
				commandProcessor.execute(new findCommand(hoaDonService, null, id));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Đầu vào không hơp lệ. Vui lòng nhập số");
			}
		}else{
			JOptionPane.showMessageDialog(null,"Vui lòng nhập ID hoá đơn cần tìm kiếm");
		}
	}
	private void showSelectedHoaDon() {
        int selectedRow = table_hoaDon.getSelectedRow();
        if (selectedRow != -1) {
			txt_maHoaDon.setText(String.valueOf(table_hoaDon.getValueAt(selectedRow, 0)));
        	txt_maPhong.setText(String.valueOf(table_hoaDon.getValueAt(selectedRow, 1)));

        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	txt_ngayLapHoaDon.setText(dateFormat.format(table_hoaDon.getValueAt(selectedRow, 2)));

        	txt_tenKhachHang.setText(String.valueOf(table_hoaDon.getValueAt(selectedRow, 3)));
        	txt_donGia.setText(String.valueOf(table_hoaDon.getValueAt(selectedRow, 4)));

        	Object soGioThueValue = table_hoaDon.getValueAt(selectedRow, 5);
			Object soNgayThueValue = table_hoaDon.getValueAt(selectedRow, 6);
				
			if (soGioThueValue instanceof Integer) {
				soGioThue = (int) soGioThueValue;
				txt_soGioThue.setText(""+soGioThue);
				txt_soNgayThue.setText("");
				cb_loaiHoaDon.setSelectedItem("Hoá Đơn Giờ");
			} else{
				soNgayThue = (int) soNgayThueValue;
				txt_soNgayThue.setText(String.valueOf(soNgayThue));
				txt_soGioThue.setText("");
				cb_loaiHoaDon.setSelectedItem("Hoá Đơn Ngày");
			}
			txt_ThanhTien.setText(String.valueOf(table_hoaDon.getValueAt(selectedRow,7)));
        }
    }
	private void populateInputFields(hoaDon hoaDon) {
		//Hien thi thong tin len TextField
		txt_maHoaDon.setText(String.valueOf(hoaDon.getMaHoaDon()));
		txt_maPhong.setText(String.valueOf(hoaDon.getMaPhong()));
		//Dinh dang ngay
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String ngayHoaDon = dateFormat.format(hoaDon.getNgayHoaDon());
		txt_ngayLapHoaDon.setText(ngayHoaDon);
		if(hoaDon instanceof hoaDonGio){
			soGioThue = ((hoaDonGio)hoaDon).getSoGioThue();
			txt_soGioThue.setText(String.valueOf(soGioThue));
			cb_loaiHoaDon.setSelectedItem("Hoá Đơn Giờ");
		}else if(hoaDon instanceof hoaDonNgay){
			soNgayThue = ((hoaDonNgay)hoaDon).getSoNgayThue();
			txt_soNgayThue.setText(String.valueOf(soNgayThue));
			cb_loaiHoaDon.setSelectedItem("Hoá Đơn Ngày");
		}
		txt_donGia.setText(String.valueOf(hoaDon.getDonGia()));
		txt_tenKhachHang.setText(hoaDon.getTenKhachHang());
		txt_ThanhTien.setText(""+hoaDon.getThanhTien());
	}
	private void thongKeSoLuong(){
		commandProcessor.execute(new soLuongCommand(hoaDonService, null));
	}
	@Override
	public void update() {
		table_Model.setRowCount(0);
		List<hoaDon> hoaDons = hoaDonService.getHoaDons();
		for (hoaDon hoaDon : hoaDons) {
			if(hoaDons.size() == 1){
				populateInputFields(hoaDon);
			}
			showHoaDon(hoaDon);
		}
		lb_soLuongHDGio.setText("" + hoaDonService.getCountGio());
		lb_soLuongHDNgay.setText(""+hoaDonService.getCountNgay());
		lb_tbThanhTien.setText(""+hoaDonService.getTrungBinhThanhTien()+" (VNĐ / Hoá Đơn)");
	}
}
