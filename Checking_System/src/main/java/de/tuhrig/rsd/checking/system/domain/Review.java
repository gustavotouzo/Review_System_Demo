package de.tuhrig.rsd.checking.system.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE) // Jackson mapper default!
public class Review {
    private ReviewId reviewId;
    private String subject;
    private String content;
    private String rejectionReason;
    private boolean approved;

    public Review(ReviewId reviewId, String subject, String content) {
        this.reviewId = reviewId;
        this.subject = subject;
        this.content = content;
    }

    public void check() {
        if(subject.length() < 5) {
            reject("Subject is too short");
        } else if(subject.length() > 100) {
            reject("Subject is too long");
        } else if(content.length() < 100) {
            reject("Content is too short");
        } else if(content.length() > 500) {
            reject("Content is too long");
            log.debug("Content is too long. [reviewId={}, length={}, allowed={}]", reviewId, content.length(), 500);
        } else {
            approve();
        }
    }

    private void reject(String reason) {
        this.approved = false;
        this.rejectionReason = reason;
        log.info("Review rejected. [reviewId={}, reason={}]", reviewId, reason);
    }

    private void approve() {
        this.approved = true;
        log.info("Review approved. [reviewId={}]", reviewId);
    }
}
