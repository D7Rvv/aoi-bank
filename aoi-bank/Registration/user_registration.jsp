 <%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!--JSTLのライブラリ-->

<c:set var="preflist" value = '${["北海道", "青森県", "岩手県", "宮城県", "秋田県", "山形県", "福島県",
						       "茨城県", "栃木県", "群馬県", "埼玉県", "千葉県", "東京都", "神奈川県",
						       "新潟県", "富山県", "石川県", "福井県", "山梨県", "長野県", "岐阜県",
						       "静岡県", "愛知県", "三重県", "滋賀県", "京都府", "大阪府", "兵庫県",
						       "奈良県", "和歌山県", "鳥取県", "島根県", "岡山県", "広島県", "山口県",
						       "徳島県", "香川県", "愛媛県", "高知県", "福岡県", "佐賀県", "長崎県",
							   "熊本県", "大分県", "宮崎県", "鹿児島県", "沖縄県"]}'
/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>口座開設画面</title>
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
		
		<main id="register-group">
		  	<h1>口座開設画面</h1><hr>
		  	<h2>下記内容をご入力の上、お進みください</h2>
		  	
		  	<div id="regitration-error">
				${error_msg}
		  	</div>
		  	
			<div id="register-input">
				<form action="<%=request.getContextPath()%>/InputCheck" method="POST">
				
				    <span>【氏名】</span>
				    <div class="fullname">
				        <input type="text" name="lastname" value="${map.lastname}" placeholder="姓">
				        <input type="text" name="firstname" value="${map.firstname}" placeholder="名">
				    </div>
				    <br>
				    
				    <span>【住所】</span>
				    <div class="address">
					    <select name="address">
					        <option value="" disabled ${empty map.address ? 'selected' : ''}>都道府県を選択してください</option>
					    	
					    	<!--preflist = 都道府県リスト-->
					    	<c:forEach var="pref" items="${preflist}">
					    		<option value="${pref}" ${map.address == pref ? 'selected' : ''}>${pref}</option>
					    	</c:forEach>
					    </select>
					</div>
				    <br>
				    
				    <span>【電話番号】</span>
				   <div class="phone">
					    <input value="000" name="tel_part1" readonly> -
						<input type="text" name="tel_part2" value="${map.tel_part2}" placeholder="1234" maxlength = 4> -
						<input type="text" name="tel_part3" value="${map.tel_part3}" placeholder="5678" maxlength = 4>
					</div>
					<br>
					
					<button id="register-send-btn" class="btn" type="submit">入力完了</button>
				</form>
			</div>
		</main>
	</body>
</html>



