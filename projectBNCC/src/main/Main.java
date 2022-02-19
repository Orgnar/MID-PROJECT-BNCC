package main;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	Vector<Employee> vEmployee = new Vector();
	Vector<Integer> vKode = new Vector();
	Vector<Integer> vIndex = new Vector();
	Vector<Integer> vManager = new Vector();
	Vector<Integer> vSupervisor = new Vector();
	Vector<Integer> vAdmin = new Vector();

	Vector<String> ID = new Vector();
	Employee employee;
	Random rand = new Random();
	Scanner scan = new Scanner(System.in);
	int size1 = 3;
	int size2 = 3;
	int size3 = 3;

	public Main() {
		int choice;
		do {
			menu();
			choice = getIntegerInput(">>");
			switch (choice) {
			case 1:
				addEmployee();
				break;
			case 2:
				if (vEmployee.size() <= 0) {
					System.out.println("Tidak ada karyawan!");
					break;
				}
				viewEmployee();
				break;
			case 3:
				if (vEmployee.size() <= 0) {
					System.out.println("Tidak ada karyawan!");
					break;
				}
				updateEmployee();
				break;
			case 4:
				deleteEmployee();
				break;
			case 5:
				System.out.println("Thank you for using this app");
				break;
			}
		} while (choice != 5);
	}

	public String getStringInput(String message) {
		String temp;
		do {
			System.out.println(message);
			temp = scan.nextLine();
			break;
		} while (true);
		return temp;
	}

	public Integer getIntegerInput(String message) {
		int temp = 0;
		do {
			System.out.println(message);
			try {
				temp = scan.nextInt();
				scan.nextLine();
			} catch (Exception e) {
				System.out.println("Input harus angka");
			}
			break;
		} while (true);
		return temp;
	}

	public void menu() {
		System.out.println("1. Insert data karyawan.");
		System.out.println("2. View data karyawan.");
		System.out.println("3. Update data karyawan.");
		System.out.println("4. Delete data karyawan.");
		System.out.println("5. Keluar dari aplikasi");
	}

	public void addEmployee() {
		String kode = "";
		String nama;
		String gender;
		String jabatan;
		int gaji = 0;

		kode += (char) ('A' + rand.nextInt(26));
		kode += (char) ('A' + rand.nextInt(26));
		kode += "-";
		kode += rand.nextInt(10);
		kode += rand.nextInt(10);
		kode += rand.nextInt(10);
		kode += rand.nextInt(10);

		for (int i = 0; i < vEmployee.size(); i++) {
			while (kode == vEmployee.get(i).getKode()) {
				kode = "";
				kode += (char) ('A' + rand.nextInt(26));
				kode += (char) ('A' + rand.nextInt(26));
				kode += "-";
				kode += rand.nextInt(10);
				kode += rand.nextInt(10);
				kode += rand.nextInt(10);
				kode += rand.nextInt(10);
			}
		}

		do {
			nama = getStringInput("Input nama karyawan [Min 3 char]: ");
		} while (nama.length() < 3);

		do {
			gender = getStringInput("Input jenis kelamin karyawan [Laki-Laki | Perempuan] [Case Sensitive]: ");
		} while (!(gender.equals("Laki-Laki") || gender.equals("Perempuan")));

		do {
			jabatan = getStringInput("Jabatan Karyawan [Manager|Supervisor|Admin] [Case Sensitive]: ");
		} while (!(jabatan.equals("Manager") || jabatan.equals("Supervisor") || jabatan.equals("Admin")));

		switch (jabatan) {
		case "Manager":
			gaji = 8000000;
			break;

		case "Supervisor":
			gaji = 6000000;
			break;

		case "Admin":
			gaji = 4000000;
			break;
		}
		employee = new Employee(kode, nama, gender, jabatan, gaji);
		vEmployee.add(employee);
//		validateBonus();

		if (vEmployee.get(vEmployee.size() - 1).getJabatan().equals("Admin")){
		validateBonus1(vAdmin,0.05,"5%");	
		}else if((vEmployee.get(vEmployee.size() - 1).getJabatan().equals("Manager"))) {
		validateBonus1(vManager,0.1,"10%");
		}else if(vEmployee.get(vEmployee.size() - 1).getJabatan().equals("Supervisor")) {
		validateBonus1(vSupervisor,0.075,"7,5%");
		}
		
	}

	public void validateBonus1(Vector<Integer>vTemp, double num, String persen) {
//		int num1 = 1;
//		num1 = num;
		vTemp.add(vEmployee.size() - 1);
		System.out.println(vTemp.size());
		System.out.println(size1);
		if (vTemp.size() < size1) {
			return;
		}
		size1 = vTemp.size();
		int calculate = vTemp.size() % 3;
		System.out.println(vTemp);
		if (calculate == 1) {
			System.out.print("Bonus sebesar " + persen + "telah diberikan kepada karyawan dengan ID ");
			for (int z = 0; z < vTemp.size() - 1; z++) {
				System.out.print(vEmployee.get(vTemp.get(z)).getKode() + ", ");
			}
			System.out.println();
			for (int j = 0; j < vTemp.size() - 1; j++) {
				int index = vTemp.get(j);
				int bonus = (int) (vEmployee.get(index).getGaji() * num);
				System.out.println(bonus);
				Employee temp = new Employee(vEmployee.get(index).getKode(), vEmployee.get(index).getNama(),
						vEmployee.get(index).getGender(), vEmployee.get(index).getJabatan(),
						(bonus + vEmployee.get(index).getGaji()));
				vEmployee.set(index, temp);
			}
		}
	}
		
	
	
	
	
	
	public void ascending() {
		for (int i = 0; i < vEmployee.size() - 1; i++) {
			for (int j = 0; j < vEmployee.size() - 1; j++) {
				if (vEmployee.get(j).getNama().compareTo(vEmployee.get(j + 1).getNama()) > 0) {
					Employee temp = vEmployee.get(j);
					vEmployee.set(j, vEmployee.get(j + 1));
					vEmployee.set(j + 1, temp);
				}
			}
		}
	}

	public void viewEmployee() {
		ascending();
		System.out.println("------------------------------------------------------------------------------------");
		System.out.printf("|%-5s |%-15s |%-25s |%-15s |%-15s |%-15s|\n", "No", "Kode Karyawan", "Nama Karyawan",
				"Jenis Kelamin", "Jabatan", "Gaji Karyawan");
		System.out.println("------------------------------------------------------------------------------------");
		for (int i = 0; i < vEmployee.size(); i++) {
			System.out.printf("|%-5d |%-15s |%-25s |%-15s |%-15s |%-15d|\n", (i + 1), vEmployee.get(i).getKode(),
					vEmployee.get(i).getNama(), vEmployee.get(i).getGender(), vEmployee.get(i).getJabatan(), vEmployee.get(i).getGaji());
		}
		System.out.println("------------------------------------------------------------------------------------");
	}

	public void updateEmployee() {
		viewEmployee();
		int index;
		String nama, gender, jabatan;
		do {
			index = getIntegerInput("Update karyawan[1-" + vEmployee.size() + "]");
		} while (index < 1 || index > vEmployee.size());
		do {
			nama = getStringInput("Input nama karyawan [Min 3 char]: ");
		} while (nama.length() < 3);

		do {
			gender = getStringInput("Input jenis kelamin karyawan [Laki-Laki | Perempuan] [Case Sensitive]: ");
		} while (!(gender.equals("Laki-Laki") || gender.equals("Perempuan")));

		do {
			jabatan = getStringInput("Jabatan Karyawan [Manager|Supervisor|Admin] [Case Sensitive]: ");
		} while (!(jabatan.equals("Manager") || jabatan.equals("Supervisor") || jabatan.equals("Admin")));

		Employee temp = new Employee(vEmployee.get(index - 1).getKode(), nama, gender, jabatan,
				vEmployee.get(index - 1).getGaji());

		vEmployee.set((index - 1), temp);
	}

	public void deleteEmployee() {
		ascending();
		viewEmployee();
		int index;
		do {
			index = getIntegerInput("Input no karyawan yang ingin di hapus");
		} while (index < 1 || index > vEmployee.size());
		System.out.println(("Karyawan dengan Kode " + vEmployee.get(index - 1).getKode() + " berhasil di hapus"));
		vEmployee.remove(index - 1);
		getStringInput("ENTER TO RETURN");
	}

	public static void main(String[] args) {
		new Main();

	}

}
