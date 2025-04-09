import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'auth_service.dart';

class LoginScreen extends StatelessWidget {
  const LoginScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
        padding: const EdgeInsets.all(20.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            const SizedBox(height: 80),
            // Header
            Column(
              children: [
                const Text(
                  "UTH",
                  style: TextStyle(
                    fontSize: 32,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                const Text(
                  "OF UNIVERSITY\nHIGHWAY OF\nMECHANISM CITY",
                  textAlign: TextAlign.center,
                  style: TextStyle(
                    fontSize: 18,
                    height: 1.2,
                  ),
                ),
                const SizedBox(height: 40),
                // App Title
                const Text(
                  "SmartTasks",
                  style: TextStyle(
                    fontSize: 28,
                    fontWeight: FontWeight.w600,
                  ),
                ),
                const SizedBox(height: 8),
                const Text(
                  "A simple and efficient to do app",
                  style: TextStyle(color: Colors.grey),
                ),
              ],
            ),
            const Spacer(),
            // Welcome Text
            const Text(
              "Ready to explore?",
              style: TextStyle(fontSize: 20),
            ),
            const Text(
              "Log in to get started.",
              style: TextStyle(fontSize: 20),
            ),
            const SizedBox(height: 40),
            // Google Sign In Button
            ElevatedButton.icon(
              icon: Image.asset('assets/google.png', width: 24),
              label: const Text(
                "SIGN IN WITH GOOGLE",
                style: TextStyle(fontSize: 16),
              ),
              style: ElevatedButton.styleFrom(
                padding: const EdgeInsets.symmetric(vertical: 16, horizontal: 24),
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(8),
                ),
              ),
              onPressed: () => Provider.of<AuthService>(context, listen: false).signInWithGoogle(),
            ),
            const Spacer(),
          ],
        ),
      ),
    );
  }
}