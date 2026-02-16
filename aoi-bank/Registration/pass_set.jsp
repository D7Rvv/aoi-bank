<%@page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>パスワード設定画面</title>
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
			<h1>パスワード設定画面</h1><hr>
			<h2>パスワードは8桁以上16桁以内で設定してください</h2>
			
			<div id="pas-set-error">
				${error_msg}
		  	</div>

			<form action="<%=request.getContextPath()%>/RegistrationComplete" method="POST">
				<label>【パスワード】</label>
				<div class="password1">
					<input type="password" name="password1" placeholder="1234ABCD" maxlength = 16>
				</div>
			    <br>
			    
			    <label>【パスワード(再入力)】</label>
				<div class="password2">
					<input type="password" name="password2" placeholder="1234ABCD" maxlength = 16>
				</div>
			    <br>
				 
				<button id="pas-set-btn" class="btn" type="submit" name="pass_set">パスワードを設定</button>
			</form>	
			
			<div>
				<br>
				<a href="${pageContext.request.contextPath}/Registration/confirmation.jsp" id="back-btn" class="btn">戻る</a>
			</div>
		</main>
	</body>
</html>



