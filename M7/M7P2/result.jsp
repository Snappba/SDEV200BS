<!-- 20251015 SDEV200 Java - Module 7, Programming Assignment 2, Chapter 38 - exercise 38.14 !-->

<%@ page import="java.util.*" %>
<%
    int numQuestions = 5;
    int score = 0;

    for (int i = 0; i < numQuestions; i++) {
        int num1 = Integer.parseInt(request.getParameter("num1" + i));
        int num2 = Integer.parseInt(request.getParameter("num2" + i));
        int correct = num1 + num2;
        int userAnswer = 0;

        try {
            userAnswer = Integer.parseInt(request.getParameter("answer" + i));
        } catch (Exception e) {
            userAnswer = -1; // invalid input
        }

        if (userAnswer == correct) score++;
    }
%>

<html>
<head><title>Quiz Result</title></head>
<body>
    <h2>Quiz Results</h2>
    <p>You got <%= score %> out of <%= numQuestions %> correct.</p>
    <a href="addition.jsp">Try Again</a>
</body>
</html>
