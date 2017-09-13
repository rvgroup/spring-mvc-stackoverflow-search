<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<style>
		a {
			color: #07C;
		    text-decoration: none;
		    cursor: pointer;
		}
		
		
		tr {
			height: 40px;
		}
		
		td {
			padding-left: 10px;
		}
	
		.hidden {
			display: none;
		}
		
		.notanswered {
			background-color: rgba(158, 158, 158, 0.24);
		}
		
		.colHeader {
			font-weight: bold;
			text-align: center;
		}
		
		.colDate {
			width: 80px;
		}
		
		.colNumber {
			text-align: center;
		}
	</style>
</head>
<body>
	<h1>Пример поиска для Stackoverflow API</h1>
	
	<form id="searchForm" method="post">
	  <div class="container">
	    <input type="text" placeholder="Укажите строку поиска" name="filtertext" required>
	    
	    <button type="submit">Найти</button>
	  </div>
	</form>
	
	<div id = "emptyResultContainer" class = "hidden">
		Нет данных для отображения. Попробуйте другой запрос.
	</div>
	
	<div id = "resultContainer" class = "hidden">		  
		  <table>	  
			   <thead>
			    <tr>
			     <td class = "colHeader">Вопрос</td>
			     <td class = "colHeader colDate">Дата</td>
			     <td class = "colHeader">Автор</td>
			     <td class = "colHeader">Ответов</td>
			    </tr> 
			   </thead>
			   
			   <tbody id = "resultBody">
			   </tbody>
		  </table>
	</div>
	
	<script type="text/javascript">
		function renderTable(rows) {
			var body = $("#resultBody");
			$("#resultBody").empty();
			
			rows.forEach(function(row, i) {
				var newRow = $('<tr>');
				
				if (row.is_answered) {
					newRow.addClass('answered');
				} else {
					newRow.addClass('notanswered');
				}
				

				var createDate = new Date(0);
				createDate.setUTCSeconds(row.creation_date);
				
				var createDateStr = createDate.toLocaleDateString();	
				var date = $('<td>').html(createDateStr);
			
				var titleFix = row.title.split('&#39;').join("'");
				
				var titleLink = $("<a />", {
				    href : row.link,
				    target: "_blank",
				    text : titleFix
				});
				
				var title = $('<td>').append(titleLink);
				
				var ownerLink = $("<a />", {
				    href : row.owner.link,
				    target: "_blank",
				    text : row.owner.display_name
				});
				var owner = $('<td>').append(ownerLink);
				
				var answerCount = $('<td class="colNumber">').html(row.answer_count);
			
				newRow.append(title);
				newRow.append(date);
				newRow.append(owner);
				newRow.append(answerCount);
				
				body.append(newRow);
			});
		}
	
		$( "#searchForm" ).submit(function( event ) {
		  event.preventDefault();
		  
		  var form =$('#searchForm');
		  var data = encodeURI(form.serialize());
		  
			$.ajax({
			    type: 'post',
			    dataType: 'json',
			    data: data,
			    success: function(data) {
               		if (data.items.length) {
               			$('#emptyResultContainer').addClass('hidden');
               			
               			renderTable(data.items);
               			
               			$('#resultContainer').removeClass('hidden');
               		} else {
               			$('#emptyResultContainer').removeClass('hidden');
               			
               			$('#resultContainer').addClass('hidden');
               		}
               }
			});
		});
	</script>
</body>
</html>