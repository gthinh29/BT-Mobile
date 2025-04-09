import 'package:flutter/material.dart';
import 'dart:math';
import '../models/task_model.dart';

class TaskViewModel extends ChangeNotifier {
  final List<Task> _tasks = [];
  List<Task> get tasks => _tasks;

  final List<Color> _cardColors = [
    Colors.purpleAccent.shade100,
    Colors.tealAccent.shade100,
    Colors.orangeAccent.shade100,
  ];

  Color _getRandomColor() {
    final random = Random();
    return _cardColors[random.nextInt(_cardColors.length)];
  }

  void addTask(String title, String description) {
    Color randomColor = _getRandomColor();
    _tasks.add(
      Task(
        title: title,
        description: description,
        // ignore: deprecated_member_use
        colorValue: randomColor.value,
      ),
    );
    notifyListeners();
  }
}
