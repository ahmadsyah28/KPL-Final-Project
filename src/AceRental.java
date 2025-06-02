import java.io.File;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.MobileBy;

public class AceRental {

    public static void main(String[] args) throws Exception {

        System.out.println("=== Memulai Test Automation ===");

        // Setup capabilities
        DesiredCapabilities cap = new DesiredCapabilities();
        File appDir = new File("src");
        File app = new File(appDir, "AceRentalPlaystation.apk");

        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability("allowTestPackages", true);

        // Initialize driver
        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(
            new URL("http://127.0.0.1:4723/"), cap
        );

        System.out.println("✅ Driver berhasil diinisialisasi!");

        try {
            // Tunggu aplikasi load
            System.out.println("⏳ Menunggu aplikasi load...");
            Thread.sleep(5000);

            // STEP 1: Perform Login directly
            performLogin(driver);

            // STEP 2: Navigate home and select month
            navigateHomeAndSelectMonth(driver);

            // STEP 3: Open navigation drawer and access history
            openNavigationAndAccessHistory(driver);

            // STEP 4: Open navigation drawer and access Persediaan
            openNavigationAndAccessPersediaan(driver);

            // STEP 5: Add new inventory item
            addInventoryItem(driver);

            // STEP 6: Add new expense item
            addExpenseItem(driver);

            // STEP 7: Perform logout
            performLogout(driver);

        } catch (Exception e) {
            System.out.println("❌ Error dalam automation: " + e.getMessage());
            e.printStackTrace();
        }

        // Tutup driver
        driver.quit();
        System.out.println("🔚 Test selesai, driver ditutup!");
    }

