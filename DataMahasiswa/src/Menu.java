import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Menu extends JFrame{
    public static void main(String[] args) {
        // buat object window
        Menu window = new Menu();

        // atur ukuran window
        window.setSize(480, 560);
        // letakkan window di tengah layar
        window.setLocationRelativeTo(null);
        // isi window
        window.setContentPane(window.mainPanel);
        // ubah warna background
        window.getContentPane().setBackground(Color.white);
        // tampilkan window
        window.setVisible(true);
        // agar program ikut berhenti saat window diclose
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua mahasiswa
    private ArrayList<Mahasiswa> listMahasiswa;

    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox jenisKelaminComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;
    private JSlider semesterSlider; // New component
    private JLabel semesterLabel; // New label
    private JLabel semesterValueLabel; // To display the current value

    // constructor
    public Menu() {
        // inisialisasi listMahasiswa
        listMahasiswa = new ArrayList<>();

        // isi listMahasiswa
        populateList();

        // isi tabel mahasiswa
        mahasiswaTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] jenisKelaminData = {"", "Laki-laki", "Perempuan"};
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel(jenisKelaminData));

        // Configure semester slider
        semesterSlider.setMinimum(1);
        semesterSlider.setMaximum(8);
        semesterSlider.setValue(1);
        semesterSlider.setMajorTickSpacing(1);
        semesterSlider.setPaintTicks(true);
        semesterSlider.setPaintLabels(true);
        semesterValueLabel.setText("1");
        
        semesterSlider.addChangeListener(e -> {
            semesterValueLabel.setText(String.valueOf(semesterSlider.getValue()));
        });

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });
        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0) {
                    deleteData();
                }
            }
        });
        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saat tombol
                clearForm();
            }
        });
        // saat salah satu baris tabel ditekan
        mahasiswaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = mahasiswaTable.getSelectedRow();

                // simpan value textfield dan combo box
                String selectedNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();
                String selectedNama = mahasiswaTable.getModel().getValueAt(selectedIndex, 2).toString();
                String selectedJenisKelamin = mahasiswaTable.getModel().getValueAt(selectedIndex, 3).toString();
                int selectedSemester = Integer.parseInt(mahasiswaTable.getModel().getValueAt(selectedIndex, 4).toString());

                // ubah isi textfield dan combo box
                nimField.setText(selectedNim);
                namaField.setText(selectedNama);
                jenisKelaminComboBox.setSelectedItem(selectedJenisKelamin);
                semesterSlider.setValue(selectedSemester);

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");
                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] column = {"No", "NIM", "Nama", "Jenis Kelamin", "Semester"};

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel temp = new DefaultTableModel(null, column);

        // isi tabel dengan listMahasiswa
        for (int i = 0; i < listMahasiswa.size(); i++) {
            Object[] row = new Object[5];
            row[0] = i + 1;
            row[1] = listMahasiswa.get(i).getNim();
            row[2] = listMahasiswa.get(i).getNama();
            row[3] = listMahasiswa.get(i).getJenisKelamin();
            row[4] = listMahasiswa.get(i).getSemester();

            temp.addRow(row);
        }

        return temp;
    }

    public void insertData() {
        // ambil value dari textfield dan combobox
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        int semester = semesterSlider.getValue();

        // tambahkan data ke dalam list
        listMahasiswa.add(new Mahasiswa(nim, nama, jenisKelamin, semester));

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Insert berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
    }

    public void updateData() {
        // ambil data dari form
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        int semester = semesterSlider.getValue();

        // ubah data mahasiswa di list
        listMahasiswa.get(selectedIndex).setNim(nim);
        listMahasiswa.get(selectedIndex).setNama(nama);
        listMahasiswa.get(selectedIndex).setJenisKelamin(jenisKelamin);
        listMahasiswa.get(selectedIndex).setSemester(semester);

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Update Berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhaasil diubah!");
    }

    public void deleteData() {
        // hapus data dari list
        listMahasiswa.remove(selectedIndex);

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Delete berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
    }

    public void clearForm() {
        // kosongkan semua texfield dan combo box
        nimField.setText("");
        namaField.setText("");
        jenisKelaminComboBox.setSelectedItem("");
        semesterSlider.setValue(1);

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");
        // sembunyikan button delete
        deleteButton.setVisible(false);
        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }

    private void populateList() {
        listMahasiswa.add(new Mahasiswa("2301001", "Aisha Nurul Izzah", "Perempuan", 3));
        listMahasiswa.add(new Mahasiswa("2302002", "Budi Santoso", "Laki-laki", 4));
        listMahasiswa.add(new Mahasiswa("2303003", "Citra Dewi Anggraeni", "Perempuan", 2));
        listMahasiswa.add(new Mahasiswa("2304004", "Daffa Pratama", "Laki-laki", 1));
        listMahasiswa.add(new Mahasiswa("2305005", "Eka Wulandari", "Perempuan", 5));
        listMahasiswa.add(new Mahasiswa("2306006", "Fajar Setiawan", "Laki-laki", 3));
        listMahasiswa.add(new Mahasiswa("2307007", "Gita Ayu Lestari", "Perempuan", 6));
        listMahasiswa.add(new Mahasiswa("2308008", "Hendra Kurniawan", "Laki-laki", 2));
        listMahasiswa.add(new Mahasiswa("2309009", "Indah Permata Sari", "Perempuan", 4));
        listMahasiswa.add(new Mahasiswa("2310010", "Joko Prasetyo", "Laki-laki", 3));
        listMahasiswa.add(new Mahasiswa("2311011", "Kartika Sari", "Perempuan", 5));
        listMahasiswa.add(new Mahasiswa("2312012", "Luki Hermawan", "Laki-laki", 2));
        listMahasiswa.add(new Mahasiswa("2313013", "Maya Indah Puspita", "Perempuan", 1));
        listMahasiswa.add(new Mahasiswa("2314014", "Nanda Putra Pratama", "Laki-laki", 3));
        listMahasiswa.add(new Mahasiswa("2315015", "Oki Setiawan", "Laki-laki", 4));
        listMahasiswa.add(new Mahasiswa("2316016", "Putri Ayu Lestari", "Perempuan", 6));
        listMahasiswa.add(new Mahasiswa("2317017", "Rizki Ramadhan", "Laki-laki", 2));
        listMahasiswa.add(new Mahasiswa("2318018", "Sari Dewi Utami", "Perempuan", 3));
        listMahasiswa.add(new Mahasiswa("2319019", "Teguh Prasetyo", "Laki-laki", 5));
        listMahasiswa.add(new Mahasiswa("2320020", "Umi Kulsum", "Perempuan", 4));
    }
}
