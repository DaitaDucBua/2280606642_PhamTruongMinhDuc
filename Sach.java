public class Sach {
    private int maSach;
    private String tenSach;
    private String tacGia;
    private double donGia;

    // Constructor
    public Sach(int maSach, String tenSach, String tacGia, double donGia) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.donGia = donGia;
    }

    // Các hàm lấy dữ liệu (Getter)
    public int getMaSach() { return maSach; }
    public String getTenSach() { return tenSach; }
    public String getTacGia() { return tacGia; }
    public double getDonGia() { return donGia; }

    // CÁC HÀM NÀY ĐỂ SỬA LỖI "UNDEFINED" CỦA BẠN
    public void setTenSach(String tenSach) { this.tenSach = tenSach; }
    public void setTacGia(String tacGia) { this.tacGia = tacGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }

    @Override
    public String toString() {
        return "Mã: " + maSach + " | Tên: " + tenSach + " | Tác giả: " + tacGia + " | Giá: " + donGia;
    }
}