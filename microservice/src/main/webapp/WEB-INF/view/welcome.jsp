<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome Page</title>
</head>
<body style="font-family: Arial, sans-serif; text-align: center; margin-top: 50px;">
    <h1 style="color: #333; font-size: 24px;">Welcome, you are successfully logged in!</h1>

    <!-- PDF Generator Button -->
    <form action="/pdf/generate" method="get" style="margin-top: 20px;">
        <button type="submit" 
            style="padding: 10px 20px; 
                   font-size: 16px; 
                   cursor: pointer; 
                   background-color: #4CAF50; 
                   color: white; 
                   border: none; 
                   border-radius: 5px; 
                   transition: background-color 0.3s ease;">
            PDF Generator
        </button>
    </form>

    <!-- PDF Downloader Button -->
    <form action="/pdf/download" method="get" style="margin-top: 20px;">
        <button type="submit" 
            style="padding: 10px 20px; 
                   font-size: 16px; 
                   cursor: pointer; 
                   background-color: #2196F3; 
                   color: white; 
                   border: none; 
                   border-radius: 5px; 
                   transition: background-color 0.3s ease;">
            PDF Downloader
        </button>
    </form>
</body>
</html>
