<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML>
<!--
   Altitude by Pixelarity
   pixelarity.com | hello@pixelarity.com
   License: pixelarity.com/license
-->
<html>
   <head>
      <title>게시글 상세보기</title>
      <meta charset="utf-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
      <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />
      <style>
         form div.content textarea{
            border-radius:0;
            resize:none;
            color:black !important;
            width: 95.5%;
         }
         
         form div.reply{
            display:flex;
            margin-bottom:3%;
         }
         
         input[type='submit']:not(#register), input[type='button'] {border-radius:0;}
         
         ul.actions li {
             padding: 0 0 0 0.2em;
         }
         
         div.info {
         	width: 18%;
         	position: relative;
         }
         
         div.info::after {
       	    content: "";
		    display: block;
		    width: 1px;
		    height: 90px;
		    background-color: #d8daee66;
		    position: absolute;
		    right: 25px;
		    top: 0;
         }
         
         p.content{
            width:63%;
         }
         
         p.writer{
            width:20%;
            font-weight:bold;
            margin-bottom: 0;
         }
         
         div.modify-button, div.modify-ready-button {
         	background-image: url("../../images/modify.png");
         	background-size: 100%;
         	background-position: center;
         	width: 25px;
         	height: 25px;
         	margin-top: 2px;
         	cursor: pointer;
         }
         
         div.delete-button {
         	background-image: url("../../images/delete.png");
         	background-size: 100%;
         	background-position: center;
         	width: 30px;
         	height: 30px;
         	cursor: pointer;
         }
         
         div.cancel-button {
         	background-image: url("../../images/cancel.png");
         	background-size: 100%;
         	background-position: center;
         	width: 20px;
         	height: 20px;
         	margin: 4px 5px 0 6px;
         	cursor: pointer;
         }
         
         div.button-wrap {
         	display: flex;
         }
         
         div.content {
         	position: relative;
         }
         
         @keyframes rotate {
         	100% {
         		transform: rotate(360deg);
         	}
         }
         
         div.dimmed {
         	position: absolute;
         	background-image: url("../../images/dimmed.png");
         	background-size: 100%;
         	background-position: center;
         	width: 104px;
         	height: 143px;
         	animation: rotate 1s linear infinite;
         	transform-origin: 50% 50%;
        	left: 40%;
    		top: -35px;
         }
      </style>
   </head>
   <body class="is-preload">
      <!-- Page Wrapper -->
         <div id="page-wrapper">

            <!-- Wrapper -->
               <div class="wrapper">
                  <div class="inner">
                     <jsp:include page="${pageContext.request.contextPath}/app/fix/header.jsp"/>
                  </div>
               </div>

            <!-- Wrapper -->
               <div class="wrapper">
                  <div class="inner">

                     <!-- Main -->
                     <section class="main">
                        <a href="#" class="image main"><img src="${pageContext.request.contextPath}/assets/images/boardView.png" alt="" /></a>
                        <div class="col-12">
                           <ul class="actions" style="display:flex; justify-content:flex-end;">
                              <li><input type="button" value="글쓰기" class="primary" onclick=""/></li>
                              <li><input type="button" value="목록" class="primary" onclick=""/></li>
                              <c:if test="${sessionScope.memberNumber == board.getMemberNumber()}">
                              <li><input type="button" value="수정" onclick="location.href='/board/boardUpdate.bo?boardNumber=${board.getBoardNumber()}'"/></li>
                              <li><input type="button" value="삭제" onclick="location.href='/board/boardDelete.bo?boardNumber=${board.getBoardNumber()}'"/></li>
                              </c:if>
                           </ul>
                        </div>
                        <header class="major">
                        
                           <h2 style="text-align:left;"><c:out value="${board.getBoardTitle()}"/></h2>
                           <p style="text-align:left; margin-bottom:1%">작성자 : <c:out value="${board.getMemberId()}"/></p>
                        </header>
                        <hr style="margin-top:0;">
                           <div>
                              첨부파일
                           </div>
                        <!-- 첨부파일 목록 -->
                        <c:forEach var="file" items="${board.getFiles()}">
                        	<a href="${pageContext.request.contextPath}/file/download.file?fileSystemName=${file.getFileSystemName()}&fileOriginalName=${file.getFileOriginalName()}">
                        		<c:out value="${file.getFileOriginalName()}"/>
                        	</a>
                        	<br>
                        </c:forEach>
                        <!-- 내용 -->
                        <br>
                        <h3><pre><c:out value="${board.getBoardContent()}"/></pre></h3>
                     </section>
                  </div>
               </div>

            <!-- Wrapper -->
               <div class="wrapper">
                  <div class="inner">

                     <!-- Footer -->
                        <footer id="footer">
                           <section class="links">
                              <div>
                                 <h3>Magna</h3>
                                 <ul class="plain">
                                    <li><a href="#">Aliquam tempus</a></li>
                                    <li><a href="#">Ultrecies nul</a></li>
                                    <li><a href="#">Gravida ultricies</a></li>
                                    <li><a href="#">Commodo etiam</a></li>
                                 </ul>
                              </div>
                              <div>
                                 <h3>Feugiat</h3>
                                 <ul class="plain">
                                    <li><a href="#">Morbi sem lorem</a></li>
                                    <li><a href="#">Praes sed dapi</a></li>
                                    <li><a href="#">Sed adipis nullam</a></li>
                                    <li><a href="#">Fus dolor lacinia</a></li>
                                 </ul>
                              </div>
                              <div>
                                 <h3>Tempus</h3>
                                 <ul class="plain">
                                    <li><a href="#">Donecnec etiam</a></li>
                                    <li><a href="#">Aapibus sedun</a></li>
                                    <li><a href="#">Namnulla tempus</a></li>
                                    <li><a href="#">Morbi set amet</a></li>
                                 </ul>
                              </div>
                              <div>
                                 <h3>Aliquam</h3>
                                 <ul class="plain">
                                    <li><a href="#">Lorem prasent dia</a></li>
                                    <li><a href="#">Nellentes ipsum</a></li>
                                    <li><a href="#">Diamsed arcu dolor</a></li>
                                    <li><a href="#">Sit amet cursus</a></li>
                                 </ul>
                              </div>
                           </section>
                           <ul class="contact-icons">
                              <li class="icon solid fa-home">1234 Fictional Street #5432 Nashville, TN 00000-0000</li>
                              <li class="icon solid fa-phone"><a href="#">(000) 000-0000</a></li>
                              <li class="icon solid fa-envelope"><a href="#">info@untitled.tld</a></li>
                           </ul>
                           <p class="copyright">&copy; Untitled. All rights reserved.</p>
                        </footer>

                  </div>
               </div>

         </div>

      <!-- Scripts -->
         <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
         <script src="${pageContext.request.contextPath}/assets/js/browser.min.js"></script>
         <script src="${pageContext.request.contextPath}/assets/js/breakpoints.min.js"></script>
         <script src="${pageContext.request.contextPath}/assets/js/util.js"></script>
         <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
   </body>
</html>