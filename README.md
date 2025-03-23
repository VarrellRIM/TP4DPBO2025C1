# TP 4 - Mahasiswa Management System

Aplikasi manajemen data mahasiswa sederhana menggunakan Java Swing.

## Janji
Saya Varrell Rizky Irvanni Mahkota dengan NIM 2306245 mengerjakan Tugas Praktikum 4 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

## Desain Program

### Struktur Kelas

Program ini terdiri dari dua kelas utama:

1. **Kelas Mahasiswa**
   - Merupakan kelas model yang menyimpan data mahasiswa
   - Atribut:
     - `nim` (String): Nomor Induk Mahasiswa
     - `nama` (String): Nama mahasiswa
     - `jenisKelamin` (String): Jenis kelamin mahasiswa (Laki-laki/Perempuan)
     - `semester` (int): Semester yang sedang ditempuh mahasiswa (1-8)
   - Method:
     - Constructor, getter, dan setter untuk semua atribut

2. **Kelas Menu**
   - Merupakan kelas utama yang mengimplementasikan antarmuka pengguna (UI)
   - Extends JFrame untuk membuat aplikasi desktop
   - Menggunakan komponen Swing seperti JTextField, JComboBox, JSlider, JTable, dll.
   - Menyediakan fungsionalitas CRUD (Create, Read, Update, Delete) untuk data mahasiswa

### Komponen UI

1. **Form Input**:
   - Field NIM (JTextField)
   - Field Nama (JTextField)
   - Dropdown Jenis Kelamin (JComboBox)
   - Slider Semester (JSlider) - komponen baru yang ditambahkan
   - Tombol Add/Update, Cancel, dan Delete

2. **Tabel Data**:
   - Menampilkan semua data mahasiswa dalam bentuk tabel
   - Kolom: No, NIM, Nama, Jenis Kelamin, Semester

## Alur Program

### 1. Inisialisasi

- Program dimulai dari method `main()` di kelas Menu
- Membuat instance Menu dan menampilkan window aplikasi
- Inisialisasi ArrayList untuk menyimpan data mahasiswa
- Mengisi data awal mahasiswa melalui method `populateList()`
- Menampilkan data dalam tabel

### 2. Menambah Data Mahasiswa

- User mengisi form (NIM, Nama, Jenis Kelamin, dan Semester)
- User menekan tombol "Add"
- Program mengambil nilai dari form dan membuat objek Mahasiswa baru
- Objek Mahasiswa ditambahkan ke ArrayList
- Tabel diperbarui untuk menampilkan data baru
- Form dikosongkan

### 3. Mengubah Data Mahasiswa

- User mengklik baris pada tabel yang ingin diubah
- Data dari baris tersebut ditampilkan di form
- Tombol "Add" berubah menjadi "Update" dan tombol "Delete" muncul
- User mengubah data pada form
- User menekan tombol "Update"
- Program mengupdate data mahasiswa di ArrayList
- Tabel diperbarui
- Form dikosongkan

### 4. Menghapus Data Mahasiswa

- User mengklik baris pada tabel yang ingin dihapus
- User menekan tombol "Delete"
- Program menghapus data mahasiswa dari ArrayList
- Tabel diperbarui
- Form dikosongkan

### 5. Membatalkan Input/Update

- User menekan tombol "Cancel"
- Form dikosongkan
- Tombol "Update" kembali menjadi "Add"
- Tombol "Delete" disembunyikan

## Fitur Baru: Slider Semester

Aplikasi ini telah ditambahkan fitur baru berupa slider untuk memilih semester mahasiswa:

- Slider memiliki rentang nilai 1-8 (mewakili semester 1 hingga 8)
- Dilengkapi dengan tick marks dan label untuk memudahkan pemilihan
- Nilai semester yang dipilih ditampilkan di sebelah slider
- Nilai semester disimpan dalam objek Mahasiswa dan ditampilkan di tabel

## Dokumentasi
https://github.com/user-attachments/assets/f2f1fcf7-2551-4e7d-89fb-0eddf211fcf3






## Catatan Implementasi

- Aplikasi menggunakan ArrayList untuk menyimpan data mahasiswa secara sementara (tidak persisten)
- Data akan hilang ketika aplikasi ditutup
