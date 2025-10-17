<!-- 20251015 SDEV200 Java - Module 7, Programming Assignment 2, Chapter 38 - exercise 38.14 -->

<%@ page import="java.util.*" %>
<%
<!-- generate the questions -->
    Random rand = new Random();
    int numQuestions = 5;
    int[] num1 = new int[numQuestions];
    int[] num2 = new int[numQuestions];

    for (int i = 0; i < numQuestions; i++) {
        num1[i] = rand.nextInt(10);
        num2[i] = rand.nextInt(10);
    }
%>

<html>
<head><title>Addition Quiz</title></head>
<body>
    <h2>Addition Quiz</h2>
    <form action="result.jsp" method="post">
        <%
            for (int i = 0; i < numQuestions; i++) {
        %>
            <%= num1[i] %> + <%= num2[i] %> = 
            <input type="text" name="answer<%= i %>">
            <input type="hidden" name="num1<%= i %>" value="<%= num1[i] %>">
            <input type="hidden" name="num2<%= i %>" value="<%= num2[i] %>"><br><br>
        <%
            }
        %>
        <input type="submit" value="Submit Answers">
    </form>
</body>
</html>

