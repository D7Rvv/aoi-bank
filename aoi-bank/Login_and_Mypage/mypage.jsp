<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!--JSTLのライブラリ-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!--JSTLのフォーマットライブラリ-->

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>あおい銀行</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@splidejs/splide@4.1.4/dist/css/splide.min.css"> <!--Splideライブラリ-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aoibank.css">
	
</head>
<body>
	<main>
		<header class="header">
			<div class="header-left">
				<h2>AOI</h2>
				<h1>あおい銀行</h1>	
			</div>
            
            <nav class="menu">
                <div class="hamburger">
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/Top/index.html">トップ</a></li>
                    <li><a href="#">振込</a></li>
                    <li><a href="#">取引履歴</a></li>
                </ul>
            </nav>
		</header>
        
        <section id="welcome-group">
            <h2>マイページ</h2>
            <div class="welcome-info">
                <p>こんにちは、${userInfo.last_name} ${userInfo.first_name}さん</p>
                <p class="login-time">
                <% java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm"); %>
                最終ログイン：<%= sdf.format(new java.util.Date()) %>
                </p>
            </div>
        </section>

		<section id="balance-group">
			<div class="balance-card">
				
                <div class="account-number">
                    <label>口座番号</label><br>
                    <span>${userInfo.account_number}</span>
                </div>  

				<div class="deposit">
					<label>普通預金残高</label><br>
                    <span class="monetary"><span class="number"><fmt:formatNumber value="${userInfo.deposit}" pattern="#,###"/></span>円</span>
				</div>
			</div>
		</section>

		<footer class="mypage-footer">
			<div id="footer-container">
				<div class="footer-info">
					<h2>あおい銀行</h2>
					<p>金融機関コード：0000</p>
					<p>東京都品川区○○ 1-2-3</p>
				</div>

				<div class="footer-links">
					<ul>
						<li><a href="#">プライバシーポリシー</a></li>
						<li><a href="#">勧誘方針</a></li>
						<li><a href="#">ご利用規約</a></li>
						<li><a href="#">お問い合わせ</a></li>
					</ul>
				</div>
			</div>

			<div class="footer-bottom">
				<p>&copy; 2026 AOI Bank, Ltd. All Rights Reserved.</p>
			</div>
		</footer>

        <!--jQueryライブラリ-->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <!--Splideライブラリ-->
		<script src="https://cdn.jsdelivr.net/npm/@splidejs/splide@4.1.4/dist/js/splide.min.js"></script>
		<script src="<%= request.getContextPath() %>/js/aoibank.js"></script>
	</main>
</body>