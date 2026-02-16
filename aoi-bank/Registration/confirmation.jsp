<%@page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>登録確認画面</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aoibank.css">
	</head>
	<body>
		<header class="header">
			<div class="header-left">
				<h2>AOI</h2>
				<h1>あおい銀行</h1>
			</div>
			
			<div id="common-navigation">
				<a href="${pageContext.request.contextPath}/Top/index.html" id="top-btn" class="btn">TOP</a>
			</div>
		</header>
		
		<main id="confirmation-group">
			<h1>登録確認画面</h1><hr>
			<h2>この内容でよろしいですか？</h2>
			${map.error}

			<div id="confirmation-data">
				
				<label>【氏名】</label>
				<div class="fullname">
					${map.lastname} ${map.firstname}
				</div>
			    <br>
			    
			    <label>【住所】</label>
			    <div class="address">
			    	${map.address}
			    </div>
			    <br>

				<label>【電話番号】</label>
				<div class="phone">
					${map.tel_part1} - ${map.tel_part2} - ${map.tel_part3}
				</div>
				<br>
				
				<a href="${pageContext.request.contextPath}/Registration/pass_set.jsp" id="confirmation-next-btn" class="btn">次へ</a>
				<a href="${pageContext.request.contextPath}/Registration/user_registration.jsp" id="back-btn" class="btn">戻る</a>
			</div>
		</main>
	</body>
</html>



