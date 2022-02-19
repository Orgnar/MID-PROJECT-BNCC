package main;

public class Employee {
String kode;
String nama;
String gender;
String jabatan;
int gaji;

public Employee(String kode, String nama, String gender, String jabatan, int gaji) {
	this.kode = kode;
	this.nama = nama;
	this.gender = gender;
	this.jabatan = jabatan;
	this.gaji = gaji;
}
public int getGaji() {
	return gaji;
}
public void setGaji(int gaji) {
	this.gaji = gaji;
}
public String getKode() {
	return kode;
}
public void setKode(String kode) {
	this.kode = kode;
}
public String getNama() {
	return nama;
}
public void setNama(String nama) {
	this.nama = nama;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getJabatan() {
	return jabatan;
}
public void setJabatan(String jabatan) {
	this.jabatan = jabatan;
}





}


