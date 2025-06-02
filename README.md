# AceRental PlayStation - Automasi Android

🤖 **Suite testing otomatis untuk aplikasi mobile AceRental PlayStation menggunakan Appium dan Java**


## 🎯 Gambaran Umum

Proyek ini menyediakan testing otomatis end-to-end yang komprehensif untuk aplikasi mobile AceRental PlayStation. Automasi ini mencakup perjalanan lengkap pengguna dari login hingga logout, termasuk fitur manajemen persediaan dan pencatatan pengeluaran.

## ✨ Fitur-Fitur

- 🔐 **Autentikasi Pengguna** - Login otomatis dengan kredensial
- 📅 **Pemilihan Tanggal** - Automasi pemilih bulan dan tahun
- 📋 **Riwayat Transaksi** - Akses dan navigasi
- 📦 **Manajemen Persediaan** - Tambah produk baru dengan harga
- 💸 **Manajemen Pengeluaran** - Catat dan simpan pengeluaran
- 🚪 **Manajemen Sesi** - Fungsi logout yang bersih
- 🔄 **Error Handling Robust** - Strategi fallback berlapis
- 📝 **Logging Detail** - Laporan testing komprehensif

## 🛠 Persyaratan Sistem

Sebelum menjalankan automasi ini, pastikan Anda telah menginstal:

### Kebutuhan Software
- **Java JDK 8+** - Runtime bahasa pemrograman
- **Android Studio** - Untuk Android SDK dan emulator
- **Appium Server 1.20+** - Framework automasi mobile
- **Maven** - Manajemen dependensi (jika menggunakan Maven)
- **Node.js 12+** - Diperlukan untuk Appium

### Kebutuhan Hardware
- **Android Emulator** atau **Perangkat Android Fisik**
- **Minimum 4GB RAM** untuk performa emulator yang stabil
- **USB Debugging aktif** (untuk perangkat fisik)

## 🚀 Instalasi & Setup

### 1. Clone Repository
```bash
git clone https://github.com/ahmadsyah28/KPL-Final-Project.git
cd KPL-Final-Project
```

### 2. Install Dependensi

#### Menggunakan Maven:
```bash
mvn clean install
```

#### Menggunakan Gradle:
```bash
./gradlew clean build
```

### 3. Setup Environment Android
```bash
# Set variabel environment ANDROID_HOME
export ANDROID_HOME=$HOME/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/platform-tools:$ANDROID_HOME/tools
```

### 4. Install Appium
```bash
npm install -g appium
npm install -g appium-doctor

# Verifikasi instalasi
appium-doctor --android
```

### 5. Jalankan Appium Server
```bash
appium --port 4723
```

### 6. Setup Android Emulator
```bash
# Buat emulator (jika belum ada)
avd create --name TestEmulator --package "system-images;android-29;google_apis;x86"

# Jalankan emulator
emulator -avd TestEmulator
```

## ⚙️ Konfigurasi

### Konfigurasi Perangkat
Update capabilities di `AceRental.java`:

```java
// Untuk Emulator
cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");

// Untuk Perangkat Fisik
cap.setCapability(MobileCapabilityType.DEVICE_NAME, "nama-perangkat-anda");
cap.setCapability(MobileCapabilityType.UDID, "udid-perangkat-anda");
```

### Konfigurasi Aplikasi
Letakkan file APK di lokasi yang benar:
```
src/
└── AceRentalPlaystation.apk
```

### Kredensial Login
Update kredensial di method `performLogin()`:
```java
emailLoginInput.sendKeys("email-anda@gmail.com");
passwordLoginInput.sendKeys("password-anda");
```

## 🏃‍♂️ Menjalankan Test

### Metode 1: Eksekusi IDE
1. Buka proyek di IDE Anda (IntelliJ IDEA/Eclipse)
2. Navigasi ke `AceRental.java`
3. Klik kanan dan pilih "Run"

### Metode 2: Command Line
```bash
# Menggunakan Maven
mvn exec:java -Dexec.mainClass="AceRental"

# Menggunakan Gradle
./gradlew run

# Eksekusi Java langsung
javac -cp ".:lib/*" AceRental.java
java -cp ".:lib/*" AceRental
```

### Metode 3: Test Suite
```bash
# Jalankan sebagai test suite
mvn test
```

## 🔄 Alur Testing

Automasi melakukan urutan berikut:

### 1. 🔐 **Autentikasi Pengguna**
- Meluncurkan aplikasi AceRental PlayStation
- Input kredensial email
- Input password
- Submit form login

