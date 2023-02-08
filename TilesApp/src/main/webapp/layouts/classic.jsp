<!-- tiles를 사용하고 싶다면 taglib선언해야함  -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
  <head>
  	<!-- tiles:getAsString : name="title"인걸 읽어오겠다. -->
    <title><tiles:getAsString name="title"/></title> 
  </head>
  <body>
        <table>
      <tr>
        <td colspan="2">
       	  <!-- insertAttribute : header부분에 해당하는걸 여기에 넣겠다는말 -->
          <tiles:insertAttribute name="header" /> 
        </td>
      </tr>
      <tr>
        <td>
          <!--  insertAttribute : menu부분에 해당하는걸 여기에 넣겠다는말 -->
          <tiles:insertAttribute name="menu" /> 
        </td>
        <td>
          <tiles:insertAttribute name="body" />
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <tiles:insertAttribute name="footer" />
        </td>
      </tr>
    </table>
  </body>
</html>