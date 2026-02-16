<%@page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>登録完了画面</title>
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
			<h1>登録完了画面</h1><hr>
			<h2>お客さまのログインIDを発行いたしました。<br>
                今後のログインに必要となりますので、大切に保管してください。</h2><br>
			<div id="login-id">
                <h2 style="color: #4ebade">「ログインID：${allGetId.login_id}」</h2>
		  	</div>

            <h2>口座番号はログイン後に確認できます。</h2>
			
			<div>
				<br>
				<a href="${pageContext.request.contextPath}/Login_and_Mypage/login_page.jsp" id="back-btn" class="btn">ログイン画面へ</a>
			</div>
		</main>
	</body>
</html>



