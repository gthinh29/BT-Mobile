import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(primarySwatch: Colors.blue),
      home: const ProfilePage(),
    );
  }
}

class ProfilePage extends StatelessWidget {
  const ProfilePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: SafeArea(
        child: Stack(
          alignment: Alignment.topCenter,
          children: [
            Padding(
              padding: const EdgeInsets.only(top: 200),
              child: _buildMainContent(),
            ),
            _buildControlBar(),
          ],
        ),
      ),
    );
  }

  Widget _buildControlBar() {
    return const Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        _ControlButton(iconUrl: AppAssets.backIcon),
        _ControlButton(iconUrl: AppAssets.editIcon),
      ],
    );
  }

  Widget _buildMainContent() {
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        CircleAvatar(
          radius: 100,
          backgroundColor: Colors.grey.shade200,
          backgroundImage: NetworkImage(AppAssets.avatarUrl),
        ),
        const SizedBox(height: 24),
        const Text(
          'Johan Smith',
          style: TextStyle(
            fontSize: 40,
            fontWeight: FontWeight.bold,
          ),
        ),
        const SizedBox(height: 8),
        const Text(
          'California, USA',
          style: TextStyle(
            fontSize: 25,
            color: Colors.grey,
          ),
        ),
      ],
    );
  }
}

class _ControlButton extends StatelessWidget {
  final String iconUrl;
  
  const _ControlButton({required this.iconUrl});

  @override
  Widget build(BuildContext context) {
    return Material(
      color: Colors.transparent,
      child: InkWell(
        onTap: () {},
        borderRadius: BorderRadius.circular(50),
        child: Padding(
          padding: const EdgeInsets.all(16),
          child: Image.network(
            iconUrl,
            width: 24,
            height: 24,
          ),
        ),
      ),
    );
  }
}

class AppAssets {
  static const avatarUrl = 
    'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg';
  static const backIcon = 
    'https://cdn-icons-png.flaticon.com/512/271/271220.png';
  static const editIcon = 
    'https://cdn-icons-png.flaticon.com/512/1159/1159633.png';
}