package KumpulanCodingan;
import java.util.Scanner;

class Customer {
    String name;
    String number;
    String type;
    String pin;
    double balance;
    boolean blocked = false;
    int pinAttempts = 0;

    Customer(String name, String number, String type, String pin, double balance) {
        this.name = name;
        this.number = number;
        this.type = type;
        this.pin = pin;
        this.balance = balance;
    }
}

public class TransaksiTiny {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Database pelanggan (contoh otomatis)
        Customer[] customers = {
            new Customer("Andi", "3812345678", "silver", "1234", 2000000),
            new Customer("Budi", "5698765432", "gold", "5678", 3500000),
            new Customer("Citra", "7411122233", "platinum", "9999", 5000000)
        };

        int choice;
        do {
            System.out.println("\n===== SISTEM TRANSAKSI SWALAYAN TINY =====");
            System.out.println("1. Pembelian");
            System.out.println("2. Top Up");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            choice = in.nextInt();
            in.nextLine();

            if (choice == 1 || choice == 2) {

                System.out.print("Masukkan nomor pelanggan: ");
                String num = in.nextLine();

                Customer c = findCustomer(customers, num);
                if (c == null) {
                    System.out.println("Akun tidak ditemukan!");
                    continue;
                }

                if (c.blocked) {
                    System.out.println("Akun telah diblokir dan tidak dapat digunakan!");
                    continue;
                }

                if (!authenticate(in, c)) {
                    System.out.println("Akun diblokir karena 3x salah PIN!");
                    continue;
                }

                if (choice == 1) {
                    System.out.print("Masukkan nominal pembelian: ");
                    double price = in.nextDouble();

                    double cashback = calculateCashback(c.type, price);

                    if (c.balance - price + cashback < 10000) {
                        System.out.println("Transaksi gagal! Saldo tidak boleh kurang dari 10.000");
                    } else {
                        c.balance = c.balance - price + cashback;
                        System.out.println("Transaksi berhasil!");
                        System.out.println("Cashback diterima: " + cashback);
                        System.out.println("Saldo akhir: " + c.balance);
                    }

                } else if (choice == 2) {
                    System.out.print("Nominal top up: ");
                    double amount = in.nextDouble();
                    c.balance += amount;
                    System.out.println("Top up berhasil! Saldo baru: " + c.balance);
                }
            }

        } while (choice != 3);

        System.out.println("Terima kasih!");
    }

    static Customer findCustomer(Customer[] arr, String num) {
        for (Customer c : arr) {
            if (c.number.equals(num)) return c;
        }
        return null;
    }

    static boolean authenticate(Scanner in, Customer c) {
        while (c.pinAttempts < 3) {
            System.out.print("Masukkan PIN: ");
            String input = in.nextLine();

            if (input.equals(c.pin)) {
                c.pinAttempts = 0;
                return true;
            }

            c.pinAttempts++;
            System.out.println("PIN salah! Percobaan: " + c.pinAttempts);
        }
        c.blocked = true;
        return false;
    }

    static double calculateCashback(String type, double price) {
        if (type.equals("silver")) {
            return (price > 1000000) ? price * 0.05 : 0;
        }
        if (type.equals("gold")) {
            return (price > 1000000) ? price * 0.07 : price * 0.02;
        }
        if (type.equals("platinum")) {
            return (price > 1000000) ? price * 0.10 : price * 0.05;
        }
        return 0;
    }
}
