import java.util.Scanner;

/**
 *
 * @author 
 * Antonia Tatarianingsih Abur
 * 2301010287
 * 10 Mei 2025
 */
class Record {
    private int id;
    private String data;

    public Record() {
        this.id = 0;
        this.data = "";
    }

    public Record(int id, String data) {
        this.id = id;
        this.data = data;
    }

    // getter and setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // getter and setter for data
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    // Override toString method to display data nicely
    @Override
    public String toString() {
        return "ID: " + id + ", Data: " + data;
    }
}

public class CatatanApp {
    private Record[] records;
    private int recordCount;
    private final int MAX_RECORDS = 10;

    public CatatanApp() {
        records = new Record[MAX_RECORDS];
        recordCount = 0;
    }

    public void addRecord(String data) {
        if (recordCount >= MAX_RECORDS) {
            System.out.println("Data storage penuh, tidak bisa menambah data lagi.");
            return;
        }
        // assign id as next number starting from 1
        int newId = (recordCount == 0) ? 1 : records[recordCount - 1].getId() + 1;
        records[recordCount] = new Record(newId, data);
        recordCount++;
        System.out.println("Data berhasil ditambahkan dengan ID: " + newId);
    }

    public void showRecords() {
        if (recordCount == 0) {
            System.out.println("Belum ada data yang tersimpan.");
            return;
        }
        System.out.println("Daftar Data:");
        for (int i = 0; i < recordCount; i++) {
            System.out.println(records[i]);
        }
    }

    public void updateRecord(int id, String newData) {
        int idx = findIndexById(id);
        if (idx == -1) {
            System.out.println("Data dengan ID " + id + " tidak ditemukan.");
            return;
        }
        records[idx].setData(newData);
        System.out.println("Data dengan ID " + id + " berhasil diperbarui.");
    }

    public void deleteRecord(int id) {
        int idx = findIndexById(id);
        if (idx == -1) {
            System.out.println("Data dengan ID " + id + " tidak ditemukan.");
            return;
        }
        // Shift elements left to overwrite deleted record
        for (int i = idx; i < recordCount - 1; i++) {
            records[i] = records[i + 1];
        }
        records[recordCount - 1] = null; // remove last duplicate
        recordCount--;
        System.out.println("Data dengan ID " + id + " berhasil dihapus.");
    }

    private int findIndexById(int id) {
        for (int i = 0; i < recordCount; i++) {
            if (records[i].getId() == id) {
                return i;
            }
        }
        return -1; // not found
    }

    private void menu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n=== Menu Aplikasi ===");
            System.out.println("1. Input Data");
            System.out.println("2. Tampilkan Data");
            System.out.println("3. Ubah Data");
            System.out.println("4. Hapus Data");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");

            while (!scanner.hasNextInt()) {
                System.out.print("Masukkan angka yang valid (1-5): ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Masukkan data: ");
                    String dataInput = scanner.nextLine();
                    addRecord(dataInput);
                    break;
                case 2:
                    showRecords();
                    break;
                case 3:
                    System.out.print("Masukkan ID data yang ingin diubah: ");
                    int updateId = readInt(scanner);
                    if (updateId != -1) {
                        System.out.print("Masukkan data baru: ");
                        String newData = scanner.nextLine();
                        updateRecord(updateId, newData);
                    }
                    break;
                case 4:
                    System.out.print("Masukkan ID data yang ingin dihapus: ");
                    int deleteId = readInt(scanner);
                    if (deleteId != -1) {
                        deleteRecord(deleteId);
                    }
                    break;
                case 5:
                    System.out.println("Keluar dari program. Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
                    break;
            }
        } while (choice != 5);

        scanner.close();
    }

    private int readInt(Scanner scanner) {
        if (!scanner.hasNextInt()) {
            System.out.println("Input tidak valid, harus angka.");
            scanner.nextLine(); // discard invalid input
            return -1;
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }

    public static void main(String[] args) {
        CatatanApp app = new CatatanApp();
        app.menu();
    }
}

