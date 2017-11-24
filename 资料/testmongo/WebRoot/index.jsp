<%@ page language="java" import="java.util.*,com.mongo.entity.*" pageEncoding="utf-8"%>
<html>
	<head>
	<body>
		<%
   				List<Person> list =  (List<Person>)session.getAttribute("person");
				out.print("<table>");
				out.print("<tr><td>id</td><td>name</td><td>message</td></tr>");
   				for(Person p :list){
   					out.print("<tr>");
   						out.print("<td>"+"id: "+p.getId()+"</td><td>name:"+p.getName()+"</td><td> message:  "+p.getMessage()+"</td>");
   					out.print("</tr>");;
   				}
   				out.print("</table>");
   		%>
	</body>
</html>