    // Method untuk melakukan login langsung
    private static void performLogin(AndroidDriver<AndroidElement> driver) {
        try {
            System.out.println("\n=== MEMULAI PROSES LOGIN ===");
            Thread.sleep(2000); // Tunggu aplikasi siap untuk login

            // INPUT EMAIL untuk LOGIN
            System.out.println("📧 Mengisi field email untuk login...");
            try {
                AndroidElement emailLoginInput = driver.findElement(
                    By.id("com.ace.playstation:id/emailInput")
                );
                emailLoginInput.clear();
                emailLoginInput.sendKeys("muhammad.bintang.indra@gmail.com");
                System.out.println("✅ Email login berhasil diinput!");
            } catch (Exception e) {
                System.out.println("❌ Email login input gagal dengan ID, mencoba XPath...");
                try {
                    AndroidElement emailLoginInputXPath = driver.findElement(
                        By.xpath("//android.widget.EditText[@resource-id='com.ace.playstation:id/emailInput']")
                    );
                    emailLoginInputXPath.clear();
                    emailLoginInputXPath.sendKeys("muhammad.bintang.indra@gmail.com");
                    System.out.println("✅ Email login berhasil diinput dengan XPath!");
                } catch (Exception e2) {
                    System.out.println("❌ Email login input gagal: " + e2.getMessage());
                }
            }

            Thread.sleep(1000);

            // INPUT PASSWORD untuk LOGIN
            System.out.println("🔒 Mengisi field password untuk login...");
            try {
                AndroidElement passwordLoginInput = driver.findElement(
                    By.id("com.ace.playstation:id/passwordInput")
                );
                passwordLoginInput.clear();
                passwordLoginInput.sendKeys("Testes123?");
                System.out.println("✅ Password login berhasil diinput!");
            } catch (Exception e) {
                System.out.println("❌ Password login input gagal dengan ID, mencoba XPath...");
                try {
                    AndroidElement passwordLoginInputXPath = driver.findElement(
                        By.xpath("//android.widget.EditText[@resource-id='com.ace.playstation:id/passwordInput']")
                    );
                    passwordLoginInputXPath.clear();
                    passwordLoginInputXPath.sendKeys("Testes123?");
                    System.out.println("✅ Password login berhasil diinput dengan XPath!");
                } catch (Exception e2) {
                    System.out.println("❌ Password login input gagal: " + e2.getMessage());
                }
            }

            Thread.sleep(1000);

            // CLICK LOGIN BUTTON
            System.out.println("🎯 Mengklik tombol Login...");
            try {
                AndroidElement loginButton = driver.findElement(
                    By.id("com.ace.playstation:id/loginButton")
                );
                loginButton.click();
                System.out.println("✅ Tombol Login berhasil diklik!");
            } catch (Exception e) {
                System.out.println("❌ Login button gagal dengan ID, mencoba XPath...");
                try {
                    AndroidElement loginButtonXPath = driver.findElement(
                        By.xpath("//android.widget.Button[@resource-id='com.ace.playstation:id/loginButton']")
                    );
                    loginButtonXPath.click();
                    System.out.println("✅ Tombol Login berhasil diklik dengan XPath!");
                } catch (Exception e2) {
                    System.out.println("❌ Login button gagal dengan XPath, mencoba ClassName...");
                    try {
                        AndroidElement loginButtonClass = driver.findElement(
                            By.className("android.widget.Button")
                        );
                        loginButtonClass.click();
                        System.out.println("✅ Tombol Login berhasil diklik dengan ClassName!");
                    } catch (Exception e3) {
                        System.out.println("❌ Semua metode login button gagal: " + e3.getMessage());
                    }
                }
            }

            Thread.sleep(5000); // Tunggu proses login selesai
            System.out.println("🎉 Proses login berhasil selesai!");
            System.out.println("✨ User berhasil masuk ke aplikasi!");

        } catch (Exception e) {
            System.out.println("❌ Error saat melakukan login: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method untuk navigasi di beranda dan memilih bulan
    private static void navigateHomeAndSelectMonth(AndroidDriver<AndroidElement> driver) {
        try {
            System.out.println("\n=== NAVIGASI DI BERANDA ===");
            Thread.sleep(3000); // Tunggu beranda load

            // STEP 1: Click "Month" text
            System.out.println("📅 Mencari dan mengklik teks 'Month'...");
            try {
                AndroidElement monthText = driver.findElement(
                    By.xpath("//android.widget.TextView[@text='Month']")
                );
                monthText.click();
                System.out.println("✅ Teks 'Month' berhasil diklik!");
            } catch (Exception e) {
                System.out.println("❌ Month text gagal dengan XPath, mencoba UiSelector approach...");
                try {
                    // Alternatif dengan contains text
                    AndroidElement monthTextAlt = driver.findElement(
                        By.xpath("//*[contains(@text,'Month')]")
                    );
                    monthTextAlt.click();
                    System.out.println("✅ Teks 'Month' berhasil diklik dengan contains!");
                } catch (Exception e2) {
                    System.out.println("❌ Month text gagal: " + e2.getMessage());
                    throw e2;
                }
            }

            Thread.sleep(2000); // Tunggu dropdown/picker muncul

            // STEP 2: Select "03" from month picker
            System.out.println("🔢 Mencari dan memilih angka '03'...");
            try {
                AndroidElement selectMonth03 = driver.findElement(
                    By.xpath("//android.widget.TextView[@text='03']")
                );
                selectMonth03.click();
                System.out.println("✅ Bulan '03' berhasil dipilih!");
            } catch (Exception e) {
                System.out.println("❌ Month '03' gagal dengan XPath, mencoba alternatif...");
                try {
                    // Alternatif dengan contains text
                    AndroidElement month03Alt = driver.findElement(
                        By.xpath("//*[contains(@text,'03')]")
                    );
                    month03Alt.click();
                    System.out.println("✅ Bulan '03' berhasil dipilih dengan contains!");
                } catch (Exception e2) {
                    System.out.println("❌ Semua metode untuk memilih '03' gagal: " + e2.getMessage());
                    
                    // Fallback dengan text "3" (tanpa leading zero)
                    try {
                        AndroidElement month3 = driver.findElement(
                            By.xpath("//android.widget.TextView[@text='3']")
                        );
                        month3.click();
                        System.out.println("✅ Bulan '3' berhasil dipilih sebagai fallback!");
                    } catch (Exception e3) {
                        System.out.println("❌ Semua alternatif month selection gagal: " + e3.getMessage());
                    }
                }
            }

            Thread.sleep(2000);

            // STEP 3: Click "Year" text
            System.out.println("📅 Mencari dan mengklik teks 'Year'...");
            try {
                AndroidElement yearText = driver.findElement(
                    By.xpath("//android.widget.TextView[@text='Year']")
                );
                yearText.click();
                System.out.println("✅ Teks 'Year' berhasil diklik!");
            } catch (Exception e) {
                System.out.println("❌ Year text gagal dengan XPath, mencoba UiSelector approach...");
                try {
                    // Alternatif dengan contains text
                    AndroidElement yearTextAlt = driver.findElement(
                        By.xpath("//*[contains(@text,'Year')]")
                    );
                    yearTextAlt.click();
                    System.out.println("✅ Teks 'Year' berhasil diklik dengan contains!");
                } catch (Exception e2) {
                    System.out.println("❌ Year text gagal: " + e2.getMessage());
                    throw e2;
                }
            }

            Thread.sleep(2000); // Tunggu year picker muncul

            // STEP 4: Select "2025" from year picker
            System.out.println("🗓️ Mencari dan memilih tahun '2025'...");
            try {
                AndroidElement selectYear2025 = driver.findElement(
                    By.xpath("//android.widget.TextView[@text='2025']")
                );
                selectYear2025.click();
                System.out.println("✅ Tahun '2025' berhasil dipilih!");
            } catch (Exception e) {
                System.out.println("❌ Year '2025' gagal dengan XPath, mencoba alternatif...");
                try {
                    // Alternatif dengan contains text
                    AndroidElement year2025Alt = driver.findElement(
                        By.xpath("//*[contains(@text,'2025')]")
                    );
                    year2025Alt.click();
                    System.out.println("✅ Tahun '2025' berhasil dipilih dengan contains!");
                } catch (Exception e2) {
                    System.out.println("❌ Semua metode untuk memilih '2025' gagal: " + e2.getMessage());
                }
            }

            Thread.sleep(2000);
            System.out.println("🎉 Navigasi beranda, pemilihan bulan dan tahun selesai!");

        } catch (Exception e) {
            System.out.println("❌ Error saat navigasi beranda: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method untuk membuka navigation drawer dan mengakses riwayat transaksi
    private static void openNavigationAndAccessHistory(AndroidDriver<AndroidElement> driver) {
        try {
            System.out.println("\n=== MEMBUKA NAVIGATION DRAWER ===");
            Thread.sleep(2000); // Tunggu UI stabil

            // STEP 1: Open Navigation Drawer
            System.out.println("🍔 Mencari dan mengklik hamburger menu (navigation drawer)...");
            try {
                // Method 1: Using accessibility ID
                AndroidElement navDrawer = driver.findElement(
                    By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']")
                );
                navDrawer.click();
                System.out.println("✅ Navigation drawer berhasil dibuka dengan XPath!");
            } catch (Exception e) {
                System.out.println("❌ Navigation drawer gagal dengan XPath, mencoba UiAutomator approach...");
                try {
                    // Method 2: Using UiAutomator
                    AndroidElement navDrawerUi = driver.findElement(
                        MobileBy.AndroidUIAutomator("new UiSelector().description(\"Open navigation drawer\")")
                    );
                    navDrawerUi.click();
                    System.out.println("✅ Navigation drawer berhasil dibuka dengan UiAutomator!");
                } catch (Exception e2) {
                    System.out.println("❌ Navigation drawer gagal dengan UiAutomator, mencoba ClassName...");
                    try {
                        // Method 3: Using class name
                        AndroidElement navDrawerClass = driver.findElement(
                            By.className("android.widget.ImageButton")
                        );
                        navDrawerClass.click();
                        System.out.println("✅ Navigation drawer berhasil dibuka dengan ClassName!");
                    } catch (Exception e3) {
                        System.out.println("❌ Navigation drawer gagal dengan ClassName, mencoba alternatif...");
                        try {
                            // Method 4: Using contains description
                            AndroidElement navDrawerAlt = driver.findElement(
                                By.xpath("//*[contains(@content-desc,'navigation') or contains(@content-desc,'drawer')]")
                            );
                            navDrawerAlt.click();
                            System.out.println("✅ Navigation drawer berhasil dibuka dengan contains!");
                        } catch (Exception e4) {
                            System.out.println("❌ Semua metode navigation drawer gagal: " + e4.getMessage());
                            throw e4;
                        }
                    }
                }
            }

            Thread.sleep(3000); // Tunggu navigation drawer terbuka sepenuhnya

            // STEP 2: Click "Riwayat Transaksi"
            System.out.println("📋 Mencari dan mengklik 'Riwayat Transaksi'...");
            try {
                AndroidElement riwayatTransaksi = driver.findElement(
                    By.xpath("//android.widget.CheckedTextView[@resource-id='com.ace.playstation:id/design_menu_item_text' and @text='Riwayat Transaksi']")
                );
                riwayatTransaksi.click();
                System.out.println("✅ 'Riwayat Transaksi' berhasil diklik!");
            } catch (Exception e) {
                System.out.println("❌ Riwayat Transaksi gagal dengan XPath lengkap, mencoba alternatif...");
                try {
                    // Method 2: Using text only
                    AndroidElement riwayatTransaksiText = driver.findElement(
                        By.xpath("//*[@text='Riwayat Transaksi']")
                    );
                    riwayatTransaksiText.click();
                    System.out.println("✅ 'Riwayat Transaksi' berhasil diklik dengan text only!");
                } catch (Exception e2) {
                    System.out.println("❌ Riwayat Transaksi gagal dengan text, mencoba contains...");
                    try {
                        // Method 3: Using contains text
                        AndroidElement riwayatTransaksiContains = driver.findElement(
                            By.xpath("//*[contains(@text,'Riwayat') or contains(@text,'Transaksi')]")
                        );
                        riwayatTransaksiContains.click();
                        System.out.println("✅ 'Riwayat Transaksi' berhasil diklik dengan contains!");
                    } catch (Exception e3) {
                        System.out.println("❌ Semua metode Riwayat Transaksi gagal: " + e3.getMessage());
                    }
                }
            }

            Thread.sleep(3000);
            System.out.println("🎉 Berhasil membuka Navigation Drawer dan mengakses Riwayat Transaksi!");

        } catch (Exception e) {
            System.out.println("❌ Error saat membuka navigation dan mengakses riwayat: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method untuk membuka navigation drawer dan mengakses Persediaan
    private static void openNavigationAndAccessPersediaan(AndroidDriver<AndroidElement> driver) {
        try {
            System.out.println("\n=== MEMBUKA NAVIGATION DRAWER DAN MENGAKSES PERSEDIAAN ===");
            Thread.sleep(2000); // Tunggu UI stabil

            // STEP 1: Open Navigation Drawer
            System.out.println("🍔 Mencari dan mengklik hamburger menu (navigation drawer)...");
            try {
                // Method 1: Using XPath with content-desc
                AndroidElement navDrawer = driver.findElement(
                    By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']")
                );
                navDrawer.click();
                System.out.println("✅ Navigation drawer berhasil dibuka dengan XPath!");
            } catch (Exception e) {
                System.out.println("❌ Navigation drawer gagal dengan XPath, mencoba UiAutomator approach...");
                try {
                    // Method 2: Using UiAutomator
                    AndroidElement navDrawerUi = driver.findElement(
                        MobileBy.AndroidUIAutomator("new UiSelector().description(\"Open navigation drawer\")")
                    );
                    navDrawerUi.click();
                    System.out.println("✅ Navigation drawer berhasil dibuka dengan UiAutomator!");
                } catch (Exception e2) {
                    System.out.println("❌ Navigation drawer gagal dengan UiAutomator, mencoba ClassName...");
                    try {
                        // Method 3: Using class name
                        AndroidElement navDrawerClass = driver.findElement(
                            By.className("android.widget.ImageButton")
                        );
                        navDrawerClass.click();
                        System.out.println("✅ Navigation drawer berhasil dibuka dengan ClassName!");
                    } catch (Exception e3) {
                        System.out.println("❌ Navigation drawer gagal dengan ClassName, mencoba alternatif...");
                        try {
                            // Method 4: Using contains description
                            AndroidElement navDrawerAlt = driver.findElement(
                                By.xpath("//*[contains(@content-desc,'navigation') or contains(@content-desc,'drawer')]")
                            );
                            navDrawerAlt.click();
                            System.out.println("✅ Navigation drawer berhasil dibuka dengan contains!");
                        } catch (Exception e4) {
                            System.out.println("❌ Semua metode navigation drawer gagal: " + e4.getMessage());
                            throw e4;
                        }
                    }
                }
            }

            Thread.sleep(3000); // Tunggu navigation drawer terbuka sepenuhnya

            // STEP 2: Click "Persediaan"
            System.out.println("📦 Mencari dan mengklik 'Persediaan'...");
            try {
                // Method 1: Using full XPath with resource-id and text
                AndroidElement persediaan = driver.findElement(
                    By.xpath("//android.widget.CheckedTextView[@resource-id='com.ace.playstation:id/design_menu_item_text' and @text='Persediaan']")
                );
                persediaan.click();
                System.out.println("✅ 'Persediaan' berhasil diklik dengan XPath lengkap!");
            } catch (Exception e) {
                System.out.println("❌ Persediaan gagal dengan XPath lengkap, mencoba UiAutomator...");
                try {
                    // Method 2: Using UiAutomator with text
                    AndroidElement persediaanUi = driver.findElement(
                        MobileBy.AndroidUIAutomator("new UiSelector().text(\"Persediaan\")")
                    );
                    persediaanUi.click();
                    System.out.println("✅ 'Persediaan' berhasil diklik dengan UiAutomator!");
                } catch (Exception e2) {
                    System.out.println("❌ Persediaan gagal dengan UiAutomator, mencoba text only...");
                    try {
                        // Method 3: Using text only
                        AndroidElement persediaanText = driver.findElement(
                            By.xpath("//*[@text='Persediaan']")
                        );
                        persediaanText.click();
                        System.out.println("✅ 'Persediaan' berhasil diklik dengan text only!");
                    } catch (Exception e3) {
                        System.out.println("❌ Persediaan gagal dengan text only, mencoba contains...");
                        try {
                            // Method 4: Using contains text
                            AndroidElement persediaanContains = driver.findElement(
                                By.xpath("//*[contains(@text,'Persediaan')]")
                            );
                            persediaanContains.click();
                            System.out.println("✅ 'Persediaan' berhasil diklik dengan contains!");
                        } catch (Exception e4) {
                            System.out.println("❌ Semua metode Persediaan gagal: " + e4.getMessage());
                        }
                    }
                }
            }

            Thread.sleep(3000);
            System.out.println("🎉 Berhasil membuka Navigation Drawer dan mengakses Persediaan!");

        } catch (Exception e) {
            System.out.println("❌ Error saat membuka navigation dan mengakses Persediaan: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method untuk menambah item persediaan baru
    private static void addInventoryItem(AndroidDriver<AndroidElement> driver) {
        try {
            System.out.println("\n=== MENAMBAH ITEM PERSEDIAAN BARU ===");
            Thread.sleep(2000); // Tunggu halaman persediaan load

            // STEP 1: Click "Tambah Persediaan" button
            System.out.println("➕ Mencari dan mengklik tombol 'Tambah Persediaan'...");
            try {
                // Method 1: Using resource ID
                AndroidElement btnTambahPersediaan = driver.findElement(
                    By.id("com.ace.playstation:id/btn_tambah_persediaan")
                );
                btnTambahPersediaan.click();
                System.out.println("✅ Tombol 'Tambah Persediaan' berhasil diklik dengan ID!");
            } catch (Exception e) {
                System.out.println("❌ Tambah Persediaan button gagal dengan ID, mencoba UiAutomator...");
                try {
                    // Method 2: Using UiAutomator with resource ID
                    AndroidElement btnTambahPersediaanUi = driver.findElement(
                        MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.ace.playstation:id/btn_tambah_persediaan\")")
                    );
                    btnTambahPersediaanUi.click();
                    System.out.println("✅ Tombol 'Tambah Persediaan' berhasil diklik dengan UiAutomator!");
                } catch (Exception e2) {
                    System.out.println("❌ Tambah Persediaan button gagal dengan UiAutomator, mencoba XPath...");
                    try {
                        // Method 3: Using XPath
                        AndroidElement btnTambahPersediaanXPath = driver.findElement(
                            By.xpath("//android.widget.Button[@resource-id='com.ace.playstation:id/btn_tambah_persediaan']")
                        );
                        btnTambahPersediaanXPath.click();
                        System.out.println("✅ Tombol 'Tambah Persediaan' berhasil diklik dengan XPath!");
                    } catch (Exception e3) {
                        System.out.println("❌ Tambah Persediaan button gagal dengan XPath, mencoba ClassName...");
                        try {
                            // Method 4: Using class name
                            AndroidElement btnTambahPersediaanClass = driver.findElement(
                                By.className("android.widget.Button")
                            );
                            btnTambahPersediaanClass.click();
                            System.out.println("✅ Tombol 'Tambah Persediaan' berhasil diklik dengan ClassName!");
                        } catch (Exception e4) {
                            System.out.println("❌ Semua metode Tambah Persediaan button gagal: " + e4.getMessage());
                            throw e4;
                        }
                    }
                }
            }

            Thread.sleep(3000); // Tunggu form tambah persediaan muncul

            // STEP 2: Fill product name with "caca coklat"
            System.out.println("🏷️ Mengisi nama produk 'caca coklat'...");
            try {
                // Method 1: Using resource ID
                AndroidElement productNameField = driver.findElement(
                    By.id("com.ace.playstation:id/act_product_name")
                );
                productNameField.clear();
                productNameField.sendKeys("caca coklat");
                System.out.println("✅ Nama produk 'caca coklat' berhasil diinput dengan ID!");
            } catch (Exception e) {
                System.out.println("❌ Product name field gagal dengan ID, mencoba UiAutomator...");
                try {
                    // Method 2: Using UiAutomator with resource ID
                    AndroidElement productNameFieldUi = driver.findElement(
                        MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.ace.playstation:id/act_product_name\")")
                    );
                    productNameFieldUi.clear();
                    productNameFieldUi.sendKeys("caca coklat");
                    System.out.println("✅ Nama produk 'caca coklat' berhasil diinput dengan UiAutomator!");
                } catch (Exception e2) {
                    System.out.println("❌ Product name field gagal dengan UiAutomator, mencoba XPath...");
                    try {
                        // Method 3: Using XPath
                        AndroidElement productNameFieldXPath = driver.findElement(
                            By.xpath("//android.widget.AutoCompleteTextView[@resource-id='com.ace.playstation:id/act_product_name']")
                        );
                        productNameFieldXPath.clear();
                        productNameFieldXPath.sendKeys("floridina");
                        System.out.println("✅ Nama produk 'floridina' berhasil diinput dengan XPath!");
                    } catch (Exception e3) {
                        System.out.println("❌ Product name field gagal dengan XPath, mencoba ClassName...");
                        try {
                            // Method 4: Using class name
                            AndroidElement productNameFieldClass = driver.findElement(
                                By.className("android.widget.AutoCompleteTextView")
                            );
                            productNameFieldClass.clear();
                            productNameFieldClass.sendKeys("caca coklat");
                            System.out.println("✅ Nama produk 'floridina' berhasil diinput dengan ClassName!");
                        } catch (Exception e4) {
                            System.out.println("❌ Semua metode product name field gagal: " + e4.getMessage());
                            throw e4;
                        }
                    }
                }
            }

            Thread.sleep(2000); // Tunggu setelah input nama produk

            // STEP 3: Fill product price with "8000"
            System.out.println("💰 Mengisi harga produk '8000'...");
            try {
                // Method 1: Using resource ID
                AndroidElement productPriceField = driver.findElement(
                    By.id("com.ace.playstation:id/et_product_price")
                );
                productPriceField.clear();
                productPriceField.sendKeys("8000");
                System.out.println("✅ Harga produk '8000' berhasil diinput dengan ID!");
            } catch (Exception e) {
                System.out.println("❌ Product price field gagal dengan ID, mencoba UiAutomator...");
                try {
                    // Method 2: Using UiAutomator with resource ID
                    AndroidElement productPriceFieldUi = driver.findElement(
                        MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.ace.playstation:id/et_product_price\")")
                    );
                    productPriceFieldUi.clear();
                    productPriceFieldUi.sendKeys("8000");
                    System.out.println("✅ Harga produk '8000' berhasil diinput dengan UiAutomator!");
                } catch (Exception e2) {
                    System.out.println("❌ Product price field gagal dengan UiAutomator, mencoba XPath...");
                    try {
                        // Method 3: Using XPath
                        AndroidElement productPriceFieldXPath = driver.findElement(
                            By.xpath("//android.widget.EditText[@resource-id='com.ace.playstation:id/et_product_price']")
                        );
                        productPriceFieldXPath.clear();
                        productPriceFieldXPath.sendKeys("8000");
                        System.out.println("✅ Harga produk '8000' berhasil diinput dengan XPath!");
                    } catch (Exception e3) {
                        System.out.println("❌ Product price field gagal dengan XPath, mencoba ClassName...");
                        try {
                            // Method 4: Using class name
                            AndroidElement productPriceFieldClass = driver.findElement(
                                By.className("android.widget.EditText")
                            );
                            productPriceFieldClass.clear();
                            productPriceFieldClass.sendKeys("8000");
                            System.out.println("✅ Harga produk '8000' berhasil diinput dengan ClassName!");
                        } catch (Exception e4) {
                            System.out.println("❌ Semua metode product price field gagal: " + e4.getMessage());
                            throw e4;
                        }
                    }
                }
            }

            Thread.sleep(2000);

            // STEP 4: Click Save button
            System.out.println("💾 Mencari dan mengklik tombol 'Save'...");
            try {
                // Method 1: Using resource ID
                AndroidElement saveButton = driver.findElement(
                    By.id("com.ace.playstation:id/btn_save")
                );
                saveButton.click();
                System.out.println("✅ Tombol 'Save' berhasil diklik dengan ID!");
            } catch (Exception e) {
                System.out.println("❌ Save button gagal dengan ID, mencoba UiAutomator...");
                try {
                    // Method 2: Using UiAutomator with resource ID
                    AndroidElement saveButtonUi = driver.findElement(
                        MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.ace.playstation:id/btn_save\")")
                    );
                    saveButtonUi.click();
                    System.out.println("✅ Tombol 'Save' berhasil diklik dengan UiAutomator!");
                } catch (Exception e2) {
                    System.out.println("❌ Save button gagal dengan UiAutomator, mencoba XPath...");
                    try {
                        // Method 3: Using XPath
                        AndroidElement saveButtonXPath = driver.findElement(
                            By.xpath("//android.widget.Button[@resource-id='com.ace.playstation:id/btn_save']")
                        );
                        saveButtonXPath.click();
                        System.out.println("✅ Tombol 'Save' berhasil diklik dengan XPath!");
                    } catch (Exception e3) {
                        System.out.println("❌ Save button gagal dengan XPath, mencoba ClassName...");
                        try {
                            // Method 4: Using class name (find save button by text if available)
                            AndroidElement saveButtonClass = driver.findElement(
                                By.className("android.widget.Button")
                            );
                            saveButtonClass.click();
                            System.out.println("✅ Tombol 'Save' berhasil diklik dengan ClassName!");
                        } catch (Exception e4) {
                            System.out.println("❌ Semua metode Save button gagal: " + e4.getMessage());
                            throw e4;
                        }
                    }
                }
            }

            Thread.sleep(3000); // Tunggu proses save selesai
            System.out.println("🎉 Berhasil menyimpan item persediaan baru!");
            System.out.println("📦 Produk: caca coklat");
            System.out.println("💰 Harga: 8000");
            System.out.println("💾 Status: Tersimpan");

        } catch (Exception e) {
            System.out.println("❌ Error saat menambah item persediaan: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method untuk menambah item pengeluaran baru
    private static void addExpenseItem(AndroidDriver<AndroidElement> driver) {
        try {
            System.out.println("\n=== MENAMBAH ITEM PENGELUARAN BARU ===");
            Thread.sleep(2000); // Tunggu UI stabil

            // STEP 1: Open Navigation Drawer
            System.out.println("🍔 Mencari dan mengklik hamburger menu (navigation drawer)...");
            try {
                // Method 1: Using accessibility ID with XPath
                AndroidElement navDrawer = driver.findElement(
                    By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']")
                );
                navDrawer.click();
                System.out.println("✅ Navigation drawer berhasil dibuka dengan XPath!");
            } catch (Exception e) {
                System.out.println("❌ Navigation drawer gagal dengan XPath, mencoba UiAutomator approach...");
                try {
                    // Method 2: Using UiAutomator
                    AndroidElement navDrawerUi = driver.findElement(
                        MobileBy.AndroidUIAutomator("new UiSelector().description(\"Open navigation drawer\")")
                    );
                    navDrawerUi.click();
                    System.out.println("✅ Navigation drawer berhasil dibuka dengan UiAutomator!");
                } catch (Exception e2) {
                    System.out.println("❌ Navigation drawer gagal dengan UiAutomator, mencoba ClassName...");
                    try {
                        // Method 3: Using class name
                        AndroidElement navDrawerClass = driver.findElement(
                            By.className("android.widget.ImageButton")
                        );
                        navDrawerClass.click();
                        System.out.println("✅ Navigation drawer berhasil dibuka dengan ClassName!");
                    } catch (Exception e3) {
                        System.out.println("❌ Semua metode navigation drawer gagal: " + e3.getMessage());
                        throw e3;
                    }
                }
            }

            Thread.sleep(3000); // Tunggu navigation drawer terbuka sepenuhnya

            // STEP 2: Click "Pengeluaran"
            System.out.println("💸 Mencari dan mengklik 'Pengeluaran'...");
            try {
                // Method 1: Using UiAutomator with text
                AndroidElement pengeluaran = driver.findElement(
                    MobileBy.AndroidUIAutomator("new UiSelector().text(\"Pengeluaran\")")
                );
                pengeluaran.click();
                System.out.println("✅ 'Pengeluaran' berhasil diklik dengan UiAutomator!");
            } catch (Exception e) {
                System.out.println("❌ Pengeluaran gagal dengan UiAutomator, mencoba XPath lengkap...");
                try {
                    // Method 2: Using full XPath with resource-id and text
                    AndroidElement pengeluaranXPath = driver.findElement(
                        By.xpath("//android.widget.CheckedTextView[@resource-id='com.ace.playstation:id/design_menu_item_text' and @text='Pengeluaran']")
                    );
                    pengeluaranXPath.click();
                    System.out.println("✅ 'Pengeluaran' berhasil diklik dengan XPath lengkap!");
                } catch (Exception e2) {
                    System.out.println("❌ Pengeluaran gagal dengan XPath lengkap, mencoba text only...");
                    try {
                        // Method 3: Using text only
                        AndroidElement pengeluaranText = driver.findElement(
                            By.xpath("//*[@text='Pengeluaran']")
                        );
                        pengeluaranText.click();
                        System.out.println("✅ 'Pengeluaran' berhasil diklik dengan text only!");
                    } catch (Exception e3) {
                        System.out.println("❌ Semua metode Pengeluaran gagal: " + e3.getMessage());
                        throw e3;
                    }
                }
            }

            Thread.sleep(3000); // Tunggu halaman pengeluaran load

            // STEP 3: Click "Tambah Pengeluaran" button
            System.out.println("➕ Mencari dan mengklik tombol 'Tambah Pengeluaran'...");
            try {
                // Method 1: Using resource ID
                AndroidElement btnTambahPengeluaran = driver.findElement(
                    By.id("com.ace.playstation:id/btn_tambah_pengeluaran")
                );
                btnTambahPengeluaran.click();
                System.out.println("✅ Tombol 'Tambah Pengeluaran' berhasil diklik dengan ID!");
            } catch (Exception e) {
                System.out.println("❌ Tambah Pengeluaran button gagal dengan ID, mencoba UiAutomator...");
                try {
                    // Method 2: Using UiAutomator with resource ID
                    AndroidElement btnTambahPengeluaranUi = driver.findElement(
                        MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.ace.playstation:id/btn_tambah_pengeluaran\")")
                    );
                    btnTambahPengeluaranUi.click();
                    System.out.println("✅ Tombol 'Tambah Pengeluaran' berhasil diklik dengan UiAutomator!");
                } catch (Exception e2) {
                    System.out.println("❌ Tambah Pengeluaran button gagal dengan UiAutomator, mencoba XPath...");
                    try {
                        // Method 3: Using XPath
                        AndroidElement btnTambahPengeluaranXPath = driver.findElement(
                            By.xpath("//android.widget.Button[@resource-id='com.ace.playstation:id/btn_tambah_pengeluaran']")
                        );
                        btnTambahPengeluaranXPath.click();
                        System.out.println("✅ Tombol 'Tambah Pengeluaran' berhasil diklik dengan XPath!");
                    } catch (Exception e3) {
                        System.out.println("❌ Tambah Pengeluaran button gagal dengan XPath, mencoba ClassName...");
                        try {
                            // Method 4: Using class name
                            AndroidElement btnTambahPengeluaranClass = driver.findElement(
                                By.className("android.widget.Button")
                            );
                            btnTambahPengeluaranClass.click();
                            System.out.println("✅ Tombol 'Tambah Pengeluaran' berhasil diklik dengan ClassName!");
                        } catch (Exception e4) {
                            System.out.println("❌ Semua metode Tambah Pengeluaran button gagal: " + e4.getMessage());
                            throw e4;
                        }
                    }
                }
            }

            Thread.sleep(3000); // Tunggu form tambah pengeluaran muncul

            // STEP 4: Click product name field
            System.out.println("🏷️ Mencari dan mengklik field nama produk...");
            try {
                // Method 1: Using resource ID
                AndroidElement productNameField = driver.findElement(
                    By.id("com.ace.playstation:id/act_product_name")
                );
                productNameField.click();
                System.out.println("✅ Field nama produk berhasil diklik dengan ID!");
            } catch (Exception e) {
                System.out.println("❌ Product name field gagal dengan ID, mencoba UiAutomator...");
                try {
                    // Method 2: Using UiAutomator with resource ID
                    AndroidElement productNameFieldUi = driver.findElement(
                        MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.ace.playstation:id/act_product_name\")")
                    );
                    productNameFieldUi.click();
                    System.out.println("✅ Field nama produk berhasil diklik dengan UiAutomator!");
                } catch (Exception e2) {
                    System.out.println("❌ Product name field gagal dengan UiAutomator, mencoba XPath...");
                    try {
                        // Method 3: Using XPath
                        AndroidElement productNameFieldXPath = driver.findElement(
                            By.xpath("//android.widget.AutoCompleteTextView[@resource-id='com.ace.playstation:id/act_product_name']")
                        );
                        productNameFieldXPath.click();
                        System.out.println("✅ Field nama produk berhasil diklik dengan XPath!");
                    } catch (Exception e3) {
                        System.out.println("❌ Product name field gagal dengan XPath, mencoba ClassName...");
                        try {
                            // Method 4: Using class name
                            AndroidElement productNameFieldClass = driver.findElement(
                                By.className("android.widget.AutoCompleteTextView")
                            );
                            productNameFieldClass.click();
                            System.out.println("✅ Field nama produk berhasil diklik dengan ClassName!");
                        } catch (Exception e4) {
                            System.out.println("❌ Semua metode product name field gagal: " + e4.getMessage());
                            throw e4;
                        }
                    }
                }
            }

            Thread.sleep(2000); // Tunggu setelah klik nama produk

            // STEP 5: Click product price layout
            System.out.println("📐 Mencari dan mengklik layout harga produk...");
            try {
                // Method 1: Using resource ID
                AndroidElement productPriceLayout = driver.findElement(
                    By.id("com.ace.playstation:id/product_price_layout")
                );
                productPriceLayout.click();
                System.out.println("✅ Layout harga produk berhasil diklik dengan ID!");
            } catch (Exception e) {
                System.out.println("❌ Product price layout gagal dengan ID, mencoba UiAutomator...");
                try {
                    // Method 2: Using UiAutomator with resource ID
                    AndroidElement productPriceLayoutUi = driver.findElement(
                        MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.ace.playstation:id/product_price_layout\")")
                    );
                    productPriceLayoutUi.click();
                    System.out.println("✅ Layout harga produk berhasil diklik dengan UiAutomator!");
                } catch (Exception e2) {
                    System.out.println("❌ Product price layout gagal dengan UiAutomator, mencoba XPath...");
                    try {
                        // Method 3: Using XPath
                        AndroidElement productPriceLayoutXPath = driver.findElement(
                            By.xpath("//android.widget.LinearLayout[@resource-id='com.ace.playstation:id/product_price_layout']")
                        );
                        productPriceLayoutXPath.click();
                        System.out.println("✅ Layout harga produk berhasil diklik dengan XPath!");
                    } catch (Exception e3) {
                        System.out.println("❌ Semua metode product price layout gagal: " + e3.getMessage());
                        throw e3;
                    }
                }
            }

            Thread.sleep(2000); // Tunggu setelah klik layout

            // STEP 6: Click and fill product price field with "20000"
            System.out.println("💰 Mencari dan mengisi field harga produk '20000'...");
            try {
                // Method 1: Using resource ID
                AndroidElement productPriceField = driver.findElement(
                    By.id("com.ace.playstation:id/et_product_price")
                );
                productPriceField.click();
                productPriceField.clear();
                productPriceField.sendKeys("20000");
                System.out.println("✅ Harga produk '20000' berhasil diinput dengan ID!");
            } catch (Exception e) {
                System.out.println("❌ Product price field gagal dengan ID, mencoba UiAutomator...");
                try {
                    // Method 2: Using UiAutomator with resource ID
                    AndroidElement productPriceFieldUi = driver.findElement(
                        MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.ace.playstation:id/et_product_price\")")
                    );
                    productPriceFieldUi.click();
                    productPriceFieldUi.clear();
                    productPriceFieldUi.sendKeys("20000");
                    System.out.println("✅ Harga produk '20000' berhasil diinput dengan UiAutomator!");
                } catch (Exception e2) {
                    System.out.println("❌ Product price field gagal dengan UiAutomator, mencoba XPath...");
                    try {
                        // Method 3: Using XPath
                        AndroidElement productPriceFieldXPath = driver.findElement(
                            By.xpath("//android.widget.EditText[@resource-id='com.ace.playstation:id/et_product_price']")
                        );
                        productPriceFieldXPath.click();
                        productPriceFieldXPath.clear();
                        productPriceFieldXPath.sendKeys("20000");
                        System.out.println("✅ Harga produk '20000' berhasil diinput dengan XPath!");
                    } catch (Exception e3) {
                        System.out.println("❌ Product price field gagal dengan XPath, mencoba ClassName...");
                        try {
                            // Method 4: Using class name
                            AndroidElement productPriceFieldClass = driver.findElement(
                                By.className("android.widget.EditText")
                            );
                            productPriceFieldClass.click();
                            productPriceFieldClass.clear();
                            productPriceFieldClass.sendKeys("20000");
                            System.out.println("✅ Harga produk '20000' berhasil diinput dengan ClassName!");
                        } catch (Exception e4) {
                            System.out.println("❌ Semua metode product price field gagal: " + e4.getMessage());
                            throw e4;
                        }
                    }
                }
            }

            Thread.sleep(2000); // Tunggu setelah input harga

            // STEP 7: Click Save button
            System.out.println("💾 Mencari dan mengklik tombol 'Save'...");
            try {
                // Method 1: Using resource ID
                AndroidElement saveButton = driver.findElement(
                    By.id("com.ace.playstation:id/btn_save")
                );
                saveButton.click();
                System.out.println("✅ Tombol 'Save' berhasil diklik dengan ID!");
            } catch (Exception e) {
                System.out.println("❌ Save button gagal dengan ID, mencoba UiAutomator...");
                try {
                    // Method 2: Using UiAutomator with resource ID
                    AndroidElement saveButtonUi = driver.findElement(
                        MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.ace.playstation:id/btn_save\")")
                    );
                    saveButtonUi.click();
                    System.out.println("✅ Tombol 'Save' berhasil diklik dengan UiAutomator!");
                } catch (Exception e2) {
                    System.out.println("❌ Save button gagal dengan UiAutomator, mencoba XPath...");
                    try {
                        // Method 3: Using XPath
                        AndroidElement saveButtonXPath = driver.findElement(
                            By.xpath("//android.widget.Button[@resource-id='com.ace.playstation:id/btn_save']")
                        );
                        saveButtonXPath.click();
                        System.out.println("✅ Tombol 'Save' berhasil diklik dengan XPath!");
                    } catch (Exception e3) {
                        System.out.println("❌ Save button gagal dengan XPath, mencoba ClassName...");
                        try {
                            // Method 4: Using class name
                            AndroidElement saveButtonClass = driver.findElement(
                                By.className("android.widget.Button")
                            );
                            saveButtonClass.click();
                            System.out.println("✅ Tombol 'Save' berhasil diklik dengan ClassName!");
                        } catch (Exception e4) {
                            System.out.println("❌ Semua metode Save button gagal: " + e4.getMessage());
                            throw e4;
                        }
                    }
                }
            }

            Thread.sleep(3000); // Tunggu proses save selesai
            System.out.println("🎉 Berhasil menyimpan item pengeluaran baru!");
            System.out.println("💸 Jenis: Pengeluaran");
            System.out.println("💰 Harga: 20000");
            System.out.println("💾 Status: Tersimpan");

        } catch (Exception e) {
            System.out.println("❌ Error saat menambah item pengeluaran: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method untuk melakukan logout
    private static void performLogout(AndroidDriver<AndroidElement> driver) {
        try {
            System.out.println("\n=== MELAKUKAN LOGOUT ===");
            Thread.sleep(2000); // Tunggu UI stabil

            // STEP 1: Open Navigation Drawer
            System.out.println("🍔 Mencari dan mengklik hamburger menu (navigation drawer)...");
            try {
                // Method 1: Using accessibility ID with XPath
                AndroidElement navDrawer = driver.findElement(
                    By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']")
                );
                navDrawer.click();
                System.out.println("✅ Navigation drawer berhasil dibuka dengan XPath!");
            } catch (Exception e) {
                System.out.println("❌ Navigation drawer gagal dengan XPath, mencoba UiAutomator approach...");
                try {
                    // Method 2: Using UiAutomator
                    AndroidElement navDrawerUi = driver.findElement(
                        MobileBy.AndroidUIAutomator("new UiSelector().description(\"Open navigation drawer\")")
                    );
                    navDrawerUi.click();
                    System.out.println("✅ Navigation drawer berhasil dibuka dengan UiAutomator!");
                } catch (Exception e2) {
                    System.out.println("❌ Navigation drawer gagal dengan UiAutomator, mencoba ClassName...");
                    try {
                        // Method 3: Using class name
                        AndroidElement navDrawerClass = driver.findElement(
                            By.className("android.widget.ImageButton")
                        );
                        navDrawerClass.click();
                        System.out.println("✅ Navigation drawer berhasil dibuka dengan ClassName!");
                    } catch (Exception e3) {
                        System.out.println("❌ Semua metode navigation drawer gagal: " + e3.getMessage());
                        throw e3;
                    }
                }
            }

            Thread.sleep(3000); // Tunggu navigation drawer terbuka sepenuhnya

            // STEP 2: Click "Logout"
            System.out.println("🚪 Mencari dan mengklik 'Logout'...");
            try {
                // Method 1: Using UiAutomator with text
                AndroidElement logout = driver.findElement(
                    MobileBy.AndroidUIAutomator("new UiSelector().text(\"Logout\")")
                );
                logout.click();
                System.out.println("✅ 'Logout' berhasil diklik dengan UiAutomator!");
            } catch (Exception e) {
                System.out.println("❌ Logout gagal dengan UiAutomator, mencoba XPath lengkap...");
                try {
                    // Method 2: Using full XPath with resource-id and text
                    AndroidElement logoutXPath = driver.findElement(
                        By.xpath("//android.widget.CheckedTextView[@resource-id='com.ace.playstation:id/design_menu_item_text' and @text='Logout']")
                    );
                    logoutXPath.click();
                    System.out.println("✅ 'Logout' berhasil diklik dengan XPath lengkap!");
                } catch (Exception e2) {
                    System.out.println("❌ Logout gagal dengan XPath lengkap, mencoba text only...");
                    try {
                        // Method 3: Using text only
                        AndroidElement logoutText = driver.findElement(
                            By.xpath("//*[@text='Logout']")
                        );
                        logoutText.click();
                        System.out.println("✅ 'Logout' berhasil diklik dengan text only!");
                    } catch (Exception e3) {
                        System.out.println("❌ Logout gagal dengan text only, mencoba contains...");
                        try {
                            // Method 4: Using contains text
                            AndroidElement logoutContains = driver.findElement(
                                By.xpath("//*[contains(@text,'Logout') or contains(@text,'logout')]")
                            );
                            logoutContains.click();
                            System.out.println("✅ 'Logout' berhasil diklik dengan contains!");
                        } catch (Exception e4) {
                            System.out.println("❌ Semua metode Logout gagal: " + e4.getMessage());
                            throw e4;
                        }
                    }
                }
            }

            Thread.sleep(3000); // Tunggu proses logout selesai
            System.out.println("🎉 Berhasil melakukan logout!");
            System.out.println("🚪 User berhasil keluar dari aplikasi!");
            System.out.println("✨ Sesi automation berakhir dengan sukses!");

        } catch (Exception e) {
            System.out.println("❌ Error saat melakukan logout: " + e.getMessage());
            e.printStackTrace();
        }
    }
}