### 2. 📅 **Navigasi Beranda**
- Navigasi ke dashboard beranda
- Pilih bulan (03)
- Pilih tahun (2025)

### 3. 📋 **Riwayat Transaksi**
- Buka navigation drawer
- Akses riwayat transaksi
- Verifikasi keberhasilan navigasi

### 4. 📦 **Manajemen Persediaan**
- Navigasi ke bagian persediaan
- Tambah produk baru: "caca coklat"
- Set harga: 8000
- Simpan item persediaan

### 5. 💸 **Manajemen Pengeluaran**
- Akses bagian pengeluaran
- Buat entri pengeluaran baru
- Set jumlah: 20000
- Simpan catatan pengeluaran

### 6. 🚪 **Pembersihan Sesi**
- Buka navigation drawer
- Lakukan logout
- Konfirmasi penghentian sesi

## 📁 Struktur Proyek

```
ace-rental-automation/
├── src/
│   ├── AceRental.java              # Kelas automasi utama
│   └── AceRentalPlaystation.apk    # Aplikasi yang ditest
├── target/                         # Kelas yang dikompilasi
├── logs/                          # Log eksekusi test
├── screenshots/                   # Screenshot kegagalan test
├── pom.xml                        # Konfigurasi Maven
├── README.md                      # Dokumentasi proyek
└── .gitignore                     # Aturan git ignore
```

## 🔧 Troubleshooting

### Masalah Umum & Solusi

#### 1. **Koneksi Appium Server Gagal**
```bash
# Cek apakah Appium berjalan
ps aux | grep appium

# Restart Appium server
pkill -f appium
appium --port 4723
```

#### 2. **Emulator Tidak Terdeteksi**
```bash
# List perangkat yang tersedia
adb devices

# Restart ADB
adb kill-server
adb start-server
```

#### 3. **Error Element Not Found**
- Verifikasi locator element menggunakan Appium Inspector
- Cek apakah elemen UI telah berubah
- Tingkatkan waktu tunggu jika diperlukan

#### 4. **Masalah Instalasi APK**
```bash
# Instalasi APK manual
adb install src/AceRentalPlaystation.apk

# Verifikasi instalasi
adb shell pm list packages | grep ace
```

#### 5. **Masalah Performa**
- Tingkatkan alokasi RAM emulator
- Tutup aplikasi yang tidak perlu
- Gunakan akselerasi hardware jika tersedia

### Mode Debug
Aktifkan logging detail:
```java
// Tambahkan ke capabilities
cap.setCapability("appium:enablePerformanceLogging", true);
cap.setCapability("appium:printPageSourceOnFindFailure", true);
```

## 📊 Laporan Testing

### Output Console
Automasi menyediakan feedback console real-time:
```
=== Memulai Test Automation ===
✅ Driver berhasil diinisialisasi!
⏳ Menunggu aplikasi load...

=== MEMULAI PROSES LOGIN ===
📧 Mengisi field email untuk login...
✅ Email login berhasil diinput!
🔒 Mengisi field password untuk login...
✅ Password login berhasil diinput!
🎯 Mengklik tombol Login...
✅ Tombol Login berhasil diklik!
🎉 Proses login berhasil selesai!
```

### Laporan Kustom
Kembangkan framework untuk menyertakan:
- Laporan test HTML
- Capture screenshot saat gagal
- Metrik performa
- Video eksekusi test

## 🤝 Kontribusi

### Panduan Development
1. **Style Kode**: Ikuti konvensi Java
2. **Error Handling**: Implementasikan strategi fallback berlapis
3. **Logging**: Tambahkan output console yang deskriptif
4. **Dokumentasi**: Update README untuk fitur baru

### Mengirim Perubahan
1. Fork repository
2. Buat branch fitur (`git checkout -b feature/fitur-amazing`)
3. Commit perubahan (`git commit -m 'Tambah fitur amazing'`)
4. Push ke branch (`git push origin feature/fitur-amazing`)
5. Buka Pull Request

### Sumber Daya Berguna
- [Dokumentasi Appium](http://appium.io/docs/)
- [Selenium WebDriver](https://selenium-python.readthedocs.io/)
- [Android Testing](https://developer.android.com/training/testing)
- [Dokumentasi Java](https://docs.oracle.com/javase/)

---

## 📝 Lisensi

Proyek ini dilisensikan di bawah Lisensi MIT - lihat file [LICENSE](LICENSE) untuk detail.

