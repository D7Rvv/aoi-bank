<%@page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>ログイン画面</title>
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
			<h1>ログイン画面</h1><hr>
			<h2>ログインIDとパスワードを入力してください</h2>
			
			<div id="login-error">
				<span style="color: red;">${error_msg}</span>
		  	</div>

			<form action="<%=request.getContextPath()%>/LoginCheck" method="POST">
				<label>【ログインID】</label>
				<div class="login_id">
					<input type="text" name="login_id" placeholder="ログインIDを入力" maxlength = 16>
				</div>
			    <br>
			    
			    <label>【パスワード】</label>
				<div class="password">
					<input type="password" name="password" placeholder="パスワードを入力" maxlength = 16>
				</div>
			    <br>
				 
				<button id="login-btn" class="btn" type="submit" name="login-btn">ログイン</button>
			</form>	
		</main>
	</body>
</html>



