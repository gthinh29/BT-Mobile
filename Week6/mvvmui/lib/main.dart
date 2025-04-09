import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'view/home_screen.dart';
import 'view_model/task_view_model.dart';

void main() {
  runApp(
    ChangeNotifierProvider(
      create: (_) => TaskViewModel(),
      child: const MyApp(),
    ),
  );
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Project MVVM App',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.deepPurple,
        appBarTheme: const AppBarTheme(
          elevation: 1,
          backgroundColor: Colors.white,
          foregroundColor: Colors.black87,
        ),
      ),
      home: const HomeScreen(),
    );
  }
}
