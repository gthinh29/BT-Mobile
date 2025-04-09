class Task {
  String title;
  String description;
  // Lưu màu đã chọn khi tạo project (để tránh đổi màu sau khi view cập nhật)
  int colorValue;

  Task({
    required this.title,
    required this.description,
    required this.colorValue,
  });
}
