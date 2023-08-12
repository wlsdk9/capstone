<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 리뷰 상세 정보</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f8f8;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        .header {
            text-align: center;
            margin-bottom: 20px;
        }
        .post-section {
            border: 1px solid #e1e1e1;
            padding: 20px;
            margin-top: 20px;
            background-color: #fff;
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
        }
        .post-section h2 {
            margin: 0;
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 10px;
        }
        .post-section p {
            margin: 0;
            font-size: 16px;
            margin-bottom: 10px;
        }
        .back-btn {
            display: block;
            text-align: center;
            margin-top: 20px;
            font-size: 18px;
            color: #007bff;
            text-decoration: none;
        }
        .back-btn:hover {
            text-decoration: underline;
        }
        .comment {
            background-color: #f5f5f5;
            box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.1);
        }
        .comment p {
            margin: 5px 0;
            font-size: 14px;
        }
        .comment-author {
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>게시판 리뷰 상세 정보</h1>
        </div>

        <div class="post-section">
            <h2>제목: ${review.title}</h2>
            <p>작성자: ${review.userId}</p>
            <p>작성시간: ${review.createdTime}</p>
            <p>별점: ${review.rating}</p>
        </div>

        <div class="post-section">
            <h2>게시물 내용</h2>
            <p>${review.content}</p>
        </div>

        <div class="post-section comment">
            <h2>댓글</h2>
            <div class="comment-list">
                <div class="comment-item">
                    <p class="comment-author">댓글 작성자</p>
                    <p class="comment-content">댓글 내용입니다.</p>
                </div>
                <!-- 댓글 아이템을 동적으로 추가할 수 있습니다. -->
            </div>
        </div>

        <a class="back-btn" href="/capstone/view/goReviewListView">이전 목록으로 돌아가기</a>
    </div>
</body>
</html>
