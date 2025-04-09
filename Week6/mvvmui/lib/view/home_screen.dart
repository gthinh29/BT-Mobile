import 'package:flutter/material.dart';
// ignore: depend_on_referenced_packages
import 'package:provider/provider.dart';
import '../view_model/task_view_model.dart';
import 'add_task_screen.dart';
import 'widget/project_card.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});
  @override
  Widget build(BuildContext context) {
    final taskVM = Provider.of<TaskViewModel>(context);
    return Scaffold(
      backgroundColor: Colors.grey.shade50,
      appBar: AppBar(
        title: const Text(
          "Projects",
          style: TextStyle(fontWeight: FontWeight.bold, fontSize: 22),
        ),
        centerTitle: true,
        elevation: 2,
        backgroundColor: Colors.white,
        foregroundColor: Colors.black87,
      ),
      body:
          taskVM.tasks.isEmpty
              ? Center(
                child: Text(
                  "Chưa có dự án. Nhấn + để tạo mới",
                  style: TextStyle(fontSize: 18, color: Colors.grey.shade600),
                ),
              )
              : ListView.builder(
                itemCount: taskVM.tasks.length,
                itemBuilder: (context, index) {
                  final task = taskVM.tasks[index];
                  return ProjectCard(task: task);
                },
              ),
      floatingActionButton: FloatingActionButton(
        onPressed:
            () => Navigator.push(
              context,
              MaterialPageRoute(builder: (_) => const AddTaskScreen()),
            ),
        backgroundColor: Colors.deepPurpleAccent,
        child: const Icon(Icons.add, size: 32),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
      bottomNavigationBar: BottomAppBar(
        shape: const CircularNotchedRectangle(),
        notchMargin: 8,
        elevation: 8,
        child: Container(
          height: 60,
          padding: const EdgeInsets.symmetric(horizontal: 20),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: const [
              Icon(Icons.home, size: 30, color: Colors.deepPurpleAccent),
              Icon(Icons.settings, size: 30, color: Colors.deepPurpleAccent),
            ],
          ),
        ),
      ),
    );
  }
}
