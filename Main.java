import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<Sach> dsSach = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int chon;

        do {
            System.out.println("\n========= QUẢN LÝ SÁCH =========");
            System.out.println("1. Thêm 1 cuốn sách");
            System.out.println("2. Xóa 1 cuốn sách");
            System.out.println("3. Thay đổi cuốn sách");
            System.out.println("4. Xuất thông tin tất cả các cuốn sách");
            System.out.println("5. Tìm cuốn sách có tựa đề chứa chữ  'Lập trình' và không phân biệt chữ hoa thường");
            System.out.println("6. Lấy sách: Nhập vào một số K  và giá sách P mong muốn tìm kiếm. Hãy lấy tối đa K cuốn sách đều thỏa mãn có giá sách <= P");
            System.out.println("7. Nhập vào một danh sách các tác giả từ bàn phím. Hãy cho biết tất cả các cuốn sách của những tác giả này.");
            System.out.println("0. Thoát");
            System.out.print("Mời chọn (0-7): ");
            
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    System.out.print("Nhập mã: "); int ma = Integer.parseInt(sc.nextLine());
                    System.out.print("Nhập tên: "); String ten = sc.nextLine();
                    System.out.print("Nhập TG: "); String tg = sc.nextLine();
                    System.out.print("Nhập giá: "); double gia = Double.parseDouble(sc.nextLine());
                    dsSach.add(new Sach(ma, ten, tg, gia));
                    break;

                case 2: // Yêu cầu 2: Xóa sách
                    System.out.print("Nhập mã sách cần xóa: "); 
                    int maXoa = Integer.parseInt(sc.nextLine());
                    dsSach.removeIf(s -> s.getMaSach() == maXoa);
                    System.out.println("Đã thực hiện xóa.");
                    break;

                case 3: // Yêu cầu 3: Thay đổi thông tin
                    System.out.print("Nhập mã sách cần sửa: "); 
                    int maSua = Integer.parseInt(sc.nextLine());
                    for (Sach s : dsSach) {
                        if (s.getMaSach() == maSua) {
                            System.out.print("Tên mới: "); s.setTenSach(sc.nextLine());
                            System.out.print("Tác giả mới: "); s.setTacGia(sc.nextLine());
                            System.out.print("Giá mới: "); s.setDonGia(Double.parseDouble(sc.nextLine()));
                        }
                    }
                    break;

                case 4:
                    System.out.println("\nDANH SÁCH SÁCH HIỆN CÓ:");
                    for (Sach s : dsSach) System.out.println(s);
                    break;

                case 5:
                    System.out.println("\nKẾT QUẢ TÌM KIẾM:");
                    boolean timThay = false;
                    // Chuyển từ khóa cần tìm về chữ thường
                    String tuKhoa = "lập trình"; 
                    
                    for (Sach s : dsSach) {
                        // Chuyển tên sách về chữ thường trước khi so sánh
                        String tenSachThap = s.getTenSach().toLowerCase();
                        
                        // Kiểm tra chứa chữ "lập trình" HOẶC "lap trinh" (để dự phòng nhập không dấu)
                        if (tenSachThap.contains(tuKhoa) || tenSachThap.contains("lap trinh")) {
                            System.out.println(s);
                            timThay = true;
                        }
                    }
                    if (!timThay) {
                        System.out.println("Không tìm thấy cuốn sách nào phù hợp.");
                    }
                    break;

                case 6: // Lấy tối đa K cuốn sách có giá <= P
                    System.out.print("Nhập số lượng K: "); 
                    int k = Integer.parseInt(sc.nextLine());
                    System.out.print("Nhập giá P tối đa: "); 
                    double p = Double.parseDouble(sc.nextLine());

                    System.out.println("\nKẾT QUẢ TÌM KIẾM (Dùng Stream):");
                    dsSach.stream()
                        .filter(s -> s.getDonGia() <= p) // Lọc các sách có giá <= P
                        .limit(k)                         // Chỉ lấy tối đa K cuốn đầu tiên thỏa mãn
                        .forEach(System.out::println);    // In kết quả ra màn hình
                    break;

                case 7: // Tìm sách theo danh sách các tác giả
                    System.out.print("Nhập các tác giả (cách nhau bằng dấu phẩy): ");
                    String inputTG = sc.nextLine();
                    
                    // 1. Chuyển chuỗi nhập vào thành tập hợp Set để tìm kiếm tối ưu
                    Set<String> tapHopTacGia = Arrays.stream(inputTG.split(","))
                                                    .map(String::trim)        // Xóa khoảng trắng thừa
                                                    .map(String::toLowerCase) // Chuyển về chữ thường
                                                    .collect(Collectors.toSet());

                    System.out.println("\nKẾT QUẢ TÌM THEO TÁC GIẢ:");
                    // 2. Filter những sách có tác giả nằm trong tập Set trên
                    dsSach.stream()
                        .filter(s -> tapHopTacGia.contains(s.getTacGia().toLowerCase().trim()))
                        .forEach(System.out::println);
                    break;
                case 0: System.out.println("Tạm biệt!"); break;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (chon != 0);
        sc.close();
    }
}