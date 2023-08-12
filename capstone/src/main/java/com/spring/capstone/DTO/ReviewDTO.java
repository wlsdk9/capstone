package com.spring.capstone.DTO;

public class ReviewDTO {
    private int reviewId;
    private String cfNumberId;
    private String writeuserid;
    private String title;
    private String content;
    private int rating;
    private String createdTime;
    private String updatedAt;
    private String cfName;
    private String cfNumber;
    private int userNo;
    private int commentId;
    private String createdAt;

    public ReviewDTO() {
    }

    public ReviewDTO(String cfNumberId, int userId, String title, String content, int rating) {
        if (cfNumberId == null) {
            throw new IllegalArgumentException("cfNumberId cannot be null");
        }

        this.cfNumberId = cfNumberId;
        this.writeuserid = writeuserid;
        this.title = title;
        this.content = content;
        this.rating = rating;
    }
    
    
    public int getCommentId() {
        return commentId;
    }
    
    public void setCommentId(int commentId) {
    	this.commentId = commentId;
    }
    
    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getCfNumberId() {
        return cfNumberId;
    }

    public void setCfNumberId(String cfNumberId) {
        this.cfNumberId = cfNumberId;
    }

    public String getUserId() {
        return writeuserid;
    }

    public void setUserId(String writeuserid) {
        this.writeuserid = writeuserid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCfName() {
        return cfName;
    }

    public void setCfName(String cfName) {
        this.cfName = cfName;
    }

    public String getCfNumber() {
        return cfNumber;
    }

    public void setCfNumber(String cfNumber) {
        this.cfNumber = cfNumber;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "reviewId=" + reviewId +
                ", cfNumberId='" + cfNumberId + '\'' +
                ", userId=" + writeuserid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                ", createdTime='" + createdTime + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", cfName='" + cfName + '\'' +
                ", cfNumber='" + cfNumber + '\'' +
                '}';
    }

    // 추가된 코드
    // 여기에 추가적인 메서드 또는 필드를 작성할 수 있습니다.
    // 이 클래스의 기능을 확장하거나 변경하고 싶은 경우 여기에 코드를 추가하세요.
}
